package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.ClienteDTO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Pessoa {

    public Cliente(ClienteDTO clienteDTO) {
        super(
                clienteDTO.getId(),
                clienteDTO.getDocumento(),
                clienteDTO.getNome(),
                clienteDTO.getPessoaTipo(),

                clienteDTO.getTelefones(),
                clienteDTO.getEmails(),
                false,
                null
        );
    }
}