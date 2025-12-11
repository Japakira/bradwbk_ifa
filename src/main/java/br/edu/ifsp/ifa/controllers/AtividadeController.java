// package br.edu.ifsp.ifa.controllers;

// import br.edu.ifsp.ifa.dtos.AtividadeDTO;
// import br.edu.ifsp.ifa.models.Atividade;
// import br.edu.ifsp.ifa.models.Iniciativa;
// import br.edu.ifsp.ifa.models.Responsavel;
// import br.edu.ifsp.ifa.services.AtividadeService;
// import br.edu.ifsp.ifa.services.IniciativaService;
// import br.edu.ifsp.ifa.services.ResponsavelService;
// import jakarta.validation.Valid;
// import java.net.URI;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/atividade")
// public class AtividadeController {

//     private final AtividadeService atividadeService;
//     private final IniciativaService iniciativaService;
//     private final ResponsavelService responsavelService;

//     public AtividadeController(AtividadeService atividadeService,
//                                IniciativaService iniciativaService,
//                                ResponsavelService responsavelService) {
//         this.atividadeService = atividadeService;
//         this.iniciativaService = iniciativaService;
//         this.responsavelService = responsavelService;
//     }

//     // -------------------------------------------------------------------------
//     // LISTAR TODAS
//     // -------------------------------------------------------------------------
//     @GetMapping
//     public ResponseEntity<List<AtividadeDTO>> listar() {
//         List<AtividadeDTO> dtos = atividadeService.listar().stream()
//                 .map(AtividadeDTO::fromEntity)
//                 .collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     // -------------------------------------------------------------------------
//     // LISTAR POR INICIATIVA
//     // -------------------------------------------------------------------------
//     @GetMapping("/iniciativa/{idIniciativa}")
//     public ResponseEntity<List<AtividadeDTO>> listarPorIniciativa(@PathVariable Iniciativa idIniciativa) {
//         List<AtividadeDTO> dtos = atividadeService.listarPorIniciativa(idIniciativa)
//                 .stream()
//                 .map(AtividadeDTO::fromEntity)
//                 .collect(Collectors.toList());

//         return ResponseEntity.ok(dtos);
//     }

//     // -------------------------------------------------------------------------
//     // CRIAR
//     // -------------------------------------------------------------------------
//     @PostMapping
//     public ResponseEntity<AtividadeDTO> criar(@Valid @RequestBody AtividadeDTO dto) {

//         Iniciativa ini = iniciativaService.buscarPorId(dto.getIniciativaId())
//                 .orElseThrow(() -> new IllegalArgumentException("Iniciativa não encontrada"));

//         Responsavel resp = responsavelService.buscarPorId(dto.getResponsavelId())
//                 .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));

//         Atividade novo = dto.toEntity(ini, resp);
//         Atividade salvo = atividadeService.criar(novo);

//         return ResponseEntity.created(URI.create("/atividade/" + salvo.getId()))
//                 .body(AtividadeDTO.fromEntity(salvo));
//     }

//     // -------------------------------------------------------------------------
//     // BUSCAR POR ID
//     // -------------------------------------------------------------------------
//     @GetMapping("/{id}")
//     public ResponseEntity<AtividadeDTO> buscar(@PathVariable UUID id) {
//         return atividadeService.buscarPorId(id)
//                 .map(AtividadeDTO::fromEntity)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // -------------------------------------------------------------------------
//     // ATUALIZAR
//     // -------------------------------------------------------------------------
//     @PutMapping("/{id}")
//     public ResponseEntity<AtividadeDTO> atualizar(@PathVariable UUID id,
//                                                   @Valid @RequestBody AtividadeDTO dto) {

//         Iniciativa ini = iniciativaService.buscarPorId(dto.getIniciativaId())
//                 .orElseThrow(() -> new IllegalArgumentException("Iniciativa não encontrada"));

//         Responsavel resp = responsavelService.buscarPorId(dto.getResponsavelId())
//                 .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));

//         Atividade data = dto.toEntity(ini, resp);

//         return atividadeService.atualizar(id, data)
//                 .map(AtividadeDTO::fromEntity)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // -------------------------------------------------------------------------
//     // DELETAR
//     // -------------------------------------------------------------------------
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deletar(@PathVariable UUID id) {
//         atividadeService.deletar(id);
//         return ResponseEntity.noContent().build();
//     }
// }

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
