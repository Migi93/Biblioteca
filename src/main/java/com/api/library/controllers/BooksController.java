package com.api.library.controllers;

import com.api.library.models.Books;
import com.api.library.services.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addBook(@RequestBody Books books) {
        booksService.insertBook(books);
        return new ResponseEntity<>(Map.of("id", books.getBookId()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Books>> getAllBooks() {
        return new ResponseEntity<>(booksService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") int bookId) {
        return new ResponseEntity<>(this.booksService.getBook(bookId), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteBook(@RequestParam("id") int bookId) {
        this.booksService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("")
    public ResponseEntity<Object> updateBook(@RequestBody Books books) {
        this.booksService.updateBook(books);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
