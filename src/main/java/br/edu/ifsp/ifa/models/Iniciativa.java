package br.edu.ifsp.ifa.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_INICIATIVA")
public class Iniciativa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 🔹 Agora é inteiro sequencial
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    // 🔹 Cada iniciativa tem 1 responsável
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    public Iniciativa() {}

    public Iniciativa(Long id, String titulo, String descricao, Responsavel responsavel) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavel = responsavel;
    }

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

    public Responsavel getResponsavel() { 
        return responsavel; 
    }
    public void setResponsavel(Responsavel responsavel) { 
        this.responsavel = responsavel; 
    }
}

