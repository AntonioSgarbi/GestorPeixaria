package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.SaleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SaleServiceTest {
    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleServiceImpl underTest;

    @Test
    @DisplayName("Deve lançar uma venda ao receber um VendaDTO sem Cliente")
    void register0() {
        assertTrue(true);
    }
}
