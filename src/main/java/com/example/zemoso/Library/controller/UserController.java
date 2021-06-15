package com.example.zemoso.Library.controller;

import com.example.zemoso.Library.entity.Book;
import com.example.zemoso.Library.entity.User;
import com.example.zemoso.Library.entity.UserLibrary;
import com.example.zemoso.Library.service.BookService;
import com.example.zemoso.Library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @PostMapping("/blinkist/user")
    public ResponseEntity<User> addUsers(@RequestBody User user){
        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.CREATED);
    }

    /*@PostMapping("/blinkist/user/books")
    public ResponseEntity<User> addBooksToUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addBooksToUser(user), HttpStatus.CREATED);
    }*/

    @GetMapping("/blinkist/user/{userid}/books/{status}")
    public ResponseEntity<List<Book>> getBooksOfUser(@PathVariable("userid") int userId,@PathVariable("status") String status){
        Optional<User> userOptional=userService.getUser(userId);
        ResponseEntity<List<Book>> responseEntity=null;
        if(userOptional.isPresent()){
            User user=userOptional.get();
            if(user.getBooks().size()>0) {
                List<Book> bookListOfUser = new ArrayList<Book>();

                for (UserLibrary userLibrary : user.getBooks()) {
                    if(userLibrary.getStatus().equalsIgnoreCase(status)){
                        bookListOfUser.add(new Book(userLibrary.getBook()));
                    }
                }
                responseEntity=new ResponseEntity<>(bookListOfUser,HttpStatus.FOUND);
            }
            else {
                responseEntity=new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }
}
