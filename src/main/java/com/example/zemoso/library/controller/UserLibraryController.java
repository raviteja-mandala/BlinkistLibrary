package com.example.zemoso.library.controller;

import com.example.zemoso.library.dto.UserLibraryDto;
import com.example.zemoso.library.entity.UserLibrary;
import com.example.zemoso.library.exception.BookAlreadyInUserLibraryException;
import com.example.zemoso.library.exception.BookDoesNotExistInUserLibraryException;
import com.example.zemoso.library.service.UserLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserLibraryController {

    @Autowired
    private UserLibraryService userLibraryService;

    @PostMapping("/blinkist/userLibrary")
    public ResponseEntity<UserLibrary> addBookToUser(@RequestBody @Valid UserLibraryDto userLibraryDto) throws BookAlreadyInUserLibraryException {
        if (!(userLibraryService.getBookInUserLibrary(userLibraryDto.getUserId(),
                userLibraryDto.getBookId()).isEmpty())) {
            throw new BookAlreadyInUserLibraryException("Book is already added in user library");
        } else {
            return ResponseEntity.ok(userLibraryService.addBookToUser(userLibraryDto.getBookId(),
                    userLibraryDto.getUserId()));
        }
    }

    @DeleteMapping("/blinkist/userLibrary/{userId}/book/{bookId}")
    public ResponseEntity<String> deleteBookForUser(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId) throws BookDoesNotExistInUserLibraryException {
        if (userLibraryService.getBookInUserLibrary(userId,bookId).isEmpty()) {
            throw new BookDoesNotExistInUserLibraryException("Book does not exist in user library.");
        }
        else {
            userLibraryService.deleteBookForUser(userId, bookId);
            return new ResponseEntity<>("Book successfully deleted for user!", HttpStatus.OK);
        }
    }

}
