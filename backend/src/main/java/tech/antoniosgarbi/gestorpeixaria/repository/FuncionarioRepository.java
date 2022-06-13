package tech.antoniosgarbi.gestorpeixaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.antoniosgarbi.gestorpeixaria.model.Funcionario;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {
    Optional<Funcionario> findByDocumento(String documento);
}
