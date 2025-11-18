package br.com.fiap.global.dao; // Pacote para a camada DAO

import br.com.fiap.global.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDAO {

    public List<User> findAll() {
        return User.listAll(); // Chama o método estático do Panache
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(User.findById(id));
    }

    @Transactional
    public User create(User user) {
        user.persist(); // Panache method to save/insert
        return user;
    }

    @Transactional
    public Optional<User> update(User user) {
        // Encontra a entidade rastreada e atualiza seus campos, se necessário.
        // Como o Panache rastreia o objeto 'user' se ele foi lido, apenas o Transactional é suficiente.
        // Se a entidade não foi lida antes, é melhor usar um findById e merge, mas para simplificar:

        Optional<User> existing = findById(user.getId());

        if (existing.isPresent()) {
            User entity = existing.get();
            entity.setNome(user.getNome());
            entity.setEmail(user.getEmail());
            entity.setSenha(user.getSenha());
            entity.setPlano(user.getPlano());
            entity.setNivel(user.getNivel());
            return Optional.of(entity); // Retorna a entidade atualizada
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteById(Long id) {
        return User.deleteById(id);
    }
}