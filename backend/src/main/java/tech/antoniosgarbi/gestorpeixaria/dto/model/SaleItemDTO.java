package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.SaleItem;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemDTO {
    private Long id;
    private Double quantity;
    private ProductDTO product;

    public SaleItemDTO(SaleItem modelo) {
        this.id = modelo.getId();
        this.quantity = modelo.getQuantity();
        this.product = new ProductDTO(modelo.getProduct());
    }
}
