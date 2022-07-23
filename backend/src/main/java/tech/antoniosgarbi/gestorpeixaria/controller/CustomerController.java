package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CustomerDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CustomerSpecBody;
import tech.antoniosgarbi.gestorpeixaria.service.contract.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.register(customerDTO));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CustomerDTO customerDTO){
        this.customerService.update(customerDTO);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(customerService.findAll(pageable));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<CustomerDTO>> findAll(@RequestBody CustomerSpecBody customerSpecBody, Pageable pageable) {
        return ResponseEntity.ok(customerService.findAll(customerSpecBody, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.accepted().body(null);
    }

}