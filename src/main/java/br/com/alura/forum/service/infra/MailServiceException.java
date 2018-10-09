package br.com.alura.forum.service.infra;

import org.springframework.mail.MailException;

public class MailServiceException extends RuntimeException {

    public MailServiceException(String message, Throwable e) {
        super(message, e);
    }
}
