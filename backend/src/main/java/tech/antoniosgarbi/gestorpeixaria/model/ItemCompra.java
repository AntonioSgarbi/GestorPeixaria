package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ItemCompraDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantidade;
    @ManyToOne
    private Produto produto;

    public ItemCompra(ItemCompraDTO dto) {
        this.id = dto.getId();
        this.quantidade = dto.getQuantidade();
        this.produto = new Produto(dto.getProduto());
    }

}
