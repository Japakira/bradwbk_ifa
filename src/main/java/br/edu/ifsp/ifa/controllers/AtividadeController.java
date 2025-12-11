package br.edu.ifsp.ifa.controllers;

import br.edu.ifsp.ifa.dtos.AtividadeDTO;
import br.edu.ifsp.ifa.models.Atividade;
import br.edu.ifsp.ifa.services.AtividadeService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    // -------------------------------------------------------------------------
    // LISTAR TODAS
    // -------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<AtividadeDTO>> listar() {
        List<AtividadeDTO> dtos = atividadeService.listar()
                .stream()
                .map(AtividadeDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // -------------------------------------------------------------------------
    // LISTAR POR INICIATIVA
    // -------------------------------------------------------------------------
    @GetMapping("/iniciativa/{iniciativaId}")
    public ResponseEntity<List<AtividadeDTO>> listarPorIniciativa(@PathVariable Long iniciativaId) {

        List<AtividadeDTO> dtos = atividadeService.listarPorIniciativa(iniciativaId)
                .stream()
                .map(AtividadeDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // -------------------------------------------------------------------------
    // CRIAR
    // -------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<AtividadeDTO> criar(@Valid @RequestBody AtividadeDTO dto) {

        Atividade atividade = dto.toEntity(); // agora baseado na nova entidade
        Atividade salvo = atividadeService.criar(atividade);

        return ResponseEntity
                .created(URI.create("/atividade/" + salvo.getId()))
                .body(AtividadeDTO.fromEntity(salvo));
    }

    // -------------------------------------------------------------------------
    // BUSCAR POR ID
    // -------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<AtividadeDTO> buscar(@PathVariable Long id) {
        return atividadeService.buscarPorId(id)
                .map(AtividadeDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------------------------
    // ATUALIZAR
    // -------------------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<AtividadeDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtividadeDTO dto) {

        Atividade atividadeAtualizada = dto.toEntity();

        return atividadeService.atualizar(id, atividadeAtualizada)
                .map(AtividadeDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------------------------
    // DELETAR
    // -------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
