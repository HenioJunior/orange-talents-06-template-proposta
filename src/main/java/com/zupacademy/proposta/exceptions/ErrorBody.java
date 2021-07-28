package com.zupacademy.proposta.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorBody {

    private String erro;
    private List<FieldError> fieldErrors;

    public ErrorBody(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ErrorBody(String erro) {
        this.erro = erro;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public String getErro() {
        return erro;
    }
}

class FieldError {
    private final String campo;
    private final String mensagem;

    public FieldError(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }

}