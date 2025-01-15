package br.com.topsystem.dscatalog.services.exceptions;

import java.io.Serial;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}
