package com.example.zemoso.library.dto;

import javax.validation.constraints.NotNull;

public class BookAuthorDto {

    @NotNull(message="Book Id should be provided.")
    private Integer bookId;

    @NotNull(message="Author Id should be provided.")
    private Integer authorId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }


}
