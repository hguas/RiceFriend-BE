package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
