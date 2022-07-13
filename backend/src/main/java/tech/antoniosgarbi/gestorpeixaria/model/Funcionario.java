package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Pessoa {

    @OneToMany
    private Set<Venda> vendas;

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