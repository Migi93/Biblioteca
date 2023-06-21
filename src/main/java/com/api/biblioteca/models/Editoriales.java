package com.api.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Editoriales {

    private Integer editorialesId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaCreacion;
}
