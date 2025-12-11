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
