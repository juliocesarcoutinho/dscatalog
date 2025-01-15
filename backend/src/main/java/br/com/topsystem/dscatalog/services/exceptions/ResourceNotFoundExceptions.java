package br.com.topsystem.dscatalog.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundExceptions extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundExceptions(String msg) {
        super(msg);
    }
}
