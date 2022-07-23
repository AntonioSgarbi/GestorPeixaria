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
class ClienteServiceTest {
    @Mock
    CustomerRepository clienteRepository;
    @InjectMocks
    CustomerServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um ClienteDTO com documento não cadastrado")
    void cadastrar0() {
        Customer clienteEsperado = Builder.cliente1();
        when(clienteRepository.findCustomerByDocument(anyString())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Customer.class))).thenReturn(clienteEsperado);

        long idResposta = underTest.register(Builder.clienteDTO1());

        assertEquals(clienteEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar uma ClienteException ao receber um ClienteDTO com documento já cadastrado no sistema")
    void cadastrar1() {
        CustomerDTO dto = Builder.clienteDTO1();

        when(clienteRepository.findCustomerByDocument(anyString())).thenReturn(Optional.of(Builder.cliente1()));

        var exception =
                assertThrows(PersonException.class, () -> underTest.register(dto));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PersonException.class, () -> underTest.update(Builder.clienteDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar ClienteDTO ao receber um ClienteDTO válido")
    void atualizarCadastro1() {
        Customer esperado = Builder.cliente1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setName(nomeNovo);
        when(clienteRepository.save(any(Customer.class))).thenReturn(esperado);

        CustomerDTO argument = Builder.clienteDTO1();
        argument.setName(nomeNovo);
        CustomerDTO resposta = underTest.update(argument);

        assertEquals(nomeNovo, resposta.getName());
    }

    @Test
    @DisplayName("Deve retornar um ClienteDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        CustomerDTO clienteEsperado = Builder.clienteDTO1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(clienteEsperado)));
        CustomerDTO resposta = underTest.findById(1L);

        assertNotNull(resposta);
        assertEquals(clienteEsperado.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar uma ClienteException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PersonException.class, () -> underTest.findById(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<ClienteDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Customer> listaCliente = List.of(Builder.cliente1(), Builder.cliente1(), Builder.cliente1());
        Page<Customer> pageCliente = new PageImpl<>(listaCliente);
        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(pageCliente);

        Page<CustomerDTO> pageResposta = underTest.findAll(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(pageCliente.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(pageCliente.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PersonException.class, () -> underTest.delete(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        CustomerDTO clienteEsperado = Builder.clienteDTO1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(clienteEsperado)));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}
