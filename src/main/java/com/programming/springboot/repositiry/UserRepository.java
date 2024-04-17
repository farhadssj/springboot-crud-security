package com.programming.springboot.repositiry;

import com.programming.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
