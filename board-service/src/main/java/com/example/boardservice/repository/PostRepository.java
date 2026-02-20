package com.example.boardservice.repository;

import com.example.boardservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 특정 사용자의 게시글 조회
    List<Post> findByUserId(Long userId);

    // 제목으로 검색 (제목에 키워드 포함)
    List<Post> findByTitleContaining(String keyword);
}