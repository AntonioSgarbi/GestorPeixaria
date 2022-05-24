package tech.antoniosgarbi.gestorpeixaria.model;

import tech.antoniosgarbi.gestorpeixaria.model.enums.PagamentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Pagamento {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime momentoRegistrado;
    private PagamentoTipo pagamentoTipo;
    private Boolean retiradaCaixa;
    private LocalDate pagamento;
    @ManyToOne
    private Funcionario funcionario;
    @Column(columnDefinition = "TEXT")
    private String descricao;

}
