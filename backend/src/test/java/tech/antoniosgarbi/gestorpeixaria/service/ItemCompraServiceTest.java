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
        SaleItem itemVenda = SaleItem.builder().id(1L).quantidade(1.0).produto(Builder.produtoUnidade1()).build();
        SaleItem itemVenda2 = SaleItem.builder().id(2L).quantidade(3.0).produto(Builder.produtoPeso1()).build();
        List<SaleItem> esperado = List.of(itemVenda, itemVenda2);
        when(itemVendaRepository.saveAll(any(List.class))).thenReturn(esperado);

        SaleItemDTO itemVendaDTO = SaleItemDTO.builder().quantidade(1.0).produto(Builder.produtoUnidadeDTO1()).build();
        SaleItemDTO itemVendaDTO2 = SaleItemDTO.builder().quantidade(3.0).produto(Builder.produtoPesoDTO1()).build();
        List<SaleItemDTO> entrada = List.of(itemVendaDTO, itemVendaDTO2);
        List<SaleItemDTO> resposta = underTest.saveAll(entrada);

        assertNotNull(resposta);
        assertEquals(itemVenda.getId(), resposta.get(0).getId());
        assertEquals(itemVenda2.getId(), resposta.get(1).getId());
        assertEquals(itemVenda.getQuantidade(), resposta.get(0).getQuantidade());
        assertEquals(itemVenda2.getQuantidade(), resposta.get(1).getQuantidade());
        assertEquals(itemVenda.getProduto().getId(), resposta.get(0).getProduto().getId());
        assertEquals(itemVenda2.getProduto().getId(), resposta.get(1).getProduto().getId());
    }

}
