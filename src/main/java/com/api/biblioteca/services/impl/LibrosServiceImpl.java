package com.api.biblioteca.services.impl;

import com.api.biblioteca.exceptions.*;
import com.api.biblioteca.models.Editoriales;
import com.api.biblioteca.models.Libros;
import com.api.biblioteca.persistance.database.mappers.EditorialesMapper;
import com.api.biblioteca.persistance.database.mappers.LibrosMapper;
import com.api.biblioteca.services.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LibrosServiceImpl implements LibrosService {

    LibrosMapper librosMapper;
    EditorialesMapper editorialesMapper;

    public LibrosServiceImpl(LibrosMapper librosMapper, EditorialesMapper editorialesMapper) {
        this.librosMapper = librosMapper;
        this.editorialesMapper = editorialesMapper;
    }

    @Override
    public void insertBook(Libros libros) {
        validateNameBook(libros);
        existEditorialOrNull(libros.getEditorial());
        existIsbn(libros.getIsbn());
        this.librosMapper.insertarLibro(libros);
    }

    @Override
    public Libros getBook(int libroId) throws BookNotFoundException {
        existBook(libroId);
        return librosMapper.getLibro(libroId);
    }

    @Override
    public List<Libros> listBooks() {
        listaVacia();
        return librosMapper.getListLibros();
    }

    @Override
    public void deleteBook(int libroId) {
        existBook(libroId);
        librosMapper.deleteBook(libroId);
    }

    //VALIDACIONES
    private void validateNameBook(Libros libros) {
        if (libros.getTitulo() == null || libros.getTitulo().isEmpty()) {
            throw new RequiredMissingFieldException("titulo", HttpStatus.BAD_REQUEST);
        }
        if (libros.getTitulo().length() > 100) {
            throw new WorngLengthFieldException("titulo", HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    private void existBook(int libroId) {
        if (librosMapper.existeLibro(libroId) < 1) {
            throw new BookNotFoundException("libro", HttpStatus.NOT_FOUND);
        }
    }

    private void existEditorialOrNull(Editoriales editoriales) {
        if (editorialesMapper.existeEditorial(editoriales.getEditorialesId()) < 1) {
            throw new EditorialNotFoundexception("editorial", HttpStatus.NOT_FOUND);
        }
    }

    private void existIsbn(String isbn) {
        if (librosMapper.existeIsbn(isbn) > 0) {
            throw new AtributteNotIsUniqueException("isbn", HttpStatus.CONFLICT);
        }
    }

    private void listaVacia() {
        if (librosMapper.listaVacia() < 1) {
            throw new ListIsEmptyOrNullException("libros", HttpStatus.NOT_FOUND);
        }
    }


}
