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
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.ProductException;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.repository.ProductRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoServiceTest {
    @Mock
    ProductRepository produtoRepository;
    @InjectMocks
    ProductServiceImpl underTest;

    @Test
    @DisplayName("Deve retornar um Long ao receber um ProdutoDTO com documento não cadastrado")
    void cadastrar0() {
        Product produtoEsperado = Builder.productUnity1();
        when(produtoRepository.save(any(Product.class))).thenReturn(produtoEsperado);

        long idResposta = underTest.register(Builder.productUnityDTO1());

        assertEquals(produtoEsperado.getId(), idResposta);
    }

    @Test
    @DisplayName("Deve lançar PessoaException ao receber um objeto que não existe no banco")
    void atualizarCadastro0() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(ProductException.class, () -> underTest.update(Builder.productUnityDTO1()));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar ClienteDTO ao receber um ClienteDTO válido")
    void atualizarCadastro1() {
        Product esperado = Builder.productUnity1();
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        String nomeNovo = "Novo nome";
        esperado.setName(nomeNovo);
        when(produtoRepository.save(any(Product.class))).thenReturn(esperado);

        ProductDTO argument = Builder.productUnityDTO1();
        argument.setName(nomeNovo);
        ProductDTO resposta = underTest.update(argument);

        assertEquals(nomeNovo, resposta.getName());
    }

    @Test
    @DisplayName("Deve retornar um ProdutoDTO ao receber um id cadastrado")
    void encontrarCadastro0() {
        ProductDTO produtoDTO = Builder.productUnityDTO1();
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(new Product(produtoDTO)));
        ProductDTO resposta = underTest.findById(1L);

        assertNotNull(resposta);
        assertEquals(produtoDTO.getId(), resposta.getId());
    }

    @Test
    @DisplayName("Deve lançar ProdutoException ao receber id não cadastrado no sistema")
    void encontrarCadastro1() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(ProductException.class, () -> underTest.findById(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma Page<ProdutoDTO> ao receber Pageable")
    void encontrarTodos0() {
        List<Product> produtoList = List.of(Builder.productUnity1(), Builder.productUnity1(), Builder.productUnity1());
        Page<Product> produtoPage = new PageImpl<>(produtoList);
        when(produtoRepository.findAll(any(Pageable.class))).thenReturn(produtoPage);

        Page<ProductDTO> pageResposta = underTest.findAll(Pageable.unpaged());

        assertNotNull(pageResposta.getContent());
        assertEquals(produtoPage.getTotalElements(), pageResposta.getTotalElements());
        assertEquals(produtoPage.getContent().get(0).getId(), pageResposta.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar ProdutoException ao receber id inválido")
    void apagarCadastro0() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(ProductException.class, () -> underTest.delete(1L));

        String mensagemEsperada = "Cadastro não encontrado";
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Deve não lançar uma exceção ao receber id existente e mudar objeto para excluído")
    void apagarCadastro1() {
        Product esperado = Builder.productUnity1();
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(esperado));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}