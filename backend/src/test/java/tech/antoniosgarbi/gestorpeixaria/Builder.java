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

    static Produto produtoUnidade1() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("prod unidade");
        produto.setQuantidadeTipo(QuantidadeTipo.UNIDADE);
        return produto;
    }

    static ProdutoDTO produtoUnidadeDTO1() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(1L);
        dto.setNome("proDto unidade");
        dto.setQuantidadeTipo(QuantidadeTipo.UNIDADE);
        return dto;
    }

    static Produto produtoPeso1() {
        Produto produto = new Produto();
        produto.setId(2L);
        produto.setNome("prod peso");
        produto.setQuantidadeTipo(QuantidadeTipo.PESO);
        return produto;
    }

    static ProdutoDTO produtoPesoDTO1() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(2L);
        dto.setNome("prodTo peso");
        return dto;
    }

    static ItemCompra itemVenda1() {
        ItemCompra itemVenda = new ItemCompra();
        itemVenda.setId(1L);
        itemVenda.setQuantidade(1.0);
        itemVenda.setProduto(produtoUnidade1());
        return itemVenda;
    }

    static ItemCompraDTO itemVendaDTO1() {
        ItemCompraDTO dto = new ItemCompraDTO();
        dto.setId(1L);
        dto.setQuantidade(1.0);
        dto.setProduto(produtoUnidadeDTO1());
        return dto;
    }

    static ItemCompra itemVenda2() {
        ItemCompra itemVenda = new ItemCompra();
        itemVenda.setId(2L);
        itemVenda.setQuantidade(250.0);
        itemVenda.setProduto(produtoPeso1());
        return itemVenda;
    }

    static ItemCompraDTO itemVendaDTO2() {
        ItemCompraDTO dto = new ItemCompraDTO();
        dto.setId(2L);
        dto.setQuantidade(250.0);
        dto.setProduto(produtoPesoDTO1());
        return dto;
    }


    static VendaDTO vendaDTO1() {
        VendaDTO dto = new VendaDTO();
        dto.setCliente(clienteDTO1());
        dto.setFuncionario(funcionarioDTO1());
        return dto;
    }
}
