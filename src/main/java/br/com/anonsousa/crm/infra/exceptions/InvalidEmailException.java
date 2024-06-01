package br.com.anonsousa.crm.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message){
        super(message);
    }
}
