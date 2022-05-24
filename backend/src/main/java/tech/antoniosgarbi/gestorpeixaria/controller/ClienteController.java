package tech.antoniosgarbi.gestorpeixaria.controller;

import tech.antoniosgarbi.gestorpeixaria.dto.ClienteDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class ClienteController {
    private final ClienteService pessoaService;

    public ClienteController(ClienteService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@RequestBody ClienteDTO cadastroBody){
        return ResponseEntity.ok(pessoaService.cadastrar(cadastroBody));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCadastro(@RequestBody ClienteDTO cadastroBody){
        this.pessoaService.atualizarCadastro(cadastroBody);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(pessoaService.encontrarTodos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        pessoaService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }


}
