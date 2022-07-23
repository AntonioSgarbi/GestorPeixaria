package tech.antoniosgarbi.gestorpeixaria.service.contract;

import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleItemDTO;

import java.util.List;

public interface SaleItemService {

    List<SaleItemDTO> saveAll(List<SaleItemDTO> entrada);

}
