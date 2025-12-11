// package br.edu.ifsp.ifa.controllers;

// import br.edu.ifsp.ifa.dtos.IniciativaDTO;
// import br.edu.ifsp.ifa.models.Iniciativa;
// import br.edu.ifsp.ifa.models.Responsavel;
// import br.edu.ifsp.ifa.services.IniciativaService;
// import br.edu.ifsp.ifa.services.ResponsavelService;
// import jakarta.validation.Valid;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.net.URI;
// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/iniciativa")
// public class IniciativaController {

//     private final IniciativaService iniciativaService;
//     private final ResponsavelService responsavelService;

//     public IniciativaController(IniciativaService iniciativaService,
//                                 ResponsavelService responsavelService) {
//         this.iniciativaService = iniciativaService;
//         this.responsavelService = responsavelService;
//     }

//     // -------------------------------------------------------------------------
//     // LISTAR
//     // -------------------------------------------------------------------------
//     @GetMapping
//     public ResponseEntity<List<IniciativaDTO>> listar() {
//         List<IniciativaDTO> dtos = iniciativaService.listar().stream()
//                 .map(IniciativaDTO::fromEntity)
//                 .collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     // -------------------------------------------------------------------------
//     // CRIAR
//     // -------------------------------------------------------------------------
//     @PostMapping
//     public ResponseEntity<IniciativaDTO> criar(@Valid @RequestBody IniciativaDTO dto) {

//         Responsavel responsavel = null;

//         if (dto.getResponsavelId() != null) {
//             responsavel = responsavelService.buscarPorId(dto.getResponsavelId());
//         }

//         Iniciativa created = iniciativaService.criar(dto.toEntity(responsavel));

//         return ResponseEntity.created(URI.create("/iniciativa/" + created.getId()))
//                 .body(IniciativaDTO.fromEntity(created));
//     }

//     // -------------------------------------------------------------------------
//     // BUSCAR
//     // -------------------------------------------------------------------------
//     @GetMapping("/{id}")
//     public ResponseEntity<IniciativaDTO> buscar(@PathVariable Long id) {
//         return iniciativaService.buscarPorId(id)
//                 .map(IniciativaDTO::fromEntity)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // -------------------------------------------------------------------------
//     // ATUALIZAR
//     // -------------------------------------------------------------------------
//     @PutMapping("/{id}")
//     public ResponseEntity<IniciativaDTO> atualizar(@PathVariable Long id,
//                                                    @Valid @RequestBody IniciativaDTO dto) {

//         Responsavel responsavel = null;

//         if (dto.getResponsavelId() != null) {
//             responsavel = responsavelService.buscarPorId(dto.getResponsavelId());
//         }

//         return iniciativaService.atualizar(id, dto.toEntity(responsavel))
//                 .map(IniciativaDTO::fromEntity)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // -------------------------------------------------------------------------
//     // DELETAR
//     // -------------------------------------------------------------------------
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deletar(@PathVariable Long id) {
//         iniciativaService.deletar(id);
//         return ResponseEntity.noContent().build();
//     }
// }


package br.edu.ifsp.ifa.controllers;

import br.edu.ifsp.ifa.dtos.IniciativaDTO;
import br.edu.ifsp.ifa.dtos.ResponsavelDTO;
import br.edu.ifsp.ifa.models.Iniciativa;
import br.edu.ifsp.ifa.models.Responsavel;
import br.edu.ifsp.ifa.services.IniciativaService;
import br.edu.ifsp.ifa.services.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/iniciativa")
public class IniciativaController {

    private final IniciativaService iniciativaService;
    private final ResponsavelService responsavelService;

    public IniciativaController(IniciativaService iniciativaService,
                                ResponsavelService responsavelService) {
        this.iniciativaService = iniciativaService;
        this.responsavelService = responsavelService;
    }

    // -------------------------------------------------------------------------
    // LISTAR
    // -------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<IniciativaDTO>> listar() {
        return ResponseEntity.ok(
                iniciativaService.listar()
                        .stream()
                        .map(IniciativaDTO::fromEntity)
                        .toList()
        );
    }

    // -------------------------------------------------------------------------
    // CRIAR
    // -------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<IniciativaDTO> criar(@Valid @RequestBody IniciativaDTO dto) {

        Responsavel responsavel = null;

        if (dto.getResponsavelId() != null) {
            ResponsavelDTO rDTO = responsavelService.buscarPorId(dto.getResponsavelId());
            responsavel = rDTO.toEntity();
        }

        Iniciativa created = iniciativaService.criar(dto.toEntity(responsavel));

        return ResponseEntity.created(URI.create("/iniciativa/" + created.getId()))
                .body(IniciativaDTO.fromEntity(created));
    }

    // -------------------------------------------------------------------------
    // BUSCAR POR ID
    // -------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<IniciativaDTO> buscar(@PathVariable Long id) {
        return iniciativaService.buscarPorId(id)
                .map(IniciativaDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------------------------
    // ATUALIZAR
    // -------------------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<IniciativaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody IniciativaDTO dto) {

        Responsavel responsavel = null;

        if (dto.getResponsavelId() != null) {
            ResponsavelDTO rDTO = responsavelService.buscarPorId(dto.getResponsavelId());
            responsavel = rDTO.toEntity();
        }

        return iniciativaService.atualizar(id, dto.toEntity(responsavel))
                .map(IniciativaDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------------------------
    // DELETAR
    // -------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        iniciativaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
