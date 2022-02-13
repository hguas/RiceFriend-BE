package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
