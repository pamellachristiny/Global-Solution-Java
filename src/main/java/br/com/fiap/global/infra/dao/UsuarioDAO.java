package br.com.fiap.biblioteca.infra.dao;

import br.com.fiap.biblioteca.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioDAO implements PanacheRepository<Usuario> {
    public Usuario buscarPorEmail(String email) {
        return find("email", email).firstResult();
    }
}