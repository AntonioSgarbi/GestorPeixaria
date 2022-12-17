package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.AvailableLotResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;
import tech.antoniosgarbi.gestorpeixaria.repository.ExpirationLotRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.StockService;
import tech.antoniosgarbi.gestorpeixaria.specification.ExpirationLotSpecification;

@Service
public class StockServiceImpl implements StockService {
    private final ExpirationLotRepository expirationLotRepository;

    public StockServiceImpl(ExpirationLotRepository expirationLotRepository) {
        this.expirationLotRepository = expirationLotRepository;
    }

    @Override
    public Long productEntry(ProductEntryRequest request) {
        ExpirationLot expirationLot = new ExpirationLot(request);

        expirationLot = expirationLotRepository.save(expirationLot);
        return expirationLot.getId();
    }

    @Override
    public Page<StockEntries> query(ExpirationLotSpecBody body, Pageable pageable) {
        Specification<ExpirationLot> specification = new ExpirationLotSpecification(body);
        return expirationLotRepository.findAll(specification, pageable).map(StockEntries::new);
    }

    @Override
    public Page<AvailableLotResponse> findAllAvailableLotsByProductId(Long id, Pageable pageable) {
        Page<ExpirationLot> p = this.expirationLotRepository
                .findAllByProductIdAndAvailableQuantityGreaterThan(id, 0.0, pageable);
        System.out.println(p.getContent());
        return p.map(AvailableLotResponse::new);
    }
}
