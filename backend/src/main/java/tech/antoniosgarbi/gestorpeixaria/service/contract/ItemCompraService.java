package tech.antoniosgarbi.gestorpeixaria.service.contract;

import tech.antoniosgarbi.gestorpeixaria.dto.model.ItemCompraDTO;

import java.util.List;

public interface ItemCompraService {

    List<ItemCompraDTO> saveAll(List<ItemCompraDTO> entrada);

}
