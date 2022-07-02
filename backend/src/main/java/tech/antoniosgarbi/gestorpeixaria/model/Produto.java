package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProdutoDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantidadeTipo;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private QuantidadeTipo quantidadeTipo;
    private Boolean excluido;
    private Double quantidadeEstoque;

    public Produto(ProdutoDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.quantidadeTipo = dto.getQuantidadeTipo();
        this.quantidadeEstoque = dto.getQuantidadeEstoque();
    }

}