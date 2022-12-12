package com.infnet.projeto.persistencia.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }
}
