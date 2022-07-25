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
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;
import tech.antoniosgarbi.gestorpeixaria.repository.CollaboratorRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.CollaboratorServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FuncionarioServiceTest {
    @Mock
    CollaboratorRepository funcionarioRepository;
    @InjectMocks
    CollaboratorServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um FornecedorDTO com documento não cadastrado")
    void cadastrar0() {
        Collaborator funcionarioEsperado = Builder.collaborator1();
        when(funcionarioRepository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(funcionarioRepository.save(any(Collaborator.class))).thenReturn(funcionarioEsperado);

        long idResposta = underTest.register(Builder.collaboratorDTO1());

        assertEquals(funcionarioEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um FornecedorDTO com documento já cadastrado no sistema")
    void cadastrar1() {
        CollaboratorDTO dto = Builder.collaboratorDTO1();

        when(funcionarioRepository.findByDocument(anyString())).thenReturn(Optional.of(Builder.collaborator1()));

        var exception =
                assertThrows(PersonException.class, () -> underTest.register(dto));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PersonException.class, () -> underTest.update(Builder.collaboratorDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar FornecedorDTO ao receber um FornecedorDTO válido")
    void atualizarCadastro1() {
        Collaborator esperado = Builder.collaborator1();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setName(nomeNovo);
        when(funcionarioRepository.save(any(Collaborator.class))).thenReturn(esperado);

        CollaboratorDTO argument = Builder.collaboratorDTO1();
        argument.setName(nomeNovo);
        CollaboratorDTO resposta = underTest.update(argument);

        assertEquals(nomeNovo, resposta.getName());
    }

    @Test
    @DisplayName("Deve retornar um FuncionarioDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        CollaboratorDTO funcionarioDTO = Builder.collaboratorDTO1();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(new Collaborator(funcionarioDTO)));
        CollaboratorDTO resposta = underTest.findById(1L);

        assertNotNull(resposta);
        assertEquals(funcionarioDTO.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PersonException.class, () -> underTest.findById(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<FornecedorDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Collaborator> fornecedorList = List.of(Builder.collaborator1(), Builder.collaborator1(), Builder.collaborator1());
        Page<Collaborator> fornecedorPage = new PageImpl<>(fornecedorList);
        when(funcionarioRepository.findAll(any(Pageable.class))).thenReturn(fornecedorPage);

        Page<CollaboratorDTO> pageResposta = underTest.findAll(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(fornecedorPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(fornecedorPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PersonException.class, () -> underTest.delete(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        Collaborator funcionarioEsperado = Builder.collaborator1();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionarioEsperado));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}