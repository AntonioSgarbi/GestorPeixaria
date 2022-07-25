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
import tech.antoniosgarbi.gestorpeixaria.dto.model.CustomerDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.service.contract.CustomerService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController underTest;

    @Test
    @DisplayName("Should return 200<id> when register success")
    void register0() {
        when(customerService.register(any(CustomerDTO.class))).thenReturn(1L);
        ResponseEntity<Long> response = underTest.register(new CustomerDTO());
        assertEquals(1L, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when register fails")
    void register1() {
        PersonException expected = new PersonException("PersonException");
        when(customerService.register(any(CustomerDTO.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.register(new CustomerDTO()));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 202<void> when update success")
    void update0() {
        when(customerService.update(any(CustomerDTO.class))).thenReturn(new CustomerDTO());
        ResponseEntity<Void> response = underTest.update(new CustomerDTO());
        assertNull(response.getBody());
        assertEquals(202, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when update fails")
    void update1() {
        PersonException expected = new PersonException("PersonException");
        when(customerService.update(any(CustomerDTO.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.update(new CustomerDTO()));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 CustomerDTO when findById success")
    void findById0() {
        CustomerDTO expected = Builder.customerDTO1();
        when(customerService.findById(any(Long.class))).thenReturn(expected);
        ResponseEntity<CustomerDTO> response = underTest.findById(1L);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when findById fails")
    void findById1() {
        PersonException expected = new PersonException("PersonException");
        when(customerService.findById(any(Long.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.findById(1L));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 Page<CustomerDTO> when findAll receives Pageable")
    void findAll0() {
        CustomerDTO expected0 = Builder.customerDTO1();
        CustomerDTO expected1 = new CustomerDTO();

        Page<CustomerDTO> expected = new PageImpl<CustomerDTO>(List.of(expected0, expected1));
        when(customerService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<CustomerDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    @DisplayName("Should return 200 Page<CustomerDTO> empty when findAll receives Pageable")
    void findAll1() {
        Page<CustomerDTO> expected = new PageImpl<CustomerDTO>(List.of());
        when(customerService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<CustomerDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<CustomerDTO> when findAll receives CustomerSpecBody")
    void findAll3() {
    }

    @Test
    @DisplayName("Should return 200 Page<CustomerDTO> empty when findAll receives CustomerSpecBody")
    void findAll4() {
    }

    @Test
    @DisplayName("Should return 204 null when delete receives id")
    void delete0() {
        ResponseEntity<Void> response = underTest.delete(1L);
        verify(customerService).delete(any(Long.class));
        assertEquals(204, response.getStatusCodeValue());
    }

}