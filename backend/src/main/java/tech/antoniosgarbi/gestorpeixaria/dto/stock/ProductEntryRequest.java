package tech.antoniosgarbi.gestorpeixaria.dto.stock;

import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;

import java.time.LocalDate;

@Data
public class ProductEntryRequest {

    private LocalDate arrivalDate;
    private LocalDate expirationDate;

    private Double arrivalQuantity;
//    private Double availableQuantity; //-----------------

//    private Double supplierPrice; //???????????
    private Double optionalPrice;

    private ProductDTO product;
    private SupplierDTO supplier;
//    private CollaboratorDTO receivedBy; //-----------------

    private boolean arrivalRegistered;
}
