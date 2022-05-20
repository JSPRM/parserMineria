package com.jsprm.parsermineria.services.parser;

import com.jsprm.parsermineria.exceptions.LineaNoValida;
import com.jsprm.parsermineria.services.ClienteService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Parser {
    Map<Character, Command> commands;
    ArrayList<Object> objetos;
    String orden;

    private ClienteService clienteService;

    public Parser() {
        commands = new HashMap<>();
        objetos = new ArrayList<>();
        orden = "";
        commands.put('H', new Header());
        commands.put('I', new Item());
        commands.put('T', new Trailer());

    }

    public void procesar(String linea){
        char primerChar = linea.charAt(0);

        try{
            Command o = commands.get(primerChar);
            o.execute(linea);
            this.orden += primerChar;
            this.objetos.add(o);
        }
        catch(Exception e) {
            throw new LineaNoValida("Formato de linea invalido");
        }
    }

}
