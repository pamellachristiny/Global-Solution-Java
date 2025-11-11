package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.model.Usuario;
import br.com.fiap.biblioteca.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @Inject
    UsuarioService service;

    @POST
    public Response criar(Usuario novoUsuario) {
        try {
            Usuario usuarioSalvo = service.criar(novoUsuario);
            // 201 Created
            return Response.created(URI.create("/usuarios/" + usuarioSalvo.id)).entity(usuarioSalvo).build();
        } catch (IllegalStateException e) {
            // 400 se o email for duplicado
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<Usuario> listarTodos() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    public Usuario buscarPorId(@PathParam("id") Long id) {
        // O Service trata o 404 (ResourceNotFoundException)
        return service.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public Usuario atualizar(@PathParam("id") Long id, Usuario dadosNovos) {
        // O Service trata o 404
        return service.atualizar(id, dadosNovos);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        // O Service trata o 404
        service.deletar(id);
        return Response.noContent().build(); // 204 No Content
    }
}