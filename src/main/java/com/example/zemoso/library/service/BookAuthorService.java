package com.example.zemoso.library.service;

import com.example.zemoso.library.dto.BookAuthorDto;
import com.example.zemoso.library.entity.Author;
import com.example.zemoso.library.entity.Book;
import com.example.zemoso.library.entity.BookAuthor;
import com.example.zemoso.library.exception.AuthorNotFoundException;
import com.example.zemoso.library.exception.BookNotFoundException;
import com.example.zemoso.library.repository.AuthorRepository;
import com.example.zemoso.library.repository.BookAuthorRepository;
import com.example.zemoso.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookAuthorService {

    @Autowired
    BookAuthorRepository bookAuthorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<BookAuthor> getAuthorsOfBook(int authorId, int bookId) {
        return bookAuthorRepository.findByAuthorAndBook(authorId, bookId);
    }

    public BookAuthor addAuthorToBook(BookAuthor bookAuthor, BookAuthorDto bookAuthorDto) throws BookNotFoundException, AuthorNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(bookAuthorDto.getBookId());
        if (bookOptional.isPresent()) {
            bookAuthor.setBook(bookOptional.get());
        } else {
            throw new BookNotFoundException("Book with id " + bookAuthorDto.getBookId() + " does not exist.");
        }
        Optional<Author> authorOptional = authorRepository.findById(bookAuthorDto.getAuthorId());
        if (authorOptional.isPresent()) {
            bookAuthor.setAuthor(authorOptional.get());
        } else {
            throw new AuthorNotFoundException("Author with id " + bookAuthorDto.getAuthorId() + " does not exist.");
        }
        bookAuthor.setCreatedOn(new Date());
        return bookAuthorRepository.save(bookAuthor);
    }

    public void deleteAuthorForBook(int authorId, int bookId) {
        bookAuthorRepository.deleteByAuthorIdAndBookId(authorId, bookId);
    }

}
