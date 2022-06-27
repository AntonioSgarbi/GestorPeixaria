package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PagamentoTipo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime momentoLancamento;
    private PagamentoTipo pagamentoTipo;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    @OneToMany
    private List<ItemVenda> produtosQuantidades;
    private Double valorTotal;

    public Venda(VendaDTO dto) {
        this.id = dto.getId();
        this.momentoLancamento = dto.getMomentoLancamento();
        this.pagamentoTipo = dto.getPagamentoTipo();
        this.cliente = new Cliente(dto.getCliente());
        this.funcionario = new Funcionario(dto.getFuncionario());
        this.produtosQuantidades = dto.getProdutosQuantidades()
                .stream()
                .map(ItemVenda::new)
                .toList();
        this.valorTotal = dto.getValorTotal();
    }

}