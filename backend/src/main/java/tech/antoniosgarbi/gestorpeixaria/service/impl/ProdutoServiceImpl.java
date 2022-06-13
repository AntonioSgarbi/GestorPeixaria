package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProdutoDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.ProdutoException;
import tech.antoniosgarbi.gestorpeixaria.model.Produto;
import tech.antoniosgarbi.gestorpeixaria.repository.ProdutoRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ProdutoService;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Long cadastrar(ProdutoDTO produtoDTO) {
        Produto modelo = new Produto(produtoDTO);
        modelo = this.produtoRepository.save(modelo);
        return modelo.getId();
    }

    @Override
    public ProdutoDTO atualizarCadastro(ProdutoDTO cadastroBody) {
        Produto modelo = this.encontrarModelo(cadastroBody.getId());
        Util.myCopyProperties(cadastroBody, modelo);
        this.produtoRepository.save(modelo);
        return cadastroBody;
    }

    @Override
    public ProdutoDTO encontrarCadastro(Long id) {
        return new ProdutoDTO(this.encontrarModelo(id));
    }

    @Override
    public Page<ProdutoDTO> encontrarTodos(Pageable pageable) {
        return this.produtoRepository.findAll(pageable).map(ProdutoDTO::new);
    }

    @Override
    public void apagarCadastro(long id) {
        ProdutoDTO dto = this.encontrarCadastro(id);
        Produto modelo = new Produto(dto);
        modelo.setExcluido(true);
        this.produtoRepository.save(modelo);
    }

    public Produto encontrarModelo(long id) {
        Optional<Produto> optional = this.produtoRepository.findById(id);
        if (optional.isEmpty()) throw new ProdutoException("Cadastro não encontrado");
        return optional.get();
    }

}