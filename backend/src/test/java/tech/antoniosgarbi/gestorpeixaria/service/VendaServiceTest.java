package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.VendaRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.VendaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        vendaDTO.setCliente(null);
        vendaDTO.setFuncionario(null);
        vendaDTO.setProdutosQuantidades(null);


        var exception = assertThrows(IllegalArgumentException.class, () -> underTest.registrarVenda(Builder.vendaDTO1()));
        String mensagemEsperada = "O cliente é obrigatório!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
