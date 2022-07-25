package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleItemDTO;
import tech.antoniosgarbi.gestorpeixaria.model.SaleItem;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleItemRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.SaleItemServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemCompraServiceTest {
    @Mock
    private SaleItemRepository itemVendaRepository;

    @InjectMocks
    private SaleItemServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar List<ItemVenda> ao receber List<ItemVendaDTO>")
    void saveAll0() {
        SaleItem itemVenda = SaleItem.builder().id(1L).quantity(1.0).product(Builder.produtoUnidade1()).build();
        SaleItem itemVenda2 = SaleItem.builder().id(2L).quantity(3.0).product(Builder.produtoPeso1()).build();
        List<SaleItem> esperado = List.of(itemVenda, itemVenda2);
        when(itemVendaRepository.saveAll(any(List.class))).thenReturn(esperado);

        SaleItemDTO itemVendaDTO = SaleItemDTO.builder().quantity(1.0).product(Builder.produtoUnidadeDTO1()).build();
        SaleItemDTO itemVendaDTO2 = SaleItemDTO.builder().quantity(3.0).product(Builder.produtoPesoDTO1()).build();
        List<SaleItemDTO> entrada = List.of(itemVendaDTO, itemVendaDTO2);
        List<SaleItemDTO> resposta = underTest.saveAll(entrada);

        assertNotNull(resposta);
        assertEquals(itemVenda.getId(), resposta.get(0).getId());
        assertEquals(itemVenda2.getId(), resposta.get(1).getId());
        assertEquals(itemVenda.getQuantity(), resposta.get(0).getQuantity());
        assertEquals(itemVenda2.getQuantity(), resposta.get(1).getQuantity());
        assertEquals(itemVenda.getProduct().getId(), resposta.get(0).getProduct().getId());
        assertEquals(itemVenda2.getProduct().getId(), resposta.get(1).getProduct().getId());
    }

}
