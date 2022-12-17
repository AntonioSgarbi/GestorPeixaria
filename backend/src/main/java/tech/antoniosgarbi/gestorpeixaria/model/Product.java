package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantityType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private QuantityType quantityType;
    private Double price;
    private Boolean excluded;

    public Product(ProductDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.quantityType = dto.getQuantityType();
        this.price = dto.getPrice();
    }

}