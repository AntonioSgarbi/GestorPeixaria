package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductSpecBody implements Serializable {
    @Serial
    private static final long serialVersionUID = -6370659237174210688L;

    private String name;
    private QuantityType quantityType;
    private Double availableQuantity;
    private SupplierDTO supplier;
}
