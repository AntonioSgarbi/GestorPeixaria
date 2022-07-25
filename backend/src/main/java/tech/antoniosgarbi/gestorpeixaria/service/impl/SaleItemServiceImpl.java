package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleItemDTO;
import tech.antoniosgarbi.gestorpeixaria.model.SaleItem;
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
        List<SaleItem> model = itemList.stream().map(SaleItem::new).toList();
        List<SaleItem> saved = saleItemRepository.saveAll(model);
        saved.forEach(item -> itemList.stream()
                .filter(itemDTO ->
                        itemDTO.getProduct().getName().equals(item.getProduct().getName()) &&
                        itemDTO.getQuantity().equals(item.getQuantity())
                )
                .findFirst()
                .ifPresent(itemDTO -> itemDTO.setId(item.getId())));

        return itemList;
    }
}
