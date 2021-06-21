package com.example.zemoso.library.service;

import com.example.zemoso.library.dto.UserDto;
import com.example.zemoso.library.entity.User;
import com.example.zemoso.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addNewUser(UserDto userDto) {
        var newUser = new User();
        newUser.setUserId(userDto.getUserId());
        newUser.setUserName(userDto.getName());
        newUser.setEmailId(userDto.getEmailId());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }
}
