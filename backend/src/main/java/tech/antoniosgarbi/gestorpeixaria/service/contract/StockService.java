package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.AvailableLotResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;

public interface StockService {
    Long productEntry(ProductEntryRequest request);

    Page<StockEntries> query(ExpirationLotSpecBody body, Pageable pageable);

    Page<AvailableLotResponse> findAllAvailableLotsByProductId(Long id, Pageable pageable);
}
