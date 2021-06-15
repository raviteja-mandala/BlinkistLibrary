package com.example.zemoso.Library.service;

import com.example.zemoso.Library.entity.Author;
import com.example.zemoso.Library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Optional<Author> getAuthorById(int authorId){
        return authorRepository.findById(authorId);
    }

    public Author createAuthor(Author author){
        Author newAuthor=new Author(author);
        authorRepository.save(newAuthor);
        return newAuthor;
    }
}
