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
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.ProductException;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ProductService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController underTest;

    @Test
    @DisplayName("Should return 200<id> when register success")
    void register0() {
        when(productService.register(any(ProductDTO.class))).thenReturn(1L);
        ResponseEntity<Long> response = underTest.register(new ProductDTO());
        assertEquals(1L, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when register fails")
    void register1() {
        ProductException expected = new ProductException("ProductException");
        when(productService.register(any(ProductDTO.class))).thenThrow(expected);
        var result = assertThrows(ProductException.class, () -> underTest.register(new ProductDTO()));
        assertEquals(expected.getMessage(), result.getMessage());

    }

    @Test
    @DisplayName("Should return 202<void> when update success")
    void update0() {
        when(productService.update(any(ProductDTO.class))).thenReturn(new ProductDTO());
        ResponseEntity<Void> response = underTest.update(new ProductDTO());
        assertNull(response.getBody());
        assertEquals(202, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws ProductException when update fails")
    void update1() {
        ProductException expected = new ProductException("ProductException");
        when(productService.update(any(ProductDTO.class))).thenThrow(expected);
        var result = assertThrows(ProductException.class, () -> underTest.update(new ProductDTO()));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 ProductDTO when findById success")
    void findById0() {
        ProductDTO expected = new ProductDTO();
        when(productService.findById(any(Long.class))).thenReturn(expected);
        ResponseEntity<ProductDTO> response = underTest.findById(1L);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws ProductException when findById fails")
    void findById1() {
        ProductException expected = new ProductException("ProductException");
        when(productService.findById(any(Long.class))).thenThrow(expected);
        var result = assertThrows(ProductException.class, () -> underTest.findById(1L));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 Page<ProductDTO> when findAll receives Pageable")
    void findAll0() {
        ProductDTO expected0 = Builder.productUnityDTO1();
        ProductDTO expected1 = Builder.productWeightDTO1();

        Page<ProductDTO> expected = new PageImpl<ProductDTO>(List.of(expected0, expected1));
        when(productService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<ProductDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<ProductDTO> empty when findAll receives Pageable")
    void findAll1() {
        Page<ProductDTO> expected = new PageImpl<>(List.of());
        when(productService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<ProductDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<ProductDTO> when findAll receives ProductSpecBody")
    void testFindAll3() {
    }

    @Test
    @DisplayName("Should return 200 Page<ProductDTO> empty when findAll receives ProductSpecBody")
    void testFindAll4() {
    }

    @Test
    @DisplayName("Should return 204 null when delete receives id")
    void delete0() {
        ResponseEntity<Void> response = underTest.delete(1L);
        verify(productService).delete(any(Long.class));
        assertEquals(204, response.getStatusCodeValue());
    }
}