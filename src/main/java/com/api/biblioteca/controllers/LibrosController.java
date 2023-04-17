package com.api.biblioteca.controllers;

import com.api.biblioteca.exceptions.*;
import com.api.biblioteca.models.Libros;
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

    public LibrosController(LibrosService librosService) {
        this.librosService = librosService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addLibro(@RequestBody Libros libros) {
        try {
            librosService.insertBook(libros);
        } catch (AtributteNotIsUnique e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El isbn introducido ya existe.");
            //TODO: No es un bad_Request, hay que mirar que hay que lanzar
        } catch (RequiredMissingFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comprueba los datos de entrada");
        } catch (WorngLengthFielException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre demasiado largo para el titulo.");
        } catch (EditorialNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La editorial que desea añadir para este libro no existe.");
        }
        return ResponseEntity.ok(String.format("Libro añadido. Id: %d", libros.getLibroId()));
    }

    @GetMapping("")
    public ResponseEntity<List<Libros>> getAllLibros() {
        return new ResponseEntity<>(librosService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLibro(@PathVariable("id") int libroId) {
        Libros libroResponse;
        try {
            libroResponse = this.librosService.getBook(libroId);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, el libro no existe.");
        }
        return new ResponseEntity<>(libroResponse, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteLibro(@RequestParam("id") int libroId) {
        try {
            this.librosService.deleteBook(libroId);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, el libro no existe.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Libro con id: " + libroId + " eliminado correctamente.");
    }
}
