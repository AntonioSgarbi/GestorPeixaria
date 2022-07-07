package tech.antoniosgarbi.gestorpeixaria;

import tech.antoniosgarbi.gestorpeixaria.dto.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantidadeTipo;

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
        produto.setNome("prod unidade");
        produto.setQuantidadeTipo(QuantidadeTipo.UNIDADE);
        return produto;
    }

    static ProdutoDTO produtoDTO1() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(1L);
        dto.setNome("proDto unidade");
        dto.setQuantidadeTipo(QuantidadeTipo.UNIDADE);
        return dto;
    }

    static Produto produto2() {
        Produto produto = new Produto();
        produto.setId(2L);
        produto.setNome("prod peso");
        produto.setQuantidadeTipo(QuantidadeTipo.PESO);
        return produto;
    }

    static ProdutoDTO produtoDTO2() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(2L);
        dto.setNome("prodTo peso");
        return dto;
    }

    static ItemVenda itemVenda1() {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setId(1L);
        itemVenda.setQuantidade(1.0);
        itemVenda.setProduto(produto1());
        return itemVenda;
    }

    static ItemVendaDTO itemVendaDTO1() {
        ItemVendaDTO dto = new ItemVendaDTO();
        dto.setId(1L);
        dto.setQuantidade(1.0);
        dto.setProduto(produtoDTO1());
        return dto;
    }

    static ItemVenda itemVenda2() {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setId(2L);
        itemVenda.setQuantidade(250.0);
        itemVenda.setProduto(produto2());
        return itemVenda;
    }

    static ItemVendaDTO itemVendaDTO2() {
        ItemVendaDTO dto = new ItemVendaDTO();
        dto.setId(2L);
        dto.setQuantidade(250.0);
        dto.setProduto(produtoDTO2());
        return dto;
    }


    static VendaDTO vendaDTO1() {
        VendaDTO dto = new VendaDTO();
        dto.setCliente(clienteDTO1());
        dto.setFuncionario(funcionarioDTO1());
        return dto;
    }
}
