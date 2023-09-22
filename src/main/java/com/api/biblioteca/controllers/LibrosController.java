package com.api.biblioteca.controllers;

import com.api.biblioteca.models.Libros;
import com.api.biblioteca.services.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class LibrosController {

    private final LibrosService librosService;

    public LibrosController(LibrosService librosService) {
        this.librosService = librosService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addLibro(@RequestBody Libros libros) {
        librosService.insertBook(libros);
        return new ResponseEntity<>(Map.of("id", libros.getLibroId()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Libros>> getAllLibros() {
        return new ResponseEntity<>(librosService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLibro(@PathVariable("id") int libroId) {
        return new ResponseEntity<>(this.librosService.getBook(libroId), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteLibro(@RequestParam("id") int libroId) {
        this.librosService.deleteBook(libroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("")
    public ResponseEntity<Object> updateLibro(@RequestBody Libros libros) {
        this.librosService.updateBook(libros);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
