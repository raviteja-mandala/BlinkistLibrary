package com.example.zemoso.library.repository;

import com.example.zemoso.library.entity.UserBookId;
import com.example.zemoso.library.entity.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, UserBookId> {

    @Query("SELECT u FROM UserLibrary u WHERE u.userBookId.userId=?1 AND u.userBookId.bookId=?2")
    List<UserLibrary> findByUserIdAndBookId(int userId, int bookId);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM user_book where user_id=:userId and book_id=:bookId",nativeQuery = true)
    void deleteByUserIdAndBookId(int userId, int bookId);

}
