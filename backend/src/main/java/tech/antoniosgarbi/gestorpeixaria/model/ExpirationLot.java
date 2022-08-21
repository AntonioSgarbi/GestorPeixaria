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
    private Double optionalPrice;
    private Boolean arrivalRegistered;
    private LocalDateTime registeredMoment;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Collaborator receivedBy;


    public ExpirationLot(ProductEntryRequest body) {
        this.arrivalDate = body.getArrivalDate();
        this.expirationDate = body.getExpirationDate();
        this.arrivalQuantity = body.getArrivalQuantity();
        this.availableQuantity = body.getArrivalQuantity();
        this.product = new Product(body.getProduct());
        this.supplier = new Supplier(body.getSupplier());
        this.receivedBy = new Collaborator(body.getReceivedBy());
        this.arrivalRegistered = body.getArrivalRegistered();
        this.registeredMoment = LocalDateTime.now();
    }

}