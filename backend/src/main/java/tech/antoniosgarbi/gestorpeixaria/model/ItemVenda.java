package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Double quantidade;
    @ManyToOne
    private Produto produto;

}
