package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleItemDTO;
import tech.antoniosgarbi.gestorpeixaria.model.SaleItem;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleItemRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaleItemServiceTest {
    @Mock
    private SaleItemRepository saleItemRepository;

    @InjectMocks
    private SaleItemServiceImpl underTest;

    @Test
    @DisplayName("Should return List<SaleItemDTO> when receiving a List<SaleItemDTO>")
    void saveAll0() {
        SaleItem saleItem0 = SaleItem.builder().id(1L).quantity(1.0).product(Builder.productUnity1()).build();
        SaleItem saleItem1 = SaleItem.builder().id(2L).quantity(3.0).product(Builder.productWeight1()).build();
        List<SaleItem> expected = List.of(saleItem0, saleItem1);
        when(saleItemRepository.saveAll(any(List.class))).thenReturn(expected);

        SaleItemDTO saleItemDTO0 = SaleItemDTO.builder().quantity(1.0).product(Builder.productUnityDTO1()).build();
        SaleItemDTO itemVendaDTO1 = SaleItemDTO.builder().quantity(3.0).product(Builder.productWeightDTO1()).build();
        List<SaleItemDTO> input = List.of(saleItemDTO0, itemVendaDTO1);
        List<SaleItemDTO> response = underTest.saveAll(input);

        assertNotNull(response);
        assertEquals(saleItem0.getQuantity(), response.get(0).getQuantity());
        assertEquals(saleItem1.getQuantity(), response.get(1).getQuantity());
        assertEquals(saleItem0.getProduct().getId(), response.get(0).getProduct().getId());
        assertEquals(saleItem1.getProduct().getId(), response.get(1).getProduct().getId());
    }

}
