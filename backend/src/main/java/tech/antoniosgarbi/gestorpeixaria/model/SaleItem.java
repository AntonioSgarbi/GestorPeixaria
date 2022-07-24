package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleItemDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    @ManyToOne
    private Product product;

    public SaleItem(SaleItemDTO dto) {
        this.id = dto.getId();
        this.quantity = dto.getQuantity();
        this.product = new Product(dto.getProduct());
    }

}
