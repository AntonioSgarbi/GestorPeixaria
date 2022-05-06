package com.antoniosgarbi.gestorpeixaria.model;

import com.antoniosgarbi.gestorpeixaria.model.enums.PagamentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime momentoLancamento;
    private PagamentoTipo pagamentoTipo;
    @ManyToOne
    private Pessoa comprador;
    @OneToMany
    private List<ItemVenda> produtosQuantidades;




}
