package com.jsprm.parsermineria.controller;

import com.jsprm.parsermineria.services.ParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParserController {

    ParserService parserService;

    @GetMapping("/nuevo")
    public ResponseEntity<?> nuevoArchivo(@RequestParam() String filePath){
        return null;
    }
}
