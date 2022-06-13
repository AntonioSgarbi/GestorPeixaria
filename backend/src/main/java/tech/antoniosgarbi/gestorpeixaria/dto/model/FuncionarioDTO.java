package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Funcionario;

import java.util.Set;

@Data
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO {

    private Set<VendaDTO> vendas;

    public FuncionarioDTO(Funcionario modelo) {
        super(
                modelo.getId(),
                modelo.getNome(),
                modelo.getDocumento(),
                modelo.getPessoaTipo(),
                modelo.getTelefones(),
                modelo.getEmail()
        );
    }
}
