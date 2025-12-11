// package br.edu.ifsp.ifa.models;

// import jakarta.persistence.*;
// import java.io.Serializable;

// @Entity
// @Table(name = "TB_RESPONSAVEL")
// public class Responsavel implements Serializable {
//     private static final long serialVersionUID = 1L;

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String nome;

//     @Column(nullable = false, unique = true)
//     private String email;

//     public Responsavel() {}

//     public Responsavel(Long id, String nome, String email) {
//         this.id = id;
//         this.nome = nome;
//         this.email = email;
//     }

//     public Long getId() { 
//         return id; 
//     }
//     public void setId(Long id) { 
//         this.id = id; 
//     }

//     public String getNome() { 
//         return nome; 
//     }
//     public void setNome(String nome) { 
//         this.nome = nome; 
//     }

//     public String getEmail() { 
//         return email; 
//     }
//     public void setEmail(String email) { 
//         this.email = email; 
//     }
// }


package br.edu.ifsp.ifa.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TB_RESPONSAVEL")
public class Responsavel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    // 🔹 Mapeamento reverso (opcional, mas recomendado)
    @OneToMany(mappedBy = "responsavel")
    private List<Iniciativa> iniciativas;

    public Responsavel() {}

    public Responsavel(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Iniciativa> getIniciativas() { return iniciativas; }
    public void setIniciativas(List<Iniciativa> iniciativas) { this.iniciativas = iniciativas; }
}
