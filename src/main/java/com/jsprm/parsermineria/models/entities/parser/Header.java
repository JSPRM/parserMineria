package com.jsprm.parsermineria.models.entities.parser;

import com.jsprm.parsermineria.enums.TiposMoneda;
import com.jsprm.parsermineria.exceptions.AtributoNoValido;
import com.jsprm.parsermineria.exceptions.LineaNoValida;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
public class Header implements Command{

    String numeroFactura;
    String numeroCliente;
    String paisCliente;
    String fechaFactura;
    String tipoMoneda;

    @Override
    public void execute(String linea) {
        String lineaValidar;
        try {
            lineaValidar = linea.substring(1);
        } catch (Exception e) {
            throw new LineaNoValida("Linea no valida");
        }

        setNumeroCliente(lineaValidar);
        setPaisCliente(lineaValidar);
        setNumeroFactura(lineaValidar);
        setFechaFactura(lineaValidar);
        setTipoMoneda(lineaValidar);

    }


    public void setNumeroFactura(String linea){
        try{
            validarNumeroFactura(linea.substring(0,8));
        }
        catch(Exception e) {
            throw new LineaNoValida("Header no valido, str.length < 8");
        }
    }

    public void validarNumeroFactura(String numeroFactura){
        if(numeroFactura.matches("^[a-zA-Z]{3}\\d{5}$")){
            this.numeroFactura = numeroFactura;
        }else{
            throw new AtributoNoValido(String.format("Numero factura invalido: %s", numeroFactura));
        }
    }

    public void setNumeroCliente(String linea){
        try{
            validarNumeroCliente(linea.substring(8,12));
        }
        catch(Exception e) {
            throw new LineaNoValida("Header no valido, str.length < 12");
        }
    }

    public void validarNumeroCliente(String numeroCliente){
        if(numeroCliente.matches("^[a-zA-Z]\\d{3}$")){
            this.numeroCliente = numeroCliente;
        }else{
            throw new AtributoNoValido(String.format("Numero cliente invalido: %s", numeroCliente));
        }
    }

    public void setPaisCliente(String linea){
        try{
            validarPaisCliente(linea.substring(12,14));
        }
        catch(Exception e) {
            throw new LineaNoValida("Header no valido, str.length < 14");
        }
    }

    public void validarPaisCliente(String paisCliente){
        if(paisCliente.matches("^[a-zA-Z]{2}$")){
            this.paisCliente = paisCliente;
        }else{
            throw new AtributoNoValido(String.format("Pais cliente invalido: %s", paisCliente));
        }
    }

    public void setFechaFactura(String linea){
        try{
            validarFechaFactura(linea.substring(14,22));
        }
        catch(Exception e) {
            throw new LineaNoValida("Header no valido, str.length < 22");
        }
    }

    public void validarFechaFactura(String fechaFactura){
        if(fechaFactura.matches("(?<!\\d)(?:(?:20\\d{2})(?:(?:(?:0[13578]|1[02])31)|(?:(?:0[1,3-9]|1[0-2])(?:29|30)))|(?:(?:20(?:0[48]|[2468][048]|[13579][26]))0229)|(?:20\\d{2})(?:(?:0?[1-9])|(?:1[0-2]))(?:0?[1-9]|1\\d|2[0-8]))(?!\\d)")){
            this.fechaFactura = fechaFactura;
        }else{
            throw new AtributoNoValido(String.format("Fecha factura invalida: %s", fechaFactura));
        }
    }

    public void setTipoMoneda(String linea){
        try{
            validarTipoMoneda(linea.substring(22,25));
        }
        catch(Exception e) {
            throw new LineaNoValida("Header no valido, str.length < 25");
        }
    }

    public void validarTipoMoneda(String tipoMoneda){
        if(tipoMoneda.matches("^[a-zA-Z]{3}$") & ObjectUtils.containsConstant(TiposMoneda.values(), tipoMoneda)){
            this.tipoMoneda = tipoMoneda;
        }else{
            throw new AtributoNoValido(String.format("Tipo de moneda invalida: %s", tipoMoneda));
        }
    }

}
