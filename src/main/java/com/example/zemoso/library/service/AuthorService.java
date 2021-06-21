package com.example.zemoso.library.service;

import com.example.zemoso.library.dto.AuthorDto;
import com.example.zemoso.library.entity.Author;
import com.example.zemoso.library.entity.Book;
import com.example.zemoso.library.entity.BookAuthor;
import com.example.zemoso.library.exception.BookNotFoundException;
import com.example.zemoso.library.repository.AuthorRepository;
import com.example.zemoso.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public Optional<Author> getAuthorById(int authorId) {
        return authorRepository.findById(authorId);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author createAuthor(Author author, AuthorDto authorDto) throws BookNotFoundException {
        author.setPhoneNumber(authorDto.getPhoneNumber());
        author.setName(authorDto.getName());
        author.setEmailId(authorDto.getEmailId());
        var bookAuthorsList= new ArrayList<BookAuthor>();
        Optional<Book> bookOptional;
        BookAuthor bookAuthor=null;
        for(var bookId : authorDto.getBookIds()){
            bookOptional=bookRepository.findById(bookId);
            if(bookOptional.isPresent()){
                bookAuthor=new BookAuthor();
                bookAuthor.setAuthor(author);
                bookAuthor.setBook(bookOptional.get());
                bookAuthor.setCreatedOn(new Date());
                bookAuthorsList.add(bookAuthor);
            }
            else{
                throw new BookNotFoundException("Book with book id :"+ bookId + " is not present");
            }
        }
        author.setAuthorBooks(bookAuthorsList);
        authorRepository.save(author);
        return author;
    }
}
