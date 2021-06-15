package com.example.zemoso.Library.service;

import com.example.zemoso.Library.entity.Book;
import com.example.zemoso.Library.entity.User;
import com.example.zemoso.Library.entity.UserBookId;
import com.example.zemoso.Library.entity.UserLibrary;
import com.example.zemoso.Library.repository.BookRepository;
import com.example.zemoso.Library.repository.UserLibraryRepository;
import com.example.zemoso.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public User addNewUser(User user){
        User newUser=new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmailId(user.getEmailId());
        newUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(newUser);
        return newUser;
    }

   /* public User addBooksToUser(User user){
        User newUser=userRepository.findById(user.getUserId()).get();
        //User newUser=new User();
        /*newUser.setUserId(user.getUserId());
        newUser.setUserName(user.getUserName());
        newUser.setEmailId(user.getEmailId());
        newUser.setPhoneNumber(user.getPhoneNumber());
        List<UserLibrary> userBooks=new ArrayList<UserLibrary>();
        if(user.getBooks()!=null) {
            UserLibrary newUserLibrary=null;
            Optional<Book> book=null;
            for (UserLibrary userLibrary : user.getBooks()) {
                newUserLibrary=new UserLibrary(new UserBookId(newUser.getUserId(), userLibrary.getUserBookId().getBookId()));
                newUserLibrary.setUser(newUser);
                book=bookRepository.findById(userLibrary.getUserBookId().getBookId());
                if(book.isPresent()){
                    newUserLibrary.setBook(book.get());
                }
                userBooks.add(newUserLibrary);
            }
            newUser.setBooks(userBooks);

        }
        userRepository.save(newUser);
        return newUser;
    }*/

    public Optional<User> getUser(int userId){
        return userRepository.findById(userId);
    }
}
