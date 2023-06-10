package com.api.biblioteca.services;

import com.api.biblioteca.models.Libros;

import java.util.List;

public interface LibrosService {
    void insertBook(Libros libros);

    Libros getBook(int libroId);

    List<Libros> listBooks();

    void deleteBook(int libroId);
}
