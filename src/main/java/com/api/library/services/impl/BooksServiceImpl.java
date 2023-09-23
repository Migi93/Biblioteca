package com.api.library.services.impl;

import com.api.library.exceptions.*;
import com.api.library.models.Books;
import com.api.library.models.Editorials;
import com.api.library.persistance.database.mappers.BooksMapper;
import com.api.library.persistance.database.mappers.EditorialsMapper;
import com.api.library.services.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BooksServiceImpl implements BooksService {

    BooksMapper booksMapper;
    EditorialsMapper editorialsMapper;

    public BooksServiceImpl(BooksMapper booksMapper, EditorialsMapper editorialsMapper) {
        this.booksMapper = booksMapper;
        this.editorialsMapper = editorialsMapper;
    }

    @Override
    public void insertBook(Books books) {
        validateNameBook(books);
        existEditorialOrNull(books.getEditorial());
        existIsbn(books.getIsbn());
        this.booksMapper.insertBook(books);
    }

    @Override
    public Books getBook(int libroId) throws BookNotFoundException {
        existBook(libroId);
        return booksMapper.getBook(libroId);
    }

    @Override
    public List<Books> listBooks() {
        emptyList();
        return booksMapper.getBooks();
    }

    @Override
    public void deleteBook(int bookId) {
        existBook(bookId);
        booksMapper.deleteBook(bookId);
    }

    @Override
    public void updateBook(Books books) {
        existBook(books.getBookId());
        existEditorialOrNull(books.getEditorial());
        validateNameBook(books);
        booksMapper.updateBook(books);
    }

    //VALIDACIONES
    private void validateNameBook(Books books) {
        if (books.getTitle() == null || books.getTitle().isEmpty()) {
            throw new RequiredMissingFieldException("title", HttpStatus.BAD_REQUEST);
        }
        if (books.getTitle().length() > 100) {
            throw new WorngLengthFieldException("title", HttpStatus.PAYLOAD_TOO_LARGE);
        }
    }

    private void existBook(int libroId) {
        if (booksMapper.existBook(libroId) < 1) {
            throw new BookNotFoundException("book", HttpStatus.NOT_FOUND);
        }
    }

    private void existEditorialOrNull(Editorials editorials) {
        if (editorialsMapper.notExistEditorial(editorials.getEditorialId()) < 1) {
            throw new EditorialNotFoundexception("editorial", HttpStatus.NOT_FOUND);
        }
    }

    private void existIsbn(String isbn) {
        if (booksMapper.existIsbn(isbn) > 0) {
            throw new AtributteNotIsUniqueException("isbn", HttpStatus.CONFLICT);
        }
    }

    private void emptyList() {
        if (booksMapper.emptyList() < 1) {
            throw new ListIsEmptyOrNullException("books", HttpStatus.NOT_FOUND);
        }
    }

}
