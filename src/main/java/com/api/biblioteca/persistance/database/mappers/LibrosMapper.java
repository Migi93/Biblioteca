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

    @Select("SELECT ID, TITULO, EDITORIAL_ID, FECHA_PUBLICACION, ISBN FROM LIBROS ORDER BY ID ASC")
    @Results({
            @Result(property = "libroId", column = "ID"),
            @Result(property = "titulo", column = "TITULO"),
            @Result(property = "editorial.editorialesId", column = "EDITORIAL_ID"),
            @Result(property = "fechaPublicacion", column = "FECHA_PUBLICACION"),
            @Result(property = "isbn", column = "ISBN")
    })
    List<Libros> getListLibros();

    @Select("SELECT ID, TITULO, EDITORIAL_ID, FECHA_PUBLICACION, ISBN FROM LIBROS WHERE ID=#{libroId}")
    @Results({
            @Result(property = "libroId", column = "ID"),
            @Result(property = "titulo", column = "TITULO"),
            @Result(property = "editorial.editorialesId", column = "EDITORIAL_ID"),
            @Result(property = "fechaPublicacion", column = "FECHA_PUBLICACION"),
            @Result(property = "isbn", column = "ISBN")
    })
    Libros getLibro(int libroId);

    @Select("SELECT COUNT(ID) FROM LIBROS WHERE ID=#{libroId}")
    int existeLibro(int libroId);

    @Delete("DELETE FROM LIBROS WHERE ID =#{libroId}")
    void deleteBook(int libroId);
}
