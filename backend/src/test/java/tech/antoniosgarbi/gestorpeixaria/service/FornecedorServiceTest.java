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
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;
import tech.antoniosgarbi.gestorpeixaria.repository.SupplierRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.SupplierServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FornecedorServiceTest {
    @Mock
    SupplierRepository fornecedorRepository;
    @InjectMocks
    SupplierServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um FornecedorDTO com documento não cadastrado")
    void cadastrar0() {
        Supplier fornecedorEsperado = Builder.supllier1();
        when(fornecedorRepository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(fornecedorRepository.save(any(Supplier.class))).thenReturn(fornecedorEsperado);

        long idResposta = underTest.register(Builder.supllierDTO1());

        assertEquals(fornecedorEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um FornecedorDTO com documento já cadastrado no sistema")
    void cadastrar1() {
        SupplierDTO dto = Builder.supllierDTO1();

        when(fornecedorRepository.findByDocument(anyString())).thenReturn(Optional.of(Builder.supllier1()));

        var exception =
                assertThrows(PersonException.class, () -> underTest.register(dto));

        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PersonException.class, () -> underTest.update(Builder.supllierDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar FornecedorDTO ao receber um FornecedorDTO válido")
    void atualizarCadastro1() {
        Supplier esperado = Builder.supllier1();
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setName(nomeNovo);
        when(fornecedorRepository.save(any(Supplier.class))).thenReturn(esperado);

        SupplierDTO argument = Builder.supllierDTO1();
        argument.setName(nomeNovo);
        SupplierDTO resposta = underTest.update(argument);

        assertEquals(nomeNovo, resposta.getName());
    }


    @Test
    @DisplayName("Deve retornar um FornecedorDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        SupplierDTO fornecedorDTO = Builder.supllierDTO1();
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(new Supplier(fornecedorDTO)));
        SupplierDTO resposta = underTest.findById(1L);

        assertNotNull(resposta);
        assertEquals(fornecedorDTO.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PersonException.class, () -> underTest.findById(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<FornecedorDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Supplier> fornecedorList = List.of(Builder.supllier1(), Builder.supllier1(), Builder.supllier1());
        Page<Supplier> fornecedorPage = new PageImpl<>(fornecedorList);
        when(fornecedorRepository.findAll(any(Pageable.class))).thenReturn(fornecedorPage);

        Page<SupplierDTO> pageResposta = underTest.findAll(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(fornecedorPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(fornecedorPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PersonException.class, () -> underTest.delete(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        Supplier fornecedor = Builder.supllier1();
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));

        assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}