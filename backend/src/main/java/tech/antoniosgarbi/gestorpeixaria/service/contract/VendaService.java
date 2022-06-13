package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;

public interface VendaService {
    Long registrarVenda(VendaDTO vendaDTO);

    Page<VendaDTO> encontrarTodos(Pageable pageable);

    VendaDTO encontrarCadastro(Long id);

    void apagarCadastro(Long id);
}
