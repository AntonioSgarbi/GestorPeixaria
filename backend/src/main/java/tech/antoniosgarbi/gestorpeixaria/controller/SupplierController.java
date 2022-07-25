package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SupplierSpecBody;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody SupplierDTO supplierDTO){
        return ResponseEntity.ok(supplierService.register(supplierDTO));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody SupplierDTO supplierDTO){
        this.supplierService.update(supplierDTO);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SupplierDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(supplierService.findAll(pageable));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<SupplierDTO>> findAll(@RequestBody SupplierSpecBody supplierSpecBody, Pageable pageable) {
        return ResponseEntity.ok(supplierService.findAll(supplierSpecBody, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
