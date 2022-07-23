package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.antoniosgarbi.gestorpeixaria.model.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

}
