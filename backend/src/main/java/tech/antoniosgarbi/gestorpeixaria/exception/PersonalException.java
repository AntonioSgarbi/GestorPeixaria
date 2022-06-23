package tech.antoniosgarbi.gestorpeixaria.exception;

import java.time.LocalDateTime;

public abstract class PersonalException extends RuntimeException {

    protected final LocalDateTime momento;

    protected PersonalException(String mensagem) {
        super(mensagem);
        momento = LocalDateTime.now();
    }

    public LocalDateTime getMomento() {
        return this.momento;
    }
}
