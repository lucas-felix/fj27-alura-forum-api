package br.com.alura.forum.handler;

import br.com.alura.forum.controller.dto.output.ValidationErrorsOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        ValidationErrorsOutputDto validationErrors = processFieldErrors(fieldErrors);
        return validationErrors;
    }

    private ValidationErrorsOutputDto processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();

        fieldErrors.forEach(error -> {
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            validationErrors.addFieldError(error.getField(), errorMessage);
        });

        return validationErrors;
    }

}
