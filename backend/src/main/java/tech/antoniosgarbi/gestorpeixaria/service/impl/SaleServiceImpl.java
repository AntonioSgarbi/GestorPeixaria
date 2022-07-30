package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleItemService;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private SaleItemService saleItemService;

    public SaleServiceImpl(SaleRepository vendaRepository) {
        this.saleRepository = vendaRepository;
    }

    @Override
    public Long register(SaleDTO vendaDTO) {
        return null;
    }

    @Override
    public Page<SaleDTO> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable).map(SaleDTO::new);
    }

    @Override
    public SaleDTO findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        saleRepository.deleteById(id);
    }
}
