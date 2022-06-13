package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Cliente;

@Data
@NoArgsConstructor
public class ClienteDTO extends PessoaDTO {

    public ClienteDTO(Cliente modelo) {
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
