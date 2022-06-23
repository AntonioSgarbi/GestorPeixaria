package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Pessoa;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PessoaDTO {
    protected Long id;
    protected String nome;
    protected String documento;
    protected PessoaTipo pessoaTipo;
    protected Set<String> telefones;
    protected String email;

    protected PessoaDTO(Pessoa modelo) {
        this.id = modelo.getId();
        this.documento = modelo.getDocumento();
        this.pessoaTipo = modelo.getPessoaTipo();
        this.telefones = modelo.getTelefones();
        this.email = modelo.getEmail();
    }

}