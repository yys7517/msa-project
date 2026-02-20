package com.example.boardservice.dto;

import com.example.boardservice.domain.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
  private Long id;
  private String title;
  private String content;
  private Long userId;
  private String username;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  // 엔티티 -> DTO 변환
  public static PostResponseDto from(Post post) {
    return PostResponseDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .userId(post.getUserId())
        .createdAt(post.getCreatedAt())
        .updatedAt(post.getUpdatedAt())
        .build();
  }

  public static PostResponseDto from(Post post, UserDto dto) {
    return PostResponseDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .userId(dto.getId())
        .username(dto.getUsername())
        .createdAt(post.getCreatedAt())
        .updatedAt(post.getUpdatedAt())
        .build();
  }
}