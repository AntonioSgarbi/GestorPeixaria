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
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SupplierSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SupplierService;
import tech.antoniosgarbi.gestorpeixaria.specification.SupplierSpecification;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SupplierControllerTest {
    @Mock
    private SupplierService supplierService;
    @InjectMocks
    private SupplierController underTest;

    @Test
    @DisplayName("Should return 200 id when register success")
    void register0() {
        when(supplierService.register(any(SupplierDTO.class))).thenReturn(1L);
        ResponseEntity<Long> response = underTest.register(new SupplierDTO());
        assertEquals(1L, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when register fails")
    void register1() {
        PersonException expected = new PersonException("PersonException");
        when(supplierService.register(any(SupplierDTO.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.register(new SupplierDTO()));
        assertEquals(expected.getMessage(), result.getMessage());

    }

    @Test
    @DisplayName("Should return 202<void> when update success")
    void update0() {
        when(supplierService.update(any(SupplierDTO.class))).thenReturn(new SupplierDTO());
        ResponseEntity<Void> response = underTest.update(new SupplierDTO());
        assertNull(response.getBody());
        assertEquals(202, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when update fails")
    void update1() {
        PersonException expected = new PersonException("PersonException");
        when(supplierService.update(any(SupplierDTO.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.update(new SupplierDTO()));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 SupplierDTO when findById success")
    void findById0() {
        SupplierDTO expected = new SupplierDTO();
        when(supplierService.findById(any(Long.class))).thenReturn(expected);
        ResponseEntity<SupplierDTO> response = underTest.findById(1L);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when findById fails")
    void findById1() {
        PersonException expected = new PersonException("PersonException");
        when(supplierService.findById(any(Long.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.findById(1L));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 Page<SupplierDTO> when findAll receives Pageable")
    void findAll0() {
        SupplierDTO expected0 = Builder.supllierDTO1();
        SupplierDTO expected1 = new SupplierDTO();

        Page<SupplierDTO> expected = new PageImpl<SupplierDTO>(List.of(expected0, expected1));
        when(supplierService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<SupplierDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<SupplierDTO> empty when findAll receives Pageable")
    void findAll1() {
        Page<SupplierDTO> expected = new PageImpl<>(List.of());
        when(supplierService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<SupplierDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<SupplierDTO> when findAll receives SupplierSpecBody")
    void findAll2() {
        List<SupplierDTO> supplierList = List.of(Builder.supllierDTO1(), Builder.supllierDTO1(), Builder.supllierDTO1());
        Page<SupplierDTO> supplierPage = new PageImpl<>(supplierList);
        when(supplierService.findAll(any(SupplierSpecBody.class), any(Pageable.class))).thenReturn(supplierPage);

        ResponseEntity<Page<SupplierDTO>> response = underTest.findAll(new SupplierSpecBody(), Pageable.unpaged());

        assertNotNull(response.getBody().getContent());
        assertEquals(3, response.getBody().getContent().size());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(supplierPage.getTotalElements(), response.getBody().getTotalElements());
        assertEquals(supplierPage.getContent().get(0).getId(), response.getBody().getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should return 204 null when delete receives id")
    void delete0() {
        ResponseEntity<Void> response = underTest.delete(1L);
        verify(supplierService).delete(any(Long.class));
        assertEquals(204, response.getStatusCodeValue());
    }

}