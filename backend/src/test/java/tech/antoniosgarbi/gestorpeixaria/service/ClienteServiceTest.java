package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ClienteDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PessoaException;
import tech.antoniosgarbi.gestorpeixaria.model.Cliente;
import tech.antoniosgarbi.gestorpeixaria.repository.ClienteRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.ClienteServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceTest {
    @Mock
    ClienteRepository clienteRepository;
    @InjectMocks
    ClienteServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um ClienteDTO com documento não cadastrado")
    void cadastrar0() {
        Cliente clienteEsperado = Builder.cliente1();
        when(clienteRepository.findClienteByDocumento(anyString())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteEsperado);

        long idResposta = underTest.cadastrar(Builder.clienteDTO1());

        assertEquals(clienteEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar uma ClienteException ao receber um ClienteDTO com documento já cadastrado no sistema")
    void cadastrar1() {
        when(clienteRepository.findClienteByDocumento(anyString())).thenReturn(Optional.of(Builder.cliente1()));

        var exception =
                assertThrows(PessoaException.class, () -> underTest.cadastrar(Builder.clienteDTO1()));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um ClienteDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        ClienteDTO clienteEsperado = Builder.clienteDTO1();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente(clienteEsperado)));
        ClienteDTO resposta = underTest.encontrarCadastro(1L);

        assertNotNull(resposta);
        assertEquals(clienteEsperado.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar uma ClienteException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PessoaException.class, () -> underTest.encontrarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<ClienteDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Cliente> listaCliente = List.of(Builder.cliente1(), Builder.cliente1(), Builder.cliente1());
        Page<Cliente> pageCliente = new PageImpl<>(listaCliente);
        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(pageCliente);

        Page<ClienteDTO> pageResposta = underTest.encontrarTodos(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(pageCliente.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(pageCliente.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PessoaException.class, () -> underTest.apagarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

}