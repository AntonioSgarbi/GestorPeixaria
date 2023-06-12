package tech.antoniosgarbi.gestorpeixaria.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshResponse;
import tech.antoniosgarbi.gestorpeixaria.service.contract.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoginControllerTest {
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private LoginController underTest;

    @Test
    @DisplayName("Should return a LoginResponse when receives valid LoginRequest")
    void authenticateUser0() {
        LoginResponse expected = LoginResponse.builder().username("username").accessToken("accessToken").refreshToken("refreshToken").build();
        when(authenticationService.authenticateUser(any(LoginRequest.class))).thenReturn(expected);
        ResponseEntity<LoginResponse> response = underTest.authenticateUser(new LoginRequest());
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return RefreshResponse 200 when receives a RefreshRequest")
    void refreshToken0() {
        RefreshResponse expected = RefreshResponse.builder().accessToken("accessToken").refreshToken("refreshToken").build();
        when(authenticationService.refreshTheToken(any(String.class))).thenReturn(expected);
        ResponseEntity<RefreshResponse> response = underTest.refreshToken(new RefreshRequest("refreshToken"));
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void resetPassword() {
        String expectedBody = "expected";
        when(this.authenticationService.resetPassword(anyString())).thenReturn(expectedBody);

        ResponseEntity<String> result = this.underTest.resetPassword("any");

        assertEquals(expectedBody, result.getBody());
        assertEquals(202, result.getStatusCode().value());
    }
}