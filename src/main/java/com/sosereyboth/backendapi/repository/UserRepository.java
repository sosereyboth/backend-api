package com.sosereyboth.backendapi.repository;

import com.sosereyboth.backendapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String username);
}
