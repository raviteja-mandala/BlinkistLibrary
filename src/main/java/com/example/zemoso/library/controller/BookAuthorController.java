package com.example.zemoso.library.controller;

import com.example.zemoso.library.dto.BookAuthorDto;
import com.example.zemoso.library.entity.BookAuthor;
import com.example.zemoso.library.exception.AuthorAlreadyAddedToBookException;
import com.example.zemoso.library.exception.AuthorNotAssignedForBookException;
import com.example.zemoso.library.exception.AuthorNotFoundException;
import com.example.zemoso.library.exception.BookNotFoundException;
import com.example.zemoso.library.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookAuthorController {
    @Autowired
    BookAuthorService bookAuthorService;

    @PostMapping("/blinkist/book/author")
    public ResponseEntity<BookAuthor> addAuthorToBook(@RequestBody @Valid BookAuthorDto bookAuthorDto) throws AuthorAlreadyAddedToBookException, AuthorNotFoundException, BookNotFoundException {
        if (!(bookAuthorService.getAuthorsOfBook(bookAuthorDto.getAuthorId(), bookAuthorDto.getBookId()).isEmpty())) {
            throw new AuthorAlreadyAddedToBookException("Author Id " + bookAuthorDto.getAuthorId() + " is already added to book with id " + bookAuthorDto.getBookId());
        } else {
            var bookAuthor = new BookAuthor();
            return new ResponseEntity<>(bookAuthorService.addAuthorToBook(bookAuthor, bookAuthorDto), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/blinkist/book/{bookId}/author/{authorId}")
    public ResponseEntity<String> deleteAuthorForBook(@PathVariable("bookId") int bookId, @PathVariable("authorId") int authorId) throws AuthorNotAssignedForBookException {
        if (bookAuthorService.getAuthorsOfBook(authorId, bookId).isEmpty()) {
            throw new AuthorNotAssignedForBookException("Author Id " + authorId + " is not author for book with id " + bookId);
        } else {
            bookAuthorService.deleteAuthorForBook(authorId, bookId);
            return new ResponseEntity<>("Author " + authorId + " is removed from list of authors for book with id " + bookId, HttpStatus.OK);
        }
    }
}
