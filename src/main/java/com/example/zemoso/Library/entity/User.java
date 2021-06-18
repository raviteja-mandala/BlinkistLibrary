package com.example.zemoso.Library.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class User {

    public User(){
    }
    @Id
    @Column(name="USER_ID")
    @GeneratedValue
    private int userId;

    @NotNull
    @Column(name="USER_NAME")
    private String userName;

    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    @Column(name="EMAIL_ID")
    private String emailId;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserLibrary> books=new ArrayList<UserLibrary>();

    public List<UserLibrary> getBooks() {
        return books;
    }

    public void setBooks(List<UserLibrary> books) {
        this.books = books;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
