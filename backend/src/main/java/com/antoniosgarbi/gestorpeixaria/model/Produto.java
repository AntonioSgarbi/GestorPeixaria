package com.antoniosgarbi.gestorpeixaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Produto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    @OneToMany
    private Set<Lote> lotes;
}
