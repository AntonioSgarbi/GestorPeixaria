package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.PessoaDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {
    @Id
    @Column(name = "id", nullable = false)
    protected Long id;
    protected String documento;
    protected String nome;
    protected PessoaTipo pessoaTipo;
    @ElementCollection
    protected Set<String> telefones;
    protected String email;
    protected Boolean excluido;

    protected Pessoa(PessoaDTO dto) {
        this.id = dto.getId();
        this.documento = dto.getDocumento();
        this.pessoaTipo = dto.getPessoaTipo();
        this.telefones = dto.getTelefones();
        this.email = dto.getEmail();
        this.excluido = false;
    }

}