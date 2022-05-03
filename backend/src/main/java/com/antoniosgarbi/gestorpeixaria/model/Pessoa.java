package com.antoniosgarbi.gestorpeixaria.model;

import com.antoniosgarbi.gestorpeixaria.dto.PessoaDTO;
import com.antoniosgarbi.gestorpeixaria.model.enums.CadastroTipo;
import com.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private PessoaTipo pessoaTipo;
    private CadastroTipo cadastroTipo;
    @ElementCollection
    private List<String> telefones;
    private String email;
    private Boolean excluido;

    public Pessoa(PessoaDTO dto) {
        this.id = dto.getId();
        this.documento = dto.getDocumento();
        this.pessoaTipo = dto.getPessoaTipo();
        this.telefones = dto.getTelefones();
        this.email = dto.getEmail();
        this.excluido = false;
    }


}

