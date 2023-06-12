package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ProductSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.ProductException;
import tech.antoniosgarbi.gestorpeixaria.exception.SaleException;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.model.Sale;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleItemService;
import tech.antoniosgarbi.gestorpeixaria.specification.ProductSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private SaleItemService saleItemService;

    @InjectMocks
    private SaleServiceImpl underTest;

    @Test
    @DisplayName("Should return Id when receives a valid SaleRequest")
    void register0() {
        SaleDTO saleRequest = Builder.saleDTO1();
        saleRequest.setSaleItems(new ArrayList<>());
        Sale sale = new Sale(saleRequest);
        sale.setId(1L);

        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        Long resultId = underTest.register(saleRequest);

        assertEquals(sale.getId(), resultId);
    }


    @Test
    @DisplayName("Should return SaleDTO when receiving a valid id")
    void findById0() {
        SaleDTO saleDTO = Builder.saleDTO1();
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(new Sale(saleDTO)));
        SaleDTO response = underTest.findById(1L);

        assertNotNull(response);
        assertEquals(saleDTO.getId(), response.getId());
    }

    @Test
    @DisplayName("Throws SaleException when id doesn't exist")
    void findById1() {
        when(saleRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(SaleException.class, () -> underTest.findById(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return Page<SaleDTO> when receives Pageable")
    void findAll0() {
        Sale sale = new Sale(Builder.saleDTO1());
        List<Sale> saleList = List.of(sale, sale, sale);
        Page<Sale> salePage = new PageImpl<>(saleList);
        when(saleRepository.findAll(any(Pageable.class))).thenReturn(salePage);

        Page<SaleDTO> response = underTest.findAll(Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(salePage.getTotalElements(), response.getTotalElements());
        assertEquals(salePage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should update Product with excluded true when receives id that exists")
    void delete1() {
        Sale expected = new Sale(Builder.saleDTO1());
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}
