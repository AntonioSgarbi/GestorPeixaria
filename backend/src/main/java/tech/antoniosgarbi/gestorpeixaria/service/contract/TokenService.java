package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateAccessToken(UserDetails userPrincipal);

    String generateAccessToken(String username);

    String generateTokenFromUsername(String username);

    String generateRefreshTokenFromUsername(String username);

    String getUserNameFromJwtToken(String token);

    boolean validateJwtToken(String authToken);

}