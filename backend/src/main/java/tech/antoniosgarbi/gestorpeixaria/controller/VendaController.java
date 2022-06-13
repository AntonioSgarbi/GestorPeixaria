package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Long> registrarVenda(@RequestBody VendaDTO cadastroBody){
        return ResponseEntity.ok(vendaService.registrarVenda(cadastroBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<VendaDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(vendaService.encontrarTodos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        vendaService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }

}