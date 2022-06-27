package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PagamentoTipo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime momentoRegistrado;
    private PagamentoTipo pagamentoTipo;
    private Boolean retiradaCaixa;
    private LocalDate dataPagamento;
    @ManyToOne
    private Funcionario funcionario;
    @Column(columnDefinition = "TEXT")
    private String descricao;

}
