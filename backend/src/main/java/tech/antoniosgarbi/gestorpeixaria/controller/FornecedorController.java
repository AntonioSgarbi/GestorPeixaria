package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FornecedorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SpecBodyFornecedor;
import tech.antoniosgarbi.gestorpeixaria.service.contract.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@RequestBody FornecedorDTO cadastroBody){
        return ResponseEntity.ok(fornecedorService.cadastrar(cadastroBody));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCadastro(@RequestBody FornecedorDTO cadastroBody){
        this.fornecedorService.atualizarCadastro(cadastroBody);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.encontrarTodos(pageable));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<FornecedorDTO>> encontrarTodos(@RequestBody SpecBodyFornecedor specBodyFornecedor, Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.encontrarTodos(specBodyFornecedor, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        fornecedorService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }


}
