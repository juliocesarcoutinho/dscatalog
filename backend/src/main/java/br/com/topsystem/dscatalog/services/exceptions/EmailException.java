package br.com.topsystem.dscatalog.services.exceptions;

@SuppressWarnings("serial")
public class EmailException extends RuntimeException {

	public EmailException(String msg) {
		super(msg);
	}
}
