package com.example.zemoso.Library.repository;

import com.example.zemoso.Library.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    //@Query("Select u FROM Book u where categoryName= ?1")
    List<Book> findByCategoryName(String categoryName);

    @Query(value = "Select * from book where book_name= :title OR author_id IN (SELECT author_id FROM author WHERE name= :authorName)",nativeQuery = true)
    List<Book> findByTitleorAuthor(String title, String authorName);

    @Override
    List<Book> findAll(Sort sort);

   // List<Book> findAllOrderByCreatedOnDesc();

    List<Book> findByBookNameAndAuthorId(String bookName, Integer authorId);
}
