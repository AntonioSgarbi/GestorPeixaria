package tech.antoniosgarbi.gestorpeixaria.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.SaleException;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaleControllerTest {
    @Mock
    private SaleService saleService;
    @InjectMocks
    private SaleController underTest;

    @Test
    @DisplayName("Should return 200 id when register success")
    void register0() {
        when(saleService.register(any(SaleDTO.class))).thenReturn(1L);
        ResponseEntity<Long> response = underTest.register(new SaleDTO());
        assertEquals(1L, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws SaleException when register fails")
    void register1() {
        SaleException expected = new SaleException("SaleException");
        when(saleService.register(any(SaleDTO.class))).thenThrow(expected);
        var result = assertThrows(SaleException.class, () -> underTest.register(new SaleDTO()));
        assertEquals(expected.getMessage(), result.getMessage());

    }

    @Test
    @DisplayName("Should return 200 SaleDTO when findById success")
    void findById0() {
        SaleDTO expected = new SaleDTO();
        when(saleService.findById(any(Long.class))).thenReturn(expected);
        ResponseEntity<SaleDTO> response = underTest.findById(1L);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws SaleException when findById fails")
    void findById1() {
        SaleException expected = new SaleException("SaleException");
        when(saleService.findById(any(Long.class))).thenThrow(expected);
        var result = assertThrows(SaleException.class, () -> underTest.findById(1L));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 Page<SaleDTO> empty when findAll receives Pageable")
    void findAll0() {
        Page<SaleDTO> expected = new PageImpl<>(List.of());
        when(saleService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<SaleDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<SaleDTO> when findAll receives Pageable")
    void findAll1() {
        SaleDTO expected0 = Builder.saleDTO1();
        SaleDTO expected1 = new SaleDTO();

        Page<SaleDTO> expected = new PageImpl<SaleDTO>(List.of(expected0, expected1));
        when(saleService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<SaleDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<SaleDTO> when findAll() receives SaleSpecBody")
    void FindAll3() {
    }

    @Test
    @DisplayName("Should return 200 Page<SaleDTO> empty when findAll receives SaleSpecBody")
    void FindAll4() {
    }

    @Test
    @DisplayName("Should return 204 null when delete receives id")
    void delete0() {
        ResponseEntity<Void> response = underTest.delete(1L);
        verify(saleService).delete(any(Long.class));
        assertEquals(204, response.getStatusCodeValue());
    }
}