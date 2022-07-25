package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.configuration.UserDetailsImpl;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshResponse;
import tech.antoniosgarbi.gestorpeixaria.exception.TokenRefreshException;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;
import tech.antoniosgarbi.gestorpeixaria.model.User;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.AuthenticationService;
import tech.antoniosgarbi.gestorpeixaria.service.contract.MailServiceStrategy;
import tech.antoniosgarbi.gestorpeixaria.service.contract.TokenService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final MailServiceStrategy mailService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(TokenService jwtUtils, AuthenticationManager authenticationManager,
                                     UserDetailsServiceImpl userDetailsService, MailServiceStrategy mailService,
                                     PasswordEncoder passwordEncoder) {
        this.tokenService = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String accessToken = tokenService.generateAccessToken(userDetails);
        String refreshToken = this.tokenService.generateRefreshTokenFromUsername(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(userDetails.getUsername())
                .roles(roles)
                .build();
    }

    @Override
    public RefreshResponse refreshTheToken(String requestRefreshToken) {
        try {
            if(this.tokenService.validateJwtToken(requestRefreshToken)) {
                String username = this.tokenService.getUserNameFromJwtToken(requestRefreshToken);
                String newAccessToken = this.tokenService.generateAccessToken(username);
                String newRefreshToken = this.tokenService.generateRefreshTokenFromUsername(username);
                return RefreshResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .build();
            } else {
                throw new BadCredentialsException("Token expirado");
            }
        } catch (Exception e) {
            throw new TokenRefreshException(e.getMessage());
        }
    }

    @Override
    public User criarUsuariodeFuncionario(Collaborator collaborator) {
        Argon2PasswordEncoder argon = new Argon2PasswordEncoder();
        String encripted = argon.encode(LocalDateTime.now().toString());

        return User.builder()
                .email(collaborator.getEmail())
                .username(collaborator.getDocument())
                .password(encripted)
                .roles(List.of("COLLABORATOR"))
                .pessoa(collaborator)
                .build();
    }

    //public test version
    @Override
    public String resetPassword(String email) {
        String generated = Integer.toString(Util.getRandomNumberInRange(100000, 999999));
        String encripted = passwordEncoder.encode(generated);

        try {
            User user = userDetailsService.findByUsername(email);
            user.setPassword(encripted);
            userDetailsService.save(user);
            mailService.sendText(email, "Gestor Peixaria - Recuperação de senha",
                    "Sua senha foi alterada para: " + generated);
        } catch (UsernameNotFoundException e) {
            User user = User.builder()
                    .email(email)
                    .username(email)
                    .password(encripted)
                    .roles(List.of("COLLABORADOR", "MANAGER"))
                    .build();
            userDetailsService.save(user);
            try {
                mailService.sendText(email, "Gestor Peixaria - Credenciais",
                        "Suas credenciais de acesso são:  \n\n" +
                                "Login: " + email + "\nSenha: " + generated);
            } catch (Exception mailException) {
                System.out.println("\nmail exception; " + mailException.getMessage());
            }
        }
        return generated;
    }
}