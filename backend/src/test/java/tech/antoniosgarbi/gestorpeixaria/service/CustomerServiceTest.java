package tech.antoniosgarbi.gestorpeixaria.service;

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
import tech.antoniosgarbi.gestorpeixaria.dto.model.CustomerDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Customer;
import tech.antoniosgarbi.gestorpeixaria.repository.CustomerRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.CustomerServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {
    @Mock
    CustomerRepository clienteRepository;
    @InjectMocks
    CustomerServiceImpl underTest;

    @Test
    @DisplayName("Should return Long when receives CustomerDTO with an unregistered document")
    void register0() {
        Customer expected = Builder.customer1();
        when(clienteRepository.findCustomerByDocument(anyString())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Customer.class))).thenReturn(expected);

        long idResposta = underTest.register(Builder.customerDTO1());

        assertEquals(expected.getId(), idResposta);
    }

    @Test
    @DisplayName("Throws PersonException when receives CustomerDTO with document already registered")
    void register1() {
        CustomerDTO dto = Builder.customerDTO1();

        when(clienteRepository.findCustomerByDocument(anyString())).thenReturn(Optional.of(Builder.customer1()));

        var exception =
                assertThrows(PersonException.class, () -> underTest.register(dto));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Throws PersonException when updating an object that doesn't exist")
    void update0() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PersonException.class, () -> underTest.update(Builder.customerDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Should return CustomerDTO when updating valid CustomerDTO")
    void update1() {
        Customer expected = Builder.customer1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        String newName = "new name";
        expected.setName(newName);
        when(clienteRepository.save(any(Customer.class))).thenReturn(expected);

        CustomerDTO argument = Builder.customerDTO1();
        argument.setName(newName);
        CustomerDTO result = underTest.update(argument);

        assertEquals(newName, result.getName());
    }

    @Test
    @DisplayName("Should return CustomerDTO when receiving a valid id")
    void findById0() {
        CustomerDTO expected = Builder.customerDTO1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(expected)));
        CustomerDTO result = underTest.findById(1L);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    @DisplayName("Throws PersonException when id doesn't exist")
    void findById1() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PersonException.class, () -> underTest.findById(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return Page<CustomerDTO> when receives Pageable")
    void findAll0() {
        List<Customer> customerList = List.of(Builder.customer1(), Builder.customer1(), Builder.customer1());
        Page<Customer> customerPage = new PageImpl<>(customerList);
        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(customerPage);

        Page<CustomerDTO> response = underTest.findAll(Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(customerPage.getTotalElements(), response.getTotalElements());
        assertEquals(customerPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Throws PersonException when deleting id that doesn't exist")
    void delete0() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PersonException.class, () -> underTest.delete(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should update Customer with excluded true when receives id that exists")
    void delete1() {
        CustomerDTO expected = Builder.customerDTO1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(expected)));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}
