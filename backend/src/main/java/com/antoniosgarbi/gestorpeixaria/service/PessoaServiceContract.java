package com.antoniosgarbi.gestorpeixaria.service;

import com.antoniosgarbi.gestorpeixaria.dto.PessoaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaServiceContract {
    long cadastrar(PessoaDTO pessoa);
    void atualizarCadastro(PessoaDTO pessoa);
    PessoaDTO encontrarCadastro(long id);
    Page<PessoaDTO> encontrarTodos(Pageable pageable);
    void apagarCadastro(long id);


}
