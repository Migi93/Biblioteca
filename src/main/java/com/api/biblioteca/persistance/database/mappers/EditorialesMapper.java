package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Editoriales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EditorialesMapper {

    @Select("SELECT ID, NOMBRE, DIRECCION, TELEFONO, CORREO, FECHA_CREACION FROM EDITORIALES WHERE ID=#{editorialesId}")
    @Result(property = "editorialesId", column = "ID")
    @Result(property = "nombre", column = "NOMBRE")
    @Result(property = "direccion", column = "DIRECCION")
    @Result(property = "telefono", column = "TELEFONO")
    @Result(property = "email", column = "CORREO")
    @Result(property = "fechaCreacion", column = "FECHA_CREACION")
    Editoriales getEditorial(int editorialesId);

    @Select("SELECT COUNT(ID) FROM EDITORIALES WHERE ID=#{editorialesId}")
    int existeEditorial(int editorialesId);
}
