package br.com.fiap.biblioteca.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario extends PanacheEntity {

    private String nome;
    private String email;
    private String senha;
    private String plano;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String plano) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.plano = plano;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }
}