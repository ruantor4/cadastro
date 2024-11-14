package com.torquato.cadastro.services.exceptions;

import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Erro de validação");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
} 
   

