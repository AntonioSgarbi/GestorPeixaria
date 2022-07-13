package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.ItemCompra;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCompraDTO {
    private Long id;
    private Double quantidade;
    private ProdutoDTO produto;

    public ItemCompraDTO(ItemCompra modelo) {
        this.id = modelo.getId();
        this.quantidade = modelo.getQuantidade();
        this.produto = new ProdutoDTO(modelo.getProduto());
    }
}
