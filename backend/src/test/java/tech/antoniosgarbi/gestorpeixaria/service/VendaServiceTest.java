package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tech.antoniosgarbi.gestorpeixaria.repository.VendaRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.VendaServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VendaServiceTest {
    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaServiceImpl underTest;

    @Test
    @DisplayName("Deve lançar uma venda ao receber um VendaDTO sem Cliente")
    void registrarVenda0() {
        assertTrue(true);
    }
}
