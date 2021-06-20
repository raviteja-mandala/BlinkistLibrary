package com.example.zemoso.library.service;

import com.example.zemoso.library.entity.Book;
import com.example.zemoso.library.entity.User;
import com.example.zemoso.library.entity.UserBookId;
import com.example.zemoso.library.entity.UserLibrary;
import com.example.zemoso.library.repository.BookRepository;
import com.example.zemoso.library.repository.UserLibraryRepository;
import com.example.zemoso.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserLibraryService {

    @Autowired
    public UserLibraryRepository userLibraryRepository;

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public UserRepository userRepository;

    public UserLibrary addBookToUser(int bookId, int userId) {
        UserLibrary newUserLibrary = new UserLibrary(new UserBookId(userId, bookId));
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            newUserLibrary.setBook(bookOptional.get());
        }
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            newUserLibrary.setUser(userOptional.get());
        }
        return userLibraryRepository.save(newUserLibrary);
    }


    public void deleteBookForUser(int userId, int bookId) {
        userLibraryRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    public List<UserLibrary> getBookInUserLibrary(int userId, int bookId) {
        return userLibraryRepository.findByUserIdAndBookId(userId, bookId);
    }
}
