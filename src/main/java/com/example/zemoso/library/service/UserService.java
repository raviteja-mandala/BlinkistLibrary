package com.example.zemoso.library.service;

import com.example.zemoso.library.entity.User;
import com.example.zemoso.library.repository.BookRepository;
import com.example.zemoso.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public User addNewUser(User user) {
        var newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmailId(user.getEmailId());
        newUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }
}
