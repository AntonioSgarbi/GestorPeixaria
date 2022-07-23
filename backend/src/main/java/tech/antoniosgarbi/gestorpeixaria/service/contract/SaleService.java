package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;

public interface SaleService {
    Long register(SaleDTO vendaDTO);

    Page<SaleDTO> findAll(Pageable pageable);

    SaleDTO findById(Long id);

    void delete(Long id);
}
