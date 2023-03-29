package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Libros;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface LibrosMapper {

    @Insert("INSERT INTO LIBROS (TITULO, EDITORIAL_ID, FECHA_PUBLICACION, ISBN) VALUES (#{titulo}, #{editorial.editorialesId}, #{fechaPublicacion, jdbcType=DATE}, "
            + "#{isbn})")
    @Options(useGeneratedKeys = true, keyProperty = "libroId", keyColumn = "ID")
    void insertarLibro(Libros libros);

    @Select("SELECT l.ID, l.TITULO, l.EDITORIAL_ID, l.FECHA_PUBLICACION, l.ISBN, e.ID, e.NOMBRE, e.DIRECCION, e.TELEFONO, e.CORREO, e.CORREO, e.FECHA_CREACION "
            + "FROM LIBROS l INNER JOIN EDITORIALES e ON l.EDITORIAL_ID = e.ID ORDER BY l.ID")
    @Results({
            @Result(property = "libroId", column = "ID"),
            @Result(property = "titulo", column = "TITULO"),
            @Result(property = "editorial.editorialesId", column = "EDITORIAL_ID"),
            @Result(property = "fechaPublicacion", column = "FECHA_PUBLICACION"),
            @Result(property = "isbn", column = "ISBN"),
            @Result(property = "editorial.nombre", column = "NOMBRE"),
            @Result(property = "editorial.direccion", column = "DIRECCION"),
            @Result(property = "editorial.telefono", column = "TELEFONO"),
            @Result(property = "editorial.email", column = "CORREO"),
            @Result(property = "editorial.fechaCreacion", column = "FECHA_CREACION")
    })
    List<Libros> getListLibros();

    @Select("SELECT l.ID, l.TITULO, l.EDITORIAL_ID, l.FECHA_PUBLICACION, l.ISBN, e.ID, e.NOMBRE, e.DIRECCION, e.TELEFONO, e.CORREO, e.CORREO, e.FECHA_CREACION "
            + "FROM LIBROS l INNER JOIN EDITORIALES e ON l.EDITORIAL_ID = e.ID WHERE l.ID = #{libroId}")
    @Results({
            @Result(property = "libroId", column = "ID"),
            @Result(property = "titulo", column = "TITULO"),
            @Result(property = "editorial.editorialesId", column = "EDITORIAL_ID"),
            @Result(property = "fechaPublicacion", column = "FECHA_PUBLICACION"),
            @Result(property = "isbn", column = "ISBN"),
            @Result(property = "editorial.nombre", column = "NOMBRE"),
            @Result(property = "editorial.direccion", column = "DIRECCION"),
            @Result(property = "editorial.telefono", column = "TELEFONO"),
            @Result(property = "editorial.email", column = "CORREO"),
            @Result(property = "editorial.fechaCreacion", column = "FECHA_CREACION")
    })
    Libros getLibro(int libroId);

    @Select("SELECT COUNT(ID) FROM LIBROS WHERE ID = #{libroId}")
    int existeLibro(int libroId);

    @Delete("DELETE FROM LIBROS WHERE ID = #{libroId}")
    void deleteBook(int libroId);

    @Select("SELECT COUNT(ISBN) FROM LIBROS WHERE ID = #{libroId}")
    @Options(useGeneratedKeys = true, keyProperty = "libroId", keyColumn = "ID")
    int existeIsbn(int libroId);
}
