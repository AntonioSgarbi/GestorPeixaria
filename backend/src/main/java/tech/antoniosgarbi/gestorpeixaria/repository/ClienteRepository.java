package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.antoniosgarbi.gestorpeixaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor {
    Optional<Cliente> findClienteByDocumento(String documento);
}
