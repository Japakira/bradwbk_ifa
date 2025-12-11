package br.edu.ifsp.ifa.controllers;

import br.edu.ifsp.ifa.dtos.ResponsavelDTO;
import br.edu.ifsp.ifa.services.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    private final ResponsavelService service;

    public ResponsavelController(ResponsavelService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponsavelDTO> criar(@Valid @RequestBody ResponsavelDTO dto) {
        ResponsavelDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/responsavel/" + criado.getId()))
                .body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ResponsavelDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idResponsavel}/associar-iniciativa/{idIniciativa}")
public ResponseEntity<String> associarResponsavelIniciativa(
        @PathVariable Long idResponsavel,
        @PathVariable Long idIniciativa) {

    service.associarResponsavelIniciativa(idResponsavel, idIniciativa);

    return ResponseEntity.ok("Responsável associado à iniciativa com sucesso!");
    }

}
