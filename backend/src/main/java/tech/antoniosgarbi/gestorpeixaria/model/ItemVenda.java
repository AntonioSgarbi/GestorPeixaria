package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ItemVendaDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantidade;
    @ManyToOne
    private Produto produto;

    public ItemVenda(ItemVendaDTO dto) {
        this.id = dto.getId();
        this.quantidade = dto.getQuantidade();
        this.produto = new Produto(dto.getProduto());
    }

}
