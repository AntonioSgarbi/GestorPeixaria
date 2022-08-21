package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;

public interface StockService {
    Long productEntry(ProductEntryRequest request, Long userId);

    Page<StockEntries> query(ExpirationLotSpecBody body, Pageable pageable);
}
