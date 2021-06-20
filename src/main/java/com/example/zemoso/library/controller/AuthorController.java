package com.example.zemoso.library.controller;

import com.example.zemoso.library.dto.AuthorDto;
import com.example.zemoso.library.entity.Author;
import com.example.zemoso.library.exception.AuthorAlreadyExistsException;
import com.example.zemoso.library.exception.BookNotFoundException;
import com.example.zemoso.library.service.AuthorService;
import com.example.zemoso.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @PostMapping("/blinkist/authors")
    public ResponseEntity<Author> addNewAuthor(@RequestBody @Valid AuthorDto authorDto) throws AuthorAlreadyExistsException, BookNotFoundException {
        Optional<Author> authorOptional = Optional.empty();
        Author author = null;
        if (authorDto.getAuthorId() != 0) {
            authorOptional = authorService.getAuthorById(authorDto.getAuthorId());

            if (authorOptional.isPresent()) {
                throw new AuthorAlreadyExistsException("Author already exists with Id : "+authorDto.getAuthorId()+".");
            } else {
                author = new Author();
                author.setAuthorId(authorDto.getAuthorId());
            }
        } else {
            author = new Author();
        }
        return new ResponseEntity<>(authorService.createAuthor(author, authorDto), HttpStatus.CREATED);
    }

    @GetMapping("/blinkist/authors")
    public ResponseEntity<List<Author>> getAllAuthors(){
        return new ResponseEntity<>(authorService.getAllAuthors(),HttpStatus.OK);
    }
}
