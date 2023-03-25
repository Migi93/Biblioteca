package com.api.biblioteca.services;

import com.api.biblioteca.models.Libros;

public interface LibrosService {
    Libros insertarLibro(Libros libros);

    Libros obtenerLibro(int libroId);
}
