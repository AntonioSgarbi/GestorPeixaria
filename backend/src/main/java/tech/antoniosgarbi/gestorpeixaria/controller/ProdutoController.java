package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProdutoDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@RequestBody ProdutoDTO cadastroBody){
        return ResponseEntity.ok(produtoService.cadastrar(cadastroBody));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCadastro(@RequestBody ProdutoDTO cadastroBody){
        this.produtoService.atualizarCadastro(cadastroBody);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(produtoService.encontrarTodos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        produtoService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }

}