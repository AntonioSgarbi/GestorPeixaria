package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ClienteDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SpecBodyCliente;

public interface ClienteService {
    long cadastrar(ClienteDTO cliente);
    ClienteDTO atualizarCadastro(ClienteDTO cliente);
    ClienteDTO encontrarCadastro(long id);
    Page<ClienteDTO> encontrarTodos(Pageable pageable);
    Page<ClienteDTO> encontrarTodos(SpecBodyCliente specBody, Pageable pageable);
    void apagarCadastro(long id);


}
