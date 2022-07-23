package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleService;

@RestController
@RequestMapping("/venda")
public class VendaController {
    private final SaleService vendaService;

    public VendaController(SaleService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Long> registrarVenda(@RequestBody SaleDTO cadastroBody){
        return ResponseEntity.ok(vendaService.register(cadastroBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(vendaService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        vendaService.delete(id);
        return ResponseEntity.accepted().body(null);
    }

}