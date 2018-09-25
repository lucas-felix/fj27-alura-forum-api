package br.com.alura.forum.validator.dto;

public class FieldErrorOutputDto extends ErrorOutputDto {

    private String field;

    FieldErrorOutputDto() { }

    public FieldErrorOutputDto(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
