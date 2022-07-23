package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SpecBodyFuncionario;
import tech.antoniosgarbi.gestorpeixaria.exception.PessoaException;
import tech.antoniosgarbi.gestorpeixaria.model.Funcionario;
import tech.antoniosgarbi.gestorpeixaria.repository.FuncionarioRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.FuncionarioService;
import tech.antoniosgarbi.gestorpeixaria.specification.FuncionarioSpecification;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public long cadastrar(FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> optional = this.funcionarioRepository.findByDocumento(funcionarioDTO.getDocumento());
        if (optional.isPresent()) throw new PessoaException("O documento informado já possui cadastro no sistema!");
        Funcionario modelo = new Funcionario(funcionarioDTO);
        modelo = this.funcionarioRepository.save(modelo);
        return modelo.getId();
    }

    @Override
    public FuncionarioDTO atualizarCadastro(FuncionarioDTO funcionarioDTO) {
        Funcionario modelo = this.encontrarModelo(funcionarioDTO.getId());
        Util.myCopyProperties(funcionarioDTO, modelo);
        this.funcionarioRepository.save(modelo);
        return funcionarioDTO;
    }

    @Override
    public FuncionarioDTO encontrarCadastro(long id) {
        return new FuncionarioDTO(this.encontrarModelo(id));
    }

    @Override
    public Page<FuncionarioDTO> encontrarTodos(Pageable pageable) {
        return this.funcionarioRepository.findAll(pageable).map(FuncionarioDTO::new);
    }

    @Override
    public Page<FuncionarioDTO> encontrarTodos(SpecBodyFuncionario specBody, Pageable pageable) {
        Specification<Funcionario> specification = new FuncionarioSpecification(specBody);
        return this.funcionarioRepository.findAll(specification, pageable).map(FuncionarioDTO::new);
    }

    @Override
    public void apagarCadastro(long id) {
        FuncionarioDTO dto = this.encontrarCadastro(id);
        Funcionario modelo = new Funcionario(dto);
        modelo.setExcluido(true);
        this.funcionarioRepository.save(modelo);
    }

    public Funcionario encontrarModelo(long id) {
        Optional<Funcionario> optional = this.funcionarioRepository.findById(id);
        if (optional.isEmpty()) throw new PessoaException("Cadastro não encontrado");
        return optional.get();
    }

}