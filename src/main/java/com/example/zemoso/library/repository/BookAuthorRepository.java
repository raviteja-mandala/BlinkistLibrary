package com.example.zemoso.library.repository;

import com.example.zemoso.library.entity.BookAuthor;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
    @Query(value="SELECT * FROM book_author WHERE author_id=:authorId AND book_id=:bookId",nativeQuery = true)
    List<BookAuthor> findByAuthorAndBook(int authorId, int bookId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM book_author WHERE author_id=:authorId AND book_id=:bookId",nativeQuery = true)
    void deleteByAuthorIdAndBookId(int authorId,int bookId);
}
