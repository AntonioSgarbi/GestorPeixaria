package tech.antoniosgarbi.gestorpeixaria.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import tech.antoniosgarbi.gestorpeixaria.service.contract.TokenService;
import tech.antoniosgarbi.gestorpeixaria.service.impl.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Profile("test")
@Primary
class AuthTokenFilterTest extends AuthTokenFilter {
    @Mock
    private TokenService tokenService;
    @Mock
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private Logger loggerInstance;
    @Mock
    private MockFilterChain filterChain;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && tokenService.validateJwtToken(jwt)) {
                String username = tokenService.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                loggerInstance.error("JWT Token is null or invalid: " + jwt);
            }
        } catch (UsernameNotFoundException e) {
            loggerInstance.error("Cannot set user authentication: {e}", e);
        }
        filterChain.doFilter(request, response);
    }

    @Test
    @DisplayName("Deve retornar nulo ao receber um request sem token")
    void parseJwt0() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNull(parseJwt(request));
    }

    @Test
    @DisplayName("Deve retornar payload ao receber um request com Authorization header v??lido")
    void parseJwt1() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + "token");

        assertNotNull(parseJwt(request));
        assertEquals("token", parseJwt(request));
    }

    @Test
    @DisplayName("Deve lan??ar ServletException quando o token for nulo")
    void doFilterInternal0() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        ServletException esperado = new ServletException();
        doThrow(esperado)
                .when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        var exception = assertThrows(ServletException.class,
                () ->this.doFilterInternal(request, response, filterChain));

        assertEquals(esperado, exception);
    }

    @Test
    @DisplayName("Deve lan??ar ServletException e logar erro quando o token for inv??lido")
    void doFilterInternal1() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        String mockToken = "mockToken";
        request.addHeader("Authorization", "Bearer "+ mockToken);
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateJwtToken(anyString())).thenReturn(false);

        ServletException esperado = new ServletException();
        doThrow(esperado)
                .when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        var exception = assertThrows(ServletException.class,
                () ->this.doFilterInternal(request, response, filterChain));
        verify(tokenService, times(1)).validateJwtToken(anyString());
        verify(loggerInstance, times(1)).error("JWT Token is null or invalid: " + mockToken);
        assertEquals(esperado, exception);
    }

    @Test
    @DisplayName("Deve lan??ar ServletException e logar erro quando o usu??rio n??o for encontrado")
    void doFilterInternal2() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer mockToken");
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateJwtToken(anyString())).thenReturn(true);

        UsernameNotFoundException userException = new UsernameNotFoundException("User not found");
        when(tokenService.getUserNameFromJwtToken(anyString()))
                .thenThrow(userException);

        ServletException esperado = new ServletException();
        doThrow(esperado)
                .when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        var exception = assertThrows(ServletException.class,
                () ->this.doFilterInternal(request, response, filterChain));

        verify(tokenService, times(1))
                .validateJwtToken(anyString());
        verify(loggerInstance, times(1))
                .error("Cannot set user authentication: {e}", userException);

        assertEquals(esperado, exception);
    }

    @Test
    @DisplayName("Deve lan??ar ServletException e usu??rio n??o tiver as permiss??es necess??rias")
    void doFilterInternal3() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer mockToken");
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateJwtToken(anyString())).thenReturn(true);
        UsernameNotFoundException userException = new UsernameNotFoundException("User not found");
        when(tokenService.getUserNameFromJwtToken(anyString()))
                .thenThrow(userException);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(UserDetailsImpl.builder().build());

        ServletException esperado = new ServletException();
        doThrow(esperado)
                .when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        var exception = assertThrows(ServletException.class,
                () ->this.doFilterInternal(request, response, filterChain));

        assertEquals(esperado, exception);
    }

    @Test
    @DisplayName("Deve ativar m??todos e n??o fazer nada ao receber token v??lido com as permiss??es necess??rias")
    void doFilterInternal4() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer mockToken");
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateJwtToken(anyString())).thenReturn(true);
        when(tokenService.getUserNameFromJwtToken(anyString())).thenReturn("user");
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(UserDetailsImpl.builder().build());
        doNothing().when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        this.doFilterInternal(request, response, filterChain);

        verify(tokenService, times(1))
                .validateJwtToken(anyString());
        verify(userDetailsService, times(1))
                .loadUserByUsername(anyString());
        verify(filterChain, times(1))
                .doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }

}
