package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.antoniosgarbi.gestorpeixaria.configuration.UserDetailsImpl;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshResponse;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;
import tech.antoniosgarbi.gestorpeixaria.model.User;
import tech.antoniosgarbi.gestorpeixaria.service.contract.MailServiceStrategy;
import tech.antoniosgarbi.gestorpeixaria.service.contract.TokenService;
import tech.antoniosgarbi.gestorpeixaria.service.impl.AuthenticationServiceImpl;
import tech.antoniosgarbi.gestorpeixaria.service.impl.UserDetailsServiceImpl;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthenticationServiceTest {
    @Mock
    private TokenService tokenService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private MailServiceStrategy mailService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private static MockedStatic<Util> util;

    @InjectMocks
    private AuthenticationServiceImpl underTest;

    @BeforeAll
    static void setUp() {
        util = mockStatic(Util.class);
    }

    @AfterAll
    static void tearDown() {
        util.close();
    }

    @Test
    @DisplayName("Should return a LoginResponse when receiving a valid LoginRequest")
    void authenticateUser0() {
        String username = "username";
        String password = "password";

        UserDetails userDetails = UserDetailsImpl.build(
                User.builder()
                        .username(username)
                        .password(password)
                        .roles(List.of("FUNCIONARIO")).build());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, password, Stream.of("FUNCIONARIO").map(SimpleGrantedAuthority::new).toList());

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(authentication);

        String accessToken = "accessToken";
        when(tokenService.generateAccessToken(any(UserDetails.class))).thenReturn(accessToken);
        String refreshToken = "refreshToken";
        when(tokenService.generateRefreshTokenFromUsername(anyString())).thenReturn(refreshToken);

        LoginRequest loginRequest = LoginRequest.builder().username(username).password(password).build();

        LoginResponse loginResponse = underTest.authenticateUser(loginRequest);

        assertEquals(accessToken, loginResponse.getAccessToken());
        assertEquals(refreshToken, loginResponse.getRefreshToken());
    }

    @Test
    @DisplayName("Throws BadCredentialsException when receiving an invalid LoginRequest")
    void authenticateUser1() {
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("BadCredentialsException"));

        LoginRequest loginRequest = LoginRequest.builder().username("username").password("password").build();

        var exception =
                assertThrows(BadCredentialsException.class, () -> underTest.authenticateUser(loginRequest));
        assertEquals("BadCredentialsException", exception.getMessage());
    }

    @Test
    @DisplayName("Should return a RefreshResponse when receiving a valid RefreshToken")
    void refreshTheToken0() {
        String username = "username";
        String refreshToken = "refreshToken";
        String accessToken = "accessToken";
        String newRefreshToken = "newRefreshToken";

        when(tokenService.validateJwtToken(anyString())).thenReturn(true);
        when(tokenService.getUserNameFromJwtToken(anyString())).thenReturn(username);
        when(tokenService.generateAccessToken(anyString())).thenReturn(accessToken);
        when(tokenService.generateRefreshTokenFromUsername(anyString())).thenReturn(newRefreshToken);

        RefreshResponse refreshResponse = underTest.refreshTheToken(refreshToken);

        assertEquals(accessToken, refreshResponse.getAccessToken());
        assertEquals(newRefreshToken, refreshResponse.getRefreshToken());
    }

    @Test
    @DisplayName("Throws TokenRefreshException when receiving an invalid RefreshToken")
    void refreshTheToken1() {
        when(tokenService.validateJwtToken(anyString())).thenReturn(false);

        var exception =
                assertThrows(RuntimeException.class, () -> underTest.refreshTheToken("refreshToken"));
        //exception encapsulada pelo TokenRefreshException
        assertEquals("Falha de autenticação: Token expirado", exception.getMessage());
    }

    @Test
    @DisplayName("Should return a User when receiving a valid Employee")
    void criarUsuariodeFuncionario0() {
        String documento = "username";
        String email = "email";
        List<String> roles = List.of("COLLABORATOR");
        Collaborator funcionario = new Collaborator();
        funcionario.setDocument(documento);
        funcionario.setEmail(email);
        User resposta = underTest.criarUsuariodeFuncionario(funcionario);

        assertEquals(documento, resposta.getUsername());
        assertEquals(email, resposta.getEmail());
        assertEquals(roles, resposta.getRoles());
        assertEquals(funcionario, resposta.getPessoa());
    }

    @Test
    @DisplayName("Should update the user with the new encrypted password when receiving a registered email address")
    void resetPassword0() {
        int senhaGerada = 222222;
        util.when(() -> Util.getRandomNumberInRange(100000, 999999)).thenReturn(senhaGerada);


        when(userDetailsService.findByUsername(anyString())).thenReturn(
                User.builder()
                        .username("username")
                        .password("password")
                        .roles(List.of("FUNCIONARIO")).build());

        String email = "email";
        String resposta = underTest.resetPassword(email);

        assertEquals(Integer.toString(senhaGerada), resposta);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userDetailsService, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should forward password to email and return the password when receiving a registered email address")
    void resetPassword1() {
        int senhaGerada = 222222;
        util.when(() -> Util.getRandomNumberInRange(100000, 999999)).thenReturn(senhaGerada);

        User userMocked = User.builder()
                            .username("username")
                            .password("password")
                            .roles(List.of("FUNCIONARIO")).build();
        when(userDetailsService.findByUsername(anyString())).thenReturn(userMocked);

        String email = "email";
        String resposta = underTest.resetPassword(email);

        assertEquals(Integer.toString(senhaGerada), resposta);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(mailService, times(1)).sendText(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should forward password to email and return the password when receiving a unregistered email address")
    void resetPassword2() {
        int senhaGerada = 222222;
        util.when(() -> Util.getRandomNumberInRange(100000, 999999)).thenReturn(senhaGerada);

        when(userDetailsService.findByUsername(anyString()))
                .thenThrow(new UsernameNotFoundException("Usuário não encontrado: "));

        String email = "email";
        String resposta = underTest.resetPassword(email);

        assertEquals(Integer.toString(senhaGerada), resposta);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(mailService, times(1)).sendText(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should return the password even without being able to send the email")
    void resetPassword3() {
        int senhaGerada = 222222;
        util.when(() -> Util.getRandomNumberInRange(100000, 999999)).thenReturn(senhaGerada);

        when(userDetailsService.findByUsername(anyString()))
                .thenThrow(new UsernameNotFoundException("Usuário não encontrado: "));

        when(userDetailsService.save(any(User.class))).thenReturn(
                User.builder()
                        .username("username")
                        .password("password")
                        .roles(List.of("FUNCIONARIO")).build());

        doThrow(new RuntimeException("Exception")).when(mailService)
                .sendText(anyString(), anyString(), anyString());

        String email = "email";
        String resposta = underTest.resetPassword(email);

        verify(passwordEncoder, times(1)).encode(anyString());
        verify(mailService, times(1)).sendText(anyString(), anyString(), anyString());
        assertEquals(Integer.toString(senhaGerada), resposta);
    }

}