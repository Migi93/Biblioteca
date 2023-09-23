package com.api.library.persistance.database.mappers;

import com.api.library.models.Books;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface BooksMapper {

    @Insert("INSERT INTO LIBROS (TITULO, EDITORIAL_ID, FECHA_PUBLICACION, ISBN) VALUES (#{title}, #{editorial.editorialId}, #{publicationDate, jdbcType=DATE}, "
            + "#{isbn})")
    @Options(useGeneratedKeys = true, keyProperty = "bookId", keyColumn = "ID")
    void insertBook(Books books);

    @Select("SELECT l.ID, l.TITULO, l.EDITORIAL_ID, l.FECHA_PUBLICACION, l.ISBN, e.ID, e.NOMBRE, e.DIRECCION, e.TELEFONO, e.CORREO, e.CORREO, e.FECHA_CREACION "
            + "FROM LIBROS l INNER JOIN EDITORIALES e ON l.EDITORIAL_ID = e.ID ORDER BY l.ID")
    @Result(property = "bookId", column = "ID")
    @Result(property = "title", column = "TITULO")
    @Result(property = "editorial.editorialId", column = "EDITORIAL_ID")
    @Result(property = "publicationDate", column = "FECHA_PUBLICACION")
    @Result(property = "isbn", column = "ISBN")
    @Result(property = "editorial.name", column = "NOMBRE")
    @Result(property = "editorial.address", column = "DIRECCION")
    @Result(property = "editorial.phone", column = "TELEFONO")
    @Result(property = "editorial.email", column = "CORREO")
    @Result(property = "editorial.creationDate", column = "FECHA_CREACION")
    List<Books> getBooks();

    @Select("SELECT l.ID, l.TITULO, l.EDITORIAL_ID, l.FECHA_PUBLICACION, l.ISBN, e.ID, e.NOMBRE, e.DIRECCION, e.TELEFONO, e.CORREO, e.CORREO, e.FECHA_CREACION "
            + "FROM LIBROS l INNER JOIN EDITORIALES e ON l.EDITORIAL_ID = e.ID WHERE l.ID = #{bookId}")
    @Result(property = "bookId", column = "ID")
    @Result(property = "title", column = "TITULO")
    @Result(property = "editorial.editorialId", column = "EDITORIAL_ID")
    @Result(property = "publicationDate", column = "FECHA_PUBLICACION")
    @Result(property = "isbn", column = "ISBN")
    @Result(property = "editorial.name", column = "NOMBRE")
    @Result(property = "editorial.address", column = "DIRECCION")
    @Result(property = "editorial.phone", column = "TELEFONO")
    @Result(property = "editorial.email", column = "CORREO")
    @Result(property = "editorial.creationDate", column = "FECHA_CREACION")
    Books getBook(int bookId);

    @Select("SELECT COUNT(ID) FROM LIBROS WHERE ID = #{bookId}")
    int existBook(int bookId);

    @Delete("DELETE FROM LIBROS WHERE ID = #{bookId}")
    void deleteBook(int bookId);

    @Select("SELECT COUNT(ISBN) FROM LIBROS WHERE ISBN = #{isbn}")
    int existIsbn(String isbn);

    @Select("SELECT COUNT(*) FROM LIBROS")
    int emptyList();

    @Update("UPDATE LIBROS SET TITULO = #{title}, EDITORIAL_ID = #{editorial.editorialId}, FECHA_PUBLICACION = #{publicationDate, jdbcType=DATE}, ISBN = #{isbn} " +
            "WHERE ID = #{bookId}")
    void updateBook(Books books);
}
