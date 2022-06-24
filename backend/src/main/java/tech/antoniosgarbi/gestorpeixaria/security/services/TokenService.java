package tech.antoniosgarbi.gestorpeixaria.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshResponse;
import tech.antoniosgarbi.gestorpeixaria.exception.TokenRefreshException;
import tech.antoniosgarbi.gestorpeixaria.repository.UserRepository;
import tech.antoniosgarbi.gestorpeixaria.security.jwt.JwtUtils;

import java.util.List;

@Service
public class TokenService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public TokenService(UserRepository userRepository, JwtUtils jwtUtils,
                        AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(userDetails);
        String refreshToken = this.jwtUtils.generateRefreshTokenFromUsername(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    public RefreshResponse refreshTheToken(String requestRefreshToken) {
        try{
            this.jwtUtils.validateJwtToken(requestRefreshToken);
            String username = this.jwtUtils.getUserNameFromJwtToken(requestRefreshToken);
            String newAccessToken = this.jwtUtils.generateAccessToken(username);
            String newRefreshToken = this.jwtUtils.generateRefreshTokenFromUsername(username);
            return RefreshResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        } catch (Exception e) {
            throw new TokenRefreshException(e.getMessage());
        }
    }

}
