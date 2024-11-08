package com.starlight.do_ti.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    // RECEBE O OBJ QUE TENTOU ACHAR E N ENCONTROU
    public ResourceNotFoundException(Object x) {
        super("Resource not found: " + x);
    }

}