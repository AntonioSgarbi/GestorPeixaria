package tech.antoniosgarbi.gestorpeixaria.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshResponse {
    private String token;
    private String requestRefreshToken;

    public RefreshResponse(String token, String requestRefreshToken) {
        this.token = token;
        this.requestRefreshToken = requestRefreshToken;
    }
}
