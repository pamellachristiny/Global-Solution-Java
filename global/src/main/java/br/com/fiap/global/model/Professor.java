package br.com.fiap.global.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PROFESSOR")
public class Professor extends PanacheEntity {

    private String nome;
    private String email;
    private String especialidade; // Ex: Java, Mobile, Cloud

    public Professor() {}

    public Professor(String nome, String email, String especialidade) {
        this.nome = nome;
        this.email = email;
        this.especialidade = especialidade;
    }

    // --- Getters e Setters ---

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}