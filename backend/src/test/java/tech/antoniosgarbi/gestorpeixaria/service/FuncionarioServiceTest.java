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
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PessoaException;
import tech.antoniosgarbi.gestorpeixaria.model.Funcionario;
import tech.antoniosgarbi.gestorpeixaria.repository.FuncionarioRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.FuncionarioServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FuncionarioServiceTest {
    @Mock
    FuncionarioRepository funcionarioRepository;
    @InjectMocks
    FuncionarioServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um FornecedorDTO com documento não cadastrado")
    void cadastrar0() {
        Funcionario funcionarioEsperado = Builder.funcionario1();
        when(funcionarioRepository.findByDocumento(anyString())).thenReturn(Optional.empty());
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionarioEsperado);

        long idResposta = underTest.cadastrar(Builder.funcionarioDTO1());

        assertEquals(funcionarioEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um FornecedorDTO com documento já cadastrado no sistema")
    void cadastrar1() {
        FuncionarioDTO dto = Builder.funcionarioDTO1();

        when(funcionarioRepository.findByDocumento(anyString())).thenReturn(Optional.of(Builder.funcionario1()));

        var exception =
                assertThrows(PessoaException.class, () -> underTest.cadastrar(dto));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PessoaException.class, () -> underTest.atualizarCadastro(Builder.funcionarioDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar FornecedorDTO ao receber um FornecedorDTO válido")
    void atualizarCadastro1() {
        Funcionario esperado = Builder.funcionario1();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setNome(nomeNovo);
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(esperado);

        FuncionarioDTO argument = Builder.funcionarioDTO1();
        argument.setNome(nomeNovo);
        FuncionarioDTO resposta = underTest.atualizarCadastro(argument);

        assertEquals(nomeNovo, resposta.getNome());
    }

    @Test
    @DisplayName("Deve retornar um FuncionarioDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        FuncionarioDTO funcionarioDTO = Builder.funcionarioDTO1();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(new Funcionario(funcionarioDTO)));
        FuncionarioDTO resposta = underTest.encontrarCadastro(1L);

        assertNotNull(resposta);
        assertEquals(funcionarioDTO.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PessoaException.class, () -> underTest.encontrarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<FornecedorDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Funcionario> fornecedorList = List.of(Builder.funcionario1(), Builder.funcionario1(), Builder.funcionario1());
        Page<Funcionario> fornecedorPage = new PageImpl<>(fornecedorList);
        when(funcionarioRepository.findAll(any(Pageable.class))).thenReturn(fornecedorPage);

        Page<FuncionarioDTO> pageResposta = underTest.encontrarTodos(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(fornecedorPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(fornecedorPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PessoaException.class, () -> underTest.apagarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        Funcionario funcionarioEsperado = Builder.funcionario1();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionarioEsperado));

        Assertions.assertThatCode(() -> underTest.apagarCadastro(1L))
                .doesNotThrowAnyException();
    }

}