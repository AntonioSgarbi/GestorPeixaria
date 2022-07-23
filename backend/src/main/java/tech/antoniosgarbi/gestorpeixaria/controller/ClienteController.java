package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ClienteDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SpecBodyCliente;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@RequestBody ClienteDTO cadastroBody){
        return ResponseEntity.ok(clienteService.cadastrar(cadastroBody));
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCadastro(@RequestBody ClienteDTO cadastroBody){
        this.clienteService.atualizarCadastro(cadastroBody);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> encontrarCadastro(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.encontrarCadastro(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> encontrarTodos(Pageable pageable) {
        return ResponseEntity.ok(clienteService.encontrarTodos(pageable));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<ClienteDTO>> encontrarTodos(@RequestBody SpecBodyCliente specBodyCliente, Pageable pageable) {
        return ResponseEntity.ok(clienteService.encontrarTodos(specBodyCliente, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        clienteService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }

}