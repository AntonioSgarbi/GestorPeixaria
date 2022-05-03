package com.antoniosgarbi.gestorpeixaria.controller;

import com.antoniosgarbi.gestorpeixaria.dto.PessoaDTO;
import com.antoniosgarbi.gestorpeixaria.service.PessoaServiceContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaServiceContract pessoaService;

    public PessoaController(PessoaServiceContract pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@RequestBody PessoaDTO pessoa){
        return ResponseEntity.ok(pessoaService.cadastrar(pessoa));
    }
    @PutMapping
    public ResponseEntity<Void> atualizarCadastro(@RequestBody PessoaDTO pessoa){
        this.pessoaService.atualizarCadastro(pessoa);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(pessoaService.encontrarTodos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        pessoaService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }


}
