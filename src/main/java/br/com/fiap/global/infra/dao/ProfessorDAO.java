package br.com.fiap.global.dao;

import br.com.fiap.global.model.Professor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfessorDAO {

    public List<Professor> findAll() {
        return Professor.listAll();
    }

    public Optional<Professor> findById(Long id) {
        return Optional.ofNullable(Professor.findById(id));
    }

    @Transactional
    public Professor create(Professor professor) {
        professor.persist();
        return professor;
    }

    @Transactional
    public Optional<Professor> update(Professor professor) {
        Optional<Professor> existing = findById(professor.getId());

        if (existing.isPresent()) {
            Professor entity = existing.get();
            entity.setNome(professor.getNome());
            entity.setEmail(professor.getEmail());
            entity.setEspecialidade(professor.getEspecialidade());
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteById(Long id) {
        return Professor.deleteById(id);
    }
}