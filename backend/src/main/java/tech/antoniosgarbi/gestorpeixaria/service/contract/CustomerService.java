package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CustomerDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CustomerSpecBody;

public interface CustomerService {
    long register(CustomerDTO cliente);
    CustomerDTO update(CustomerDTO cliente);
    CustomerDTO findById(long id);
    Page<CustomerDTO> findAll(Pageable pageable);
    Page<CustomerDTO> findAll(CustomerSpecBody specBody, Pageable pageable);
    void delete(long id);


}
