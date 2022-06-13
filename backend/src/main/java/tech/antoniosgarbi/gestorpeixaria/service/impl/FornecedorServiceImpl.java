package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FornecedorDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PessoaException;
import tech.antoniosgarbi.gestorpeixaria.model.Fornecedor;
import tech.antoniosgarbi.gestorpeixaria.repository.FornecedorRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.FornecedorService;

import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorServiceImpl(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public long cadastrar(FornecedorDTO fornecedorDTO) {
        Optional<Fornecedor> optional = this.fornecedorRepository.findByDocumento(fornecedorDTO.getDocumento());
        if (optional.isPresent()) throw new PessoaException("O documento informado já possui cadastro no sistema!");
        Fornecedor modelo = new Fornecedor(fornecedorDTO);
        modelo = this.fornecedorRepository.save(modelo);
        return modelo.getId();
    }

    @Override
    public FornecedorDTO atualizarCadastro(FornecedorDTO fornecedorDTO) {
        Fornecedor modelo = this.encontrarModelo(fornecedorDTO.getId());
        Util.myCopyProperties(fornecedorDTO, modelo);
        this.fornecedorRepository.save(modelo);
        return fornecedorDTO;
    }

    @Override
    public FornecedorDTO encontrarCadastro(long id) {
        return new FornecedorDTO(this.encontrarModelo(id));
    }

    @Override
    public Page<FornecedorDTO> encontrarTodos(Pageable pageable) {
        return this.fornecedorRepository.findAll(pageable).map(FornecedorDTO::new);
    }

    @Override
    public void apagarCadastro(long id) {
        FornecedorDTO dto = this.encontrarCadastro(id);
        Fornecedor modelo = new Fornecedor(dto);
        modelo.setExcluido(true);
        this.fornecedorRepository.save(modelo);
    }

    private Fornecedor encontrarModelo(long id) {
        Optional<Fornecedor> optional = this.fornecedorRepository.findById(id);
        if (optional.isEmpty()) throw new PessoaException("Cadastro não encontrado");
        return optional.get();
    }

}