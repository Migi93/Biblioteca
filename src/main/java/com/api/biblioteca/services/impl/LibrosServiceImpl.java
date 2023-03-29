package com.api.biblioteca.services.impl;

import com.api.biblioteca.exceptions.*;
import com.api.biblioteca.models.Editoriales;
import com.api.biblioteca.models.Libros;
import com.api.biblioteca.persistance.database.mappers.EditorialesMapper;
import com.api.biblioteca.persistance.database.mappers.LibrosMapper;
import com.api.biblioteca.services.LibrosService;
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
    public void insertBook(Libros libros) throws RequiredMissingFieldException, WorngLengthFielException, EditorialNotFound, AtributteNotIsUnique {
        validateNameBook(libros);
        existEditorialOrNull(libros.getEditorial());
        existIsbn(libros.getLibroId());
        this.librosMapper.insertarLibro(libros);
    }

    @Override
    public Libros getBook(int libroId) throws BookNotFoundException {
        existBook(libroId);
        return librosMapper.getLibro(libroId);
    }

    @Override
    public List<Libros> listBooks() {
        return librosMapper.getListLibros();
    }

    @Override
    public void deleteBook(int libroId) throws BookNotFoundException {
        existBook(libroId);
        librosMapper.deleteBook(libroId);
    }

    //VALIDACIONES
    private void validateNameBook(Libros libros) throws RequiredMissingFieldException, WorngLengthFielException {
        if (libros.getTitulo() == null || libros.getTitulo().isEmpty()) {
            throw new RequiredMissingFieldException();
        }
        if (libros.getTitulo().length() > 100) {
            throw new WorngLengthFielException();
        }
    }

    private void existBook(int libroId) throws BookNotFoundException {
        if (librosMapper.existeLibro(libroId) < 1) {
            throw new BookNotFoundException();
        }
    }

    private void existEditorialOrNull(Editoriales editoriales) throws EditorialNotFound {
        if (editorialesMapper.existeEditorial(editoriales.getEditorialesId()) < 1) {
            throw new EditorialNotFound();
        }
    }

    private void existIsbn(int libroId) throws AtributteNotIsUnique {
        if (librosMapper.existeIsbn(libroId) > 1) {
            throw new AtributteNotIsUnique();
        }
    }


}
