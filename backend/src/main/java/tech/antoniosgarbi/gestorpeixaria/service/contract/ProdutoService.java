package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProdutoDTO;

public interface ProdutoService {

    Long cadastrar(ProdutoDTO cadastroBody);

    ProdutoDTO atualizarCadastro(ProdutoDTO cadastroBody);

    ProdutoDTO encontrarCadastro(Long id);

    void apagarCadastro(long id);

    Page<ProdutoDTO> encontrarTodos(Pageable pageable);
}
