package tech.antoniosgarbi.gestorpeixaria;

import tech.antoniosgarbi.gestorpeixaria.dto.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

public interface Builder {

    static Customer cliente1() {
        Customer cliente = new Customer();
        cliente.setId(1L);
        return cliente;
    }

    static CustomerDTO clienteDTO1() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Supplier fornecedor1() {
        Supplier fornecedor = new Supplier();
        fornecedor.setId(1L);
        fornecedor.setDocument("documento");
        return fornecedor;
    }

    static SupplierDTO fornecedorDTO1() {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Collaborator funcionario1() {
        Collaborator funcionario = new Collaborator();
        funcionario.setId(1L);
        funcionario.setDocument("documento");
        return funcionario;
    }

    static CollaboratorDTO funcionarioDTO1() {
        CollaboratorDTO dto = new CollaboratorDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Product produtoUnidade1() {
        Product produto = new Product();
        produto.setId(1L);
        produto.setName("prod unidade");
        produto.setQuantityType(QuantityType.UNITY);
        return produto;
    }

    static ProductDTO produtoUnidadeDTO1() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("proDto unidade");
        dto.setQuantityType(QuantityType.UNITY);
        return dto;
    }

    static Product produtoPeso1() {
        Product produto = new Product();
        produto.setId(2L);
        produto.setName("prod peso");
        produto.setQuantityType(QuantityType.WEIGHT);
        return produto;
    }

    static ProductDTO produtoPesoDTO1() {
        ProductDTO dto = new ProductDTO();
        dto.setId(2L);
        dto.setName("prodTo peso");
        return dto;
    }

    static SaleItem itemVenda1() {
        SaleItem itemVenda = new SaleItem();
        itemVenda.setId(1L);
        itemVenda.setQuantity(1.0);
        itemVenda.setProduct(produtoUnidade1());
        return itemVenda;
    }

    static SaleItemDTO itemVendaDTO1() {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setId(1L);
        dto.setQuantity(1.0);
        dto.setProduct(produtoUnidadeDTO1());
        return dto;
    }

    static SaleItem itemVenda2() {
        SaleItem itemVenda = new SaleItem();
        itemVenda.setId(2L);
        itemVenda.setQuantity(250.0);
        itemVenda.setProduct(produtoPeso1());
        return itemVenda;
    }

    static SaleItemDTO itemVendaDTO2() {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setId(2L);
        dto.setQuantity(250.0);
        dto.setProduct(produtoPesoDTO1());
        return dto;
    }


    static SaleDTO vendaDTO1() {
        SaleDTO dto = new SaleDTO();
        dto.setCustomer(clienteDTO1());
        dto.setCollaborator(funcionarioDTO1());
        return dto;
    }
}
