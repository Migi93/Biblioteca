package com.api.biblioteca.controllers;

import com.api.biblioteca.models.Libros;
import com.api.biblioteca.services.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/libros")
public class LibrosController {

    LibrosService librosService;

    public LibrosController(LibrosService librosService) {
        this.librosService = librosService;
    }

    @PostMapping("")
    ResponseEntity<Libros> addLibro(@RequestBody Libros libros) {
        this.librosService.insertarLibro(libros);
        return new ResponseEntity<>(libros, HttpStatus.CREATED);
    }
    /*
    @PostMapping("")
    ResponseEntity addLibro() {
        return ResponseEntity.ok("Funciona");
    }
    */
}
