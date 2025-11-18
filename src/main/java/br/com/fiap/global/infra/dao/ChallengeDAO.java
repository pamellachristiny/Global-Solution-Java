package br.com.fiap.global.dao;

import br.com.fiap.global.model.Challenge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ChallengeDAO {

    public List<Challenge> findAll() {
        return Challenge.listAll();
    }

    public Optional<Challenge> findById(Long id) {
        return Optional.ofNullable(Challenge.findById(id));
    }

    @Transactional
    public Challenge create(Challenge challenge) {
        challenge.persist();
        return challenge;
    }

    @Transactional
    public Optional<Challenge> update(Challenge challenge) {
        // Encontra o registro existente
        Optional<Challenge> existing = findById(challenge.getId());

        if (existing.isPresent()) {
            Challenge entity = existing.get();
            // Atualiza os campos
            entity.setNomeChallenge(challenge.getNomeChallenge());
            entity.setDescricaoChallenge(challenge.getDescricaoChallenge());
            entity.setTempo(challenge.getTempo());
            // Panache/Hibernate faz o save automaticamente por ser @Transactional
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteById(Long id) {
        return Challenge.deleteById(id);
    }
}