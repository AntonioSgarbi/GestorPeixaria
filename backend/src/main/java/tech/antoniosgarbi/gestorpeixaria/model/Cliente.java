package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ClienteDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Pessoa {

    @OneToMany
    private Set<Venda> compras;

    public Cliente(ClienteDTO clienteDTO) {
        super(
                clienteDTO.getId(),
                clienteDTO.getDocumento(),
                clienteDTO.getNome(),
                clienteDTO.getPessoaTipo(),
                clienteDTO.getTelefones(),
                clienteDTO.getEmail(),
                false
        );
    }
}