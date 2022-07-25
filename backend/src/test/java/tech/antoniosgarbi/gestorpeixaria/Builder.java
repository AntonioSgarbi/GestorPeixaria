package tech.antoniosgarbi.gestorpeixaria;

import tech.antoniosgarbi.gestorpeixaria.dto.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

public interface Builder {

    static Customer customer1() {
        Customer cliente = new Customer();
        cliente.setId(1L);
        return cliente;
    }

    static CustomerDTO customerDTO1() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Supplier supllier1() {
        Supplier fornecedor = new Supplier();
        fornecedor.setId(1L);
        fornecedor.setDocument("documento");
        return fornecedor;
    }

    static SupplierDTO supllierDTO1() {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Collaborator collaborator1() {
        Collaborator funcionario = new Collaborator();
        funcionario.setId(1L);
        funcionario.setDocument("documento");
        return funcionario;
    }

    static CollaboratorDTO collaboratorDTO1() {
        CollaboratorDTO dto = new CollaboratorDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Product productUnity1() {
        Product produto = new Product();
        produto.setId(1L);
        produto.setName("prod unidade");
        produto.setQuantityType(QuantityType.UNITY);
        return produto;
    }

    static ProductDTO productUnityDTO1() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("proDto unidade");
        dto.setQuantityType(QuantityType.UNITY);
        return dto;
    }

    static Product productWeight1() {
        Product produto = new Product();
        produto.setId(2L);
        produto.setName("prod peso");
        produto.setQuantityType(QuantityType.WEIGHT);
        return produto;
    }

    static ProductDTO productWeightDTO1() {
        ProductDTO dto = new ProductDTO();
        dto.setId(2L);
        dto.setName("prodTo peso");
        return dto;
    }

    static SaleItem saleItem1() {
        SaleItem itemVenda = new SaleItem();
        itemVenda.setId(1L);
        itemVenda.setQuantity(1.0);
        itemVenda.setProduct(productUnity1());
        return itemVenda;
    }

    static SaleItemDTO saleItemDTO1() {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setId(1L);
        dto.setQuantity(1.0);
        dto.setProduct(productUnityDTO1());
        return dto;
    }

    static SaleItem saleItem2() {
        SaleItem itemVenda = new SaleItem();
        itemVenda.setId(2L);
        itemVenda.setQuantity(250.0);
        itemVenda.setProduct(productWeight1());
        return itemVenda;
    }

    static SaleItemDTO saleItemDTO2() {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setId(2L);
        dto.setQuantity(250.0);
        dto.setProduct(productWeightDTO1());
        return dto;
    }

    static SaleDTO saleDTO1() {
        SaleDTO dto = new SaleDTO();
        dto.setCustomer(customerDTO1());
        dto.setCollaborator(collaboratorDTO1());
        return dto;
    }

}
