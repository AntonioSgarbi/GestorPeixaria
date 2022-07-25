package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody SaleDTO cadastroBody){
        return ResponseEntity.ok(saleService.register(cadastroBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(saleService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}