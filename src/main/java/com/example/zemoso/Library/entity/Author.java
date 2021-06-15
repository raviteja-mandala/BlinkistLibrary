package com.example.zemoso.Library.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {

    public Author(){

    }
    public Author(Author author){
        this.authorId=author.getAuthorId();
        this.name=author.getName();
        this.phoneNumber=author.getPhoneNumber();
        this.emailId=author.getEmailId();
    }
    @Id
    @GeneratedValue
    @Column(name="AUTHOR_ID")
    private int authorId;


    @NotNull
    private String name;

    @Column(name="EMAIL_ID")
    private String emailId;

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

    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

}
