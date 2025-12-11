// package br.edu.ifsp.ifa.dtos;

// import br.edu.ifsp.ifa.models.Iniciativa;
// import br.edu.ifsp.ifa.models.Responsavel;
// import jakarta.validation.constraints.NotBlank;

// public class IniciativaDTO {

//     private Long id;

//     @NotBlank(message = "titulo é obrigatório")
//     private String titulo;

//     @NotBlank(message = "descricao é obrigatória")
//     private String descricao;

//     private Long responsavelId;

//     public IniciativaDTO() {}

//     public IniciativaDTO(Long id, String titulo, String descricao, Long responsavelId) {
//         this.id = id;
//         this.titulo = titulo;
//         this.descricao = descricao;
//         this.responsavelId = responsavelId;
//     }

//     public Long getId() { 
//         return id; 
//     }
//     public void setId(Long id) { 
//         this.id = id; 
//     }

//     public String getTitulo() { 
//         return titulo; 
//     }
//     public void setTitulo(String titulo) { 
//         this.titulo = titulo; 
//     }

//     public String getDescricao() { 
//         return descricao; 
//     }
//     public void setDescricao(String descricao) { 
//         this.descricao = descricao; 
//     }

//     public Long getResponsavelId() { 
//         return responsavelId; 
//     }
//     public void setResponsavelId(Long responsavelId) { 
//         this.responsavelId = responsavelId; 
//     }

//     // Converte DTO -> Entity
//     public Iniciativa toEntity(Responsavel responsavel) {
//         return new Iniciativa(this.id, this.titulo, this.descricao, responsavel);
//     }

//     // Converte Entity -> DTO
//     public static IniciativaDTO fromEntity(Iniciativa iniciativa) {

//         Long responsavelId = (iniciativa.getResponsavel() != null)
//                 ? iniciativa.getResponsavel().getId()
//                 : null;

//         return new IniciativaDTO(
//                 iniciativa.getId(),
//                 iniciativa.getTitulo(),
//                 iniciativa.getDescricao(),
//                 responsavelId
//         );
//     }
// }

package br.edu.ifsp.ifa.dtos;

import br.edu.ifsp.ifa.models.Iniciativa;
import br.edu.ifsp.ifa.models.Responsavel;
import jakarta.validation.constraints.NotBlank;

public class IniciativaDTO {

    private Long id;

    @NotBlank(message = "titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "descricao é obrigatória")
    private String descricao;

    private Long responsavelId;

    public IniciativaDTO() {}

    public IniciativaDTO(Long id, String titulo, String descricao, Long responsavelId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavelId = responsavelId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getResponsavelId() { return responsavelId; }
    public void setResponsavelId(Long responsavelId) { this.responsavelId = responsavelId; }

    // DTO -> Entity
    public Iniciativa toEntity(Responsavel responsavel) {
        return new Iniciativa(
                this.id,
                this.titulo,
                this.descricao,
                responsavel   // pode ser null
        );
    }

    // Entity -> DTO
    public static IniciativaDTO fromEntity(Iniciativa iniciativa) {

        Long responsavelId = (iniciativa.getResponsavel() != null)
                ? iniciativa.getResponsavel().getId()
                : null;

        return new IniciativaDTO(
                iniciativa.getId(),
                iniciativa.getTitulo(),
                iniciativa.getDescricao(),
                responsavelId
        );
    }
}
