package br.com.fiap.global.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "TB_CHALLENGE")
public class Challenge extends PanacheEntity {

    // NOVO CAMPO: Chave estrangeira para a tabela Curso
    @ManyToOne
    public Curso curso; // Panache permite campos públicos

    // Campos existentes
    private String nomeChallenge;
    private String descricaoChallenge;
    private LocalDate tempo;

    // ... (Construtores, Getters e Setters simplificados) ...

    public Challenge() {}

    public Challenge(String nomeChallenge, String descricaoChallenge, LocalDate tempo, Curso curso) {
        this.nomeChallenge = nomeChallenge;
        this.descricaoChallenge = descricaoChallenge;
        this.tempo = tempo;
        this.curso = curso;
    }

    // Getters e Setters
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getNomeChallenge() { return nomeChallenge; }
    public void setNomeChallenge(String nomeChallenge) { this.nomeChallenge = nomeChallenge; }

    public String getDescricaoChallenge() { return descricaoChallenge; }
    public void setDescricaoChallenge(String descricaoChallenge) { this.descricaoChallenge = descricaoChallenge; }

    public LocalDate getTempo() { return tempo; }
    public void setTempo(LocalDate tempo) { this.tempo = tempo; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}