package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_auth")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @ElementCollection
    private List<String> roles;

    @Column(columnDefinition = "text")
    private String password;

    @OneToOne//(optional = false) comment only for tests
    private Person pessoa;

}

