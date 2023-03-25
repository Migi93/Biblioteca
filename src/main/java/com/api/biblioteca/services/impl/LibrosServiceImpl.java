package com.api.biblioteca.services.impl;

import com.api.biblioteca.models.Libros;
import com.api.biblioteca.persistance.database.mappers.LibrosMapper;
import com.api.biblioteca.services.LibrosService;
import org.springframework.stereotype.Service;

@Service
public class LibrosServiceImpl implements LibrosService {
    LibrosMapper librosMapper;

    public LibrosServiceImpl(LibrosMapper librosMapper) {
        this.librosMapper = librosMapper;
    }

    @Override
    public Libros insertarLibro(Libros libros) {
        libros.setEditorial(libros.getEditorial());
        this.librosMapper.insertarLibro(libros);
        return libros;
    }
}
