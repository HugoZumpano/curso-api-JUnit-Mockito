package br.com.hugo.api.services.exceptions;

import javax.management.RuntimeMBeanException;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
