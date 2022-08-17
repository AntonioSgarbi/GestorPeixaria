package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExpirationLot {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate arrivalDate;
    private LocalDate expirationDate;
    private Double arrivalQuantity;
    private Double availableQuantity;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Collaborator receivedBy;
    private Boolean arrivalRegistered;
    private LocalDateTime registeredMoment;


    public ExpirationLot(ProductEntryRequest request) {
        this.arrivalDate = request.getArrivalDate();
        this.expirationDate = request.getExpirationDate();
        this.arrivalQuantity = request.getArrivalQuantity();
        this.availableQuantity = request.getArrivalQuantity();
        this.product = new Product(request.getProduct());
        this.supplier = new Supplier(request.getSupplier());
        this.receivedBy = new Collaborator(request.getReceivedBy());
        this.arrivalRegistered = false;
        this.registeredMoment = null;
    }
}
