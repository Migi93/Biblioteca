package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Libros;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LibrosMapper {

    @Insert("INSERT INTO libros (titulo, editorial_id, fecha_publicacion, isbn) VALUES (#{titulo}, #{editorial.editorialesId}, #{fechaPublicacion, jdbcType=DATE}, "
            + "#{isbn})")
    @Options(useGeneratedKeys = true, keyProperty = "libroId", keyColumn = "ID")
    void insertarLibro(Libros libros);

    @Select("SELECT * FROM libros WHERE id=#{libroId}")
    Libros getLibro(int libroId);

    @Select("SELECT COUNT(id) FROM libros WHERE id=#{libroId}")
    int existeLibro(int libroId);
}
