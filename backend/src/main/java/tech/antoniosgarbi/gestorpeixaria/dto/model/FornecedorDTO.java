package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.model.Fornecedor;

@Data
@AllArgsConstructor
public class FornecedorDTO extends PessoaDTO {

    public FornecedorDTO(Fornecedor modelo) {
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
