package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Editorials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EditorialsMapper {

    @Select("SELECT ID, NOMBRE, DIRECCION, TELEFONO, CORREO, FECHA_CREACION FROM EDITORIALES WHERE ID=#{editorialId}")
    @Result(property = "editorialId", column = "ID")
    @Result(property = "name", column = "NOMBRE")
    @Result(property = "address", column = "DIRECCION")
    @Result(property = "phone", column = "TELEFONO")
    @Result(property = "email", column = "CORREO")
    @Result(property = "creationDate", column = "FECHA_CREACION")
    Editorials getEditorial(int editorialsId);

    @Select("SELECT COUNT(*) FROM EDITORIALES WHERE NOMBRE=#{nombre}")
    int existEditorial(String nameEditorial);

    @Select("SELECT COUNT(ID) FROM EDITORIALES WHERE ID=#{editorialId}")
    int notExistEditorial(int editorialId);

    @Select("SELECT ID, NOMBRE, DIRECCION, TELEFONO, CORREO, FECHA_CREACION FROM EDITORIALES")
    @Result(property = "editorialId", column = "ID")
    @Result(property = "name", column = "NOMBRE")
    @Result(property = "address", column = "DIRECCION")
    @Result(property = "phone", column = "TELEFONO")
    @Result(property = "email", column = "CORREO")
    @Result(property = "creationDate", column = "FECHA_CREACION")
    List<Editorials> getEditorials();

    @Insert("INSERT INTO EDITORIALES (NOMBRE, DIRECCION, TELEFONO, CORREO, FECHA_CREACION) VALUES (#{name}, #{address}, #{phone}, #{email}, " +
            "SYSDATE)")
    @Options(useGeneratedKeys = true, keyProperty = "editorialId", keyColumn = "ID")
    void insertEditorial(Editorials editorials);

}
