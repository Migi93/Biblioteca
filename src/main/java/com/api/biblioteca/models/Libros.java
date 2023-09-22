package com.api.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Libros {
    private int libroId;
    private String titulo;
    private Editorials editorial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaPublicacion;
    private String isbn;
}
