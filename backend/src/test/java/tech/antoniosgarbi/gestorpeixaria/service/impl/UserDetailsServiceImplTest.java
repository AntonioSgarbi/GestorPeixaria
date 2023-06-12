package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.model.User;
import tech.antoniosgarbi.gestorpeixaria.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl underTest;

    @Test
    void save() {
        when(this.userRepository.save(any(User.class))).thenReturn(new User());

        this.underTest.save(new User());

        verify(this.userRepository).save(any(User.class));
    }

    @Test
    void findByUsername() {
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));

        this.underTest.findByUsername("username");

        verify(this.userRepository).findByUsername(anyString());
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setRoles(List.of("role1", "role2"));
        user.setEmail("antonio@email.com");
        user.setUsername("username");
        user.setPassword("password");

        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        this.underTest.loadUserByUsername("username");

        verify(this.userRepository).findByUsername(anyString());
    }
}