package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PaymentType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime moment;
    private PaymentType paymentType;
    @ManyToOne
    private Customer customer;
    @ManyToOne(optional = false)
    private Collaborator collaborator;
    @OneToMany
    private List<SaleItem> saleItems;
    private Double totalValue;

    public Sale(SaleDTO dto) {
        this.id = dto.getId();
        this.moment = dto.getMoment();
        this.paymentType = dto.getPaymentType();
        this.customer = new Customer(dto.getCustomer());
        this.collaborator = new Collaborator(dto.getCollaborator());
        this.saleItems = dto.getSaleItems()
                .stream()
                .map(SaleItem::new)
                .toList();
        this.totalValue = dto.getTotalValue();
    }

}