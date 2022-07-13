package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshResponse;
import tech.antoniosgarbi.gestorpeixaria.service.contract.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final AuthenticationService tokenService;

    public LoginController(AuthenticationService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(tokenService.authenticateUser(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refreshtoken(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(tokenService.refreshTheToken(request.getRefreshToken()));
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> resetPassword(@RequestBody String email) {
        return ResponseEntity.accepted().body(tokenService.resetPassword(email));
    }

}
