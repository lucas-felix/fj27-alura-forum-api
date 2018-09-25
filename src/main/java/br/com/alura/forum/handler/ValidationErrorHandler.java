package br.com.alura.forum.handler;

import br.com.alura.forum.validator.dto.FieldErrorOutputDto;
import br.com.alura.forum.validator.dto.ValidationErrorsOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

        ValidationErrorsOutputDto validationErrors = processErrors(allErrors);
        return validationErrors;
    }

    private ValidationErrorsOutputDto processErrors(List<ObjectError> errors) {
        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();

        for (ObjectError error : errors) {
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                validationErrors.addFieldError(fieldError.getField(), errorMessage);

            } else {
                validationErrors.addError(errorMessage);
            }
        }

        return validationErrors;
    }

}
