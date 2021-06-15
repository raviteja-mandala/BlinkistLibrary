package com.example.zemoso.Library.service;

import com.example.zemoso.Library.entity.UserBookId;
import com.example.zemoso.Library.entity.UserLibrary;
import com.example.zemoso.Library.repository.BookRepository;
import com.example.zemoso.Library.repository.UserLibraryRepository;
import com.example.zemoso.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserLibraryService {

    @Autowired
    public UserLibraryRepository userLibraryRepository;

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public UserRepository userRepository;

    public UserLibrary addBookToUser(int bookId, int userId){
        UserLibrary newUserLibrary=new UserLibrary(new UserBookId(userId,bookId));
        newUserLibrary.setBook(bookRepository.findById(bookId).get());
        newUserLibrary.setUser(userRepository.findById(userId).get());
        return userLibraryRepository.save(newUserLibrary);
    }

    public void deleteBookForUser(int userId, int bookId){
        userLibraryRepository.deleteById(new UserBookId(userId,bookId));
    }

    public List<UserLibrary> getBookInUserLibrary(int userId, int bookId){
        return userLibraryRepository.findByUserIdAndBookId(userId, bookId);
    }
}
