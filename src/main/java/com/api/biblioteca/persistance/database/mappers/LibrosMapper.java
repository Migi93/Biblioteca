package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Libros;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LibrosMapper {

    @Insert("INSERT INTO LIBROS (TITULO, EDITORIAL_ID, FECHA_PUBLICACION, ISBN) VALUES (#{titulo}, #{editorial.editorialesId}, #{fechaPublicacion, jdbcType=DATE}, "
            + "#{isbn})")
    @Options(useGeneratedKeys = true, keyProperty = "libroId", keyColumn = "ID")
    void insertarLibro(Libros libros);

    @Select("SELECT ID, TITULO, EDITORIAL_ID, FECHA_PUBLICACION, isbn FROM LIBROS WHERE ID=#{libroId}")
    Libros getLibro(int libroId);

    @Select("SELECT COUNT(ID) FROM LIBROS WHERE ID=#{libroId}")
    int existeLibro(int libroId);
}
