package tech.antoniosgarbi.gestorpeixaria.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.service.contract.AuthenticationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoginControllerTest {
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private LoginController underTest;

    @Test
    void authenticateUser0() {
        LoginResponse expected = LoginResponse.builder().username("username").accessToken("accessToken").refreshToken("refreshToken").build();
        when(authenticationService.authenticateUser(any(LoginRequest.class))).thenReturn(expected);
        ResponseEntity<LoginResponse> response = underTest.authenticateUser(new LoginRequest());
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void refreshtoken() {
    }

    @Test
    void resetPassword() {
    }
}