package br.com.fiap.global.dao;

import br.com.fiap.global.model.Curso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CursoDAO {

    public List<Curso> findAll() {
        return Curso.listAll();
    }

    public Optional<Curso> findById(Long id) {
        return Optional.ofNullable(Curso.findById(id));
    }

    @Transactional
    public Curso create(Curso curso) {
        curso.persist();
        return curso;
    }

    @Transactional
    public Optional<Curso> update(Curso curso) {
        Optional<Curso> existing = findById(curso.getId());

        if (existing.isPresent()) {
            Curso entity = existing.get();
            entity.setNomeCurso(curso.getNomeCurso());
            entity.setInstrutor(curso.getInstrutor());
            // A lista de desafios é atualizada automaticamente
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteById(Long id) {
        // Devido ao CascadeType.ALL em Curso, deletar o curso deletará os desafios associados.
        return Curso.deleteById(id);
    }
}