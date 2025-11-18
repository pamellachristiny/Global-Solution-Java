package br.com.fiap.global.resource;

import br.com.fiap.global.model.User;
import br.com.fiap.global.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

// Importante: A URL base é /users
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource { // Note a convenção Resource, não Controller

    @Inject
    UserService userService;

    // --- GET ALL ---
    @GET
    public List<User> listarTodos() {
        return userService.findAll(); // Método que o compilador reclamou
    }

    // --- GET BY ID ---
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        // Usa o método do Service que lança a exceção 404
        User user = userService.buscarPorIdOuFalhar(id);
        return Response.ok(user).build();
    }

    // --- CREATE ---
    @POST
    public Response criarUser(User novoUser) {
        User salvoUser = userService.create(novoUser); // Método que o compilador reclamou
        return Response.status(Response.Status.CREATED).entity(salvoUser).build();
    }

    // --- UPDATE ---
    @PUT
    @Path("/{id}")
    public Response atualizarUser(@PathParam("id") Long id, User userAtualizado) {
        User user = userService.update(id, userAtualizado);
        return Response.ok(user).build();
    }

    // --- DELETE ---
    @DELETE
    @Path("/{id}")
    public Response deletarUser(@PathParam("id") Long id) {
        if (userService.delete(id)) { // Método que o compilador reclamou
            return Response.noContent().build();
        } else {
            // Em Panache, o deleteById retorna false se não encontrar o registro,
            // mas se você quiser garantir que a exclusão falhou, pode buscar primeiro.
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}