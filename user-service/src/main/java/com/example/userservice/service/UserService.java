package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) ///  블로킹 방식, 요청 -> 응답
public class UserService {

  private final UserRepository userRepository;

  // 회원가입
  @Transactional
  public UserResponseDto createUser(UserRequestDto request) {
    // 중복 체크
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
    }

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
    }

    // User 엔티티 생성
    User user = User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(request.getPassword())  // 실무에서는 암호화 필수!
        .build();

    // 저장
    User savedUser = userRepository.save(user);

    // DTO 변환 후 반환
    return UserResponseDto.from(savedUser);
  }

  // 사용자 조회 (ID로)
  public UserResponseDto getUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

    return UserResponseDto.from(user);
  }

  // 사용자 조회 (username으로)
  public UserResponseDto getUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

    return UserResponseDto.from(user);
  }
}