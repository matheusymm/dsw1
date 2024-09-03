package br.ufscar.dc.dsw.validation;

import java.nio.charset.StandardCharsets;

public class CNPJAlreadyInUseException extends Exception {
    public CNPJAlreadyInUseException(String message) {
        super(new String(message.getBytes(), StandardCharsets.UTF_8));  // Chama o construtor da superclasse RuntimeException
    }
}
