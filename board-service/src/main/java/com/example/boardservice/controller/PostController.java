package com.example.boardservice.controller;

import com.example.boardservice.domain.Post;
import com.example.boardservice.dto.PostRequestDto;
import com.example.boardservice.dto.PostResponseDto;
import com.example.boardservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  // 게시글 작성
  @PostMapping
  public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto request) {
    PostResponseDto saved = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  // 게시글 목록 조회
  @GetMapping
  public ResponseEntity<List<PostResponseDto>> getAllPosts() {
    List<PostResponseDto> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }

  // 게시글 상세 조회
  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
    PostResponseDto post = postService.getPost(id);
    return ResponseEntity.ok(post);
  }

  // 게시글 수정
  @PutMapping("/{id}")
  public ResponseEntity<PostResponseDto> updatePost(
      @PathVariable Long id,
      @RequestBody PostRequestDto request) {
    PostResponseDto updated = postService.updatePost(id, request);
    return ResponseEntity.ok(updated);
  }

  // 게시글 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.noContent().build();
  }
}