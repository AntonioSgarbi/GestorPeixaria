package tech.antoniosgarbi.gestorpeixaria;

import tech.antoniosgarbi.gestorpeixaria.dto.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.Cliente;
import tech.antoniosgarbi.gestorpeixaria.model.Fornecedor;
import tech.antoniosgarbi.gestorpeixaria.model.Funcionario;
import tech.antoniosgarbi.gestorpeixaria.model.Produto;

public interface Builder {

    static Cliente cliente1() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        return cliente;
    }

    static ClienteDTO clienteDTO1() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setDocumento("documento");
        return dto;
    }

    static Fornecedor fornecedor1() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        fornecedor.setDocumento("documento");
        return fornecedor;
    }

    static FornecedorDTO fornecedorDTO1() {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(1L);
        dto.setDocumento("documento");
        return dto;
    }

    static Funcionario funcionario1() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setDocumento("documento");
        return funcionario;
    }

    static FuncionarioDTO funcionarioDTO1() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(1L);
        dto.setDocumento("documento");
        return dto;
    }

    static Produto produto1() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("produto");
        return produto;
    }

    static ProdutoDTO produtoDTO1() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(1L);
        dto.setNome("produto");
        return dto;
    }

    static VendaDTO vendaDTO1() {
        VendaDTO dto = new VendaDTO();
        dto.setCliente(clienteDTO1());
        dto.setFuncionario(funcionarioDTO1());
        return dto;
    }
}
