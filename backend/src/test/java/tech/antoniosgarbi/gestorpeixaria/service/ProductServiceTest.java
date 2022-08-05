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
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ProductSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.ProductException;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.repository.ProductRepository;
import tech.antoniosgarbi.gestorpeixaria.service.impl.ProductServiceImpl;
import tech.antoniosgarbi.gestorpeixaria.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl underTest;

    @Test
    @DisplayName("Should save and return Long when receives ProductDTO")
    void register0() {
        Product expected = Builder.productUnity1();
        when(productRepository.save(any(Product.class))).thenReturn(expected);

        long reponse = underTest.register(Builder.productUnityDTO1());

        assertEquals(expected.getId(), reponse);
    }

    @Test
    @DisplayName("Throws ProductException when updating an object that doesn't exist")
    void update0() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(ProductException.class, () -> underTest.update(Builder.productUnityDTO1()));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should return ProductDTO when updating valid ProductDTO")
    void update1() {
        Product expected = Builder.productUnity1();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        String newName = "new name";
        expected.setName(newName);
        when(productRepository.save(any(Product.class))).thenReturn(expected);

        ProductDTO argument = Builder.productUnityDTO1();
        argument.setName(newName);
        ProductDTO response = underTest.update(argument);

        assertEquals(newName, response.getName());
    }

    @Test
    @DisplayName("Should return ProductDTO when receiving a valid id")
    void findById0() {
        ProductDTO productDTO = Builder.productUnityDTO1();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product(productDTO)));
        ProductDTO response = underTest.findById(1L);

        assertNotNull(response);
        assertEquals(productDTO.getId(), response.getId());
    }

    @Test
    @DisplayName("Throws ProductException when id doesn't exist")
    void findById1() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(ProductException.class, () -> underTest.findById(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return Page<ProductDTO> when receives Pageable")
    void findAll0() {
        List<Product> productList = List.of(Builder.productUnity1(), Builder.productUnity1(), Builder.productUnity1());
        Page<Product> productPage = new PageImpl<>(productList);
        when(productRepository.findAll(any(Pageable.class))).thenReturn(productPage);

        Page<ProductDTO> response = underTest.findAll(Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(productPage.getTotalElements(), response.getTotalElements());
        assertEquals(productPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should return Page<ProductDTO> when receives ProductSpecBody and Pageable")
    void findAll1() {
        List<Product> productList = List.of(Builder.productUnity1(), Builder.productUnity1(), Builder.productUnity1());
        Page<Product> productPage = new PageImpl<>(productList);
        when(productRepository.findAll(any(ProductSpecification.class),any(Pageable.class))).thenReturn(productPage);

        Page<ProductDTO> response = underTest.findAll(new ProductSpecBody(), Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(productPage.getTotalElements(), response.getTotalElements());
        assertEquals(productPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Throws ProductException when deleting id that doesn't exist")
    void delete0() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(ProductException.class, () -> underTest.delete(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should update Product with excluded true when receives id that exists")
    void delete1() {
        Product expected = Builder.productUnity1();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}