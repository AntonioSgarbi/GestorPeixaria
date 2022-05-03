package com.antoniosgarbi.gestorpeixaria.repository;

import com.antoniosgarbi.gestorpeixaria.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
