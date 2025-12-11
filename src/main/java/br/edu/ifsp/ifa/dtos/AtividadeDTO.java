// package br.edu.ifsp.ifa.dtos;

// import br.edu.ifsp.ifa.models.Atividade;
// import br.edu.ifsp.ifa.models.Iniciativa;
// import br.edu.ifsp.ifa.models.Responsavel;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;

// import java.util.UUID;

// public class AtividadeDTO {

//     private UUID id;

//     @NotBlank(message = "titulo é obrigatório")
//     private String titulo;

//     @NotBlank(message = "status é obrigatório")
//     private String status;

//     @NotNull(message = "iniciativa.id é obrigatório")
//     private UUID iniciativaId;

//     @NotNull(message = "responsavel.id é obrigatório")
//     private UUID responsavelId;

//     public AtividadeDTO() {}

//     public AtividadeDTO(UUID id, String titulo, String status, UUID iniciativaId, UUID responsavelId) {
//         this.id = id;
//         this.titulo = titulo;
//         this.status = status;
//         this.iniciativaId = iniciativaId;
//         this.responsavelId = responsavelId;
//     }

//     public UUID getId() { return id; }
//     public void setId(UUID id) { this.id = id; }

//     public String getTitulo() { return titulo; }
//     public void setTitulo(String titulo) { this.titulo = titulo; }

//     public String getStatus() { return status; }
//     public void setStatus(String status) { this.status = status; }

//     public UUID getIniciativaId() { return iniciativaId; }
//     public void setIniciativaId(UUID iniciativaId) { this.iniciativaId = iniciativaId; }

//     public UUID getResponsavelId() { return responsavelId; }
//     public void setResponsavelId(UUID responsavelId) { this.responsavelId = responsavelId; }

//     public Atividade toEntity(Iniciativa iniciativa, Responsavel responsavel) {
//         Atividade a = new Atividade();
//         a.setId(this.id);
//         a.setTitulo(this.titulo);
//         a.setStatus(this.status);
//         a.setIniciativa(iniciativa);
//         a.setResponsavel(responsavel);
//         return a;
//     }

//     public static AtividadeDTO fromEntity(Atividade a) {
//         if (a == null) return null;
//         UUID iniId = a.getIniciativa() != null ? a.getIniciativa().getId() : null;
//         UUID respId = a.getResponsavel() != null ? a.getResponsavel().getId() : null;
//         return new AtividadeDTO(a.getId(), a.getTitulo(), a.getStatus(), iniId, respId);
//     }
// }

package br.edu.ifsp.ifa.dtos;

import br.edu.ifsp.ifa.models.Atividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AtividadeDTO {

    private Long id;

    @NotBlank(message = "titulo é obrigatório")
    private String titulo;

    private String descricao;

    @NotBlank(message = "status é obrigatório")
    private String status;

    private LocalDateTime data;

    @NotNull(message = "iniciativaId é obrigatório")
    private Long iniciativaId;

    private Long responsavelId;

    public AtividadeDTO() {}

    public AtividadeDTO(Long id, String titulo, String descricao, String status,
                        LocalDateTime data, Long iniciativaId, Long responsavelId) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.data = data;
        this.iniciativaId = iniciativaId;
        this.responsavelId = responsavelId;
    }

    // -------------------------------------------------------------------------
    // DTO → ENTITY
    // -------------------------------------------------------------------------
    public Atividade toEntity() {
        return new Atividade(
                this.id,
                this.titulo,
                this.descricao,
                this.status,
                this.data,
                this.iniciativaId,
                this.responsavelId
        );
    }

    // -------------------------------------------------------------------------
    // ENTITY → DTO
    // -------------------------------------------------------------------------
    public static AtividadeDTO fromEntity(Atividade a) {
        if (a == null) return null;

        return new AtividadeDTO(
                a.getId(),
                a.getTitulo(),
                a.getDescricao(),
                a.getStatus(),
                a.getData(),
                a.getIniciativaId(),
                a.getResponsavelId()
        );
    }

    // -------------------------------------------------------------------------
    // GETTERS E SETTERS
    // -------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Long getIniciativaId() { return iniciativaId; }
    public void setIniciativaId(Long iniciativaId) { this.iniciativaId = iniciativaId; }

    public Long getResponsavelId() { return responsavelId; }
    public void setResponsavelId(Long responsavelId) { this.responsavelId = responsavelId; }
}
