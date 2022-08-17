package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private QuantityType quantityType;
    private List<SupplierDTO> supplier;

    public ProductDTO(Product model) {
        this.id = model.getId();
        this.name = model.getName();
        this.quantityType = model.getQuantityType();
        if(model.getSupplier() != null)
            this.supplier = model.getSupplier().stream().map(SupplierDTO::new).toList();
    }

}