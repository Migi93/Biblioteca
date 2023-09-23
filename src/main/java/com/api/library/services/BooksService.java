package com.api.library.services;

import com.api.library.models.Books;

import java.util.List;

public interface BooksService {
    void insertBook(Books books);

    Books getBook(int libroId);

    List<Books> listBooks();

    void deleteBook(int libroId);

    void updateBook(Books books);
}
