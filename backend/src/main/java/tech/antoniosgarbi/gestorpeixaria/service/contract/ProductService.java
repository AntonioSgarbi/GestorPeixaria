package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ProductSpecBody;

public interface ProductService {

    Long register(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO);

    ProductDTO findById(Long id);

    void delete(long id);

    Page<ProductDTO> findAll(Pageable pageable);

    Page<ProductDTO> findAll(ProductSpecBody specBody, Pageable pageable);
}
