package br.com.fiap.global.exeption;

public class RequisicaoInvalidaException extends RuntimeException {
    public RequisicaoInvalidaException(String message) {
        super(message);
    }
}
