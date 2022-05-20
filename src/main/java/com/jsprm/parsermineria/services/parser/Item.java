package com.jsprm.parsermineria.services.parser;

import com.jsprm.parsermineria.exceptions.AtributoNoValido;
import com.jsprm.parsermineria.exceptions.LineaNoValida;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item implements Command{

    String numeroProducto;
    String antiguedadProducto;
    String cantidadProducto;
    String valorNetoProducto;
    String basuraFinalLinea;


    @Override
    public void execute(String linea) {
        String lineaValidar;
        try {
            lineaValidar = linea.substring(1);
        } catch (Exception e) {
            throw new LineaNoValida("Linea no valida");
        }

        setNombreProducto(lineaValidar);
        setAntiguedadProducto(lineaValidar);
        setCantidadProducto(lineaValidar);
        setValorNetoProducto(lineaValidar);
        setBasuraFinalLinea(lineaValidar);
    }


    public void setNombreProducto(String linea){
        try{
            validarNombreProducto(linea.substring(0,7));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea item no valida, str.length < 7");
        }
    }

    public void validarNombreProducto(String numeroProducto){
        if(numeroProducto.matches("^[a-zA-Z]{4}\\d{3}$")){
            this.numeroProducto = numeroProducto;
        }else{
            throw new AtributoNoValido(String.format("Id producto invalido: %s", numeroProducto));
        }
    }

    public void setAntiguedadProducto(String linea){
        try{
            validarAntiguedadProducto(linea.substring(7,9));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea item no valida, str.length < 9");
        }
    }

    public void validarAntiguedadProducto(String antiguedadProducto){
        if(antiguedadProducto.matches("^(\\s)?\\d{1,2}$")){
            this.antiguedadProducto = antiguedadProducto;
        }else{
            throw new AtributoNoValido(String.format("Antiguedad de producto invalida: %s", antiguedadProducto));
        }
    }

    public void setCantidadProducto(String linea){
        try{
            validarCantidadProducto(linea.substring(9,13));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea item no valida, str.length < 13");
        }
    }

    public void validarCantidadProducto(String cantidadProducto){
        if(cantidadProducto.matches("^(\\s{1,3})?\\d{1,4}$")){
            this.cantidadProducto = cantidadProducto;
        }else{
            throw new AtributoNoValido(String.format("Cantidad de producto invalida: %s", cantidadProducto));
        }
    }

    public void setValorNetoProducto(String linea){
        try{
            validarValorNetoProducto(linea.substring(13,21));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea item no valida, str.length < 21");
        }
    }

    public void validarValorNetoProducto(String valorNetoProducto){
        if(valorNetoProducto.matches("^(\\s{0,5})?\\d{0,5}\\.\\d{2}$")){
            this.valorNetoProducto = valorNetoProducto;
        }else{
            throw new AtributoNoValido(String.format("Valor neto invalido: %s", valorNetoProducto));
        }
    }

    public void setBasuraFinalLinea(String linea){
        try{
            validarBasuraFinalLinea(linea.substring(21,25));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea item no valida, str.length < 25");
        }
    }

    public void validarBasuraFinalLinea(String basuraFinalLinea){
        if(basuraFinalLinea.matches("^\\s{4}$")){
            this.basuraFinalLinea = basuraFinalLinea;
        }else{
            throw new LineaNoValida("Linea no valida");
        }
    }



}

