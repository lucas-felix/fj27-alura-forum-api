package br.com.alura.forum.service.infra;

public class MailServiceException extends RuntimeException {

    public MailServiceException(String message) {
        super(message);
    }
}
