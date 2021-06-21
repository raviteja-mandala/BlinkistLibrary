package com.example.zemoso.library.controller;

import com.example.zemoso.library.dto.BookDto;
import com.example.zemoso.library.entity.Book;
import com.example.zemoso.library.exception.AuthorNotFoundException;
import com.example.zemoso.library.exception.BookAlreadyPresentException;
import com.example.zemoso.library.service.BookService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blinkist")
@Slf4j
public class BookController {
    @Autowired
    public BookService bookService;

    @GetMapping("/books/category/{categoryName}")
    @ApiOperation(value = "Get books by category", notes = "provide category name in the url", response = Book.class)
    public List<Book> getBooksByCategory(@PathVariable("categoryName") String categoryName) {
        log.trace("getting all books category wise.");
        return bookService.getBooksByCategory(categoryName);
    }

    @GetMapping("/books/title/{title}")
    @ApiOperation(value = "Get books by title", notes = "provide title name in the url", response = Book.class)
    public List<Book> getBooksByTitleorAuthor(@PathVariable("title") String title) {
        log.debug("getting all books title wise.");
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/books")
    public List<Book> getRecentlyAddedTitles() {
        return bookService.getRecentlyAddedTitles();
    }

    //Below api is used to create books
    @PostMapping("/books")
    public ResponseEntity<Book> addNewBook(@RequestBody @Valid BookDto bookDto) throws BookAlreadyPresentException, AuthorNotFoundException {
        Optional<Book> bookOptional;
        Book book=null;
        if(bookDto.getBookId()!=0) {
            bookOptional = bookService.getBookById(bookDto.getBookId());

            if (bookOptional.isPresent()){
                log.error("Book already exists");
                throw new BookAlreadyPresentException("Book already exists with this Id.");
            }
            else{
                book=new Book();
                book.setBookId(bookDto.getBookId());
            }
        }else {
            book = new Book();
        }
            return new ResponseEntity<>(bookService.createBook(book,bookDto), HttpStatus.CREATED);

    }
}
