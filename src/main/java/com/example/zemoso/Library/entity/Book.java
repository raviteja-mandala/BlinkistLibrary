package com.example.zemoso.Library.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="BOOK")
public class Book implements Serializable {
    public static final long serialId=123344L;
    public Book(){

    }

    public Book(Book book){
        this.authorId=book.getAuthorId();
        this.bookId=book.getBookId();
        this.bookName=book.getBookName();
        this.categoryId=book.getCategoryId();
        this.categoryName=book.getCategoryName();
        this.createdBy="admin";
        this.createdOn=new Date();
    }
    @Id
    @GeneratedValue
    @Column(name="BOOK_ID")
    private int bookId;

    @Column(name="BOOK_NAME")
    private String bookName;

    @Column(name="CATEGORY_ID")
    private int categoryId;

    @Column(name="CATEGORY_NAME")
    private String categoryName;

    @Column(name="AUTHOR_ID")
    private int authorId;

    private String createdBy;
    @NotNull
    @Column(name="CREATION_DATE")
    private Date createdOn;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }





}
