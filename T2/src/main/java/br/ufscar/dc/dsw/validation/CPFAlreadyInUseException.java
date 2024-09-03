package br.ufscar.dc.dsw.validation;

import java.nio.charset.StandardCharsets;

public class CPFAlreadyInUseException extends  RuntimeException{
	// Construtor que aceita uma mensagem de erro
    public CPFAlreadyInUseException(String message) {
        super(new String(message.getBytes(), StandardCharsets.UTF_8));  // Chama o construtor da superclasse RuntimeException
    }
}
