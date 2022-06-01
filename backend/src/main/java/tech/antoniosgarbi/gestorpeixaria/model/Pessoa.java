package tech.antoniosgarbi.gestorpeixaria.model;

import tech.antoniosgarbi.gestorpeixaria.dto.PessoaDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String documento;
    protected String nome;
    protected PessoaTipo pessoaTipo;
    @ElementCollection
    protected Set<String> telefones;
    @ElementCollection
    protected Set<String> emails;
    protected Boolean excluido;
    @OneToMany
    protected Set<Venda> compra;

    public Pessoa(PessoaDTO dto) {
        this.id = dto.getId();
        this.documento = dto.getDocumento();
        this.pessoaTipo = dto.getPessoaTipo();
        this.telefones = dto.getTelefones();
        this.emails = dto.getEmails();
        this.excluido = false;
    }

}