package com.example.zemoso.Library.repository;

import com.example.zemoso.Library.entity.UserBookId;
import com.example.zemoso.Library.entity.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, UserBookId> {

    @Query("SELECT u FROM UserLibrary u WHERE u.userBookId.userId=?1 AND u.userBookId.bookId=?2")
    List<UserLibrary> findByUserIdAndBookId(int userId, int bookId);

}
