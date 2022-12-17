package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SupplierSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;
import tech.antoniosgarbi.gestorpeixaria.repository.SupplierRepository;
import tech.antoniosgarbi.gestorpeixaria.specification.SupplierSpecification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SupplierServiceTest {
    @Mock
    SupplierRepository supplierRepository;
    @InjectMocks
    SupplierServiceImpl underTest;

    @Test
    @DisplayName("Should return Long when receives SupplierDTO with an unregistered document")
    void register0() {
        Supplier expected = Builder.supllier1();
        when(supplierRepository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(supplierRepository.save(any(Supplier.class))).thenReturn(expected);

        long response = underTest.register(Builder.supllierDTO1());

        assertEquals(expected.getId(), response);
    }

    @Test
    @DisplayName("Throws PersonException when receives SupplierDTO with document already registered")
    void register1() {
        SupplierDTO dto = Builder.supllierDTO1();

        when(supplierRepository.findByDocument(anyString())).thenReturn(Optional.of(Builder.supllier1()));

        var exception =
                assertThrows(PersonException.class, () -> underTest.register(dto));

        String expectedMessage = "O documento informado já possui cadastro no sistema!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Throws PersonException when updating an object that doesn't exist")
    void update0() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PersonException.class, () -> underTest.update(Builder.supllierDTO1()));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should return SupplierDTO when updating valid SupplierDTO")
    void update1() {
        Supplier expected = Builder.supllier1();
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        String newName = "new name";
        expected.setName(newName);
        when(supplierRepository.save(any(Supplier.class))).thenReturn(expected);

        SupplierDTO argument = Builder.supllierDTO1();
        argument.setName(newName);
        SupplierDTO response = underTest.update(argument);

        assertEquals(newName, response.getName());
    }

    @Test
    @DisplayName("Should return SupplierDTO when receiving a valid id")
    void findById0() {
        SupplierDTO expected = Builder.supllierDTO1();
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(new Supplier(expected)));
        SupplierDTO response = underTest.findById(1L);

        assertNotNull(response);
        assertEquals(expected.getId(), response.getId());
    }

    @Test
    @DisplayName("Throws PersonException when id doesn't exist")
    void findById1() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PersonException.class, () -> underTest.findById(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return Page<SupplierDTO> when receives Pageable")
    void findAll0() {
        List<Supplier> supplierList = List.of(Builder.supllier1(), Builder.supllier1(), Builder.supllier1());
        Page<Supplier> supplierPage = new PageImpl<>(supplierList);
        when(supplierRepository.findAll(any(Pageable.class))).thenReturn(supplierPage);

        Page<SupplierDTO> response = underTest.findAll(Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(supplierPage.getTotalElements(), response.getTotalElements());
        assertEquals(supplierPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should return Page<SupplierDTO> when receives SupplierSpecBody and Pageable")
    void findAll1() {
        List<Supplier> supplierList = List.of(Builder.supllier1(), Builder.supllier1(), Builder.supllier1());
        Page<Supplier> supplierPage = new PageImpl<>(supplierList);
        when(supplierRepository.findAll(any(SupplierSpecification.class), any(Pageable.class))).thenReturn(supplierPage);

        Page<SupplierDTO> response = underTest.findAll(new SupplierSpecBody(), Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(supplierPage.getTotalElements(), response.getTotalElements());
        assertEquals(supplierPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Throws PersonException when deleting id that doesn't exist")
    void delete0() {
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PersonException.class, () -> underTest.delete(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should update Supplier with excluded true when receives id that exists")
    void delete1() {
        Supplier supplier = Builder.supllier1();
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(supplier));

        assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}