package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.ItemVenda;


@Data
@NoArgsConstructor
public class ItemVendaDTO {
    private Long id;
    private Double quantidade;
    private ProdutoDTO produto;

    public ItemVendaDTO(ItemVenda modelo) {
        this.id = modelo.getId();
        this.quantidade = modelo.getQuantidade();
        this.produto = new ProdutoDTO(modelo.getProduto());
    }
}
