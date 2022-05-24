package tech.antoniosgarbi.gestorpeixaria.repository;

import tech.antoniosgarbi.gestorpeixaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findClienteByDocumento(String documento);
}
