package com.infnet.projeto.persistencia.exception;

public class senhaInvalidaException extends RuntimeException {
    public senhaInvalidaException() {
        super("senha invalida");
    }
}
