package br.com.alura.forum.validator.dto;

import br.com.alura.forum.validator.dto.FieldErrorOutputDto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

    private List<ErrorOutputDto> errors = new ArrayList<>();

    public void addError(String message) {
        errors.add(new ErrorOutputDto(message));
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        errors.add(fieldError);
    }

    public List<ErrorOutputDto> getErrors() {
        return errors;
    }

    public int getNumberOfErrors() {
        return this.errors.size();
    }
}