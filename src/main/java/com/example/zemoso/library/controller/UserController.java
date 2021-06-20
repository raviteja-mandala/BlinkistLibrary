package com.example.zemoso.library.controller;

import com.example.zemoso.library.dto.UserDto;
import com.example.zemoso.library.entity.Book;
import com.example.zemoso.library.entity.User;
import com.example.zemoso.library.entity.UserLibrary;
import com.example.zemoso.library.exception.UserAlreadyPresentException;
import com.example.zemoso.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserService userService;

    @PostMapping("/blinkist/user")
    public ResponseEntity<User> addUsers(@RequestBody @Valid UserDto userDto) throws UserAlreadyPresentException {
        if(userDto.getUserId()!=0) {
            Optional<User> userOptional=userService.getUser(userDto.getUserId());
            if(userOptional.isPresent()){
                throw new UserAlreadyPresentException("User already exists with user id "+userDto.getUserId());
            }
        }
        return new ResponseEntity<>(userService.addNewUser(userDto), HttpStatus.CREATED);
    }

    @Nullable
    @GetMapping("/blinkist/user/{userid}/books/{status}")
    public  ResponseEntity<List<Book>> getBooksOfUser(@PathVariable("userid") int userId, @PathVariable("status") String status) {
        Optional<User> userOptional = userService.getUser(userId);
        ResponseEntity<List<Book>> responseEntity = null;
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            if (!(user.getBooks().isEmpty())) {
                List<Book> bookListOfUser = new ArrayList<>();

                for (UserLibrary userLibrary : user.getBooks()) {
                    if ("All".equalsIgnoreCase(status) || userLibrary.getStatus().equalsIgnoreCase(status)) {
                        bookListOfUser.add(userLibrary.getBook());
                    }
                }
                responseEntity = new ResponseEntity<>(bookListOfUser, HttpStatus.FOUND);
            } else {
                log.trace("There are no books for this user.");
                responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }
}
