package br.edu.ifsp.ifa.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = false)
    private String status;

    @Column(name = "data", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime data;

    @Column(name = "iniciativa_id", nullable = false)
    private Long iniciativaId;

    @Column(name = "responsavel_id", nullable = true)
    private Long responsavelId;

    public Atividade() {
        this.data = LocalDateTime.now(); // valor padrão
    }

    public Atividade(Long id, String titulo, String descricao, String status, 
                     LocalDateTime data, Long iniciativaId, Long responsavelId) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.data = (data != null ? data : LocalDateTime.now());
        this.iniciativaId = iniciativaId;
        this.responsavelId = responsavelId;
    }

    // GETTERS E SETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Long getIniciativaId() {
        return iniciativaId;
    }
    public void setIniciativaId(Long iniciativaId) {
        this.iniciativaId = iniciativaId;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }
    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }
}
