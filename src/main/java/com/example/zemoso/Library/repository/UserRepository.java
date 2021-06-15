package com.example.zemoso.Library.repository;

import com.example.zemoso.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
