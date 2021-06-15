package com.example.zemoso.Library.service;

import com.example.zemoso.Library.entity.Book;
import com.example.zemoso.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooksByCategory(String categoryName){
        return bookRepository.findByCategoryName(categoryName);
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findByTitleorAuthor(title,title);
    }

    public List<Book> getRecentlyAddedTitles(){
        return bookRepository.findAll(Sort.by("createdOn").descending());
    }

    public List<Book> getBookById(String title,int authorId){
        return bookRepository.findByBookNameAndAuthorId(title,authorId);
    }

    public Book createBook(Book book){
        Book newBook=new Book(book);
        bookRepository.save(newBook);
        return newBook;
    }


}
