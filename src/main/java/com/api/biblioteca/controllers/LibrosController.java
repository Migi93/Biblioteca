package com.api.biblioteca.controllers;

import com.api.biblioteca.exceptions.BookNotFoundException;
import com.api.biblioteca.models.Libros;
import com.api.biblioteca.services.EditorialesService;
import com.api.biblioteca.services.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController()
@RestController
@RequestMapping("/libros")
public class LibrosController {

    private final LibrosService librosService;
    private final EditorialesService editorialesService;

    public LibrosController(LibrosService librosService, EditorialesService editorialesService) {
        this.librosService = librosService;
        this.editorialesService = editorialesService;
    }

    @PostMapping("")
    ResponseEntity<Libros> addLibro(@RequestBody Libros libros) {
        Libros libroDevuelto = librosService.insertBook(libros);
        return new ResponseEntity<>(libroDevuelto, HttpStatus.CREATED);
    }

    @GetMapping("")
    ResponseEntity<List<Libros>> getAllLibros() {
        List<Libros> listaLibros = librosService.listBooks();
        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Libros> getLibro(@PathVariable("id") int libroId) {
        Libros libroResponse = this.librosService.getBook(libroId);
        libroResponse.setEditorial(editorialesService.obtenerEditorial(libroResponse.getEditorial().getEditorialesId()));
        return new ResponseEntity<>(libroResponse, HttpStatus.OK);
    }

    @DeleteMapping("")
    ResponseEntity deleteLibro(@RequestParam("id") int libroId) {
        try {
            this.librosService.deleteBook(libroId);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, el libro no existe.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Libro con id: " + libroId + " eliminado correctamente.");
    }
}
