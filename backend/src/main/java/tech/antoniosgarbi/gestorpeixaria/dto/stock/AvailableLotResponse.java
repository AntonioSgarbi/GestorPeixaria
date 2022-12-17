package tech.antoniosgarbi.gestorpeixaria.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AvailableLotResponse {

    private Long id;
    private LocalDate arrivalDate;
    private LocalDate expirationDate;
    private Double arrivalQuantity;
    private Double availableQuantity;
    private Double optionalPrice;
    private LocalDateTime registeredMoment;
    private SupplierDTO supplier;
    private CollaboratorDTO receivedBy;

    public AvailableLotResponse(ExpirationLot model) {
        this.id = model.getId();
        this.arrivalDate = model.getArrivalDate();
        this.expirationDate = model.getExpirationDate();
        this.arrivalQuantity = model.getArrivalQuantity();
        this.availableQuantity = model.getAvailableQuantity();
        this.optionalPrice = model.getOptionalPrice();
        this.registeredMoment = model.getRegisteredMoment();
        if(model.getSupplier() != null)
            this.supplier = new SupplierDTO(model.getSupplier());
        if(model.getReceivedBy() != null)
            this.receivedBy = new CollaboratorDTO(model.getReceivedBy());
    }

}
