package com.api.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Editoriales {

    private Integer editorialesId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaCreacion;

    public Integer getEditorialesId() {
        return editorialesId;
    }

    public void setEditorialesId(Integer editorialesId) {
        this.editorialesId = editorialesId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
