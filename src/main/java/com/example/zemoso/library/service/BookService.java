package com.example.zemoso.library.service;

import com.example.zemoso.library.dto.BookDto;
import com.example.zemoso.library.entity.Author;
import com.example.zemoso.library.entity.Book;
import com.example.zemoso.library.entity.BookAuthor;
import com.example.zemoso.library.exception.AuthorNotFoundException;
import com.example.zemoso.library.repository.AuthorRepository;
import com.example.zemoso.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Book> getBooksByCategory(String categoryName){
        return bookRepository.findByCategoryName(categoryName);
    }

    public List<Book> getBooksByTitle(String title){
        log.info("inside book service, get books by Title");
        return bookRepository.findByTitleorAuthor(title,title);
    }

    public List<Book> getRecentlyAddedTitles(){
        return bookRepository.findAll(Sort.by("createdOn").descending());
    }

    public Optional<Book> getBookById(int bookId){
        return bookRepository.findById(bookId);
    }

    public Book createBook(Book book, BookDto bookDto) throws AuthorNotFoundException {
        book.setBookName(bookDto.getBookTitle());
        book.setCreatedBy("ADMIN");
        book.setCreatedOn(new Date());
        book.setCategoryId(bookDto.getCategoryId());
        book.setCategoryName(bookDto.getCategoryName());
        Optional<Author> authorOptional=null;
        BookAuthor bookAuthor=null;
        var listOfAuthorsForBook=new ArrayList<BookAuthor>();
        for(var authorId : bookDto.getAuthorIds()){
            authorOptional=authorRepository.findById(authorId);
            if(authorOptional.isPresent()){
                bookAuthor=new BookAuthor();
                bookAuthor.setBook(book);
                bookAuthor.setAuthor(authorOptional.get());
                bookAuthor.setCreatedOn(new Date());
                listOfAuthorsForBook.add(bookAuthor);
            }
            else{
                throw new AuthorNotFoundException("Author with author id :"+ authorId + " is not present");
            }
        }
        book.setBookAuthors(listOfAuthorsForBook);
        bookRepository.save(book);
        return book;
    }


}
