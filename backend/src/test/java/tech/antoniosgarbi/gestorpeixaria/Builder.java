package tech.antoniosgarbi.gestorpeixaria;

import tech.antoniosgarbi.gestorpeixaria.dto.ClienteDTO;
import tech.antoniosgarbi.gestorpeixaria.model.Cliente;

public interface Builder {

    static Cliente cliente1() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        return cliente;
    }

    static ClienteDTO clienteDTO1() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setDocumento("documento");
        return dto;
    }


}
