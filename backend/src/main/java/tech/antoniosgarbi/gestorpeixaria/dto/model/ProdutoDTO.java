package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Produto;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantidadeTipo;

@Data
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private QuantidadeTipo quantidadeTipo;

    public ProdutoDTO(Produto modelo) {
        this.id = modelo.getId();
        this.nome = modelo.getNome();
        this.quantidadeTipo = modelo.getQuantidadeTipo();
    }

}