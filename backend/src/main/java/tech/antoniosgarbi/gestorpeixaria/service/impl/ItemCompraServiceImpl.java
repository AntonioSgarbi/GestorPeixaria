package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ItemCompraDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.ItemCompraRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ItemCompraService;

import java.util.List;

@Service
public class ItemCompraServiceImpl implements ItemCompraService {
    private final ItemCompraRepository itemCompraRepository;

    public ItemCompraServiceImpl(ItemCompraRepository itemCompraRepository) {
        this.itemCompraRepository = itemCompraRepository;
    }

    @Override
    public List<ItemCompraDTO> saveAll(List<ItemCompraDTO> entrada) {
        return null;
    }
}
