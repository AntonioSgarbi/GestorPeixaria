package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.model.Sale;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaleServiceTest {
    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleServiceImpl underTest;

//    @Test
//    @DisplayName("Should return SaleResponse when receives a valid SaleRequest")
    void register0() {
        SaleDTO saleRequest = Builder.saleDTO1();
        Sale sale = new Sale(saleRequest);
        sale.setId(1L);
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        Long resultId = underTest.register(saleRequest);

        assertEquals(sale.getId(), resultId);
    }
}
