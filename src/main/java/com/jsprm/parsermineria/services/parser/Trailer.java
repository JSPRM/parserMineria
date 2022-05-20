package com.jsprm.parsermineria.services.parser;

import com.jsprm.parsermineria.exceptions.AtributoNoValido;
import com.jsprm.parsermineria.exceptions.LineaNoValida;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trailer implements Command{

    String totalItemsEnFactura;
    String valorNetoTotalItems;
    String basuraFinalLinea;


    @Override
    public void execute(String linea) {
        String lineaValidar;
        try {
            lineaValidar = linea.substring(1);
        } catch (Exception e) {
            throw new LineaNoValida("Linea no valida");
        }
        setTotalItemsEnFactura(lineaValidar);
        setValorNetoTotalItems(lineaValidar);
        setBasuraFinalLinea(lineaValidar);
    }

    public void setTotalItemsEnFactura(String linea){
        try{
            validarTotalItemsEnFactura(linea.substring(0,2));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea trailer no valida, str.length < 2");
        }
    }

    public void validarTotalItemsEnFactura(String totalItemsEnFactura){
        if(totalItemsEnFactura.matches("^(\\s)?\\d{1,2}$")){
            this.totalItemsEnFactura = totalItemsEnFactura;
        }else{
            throw new AtributoNoValido(String.format("Total items invalido invalido: %s", totalItemsEnFactura));
        }
    }

    public void setValorNetoTotalItems(String linea){
        try{
            validarValorNetoTotalItems(linea.substring(2,13));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea trailer no valida, str.length < 13");
        }
    }

    public void validarValorNetoTotalItems(String valorNetoTotalItems){
        if(valorNetoTotalItems.matches("^(\\s{0,8})?\\d{0,8}\\.\\d{2}$")){
            this.valorNetoTotalItems = valorNetoTotalItems;
        }else{
            throw new AtributoNoValido(String.format("Valor neto total invalido: %s", valorNetoTotalItems));
        }
    }

    public void setBasuraFinalLinea(String linea){
        try{
            validarBasuraFinalLinea(linea.substring(13,25));
        }
        catch(Exception e) {
            throw new LineaNoValida("Linea trailer no valida, str.length < 25");
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
