package tech.antoniosgarbi.gestorpeixaria.exception;

import java.time.LocalDateTime;

public abstract class PersonalException extends RuntimeException {

    private LocalDateTime momento;

    public PersonalException(String mensagem) {
        super(mensagem);
        momento = LocalDateTime.now();
    }
}
