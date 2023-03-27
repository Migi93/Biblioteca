package com.api.biblioteca.controllers;

import com.api.biblioteca.models.Libros;
import com.api.biblioteca.services.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController()
@RestController
@RequestMapping("/libros")
public class LibrosController {

    LibrosService librosService;


    public LibrosController(LibrosService librosService) {
        this.librosService = librosService;
    }

    @PostMapping("")
    ResponseEntity<Libros> addLibro(@RequestBody Libros libros) {
        Libros libroDevuelto = librosService.insertarLibro(libros);
        return new ResponseEntity<>(libroDevuelto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Libros> getLibro(@PathVariable("id") int libroId) {
        return new ResponseEntity<>(this.librosService.obtenerLibro(libroId), HttpStatus.OK);
    }

}
