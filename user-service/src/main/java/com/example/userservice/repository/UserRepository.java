package com.example.userservice.repository;

import com.example.userservice.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // username으로 사용자 찾기
    Optional<User> findByUsername(String username);

    // email로 사용자 찾기
    Optional<User> findByEmail(String email);

    // username 중복 체크
    boolean existsByUsername(String username);

    // email 중복 체크
    boolean existsByEmail(String email);
}