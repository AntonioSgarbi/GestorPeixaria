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
    @DisplayName("Deve retornar um Long ao receber um ProdutoDTO com documento não cadastrado")
    void cadastrar0() {
        Produto produtoEsperado = Builder.produtoUnidade1();
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoEsperado);

        long idResposta = underTest.cadastrar(Builder.produtoUnidadeDTO1());

        assertEquals(produtoEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(ProdutoException.class, () -> underTest.atualizarCadastro(Builder.produtoUnidadeDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar ClienteDTO ao receber um ClienteDTO válido")
    void atualizarCadastro1() {
        Produto esperado = Builder.produtoUnidade1();
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setNome(nomeNovo);
        when(produtoRepository.save(any(Produto.class))).thenReturn(esperado);

        ProdutoDTO argument = Builder.produtoUnidadeDTO1();
        argument.setNome(nomeNovo);
        ProdutoDTO resposta = underTest.atualizarCadastro(argument);

        assertEquals(nomeNovo, resposta.getNome());
    }

    @Test
    @DisplayName("Deve retornar um ProdutoDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        ProdutoDTO produtoDTO = Builder.produtoUnidadeDTO1();
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
    @DisplayName("Deve retornar uma Page<ProdutoDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Produto> produtoList = List.of(Builder.produtoUnidade1(), Builder.produtoUnidade1(), Builder.produtoUnidade1());
        Page<Produto> produtoPage = new PageImpl<>(produtoList);
        when(produtoRepository.findAll(any(Pageable.class))).thenReturn(produtoPage);

        Page<ProdutoDTO> pageResposta = underTest.encontrarTodos(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(produtoPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(produtoPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar ProdutoException ao receber id inválido")
    void apagarCadastro0() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(ProdutoException.class, () -> underTest.apagarCadastro(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        Produto esperado = Builder.produtoUnidade1();
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        Assertions.assertThatCode(() -> underTest.apagarCadastro(1L))
                .doesNotThrowAnyException();
    }

}