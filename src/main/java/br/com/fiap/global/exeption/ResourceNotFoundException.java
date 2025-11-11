package br.com.fiap.biblioteca.exception; // PACOTE EXCEPTION

import jakarta.ws.rs.NotFoundException;

public class ResourceNotFoundException extends NotFoundException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}