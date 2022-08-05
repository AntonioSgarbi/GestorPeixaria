package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private QuantityType quantityType;
    private Double availableQuantity;
    private List<SupplierDTO> supplier;

    public ProductDTO(Product model) {
        this.id = model.getId();
        this.name = model.getName();
        this.quantityType = model.getQuantityType();
        this.availableQuantity = model.getAvailableQuantity();
        if(model.getSupplier() != null)
            this.supplier = model.getSupplier().stream().map(SupplierDTO::new).toList();
    }

}