package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.antoniosgarbi.gestorpeixaria.model.Fornecedor;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, JpaSpecificationExecutor<Fornecedor> {
    Optional<Fornecedor> findByDocumento(String documento);
}
