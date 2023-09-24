package com.api.library.services.impl;

import com.api.library.exceptions.AtributteNotIsUniqueException;
import com.api.library.exceptions.BookNotFoundException;
import com.api.library.exceptions.EditorialNotFoundexception;
import com.api.library.exceptions.ListIsEmptyOrNullException;
import com.api.library.models.Books;
import com.api.library.models.Editorials;
import com.api.library.persistance.database.mappers.BooksMapper;
import com.api.library.persistance.database.mappers.EditorialsMapper;
import com.api.library.services.BooksService;
import com.api.library.services.Enum.Amount;
import com.api.library.services.utils.ValidationsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BooksServiceImpl implements BooksService {
    private final BooksMapper booksMapper;
    private final EditorialsMapper editorialsMapper;
    private final ValidationsUtils validationsUtils;

    public BooksServiceImpl(BooksMapper booksMapper, EditorialsMapper editorialsMapper) {
        this.booksMapper = booksMapper;
        this.editorialsMapper = editorialsMapper;
        validationsUtils = new ValidationsUtils();
    }

    @Override
    public void insertBook(Books books) {
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_100.getValue(), books.getTitle().length(), "title");
        validationsUtils.validateNotIsEmpty(books.getTitle(), "title");
        existEditorial(books.getEditorial());
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_17.getValue(), books.getIsbn().length(), "isbn");
        validationsUtils.validateNotIsEmpty(books.getIsbn(), "isbn");
        existIsbn(books.getIsbn());
        booksMapper.insertBook(books);
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
        existEditorial(books.getEditorial());
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_100.getValue(), books.getTitle().length(), "title");
        validationsUtils.validateNotIsEmpty(books.getTitle(), "title");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_17.getValue(), books.getIsbn().length(), "isbn");
        validationsUtils.validateNotIsEmpty(books.getIsbn(), "isbn");
        booksMapper.updateBook(books);
    }

    private void existBook(int libroId) {
        if (booksMapper.existBook(libroId) < 1) {
            throw new BookNotFoundException("book", HttpStatus.NOT_FOUND);
        }
    }

    private void existEditorial(Editorials editorials) {
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
