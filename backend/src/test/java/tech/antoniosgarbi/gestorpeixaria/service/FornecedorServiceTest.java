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
import tech.antoniosgarbi.gestorpeixaria.dto.model.FornecedorDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PessoaException;
import tech.antoniosgarbi.gestorpeixaria.model.Fornecedor;
import tech.antoniosgarbi.gestorpeixaria.repository.FornecedorRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.FornecedorServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FornecedorServiceTest {
    @Mock
    FornecedorRepository fornecedorRepository;
    @InjectMocks
    FornecedorServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um FornecedorDTO com documento não cadastrado")
    void cadastrar0() {
        Fornecedor fornecedorEsperado = Builder.fornecedor1();
        when(fornecedorRepository.findByDocumento(anyString())).thenReturn(Optional.empty());
        when(fornecedorRepository.save(any(Fornecedor.class))).thenReturn(fornecedorEsperado);

        long idResposta = underTest.cadastrar(Builder.fornecedorDTO1());

        assertEquals(fornecedorEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um FornecedorDTO com documento já cadastrado no sistema")
    void cadastrar1() {
        FornecedorDTO dto = Builder.fornecedorDTO1();

        when(fornecedorRepository.findByDocumento(anyString())).thenReturn(Optional.of(Builder.fornecedor1()));

        var exception =
                assertThrows(PessoaException.class, () -> underTest.cadastrar(dto));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PessoaException.class, () -> underTest.atualizarCadastro(Builder.fornecedorDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar FornecedorDTO ao receber um FornecedorDTO válido")
    void atualizarCadastro1() {
        Fornecedor esperado = Builder.fornecedor1();
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setNome(nomeNovo);
        when(fornecedorRepository.save(any(Fornecedor.class))).thenReturn(esperado);

        FornecedorDTO argument = Builder.fornecedorDTO1();
        argument.setNome(nomeNovo);
        FornecedorDTO resposta = underTest.atualizarCadastro(argument);

        assertEquals(nomeNovo, resposta.getNome());
    }


    @Test
    @DisplayName("Deve retornar um FornecedorDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        FornecedorDTO fornecedorDTO = Builder.fornecedorDTO1();
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(new Fornecedor(fornecedorDTO)));
        FornecedorDTO resposta = underTest.encontrarCadastro(1L);

        assertNotNull(resposta);
        assertEquals(fornecedorDTO.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PessoaException.class, () -> underTest.encontrarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<FornecedorDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Fornecedor> fornecedorList = List.of(Builder.fornecedor1(), Builder.fornecedor1(), Builder.fornecedor1());
        Page<Fornecedor> fornecedorPage = new PageImpl<>(fornecedorList);
        when(fornecedorRepository.findAll(any(Pageable.class))).thenReturn(fornecedorPage);

        Page<FornecedorDTO> pageResposta = underTest.encontrarTodos(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(fornecedorPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(fornecedorPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PessoaException.class, () -> underTest.apagarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        Fornecedor fornecedor = Builder.fornecedor1();
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));

        assertThatCode(() -> underTest.apagarCadastro(1L))
                .doesNotThrowAnyException();
    }

}