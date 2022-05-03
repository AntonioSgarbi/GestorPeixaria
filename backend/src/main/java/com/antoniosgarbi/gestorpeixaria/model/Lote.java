package com.antoniosgarbi.gestorpeixaria.model;

import javax.persistence.*;

@Entity
public class Lote {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Produto produto;
}
