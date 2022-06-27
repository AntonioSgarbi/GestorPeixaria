package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends Pessoa {

    @OneToMany
    private Set<Venda> vendas;
    @OneToOne
    private User user;

    private Double salario;

    public Funcionario(FuncionarioDTO dto) {
        super(
                dto.getId(),
                dto.getDocumento(),
                dto.getNome(),
                dto.getPessoaTipo(),
                dto.getTelefones(),
                dto.getEmail(),
                false
        );
    }
}