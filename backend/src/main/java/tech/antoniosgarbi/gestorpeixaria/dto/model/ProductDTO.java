package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private QuantityType quantityType;
    private Double availableQuantity;

    public ProductDTO(Product model) {
        this.id = model.getId();
        this.name = model.getName();
        this.quantityType = model.getQuantityType();
        this.availableQuantity = model.getAvailableQuantity();
    }

}