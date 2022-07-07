package tech.antoniosgarbi.gestorpeixaria.service.contract;

import tech.antoniosgarbi.gestorpeixaria.configuration.UserDetailsImpl;

public interface TokenService {
    String generateAccessToken(UserDetailsImpl userPrincipal);

    String generateAccessToken(String username);

    String generateTokenFromUsername(String username);

    String generateRefreshTokenFromUsername(String username);

    String getUserNameFromJwtToken(String token);

    boolean validateJwtToken(String authToken);

}