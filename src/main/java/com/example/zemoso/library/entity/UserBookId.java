package com.example.zemoso.library.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class UserBookId implements Serializable {
    public static final long SERIALID = 123344L;
    public UserBookId(){
        //no-arg constructor needed for entity
    }

    public UserBookId(int userId, int bookId){
        this.userId=userId;
        this.bookId=bookId;
    }

    private int userId;
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
