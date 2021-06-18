package com.example.zemoso.library.service;

import com.example.zemoso.library.entity.Author;
import com.example.zemoso.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Optional<Author> getAuthorById(int authorId) {
        return authorRepository.findById(authorId);
    }

    public Author createAuthor(Author author) {
        var newAuthor = new Author(author);
        authorRepository.save(newAuthor);
        return newAuthor;
    }
}
