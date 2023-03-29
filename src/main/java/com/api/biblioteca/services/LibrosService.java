package com.api.biblioteca.services;

import com.api.biblioteca.exceptions.*;
import com.api.biblioteca.models.Libros;

import java.util.List;

public interface LibrosService {
    void insertBook(Libros libros) throws RequiredMissingFieldException, WorngLengthFielException, EditorialNotFound, AtributteNotIsUnique;

    Libros getBook(int libroId) throws BookNotFoundException;

    List<Libros> listBooks();

    void deleteBook(int libroId) throws BookNotFoundException;
}
