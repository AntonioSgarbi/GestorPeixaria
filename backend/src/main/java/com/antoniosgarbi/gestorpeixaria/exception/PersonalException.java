package com.antoniosgarbi.gestorpeixaria.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class PersonalException extends RuntimeException {
    private String erro;
    private LocalDateTime momento;

    public PersonalException(String erro) {
        this.erro = erro;
        momento = LocalDateTime.now();
    }
}
