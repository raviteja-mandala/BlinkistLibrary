package com.example.zemoso.Library.controller;

import com.example.zemoso.Library.entity.Author;
import com.example.zemoso.Library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/blinkist/author")
    public ResponseEntity<Author> addNewAuthor(@RequestBody Author author){
        Optional<Author> authorVariable=authorService.getAuthorById(author.getAuthorId());
        if(!(authorVariable.isPresent())){
            return new ResponseEntity<>(authorService.createAuthor(author),HttpStatus.CREATED);

        }
        else{
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
}
