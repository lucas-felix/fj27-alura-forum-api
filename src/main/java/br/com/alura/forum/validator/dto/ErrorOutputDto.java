package br.com.alura.forum.validator.dto;

public class ErrorOutputDto {

    private String message;

    ErrorOutputDto() { }

    public ErrorOutputDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
