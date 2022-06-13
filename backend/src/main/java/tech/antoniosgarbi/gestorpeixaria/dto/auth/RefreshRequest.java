package tech.antoniosgarbi.gestorpeixaria.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshRequest {
    private String refreshToken;
}
