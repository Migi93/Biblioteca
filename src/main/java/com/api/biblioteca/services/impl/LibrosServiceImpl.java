package com.api.biblioteca.services.impl;

import com.api.biblioteca.models.Libros;
import com.api.biblioteca.persistance.database.mappers.EditorialesMapper;
import com.api.biblioteca.persistance.database.mappers.LibrosMapper;
import com.api.biblioteca.services.LibrosService;
import org.springframework.stereotype.Service;

@Service
public class LibrosServiceImpl implements LibrosService {

    LibrosMapper librosMapper;

    EditorialesMapper editorialesMapper;

    public LibrosServiceImpl(LibrosMapper librosMapper, EditorialesMapper editorialesMapper) {
        this.librosMapper = librosMapper;
        this.editorialesMapper = editorialesMapper;
    }

    @Override
    public Libros insertarLibro(Libros libros) {
        this.librosMapper.insertarLibro(libros);
        return libros;
    }

    @Override
    public Libros obtenerLibro(int libroId) {
        return librosMapper.getLibro(libroId);
    }

}
