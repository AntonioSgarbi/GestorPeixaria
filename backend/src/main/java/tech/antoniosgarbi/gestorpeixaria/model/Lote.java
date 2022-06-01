package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.model.enums.QuantidadeTipo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lote {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private QuantidadeTipo quantidadeTipo;
    private double quantidade;
    private LocalDate validade;
    private LocalDateTime registroEntrada;
    @ManyToOne
    private Produto produto;
}
