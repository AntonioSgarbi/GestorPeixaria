package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.antoniosgarbi.gestorpeixaria.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>, JpaSpecificationExecutor<Venda> {
}
