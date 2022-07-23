package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.VendaRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleItemService;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleService;

@Service
public class VendaServiceImpl implements SaleService {
    private final VendaRepository vendaRepository;
    private SaleItemService itemCompraService;

    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Long register(SaleDTO vendaDTO) {
        return null;
    }

    @Override
    public Page<SaleDTO> findAll(Pageable pageable) {
        return vendaRepository.findAll(pageable).map(SaleDTO::new);
    }

    @Override
    public SaleDTO findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        vendaRepository.deleteById(id);
    }
}
