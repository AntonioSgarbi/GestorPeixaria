package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

public interface ExpirationLotRepository extends JpaRepository<ExpirationLot, Long> {

}
