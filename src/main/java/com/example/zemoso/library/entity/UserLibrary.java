package com.example.zemoso.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_BOOK")
public class UserLibrary {
    public static final long serialId = 12465778L;

    public UserLibrary() {
        //no-arg constructor needed for entity
    }

    public UserLibrary(UserBookId userBookId) {
        this.userBookId = userBookId;
        this.lastUpdateDate = new Date();
    }

    @EmbeddedId
    private UserBookId userBookId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    //@Column(columnDefinition = "varchar(255) default 'CR'")
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserBookId getUserBookId() {
        return userBookId;
    }

    public void setUserBookId(UserBookId userBookId) {
        this.userBookId = userBookId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


}
