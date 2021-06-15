package com.example.zemoso.Library.controller;

import com.example.zemoso.Library.entity.UserLibrary;
import com.example.zemoso.Library.service.UserLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLibraryController {

    @Autowired
    private UserLibraryService userLibraryService;
    @PostMapping("/blinkist/userLibrary")
    public ResponseEntity<UserLibrary>  addBookToUser(@RequestBody UserLibrary userLibrary){
        return ResponseEntity.ok(userLibraryService.addBookToUser(userLibrary.getUserBookId().getBookId(),
                userLibrary.getUserBookId().getUserId()));
    }

    @DeleteMapping("/blinkist/userLibrary/{userId}/{bookId}")
    public ResponseEntity<String> deleteBookForUser(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId){
        if(userLibraryService.getBookInUserLibrary(userId,bookId).size()==0){
            return new ResponseEntity<>("Book not found in user library",HttpStatus.NOT_FOUND);
        }
        else{
            userLibraryService.deleteBookForUser(userId,bookId);
            return new ResponseEntity<>("Book successfully deleted for user!",HttpStatus.CREATED);
        }

    }

}
