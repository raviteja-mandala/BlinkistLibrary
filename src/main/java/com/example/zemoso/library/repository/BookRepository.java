package com.example.zemoso.library.repository;

import com.example.zemoso.library.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByCategoryName(String categoryName);

    @Query(value = "Select * from book where book_name= :title OR book_id IN (SELECT book_id FROM book_author,author WHERE name= :authorName and " +
            "book_author.author_id=author.author_id)",nativeQuery = true)
    List<Book> findByTitleorAuthor(String title, String authorName);

    @Override
    List<Book> findAll(Sort sort);

}
