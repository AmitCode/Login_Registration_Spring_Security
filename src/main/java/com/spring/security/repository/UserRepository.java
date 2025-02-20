package com.spring.security.repository;

import com.spring.security.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserEmail(String userEmail);
}
