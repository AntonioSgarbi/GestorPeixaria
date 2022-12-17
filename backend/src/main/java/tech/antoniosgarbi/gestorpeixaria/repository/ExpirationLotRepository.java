package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

public interface ExpirationLotRepository extends
        JpaRepository<ExpirationLot, Long>, JpaSpecificationExecutor<ExpirationLot> {

    Page<ExpirationLot> findAllByProductIdAndAvailableQuantityGreaterThan(
            Long productId, Double minAvailableQuantity, Pageable pageable);

}
