package com.api.biblioteca.controllers;

import com.api.biblioteca.models.Libros;
import com.api.biblioteca.services.EditorialesService;
import com.api.biblioteca.services.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController()
@RestController
@RequestMapping("/libros")
public class LibrosController {

    LibrosService librosService;
    EditorialesService editorialesService;

    public LibrosController(LibrosService librosService, EditorialesService editorialesService) {
        this.librosService = librosService;
        this.editorialesService = editorialesService;
    }

    @PostMapping("")
    ResponseEntity<Libros> addLibro(@RequestBody Libros libros) {
        Libros libroDevuelto = librosService.insertarLibro(libros);
        return new ResponseEntity<>(libroDevuelto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Libros> getLibro(@PathVariable("id") int libroId) {
        Libros libroResponse = this.librosService.obtenerLibro(libroId);
        libroResponse.setEditorial(editorialesService.obtenerEditorial(libroResponse.getEditorial().getEditorialesId()));
        return new ResponseEntity<>(libroResponse, HttpStatus.OK);
    }

}
