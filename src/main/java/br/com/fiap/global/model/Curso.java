package br.com.fiap.global.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CURSO")
public class Curso extends PanacheEntity {

    private String nomeCurso;
    private String instrutor;

    // Relacionamento One-to-Many: Um curso tem muitos desafios
    // mappedBy="curso" indica o campo na entidade Challenge que mapeia essa relação.
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Challenge> desafios = new ArrayList<>();

    public Curso() {}

    public Curso(String nomeCurso, String instrutor) {
        this.nomeCurso = nomeCurso;
        this.instrutor = instrutor;
    }

    // Getters e Setters
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getNomeCurso() { return nomeCurso; }
    public void setNomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; }

    public String getInstrutor() { return instrutor; }
    public void setInstrutor(String instrutor) { this.instrutor = instrutor; }

    public List<Challenge> getDesafios() { return desafios; }
    public void setDesafios(List<Challenge> desafios) { this.desafios = desafios; }

    // Método auxiliar para manter a consistência do relacionamento bidirecional
    public void addChallenge(Challenge challenge) {
        desafios.add(challenge);
        challenge.setCurso(this);
    }
}