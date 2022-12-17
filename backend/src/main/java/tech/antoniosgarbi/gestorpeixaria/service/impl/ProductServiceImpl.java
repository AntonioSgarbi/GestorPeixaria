package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ProductSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.ProductException;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.repository.ProductRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ProductService;
import tech.antoniosgarbi.gestorpeixaria.specification.ProductSpecification;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long register(ProductDTO productDTO) {
        Product model = new Product(productDTO);
        model = this.productRepository.save(model);
        return model.getId();
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        Product model = this.findModel(productDTO.getId());
        Util.copyPropertiesIgnoreNull(productDTO, model);
        this.productRepository.save(model);
        return productDTO;
    }

    @Override
    public ProductDTO findById(Long id) {
        return new ProductDTO(this.findModel(id));
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable).map(ProductDTO::new);
    }

    @Override
    public Page<ProductDTO> findAll(ProductSpecBody productSpecBody, Pageable pageable) {
        Specification<Product> specification = new ProductSpecification(productSpecBody);
        return this.productRepository.findAll(specification, pageable).map(ProductDTO::new);
    }

    @Override
    public void delete(long id) {
        ProductDTO dto = this.findById(id);
        Product model = new Product(dto);
        model.setExcluded(true);
        this.productRepository.save(model);
    }

    public Product findModel(long id) {
        Optional<Product> optional = this.productRepository.findById(id);
        if (optional.isEmpty()) throw new ProductException("Cadastro não encontrado");
        return optional.get();
    }

}