package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockEntries {

    private Long id;
    private LocalDate arrivalDate;
    private LocalDate expirationDate;
    private Double arrivalQuantity;
    private Double availableQuantity;
    private Double optionalPrice;
    private Boolean arrivalRegistered;
    private LocalDateTime registeredMoment;
    private ProductDTO product;
    private SupplierDTO supplier;
    private CollaboratorDTO receivedBy;

    public StockEntries(ExpirationLot expirationLot) {
        this.id = expirationLot.getId();
        this.arrivalDate = expirationLot.getArrivalDate();
        this.expirationDate = expirationLot.getExpirationDate();
        this.arrivalQuantity = expirationLot.getArrivalQuantity();
        this.availableQuantity = expirationLot.getAvailableQuantity();
        this.optionalPrice = expirationLot.getOptionalPrice();
        this.arrivalRegistered = expirationLot.getArrivalRegistered();
        this.registeredMoment = expirationLot.getRegisteredMoment();

        if(expirationLot.getProduct() != null)
            this.product = new ProductDTO(expirationLot.getProduct());
        if(expirationLot.getSupplier() != null)
            this.supplier = new SupplierDTO(expirationLot.getSupplier());
        if(expirationLot.getReceivedBy() != null)
            this.receivedBy = new CollaboratorDTO(expirationLot.getReceivedBy());
    }

}