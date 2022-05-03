package com.antoniosgarbi.gestorpeixaria.dto;

import com.antoniosgarbi.gestorpeixaria.model.Pessoa;
import com.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private Long id;
    private String documento;
    private PessoaTipo pessoaTipo;
    private List<String> telefones;
    private String email;

    public PessoaDTO(Pessoa modelo) {
        this.id = modelo.getId();
        this.documento = modelo.getDocumento();
        this.pessoaTipo = modelo.getPessoaTipo();
        this.telefones = modelo.getTelefones();
        this.email = modelo.getEmail();
    }

}

