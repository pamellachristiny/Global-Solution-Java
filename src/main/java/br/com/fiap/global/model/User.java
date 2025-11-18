package br.com.fiap.global.model; // Pacote ajustado para consistência

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class User extends PanacheEntity {

    // Panache usa campos públicos para acesso simplificado, mas campos privados com getters/setters
    // como você usou são perfeitamente válidos.
    private String nome;
    private String email;
    private String senha;
    private String plano;
    private String nivel;

    public User() {}

    public User(String nome, String email, String senha, String plano, String nivel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.plano = plano;
        this.nivel = nivel;
    }

    // Nota: O método getId() de PanacheEntity é Long. Você pode usar Long ou id diretamente.
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getPlano() {return plano;}
    public void setPlano(String plano) {this.plano = plano;}
    public String getNivel() {return nivel;}
    public void setNivel(String nivel) {this.nivel = nivel;}
}