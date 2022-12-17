package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CustomerDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CustomerSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Customer;
import tech.antoniosgarbi.gestorpeixaria.repository.CustomerRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.CustomerService;
import tech.antoniosgarbi.gestorpeixaria.specification.CustomerSpecification;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public long register(CustomerDTO customerDTO) {
        Optional<Customer> optional = this.customerRepository.findCustomerByDocument(customerDTO.getDocument());
        if (optional.isPresent()) throw new PersonException("O documento informado já possui cadastro no sistema!");
        Customer model = new Customer(customerDTO);
        model = this.customerRepository.save(model);
        return model.getId();
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Customer model = this.findModel(customerDTO.getId());
        Util.copyPropertiesIgnoreNull(customerDTO, model);
        this.customerRepository.save(model);
        return customerDTO;
    }

    @Override
    public CustomerDTO findById(long id) {
        return new CustomerDTO(this.findModel(id));
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable).map(CustomerDTO::new);
    }

    @Override
    public Page<CustomerDTO> findAll(CustomerSpecBody specBody, Pageable pageable) {
        Specification<Customer> specification = new CustomerSpecification(specBody);
        return this.customerRepository.findAll(specification, pageable).map(CustomerDTO::new);
    }

    @Override
    public void delete(long id) {
        CustomerDTO dto = this.findById(id);
        Customer model = new Customer(dto);
        model.setExcluded(true);
        this.customerRepository.save(model);
    }

    public Customer findModel(long id) {
        Optional<Customer> optional = this.customerRepository.findById(id);
        if (optional.isEmpty()) throw new PersonException("Cadastro não encontrado");
        return optional.get();
    }

}