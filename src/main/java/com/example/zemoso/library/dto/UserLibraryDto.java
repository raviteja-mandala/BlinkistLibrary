package com.example.zemoso.library.dto;

import javax.validation.constraints.NotNull;

public class UserLibraryDto {
    @NotNull(message = "User id should be provided.")
    private int userId;
    @NotNull(message = "Book Id should be provided.")
    private int bookId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
