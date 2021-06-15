package com.example.zemoso.Library.controller;

import com.example.zemoso.Library.entity.Book;
import com.example.zemoso.Library.exception.BookAlreadyPresentException;
import com.example.zemoso.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blinkist")
public class BookController {
    @Autowired
    public BookService bookService;

    @GetMapping("/books/category/{categoryName}")
    public List<Book> getBooksByCategory(@PathVariable("categoryName") String categoryName){
            return bookService.getBooksByCategory(categoryName);
    }

    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitleorAuthor(@PathVariable("title") String title){
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/books")
    public List<Book> getRecentlyAddedTitles(){
        return bookService.getRecentlyAddedTitles();
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) throws BookAlreadyPresentException {
        List<Book> bookVariable=bookService.getBookById(book.getBookName(),book.getAuthorId());
        if(bookVariable.size()>0){
            throw new BookAlreadyPresentException("This book is already present");
        }
        else{
            return new ResponseEntity<>(bookService.createBook(book),HttpStatus.CREATED);
        }
    }
}
