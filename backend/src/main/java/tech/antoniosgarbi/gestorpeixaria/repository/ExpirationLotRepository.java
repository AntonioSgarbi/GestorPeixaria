package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

public interface ExpirationLotRepository extends
        JpaRepository<ExpirationLot, Long>, JpaSpecificationExecutor<ExpirationLot> {
}
