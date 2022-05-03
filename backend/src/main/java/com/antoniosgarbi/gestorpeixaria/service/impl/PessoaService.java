package com.antoniosgarbi.gestorpeixaria.service.impl;

import com.antoniosgarbi.gestorpeixaria.dto.PessoaDTO;
import com.antoniosgarbi.gestorpeixaria.exception.PessoaException;
import com.antoniosgarbi.gestorpeixaria.model.Pessoa;
import com.antoniosgarbi.gestorpeixaria.repository.PessoaRepository;
import com.antoniosgarbi.gestorpeixaria.service.PessoaServiceContract;
import com.antoniosgarbi.gestorpeixaria.service.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService implements PessoaServiceContract {
    private final PessoaRepository pessoaRespository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRespository = pessoaRepository;
    }

    @Override
    public long cadastrar(PessoaDTO pessoa) {
        Pessoa modelo = new Pessoa(pessoa);
        modelo = this.pessoaRespository.save(modelo);
        return modelo.getId();
    }

    @Override
    public void atualizarCadastro(PessoaDTO cliente) {
        Pessoa modelo = this.encontrarCadastroPorId(cliente.getId());
        Util.myCopyProperties(cliente, modelo);
        this.pessoaRespository.save(modelo);
    }

    @Override
    public PessoaDTO encontrarCadastro(long id) {
        return  new PessoaDTO(this.encontrarCadastroPorId(id));
    }

    @Override
    public Page<PessoaDTO> encontrarTodos(Pageable pageable) {
        return this.pessoaRespository.findAll(pageable).map(PessoaDTO::new);
    }

    @Override
    public void apagarCadastro(long id) {
        Pessoa  modelo = this.encontrarCadastroPorId(id);
        modelo.setExcluido(true);
        this.pessoaRespository.save(modelo);
    }

    private Pessoa encontrarCadastroPorId(long id) {
        Optional<Pessoa> optional = this.pessoaRespository.findById(id);
        if(optional.isEmpty()) throw new PessoaException("Cadastro não encontrado");
        return  optional.get();
    }

}
