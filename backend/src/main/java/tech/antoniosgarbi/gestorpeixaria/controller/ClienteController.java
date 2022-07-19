package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ClienteDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FuncionarioDTO;
import tech.antoniosgarbi.gestorpeixaria.service.contract.FuncionarioService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastro(@PathVariable Long id) {
        clienteService.apagarCadastro(id);
        return ResponseEntity.accepted().body(null);
    }

}