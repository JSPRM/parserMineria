package com.jsprm.parsermineria.exceptions.handler;

import com.jsprm.parsermineria.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = ErrorNoValido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> totalNoValido(ErrorNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }

    @ExceptionHandler(value = TotalNoValido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> totalNoValido(TotalNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }

    @ExceptionHandler(value = ArchivoNoValido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> archivoNoValido(ArchivoNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = LineaNoValida.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> headerLineaNoValida(LineaNoValida ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = EntidadNoValida.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> headerLineaNoValida(EntidadNoValida ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = AtributoNoValido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> atributoNoValido(AtributoNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = OrdenNoValido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> atributoNoValido(OrdenNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = NumeroItemsNoValido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> atributoNoValido(NumeroItemsNoValido ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }
}
