package com.example.boardservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  private Long id;
  private String username;
  private String email;
  // password는 응답에 포함 안 함! (보안)
  private LocalDateTime createdAt;
}