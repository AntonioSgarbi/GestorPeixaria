package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FornecedorDTO;

public interface FornecedorService {
    long cadastrar(FornecedorDTO fornecedor);
    FornecedorDTO atualizarCadastro(FornecedorDTO fornecedor);
    FornecedorDTO encontrarCadastro(long id);
    Page<FornecedorDTO> encontrarTodos(Pageable pageable);
    void apagarCadastro(long id);


}
