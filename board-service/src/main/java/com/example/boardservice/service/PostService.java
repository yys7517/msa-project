package com.example.boardservice.service;

import com.example.boardservice.client.UserClient;
import com.example.boardservice.domain.Post;
import com.example.boardservice.dto.PostRequestDto;
import com.example.boardservice.dto.PostResponseDto;
import com.example.boardservice.dto.UserDto;
import com.example.boardservice.repository.PostRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

  private final PostRepository postRepository;
  private final UserClient userClient;

  // 게시글 작성
  @Transactional
  public PostResponseDto createPost(PostRequestDto request) {
    Post post = Post.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .userId(request.getUserId())
        .build();

    Post saved = postRepository.save(post);
    UserDto user = userClient.getUser(saved.getUserId());

    return PostResponseDto.from(saved, user);
  }

  // 게시글 목록 조회
  public List<PostResponseDto> getAllPosts() {

    return postRepository.findAll().stream()
        .map(post -> {
          UserDto user = userClient.getUser(post.getUserId());
          return PostResponseDto.from(post, user);
        })
        .collect(Collectors.toList());
  }

  // 게시글 상세 조회
  public PostResponseDto getPost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    // 상세 조회 시에도 작성자 정보가 필요하다면 userClient를 호출하세요.
    UserDto user = userClient.getUser(post.getUserId());
    return PostResponseDto.from(post, user);
  }

  // 게시글 수정 (반환 타입 변경)
  @Transactional
  public PostResponseDto updatePost(Long id, PostRequestDto request) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    // Dirty Checking에 의해 트랜잭션 종료 시 DB에 반영됨
    post.update(request.getTitle(), request.getContent());

    // 수정된 데이터와 함께 유저 정보 조회 후 반환
    UserDto user = userClient.getUser(post.getUserId());
    return PostResponseDto.from(post, user);
  }

  // 게시글 삭제 (반환 타입 변경 - 삭제된 정보 확인용)
  @Transactional
  public void deletePost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    postRepository.delete(post);
  }
}