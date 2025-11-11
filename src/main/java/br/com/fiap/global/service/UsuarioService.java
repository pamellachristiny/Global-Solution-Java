package br.com.fiap.biblioteca.service;

import br.com.fiap.biblioteca.model.Usuario;
import br.com.fiap.biblioteca.infra.dao.UsuarioDAO;
import br.com.fiap.biblioteca.exception.ResourceNotFoundException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioDAO dao;

    public List<Usuario> listar() {
        return dao.listAll();
    }

    public Usuario buscarPorId(Long id) {
        return dao.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        if (dao.buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalStateException("Erro de Negócio: E-mail já cadastrado.");
        }

        dao.persist(usuario);
        return usuario;
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dadosNovos) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dadosNovos.getNome());
        usuario.setEmail(dadosNovos.getEmail());
        usuario.setSenha(dadosNovos.getSenha());
        return usuario;
    }

    @Transactional
    public void deletar(Long id) {
        boolean deletado = dao.deleteById(id);
        if (!deletado) {
            throw new ResourceNotFoundException("Usuário não encontrado para exclusão com o ID: " + id);
        }
    }
}