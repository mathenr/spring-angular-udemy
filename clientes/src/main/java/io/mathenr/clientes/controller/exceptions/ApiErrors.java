package io.mathenr.clientes.controller.exceptions;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors_){
        errors = errors_;
    }

    public ApiErrors(String message){
        errors = Arrays.asList(message);
    }
}
