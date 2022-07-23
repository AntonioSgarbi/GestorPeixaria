package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;
import tech.antoniosgarbi.gestorpeixaria.service.contract.CollaboratorService;

@RestController
@RequestMapping("/funcionario")
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody CollaboratorDTO collaboratorDTO){
        return ResponseEntity.ok(collaboratorService.register(collaboratorDTO));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CollaboratorDTO collaboratorDTO){
        this.collaboratorService.update(collaboratorDTO);
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(collaboratorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CollaboratorDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(collaboratorService.findAll(pageable));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<CollaboratorDTO>> findAll(@RequestBody CollaboratorSpecBody collaboratorSpecBody, Pageable pageable) {
        return ResponseEntity.ok(collaboratorService.findAll(collaboratorSpecBody, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        collaboratorService.delete(id);
        return ResponseEntity.accepted().body(null);
    }

}