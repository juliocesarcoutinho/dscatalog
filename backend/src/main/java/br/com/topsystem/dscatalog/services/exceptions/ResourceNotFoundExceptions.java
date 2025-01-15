package br.com.topsystem.dscatalog.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {
    public ResourceNotFoundExceptions(String msg) {
        super(msg);
    }
}
