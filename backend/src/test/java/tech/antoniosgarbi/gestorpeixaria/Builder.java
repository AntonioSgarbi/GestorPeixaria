package tech.antoniosgarbi.gestorpeixaria;

import tech.antoniosgarbi.gestorpeixaria.dto.model.*;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;
import tech.antoniosgarbi.gestorpeixaria.model.*;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Builder {

    static Customer customer1() {
        Customer customer = new Customer();
        customer.setId(1L);
        return customer;
    }

    static CustomerDTO customerDTO1() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Supplier supllier1() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setDocument("documento");
        return supplier;
    }

    static SupplierDTO supllierDTO1() {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Collaborator collaborator1() {
        Collaborator collaborator = new Collaborator();
        collaborator.setId(1L);
        collaborator.setDocument("documento");
        return collaborator;
    }

    static CollaboratorDTO collaboratorDTO1() {
        CollaboratorDTO dto = new CollaboratorDTO();
        dto.setId(1L);
        dto.setDocument("documento");
        return dto;
    }

    static Product productUnity1() {
        Product product = new Product();
        product.setId(1L);
        product.setName("prod unidade");
        product.setQuantityType(QuantityType.UNITY);
        return product;
    }

    static ProductDTO productUnityDTO1() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("proDto unidade");
        dto.setQuantityType(QuantityType.UNITY);
        return dto;
    }

    static Product productWeight1() {
        Product product = new Product();
        product.setId(2L);
        product.setName("prod peso1");
        product.setQuantityType(QuantityType.WEIGHT);
        return product;
    }

    static ProductDTO productWeightDTO1() {
        ProductDTO dto = new ProductDTO();
        dto.setId(2L);
        dto.setName("prod peso1");
        return dto;
    }

    static SaleItem saleItem1() {
        SaleItem saleItem = new SaleItem();
        saleItem.setId(1L);
        saleItem.setQuantity(1.0);
        saleItem.setProduct(productUnity1());
        return saleItem;
    }

    static SaleItemDTO saleItemDTO1() {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setId(1L);
        dto.setQuantity(1.0);
        dto.setProduct(productUnityDTO1());
        return dto;
    }

    static SaleItem saleItem2() {
        SaleItem saleItem = new SaleItem();
        saleItem.setId(2L);
        saleItem.setQuantity(250.0);
        saleItem.setProduct(productWeight1());
        return saleItem;
    }

    static SaleItemDTO saleItemDTO2() {
        SaleItemDTO dto = new SaleItemDTO(saleItem2());
        dto.setId(2L);
        dto.setQuantity(250.0);
        dto.setProduct(productWeightDTO1());
        return dto;
    }

    static SaleDTO saleDTO1() {
        SaleDTO dto = new SaleDTO();
        dto.setCustomer(customerDTO1());
        dto.setCollaborator(collaboratorDTO1());
        dto.setSaleItems(new ArrayList<>());
        return dto;
    }

    static ProductEntryRequest productEntryRequest1() {
        ProductEntryRequest productEntryRequest = new ProductEntryRequest();

        productEntryRequest.setProduct(productUnityDTO1());
        productEntryRequest.setSupplier(supllierDTO1());
        productEntryRequest.setArrivalDate(LocalDate.now());
        productEntryRequest.setArrivalQuantity(20.0);

        return productEntryRequest;
    }

    static ExpirationLot expirationLot1() {
        ExpirationLot expected = new ExpirationLot();

        expected.setId(1L);
        expected.setAvailableQuantity(10.0);
        expected.setProduct(Builder.productUnity1());
        expected.setSupplier(Builder.supllier1());

        return expected;
    }

    static ExpirationLot expirationLot2() {
        ExpirationLot expected = new ExpirationLot();

        expected.setId(2L);
        expected.setAvailableQuantity(10.0);
        expected.setProduct(Builder.productWeight1());
        expected.setSupplier(Builder.supllier1());

        return expected;
    }

}
