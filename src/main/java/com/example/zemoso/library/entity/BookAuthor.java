package com.example.zemoso.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="BOOK_AUTHOR")
public class BookAuthor {
    public static final long SERIALID = 123344L;
    public BookAuthor(){
        // no arg constructor needed for entity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    @JsonIgnore
    private Author author;

    @Column(name= "CREATED_ON")
    private Date createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}
