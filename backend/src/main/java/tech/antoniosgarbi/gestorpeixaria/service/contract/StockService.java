package tech.antoniosgarbi.gestorpeixaria.service.contract;

import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;

public interface StockService {
    Long productEntry(ProductEntryRequest request);
}
