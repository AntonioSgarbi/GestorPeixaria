package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PagamentoTipo;
import tech.antoniosgarbi.gestorpeixaria.repository.VendaRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.VendaService;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class VendaServiceTest {
    @Mock
    private VendaRepository vendaRepository;

    @Mock



    @Autowired
    private VendaService underTest;


    @Test
    @DisplayName("Deve lançar uma venda ao receber um VendaDTO sem Cliente")
void registrarVenda0() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setMomentoLancamento(LocalDateTime.now());
        vendaDTO.setPagamentoTipo(PagamentoTipo.DINHEIRO);
        vendaDTO.setValorTotal(10.0);
        vendaDTO.setProdutosQuantidades(List.of(Builder.itemVendaDTO1(), Builder.itemVendaDTO2()));
        vendaDTO.setFuncionario(Builder.funcionarioDTO1());

    }
}
