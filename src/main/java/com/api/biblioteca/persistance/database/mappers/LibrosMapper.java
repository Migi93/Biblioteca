package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Libros;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface LibrosMapper {

    @Insert("INSERT INTO libros (titulo, editorial_id, fecha_publicacion, isbn) VALUES (#{titulo}, #{editorial.editorialesId}, #{fechaPublicacion, jdbcType=DATE}, " +
            "#{isbn})")
    @Options(useGeneratedKeys = true, keyProperty = "libroId", keyColumn = "ID")
    void insertarLibro(Libros libros);
}
