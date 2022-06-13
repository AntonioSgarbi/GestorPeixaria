package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;

public interface FuncionarioService {
    long cadastrar(FuncionarioDTO funcionario);
    FuncionarioDTO atualizarCadastro(FuncionarioDTO funcionario);
    FuncionarioDTO encontrarCadastro(long id);
    Page<FuncionarioDTO> encontrarTodos(Pageable pageable);
    void apagarCadastro(long id);


}
