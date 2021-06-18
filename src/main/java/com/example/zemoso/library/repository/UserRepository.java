package com.example.zemoso.library.repository;

import com.example.zemoso.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
