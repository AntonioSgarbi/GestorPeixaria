package tech.antoniosgarbi.gestorpeixaria.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String jwt;
    private String token;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
