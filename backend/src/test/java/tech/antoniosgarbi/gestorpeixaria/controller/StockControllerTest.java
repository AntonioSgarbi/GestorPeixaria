package tech.antoniosgarbi.gestorpeixaria.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.AvailableLotResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;
import tech.antoniosgarbi.gestorpeixaria.service.contract.StockService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController underTest;

    @Test
    void productEntry() {
        when(this.stockService.productEntry(any(ProductEntryRequest.class))).thenReturn(1L);

        ResponseEntity<Long> response = this.underTest.productEntry(new ProductEntryRequest());

        assertEquals(1L, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findByPruduct() {
        when(this.stockService.findAllAvailableLotsByProductId(anyLong(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        ResponseEntity<Page<AvailableLotResponse>> response = this.underTest.findByPruduct(1L, Pageable.unpaged());

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void query() {
        when(this.stockService.query(any(ExpirationLotSpecBody.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        ResponseEntity<Page<StockEntries>> response = this.underTest.query(new ExpirationLotSpecBody(), Pageable.unpaged());

        assertEquals(200, response.getStatusCodeValue());
    }
}