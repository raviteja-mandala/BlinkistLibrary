package com.example.zemoso.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    public Author() {
        ////no-arg constructor needed for entity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID")
    private int authorId;


    @NonNull
    private String name;

    @Column(name = "EMAIL_ID")
    private String emailId;

    public List<BookAuthor> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<BookAuthor> authorBooks) {
        this.authorBooks = authorBooks;
    }

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<BookAuthor> authorBooks;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

}
