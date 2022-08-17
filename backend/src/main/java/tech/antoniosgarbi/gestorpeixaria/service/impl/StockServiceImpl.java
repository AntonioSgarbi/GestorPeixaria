package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;
import tech.antoniosgarbi.gestorpeixaria.repository.ExpirationLotRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.StockService;

@Service
public class StockServiceImpl implements StockService {
    private final ExpirationLotRepository expirationLotRepository;

    public StockServiceImpl(ExpirationLotRepository expirationLotRepository) {
        this.expirationLotRepository = expirationLotRepository;
    }

    @Override
    public Long productEntry(ProductEntryRequest request) {
        ExpirationLot expirationLot = new ExpirationLot();
        Util.myCopyProperties(request, expirationLot);

        expirationLot = expirationLotRepository.save(expirationLot);
        return expirationLot.getId();
    }
}
