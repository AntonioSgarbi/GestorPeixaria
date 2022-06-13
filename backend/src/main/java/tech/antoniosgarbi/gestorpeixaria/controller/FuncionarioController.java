package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@RequestBody FuncionarioDTO cadastroBody){
        return ResponseEntity.ok(funcionarioService.cadastrar(cadastroBody));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCadastro(@RequestBody FuncionarioDTO cadastroBody){
        this.funcionarioService.atualizarCadastro(cadastroBody);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(funcionarioService.encontrarTodos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        funcionarioService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }

}