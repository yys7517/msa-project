package com.example.boardservice.client;

import com.example.boardservice.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
  @GetMapping("/api/users/{id}")
  UserDto getUser(@PathVariable Long id);
}