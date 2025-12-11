package br.edu.ifsp.ifa.dtos;

import br.edu.ifsp.ifa.models.Responsavel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ResponsavelDTO {

    private Long id;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotBlank(message = "email é obrigatório")
    @Email(message = "email inválido")
    private String email;

    public ResponsavelDTO() {}

    public ResponsavelDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // -------------------------
    // GETTERS E SETTERS
    // -------------------------
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    // -------------------------
    // CONVERSÃO PARA ENTIDADE
    // -------------------------
    public Responsavel toEntity() {
        Responsavel entity = new Responsavel();
        entity.setId(this.id);
        entity.setNome(this.nome);
        entity.setEmail(this.email);
        return entity;
    }

    // -------------------------
    // CONVERSÃO A PARTIR DE ENTIDADE
    // -------------------------
    public static ResponsavelDTO fromEntity(Responsavel r) {
        if (r == null) return null;
        return new ResponsavelDTO(
            r.getId(),
            r.getNome(),
            r.getEmail()
        );
    }
}
