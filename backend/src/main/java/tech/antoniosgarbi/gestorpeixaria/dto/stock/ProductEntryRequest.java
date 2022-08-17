package tech.antoniosgarbi.gestorpeixaria.dto.stock;

import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class ProductEntryRequest {
    private LocalDate arrivalDate;
    private LocalDate expirationDate;
    private Double arrivalQuantity;
    private Double availableQuantity;
    @ManyToOne
    private ProductDTO product;
    @ManyToOne
    private SupplierDTO supplier;
    private Boolean arrivalRegistered;
}
