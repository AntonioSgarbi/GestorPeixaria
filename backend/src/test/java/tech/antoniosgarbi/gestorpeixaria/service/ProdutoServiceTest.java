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
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProdutoDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.ProdutoException;
import tech.antoniosgarbi.gestorpeixaria.model.Produto;
import tech.antoniosgarbi.gestorpeixaria.repository.ProdutoRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.ProdutoServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoServiceTest {
    @Mock
    ProdutoRepository produtoRepository;
    @InjectMocks
    ProdutoServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um FornecedorDTO com documento não cadastrado")
    void cadastrar0() {
        Produto produtoEsperado = Builder.produto1();
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoEsperado);

        long idResposta = underTest.cadastrar(Builder.produtoDTO1());

        assertEquals(produtoEsperado.getId(), idResposta);
    }

//    @Test
//    @DisplayName("Deve lançar PessoaException ao receber um FornecedorDTO com documento já cadastrado no sistema")
//    void cadastrar1() {
////        when(produtoRepository.findByDocumento(anyString())).thenReturn(Optional.of(Builder.funcionario1()));
//
//        var exception =
//                assertThrows(PessoaException.class, () -> underTest.cadastrar(Builder.produtoDTO1()));
//
//        String mensagemEsperada = "O documento informado já possui cadastro no sistema!";
//        assertEquals(mensagemEsperada, exception.getMessage());
//    }

    @Test
    @DisplayName("Deve retornar um FuncionarioDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        ProdutoDTO produtoDTO = Builder.produtoDTO1();
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(new Produto(produtoDTO)));
        ProdutoDTO resposta = underTest.encontrarCadastro(1L);

        assertNotNull(resposta);
        assertEquals(produtoDTO.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar ProdutoException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(ProdutoException.class, () -> underTest.encontrarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<FornecedorDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Produto> produtoList = List.of(Builder.produto1(), Builder.produto1(), Builder.produto1());
        Page<Produto> produtoPage = new PageImpl<>(produtoList);
        when(produtoRepository.findAll(any(Pageable.class))).thenReturn(produtoPage);

        Page<ProdutoDTO> pageResposta = underTest.encontrarTodos(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(produtoPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(produtoPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber id inválido")
    void apagarCadastro0() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(ProdutoException.class, () -> underTest.apagarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

}