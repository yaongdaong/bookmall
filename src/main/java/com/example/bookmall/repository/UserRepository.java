package com.example.bookmall.repository;


import com.example.bookmall.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 회원정보 저장, 수정
    User save(User user);
    // 회원정보 삭제
    void deleteById(Long id);
    // 회원정보 목록 조회
    List<User> findAll();
    // 회원정보 단일 조회
    Optional<User> findByUsername(String username);
    //
    // User findByUsername(String username);
}