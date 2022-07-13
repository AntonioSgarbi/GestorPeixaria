package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Venda;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PagamentoTipo;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class VendaDTO {

    private Long id;
    private LocalDateTime momentoLancamento;
    private PagamentoTipo pagamentoTipo;
    private ClienteDTO cliente;
    private FuncionarioDTO funcionario;
    private List<ItemCompraDTO> produtosQuantidades;
    private Double valorTotal;

    public VendaDTO(Venda modelo) {
        this.id = modelo.getId();
        this.momentoLancamento = modelo.getMomentoLancamento();
        this.pagamentoTipo = modelo.getPagamentoTipo();
        this.cliente = new ClienteDTO(modelo.getCliente());
        this.funcionario = new FuncionarioDTO(modelo.getFuncionario());
        this.produtosQuantidades = modelo.getProdutosQuantidades()
                .stream()
                .map(ItemCompraDTO::new)
                .toList();
        this.valorTotal = modelo.getValorTotal();
    }

}