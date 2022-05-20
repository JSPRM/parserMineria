package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.exceptions.ArchivoNoValido;
import com.jsprm.parsermineria.exceptions.LineaNoValida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParserServiceTest {

    @Autowired
    private ParserService parserService;

    @Test
    @DisplayName("ControlaCharInicialInvalido")
    void ControlaCharInicialInvalido() {
        String direccion = new File("src/test/archivos/CharInicialInvalido.txt").getAbsolutePath();
        String[] direccionesTest = new String[]{direccion};
        assertThrows(LineaNoValida.class, () -> parserService.parserMain(direccionesTest));
    }

    @Test
    @DisplayName("ControlaArchivoInvalido")
    void ControlaArchivoInvalido() {
        String direccion = new File("src/test/archivos/noexisto.lol").getAbsolutePath();
        String[] direccionesTest = new String[]{direccion};
        assertThrows(ArchivoNoValido.class, () -> parserService.parserMain(direccionesTest));
    }
}