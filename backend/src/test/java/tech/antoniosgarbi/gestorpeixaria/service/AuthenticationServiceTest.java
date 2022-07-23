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
    @DisplayName("Deve retornar um LoginResponse ao receber um LoginRequest válido")
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
    @DisplayName("Deve lançar BadCredentialsException ao receber um LoginRequest inválido")
    void authenticateUser1() {
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("BadCredentialsException"));

        LoginRequest loginRequest = LoginRequest.builder().username("username").password("password").build();

        var exception =
                assertThrows(BadCredentialsException.class, () -> underTest.authenticateUser(loginRequest));
        assertEquals("BadCredentialsException", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um RefreshResponse ao receber um RefreshToken válido")
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
    @DisplayName("Deve lançar TokenRefreshException ao receber um RefreshToken inválido")
    void refreshTheToken1() {
        when(tokenService.validateJwtToken(anyString())).thenReturn(false);

        var exception =
                assertThrows(RuntimeException.class, () -> underTest.refreshTheToken("refreshToken"));
        //exception encapsulada pelo TokenRefreshException
        assertEquals("Falha de autenticação: Token expirado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um User ao receber um Funcionario válido")
    void criarUsuariodeFuncionario0() {
        String documento = "username";
        String email = "email";
        List<String> roles = List.of("FUNCIONARIO");
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
    @DisplayName("Deve atualizar usuario com a nova senha encriptada ao receber um endereço de email cadastrado")
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
    @DisplayName("Deve encaminhar senha para o email e retornar a senha ao receber um endereço de email cadastrado")
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
    @DisplayName("Deve encaminhar senha para o email e retornar a senha ao receber um endereço de email não cadastrado")
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
    @DisplayName("Deve retornar a senha mesmo sem conseguir enviar o email")
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