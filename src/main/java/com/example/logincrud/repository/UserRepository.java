package com.example.logincrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.logincrud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
}