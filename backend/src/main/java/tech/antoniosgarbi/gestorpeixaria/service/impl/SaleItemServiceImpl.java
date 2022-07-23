package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleItemDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleItemRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleItemService;

import java.util.List;

@Service
public class SaleItemServiceImpl implements SaleItemService {
    private final SaleItemRepository saleItemRepository;

    public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    public List<SaleItemDTO> saveAll(List<SaleItemDTO> itemList) {
        return null;
    }
}
