package tech.antoniosgarbi.gestorpeixaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.model.Cliente;

@Data
@AllArgsConstructor
public class ClienteDTO extends PessoaDTO {

    public ClienteDTO(Cliente modelo) {
        super(
                modelo.getId(),
                modelo.getNome(),
                modelo.getDocumento(),
                modelo.getPessoaTipo(),
                modelo.getTelefones(),
                modelo.getEmails()
        );
    }
}
