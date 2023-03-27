package com.api.biblioteca.mappers;

import com.api.biblioteca.models.Editoriales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EditorialesMapper {

    @Select("SELECT ID, NOMBRE, DIRECCION, TELEFONO, CORREO, FECHA_CREACION FROM EDITORIALES WHERE ID=#{editorialesId}")
    Editoriales getEditorial(int editorialesId);
}
