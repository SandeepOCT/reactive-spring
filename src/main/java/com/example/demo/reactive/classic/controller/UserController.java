package com.example.demo.reactive.classic.controller;

import com.example.demo.reactive.Util;
import com.example.demo.reactive.classic.modal.User;
import com.example.demo.reactive.classic.modal.UserRecord;
import com.example.demo.reactive.classic.service.identity.IdentityService;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classic")
public class UserController {

  private final IdentityService identityService;

  public UserController(IdentityService identityService) {
    this.identityService = identityService;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return identityService.findIdentities().stream()
        // Simulate a processing delay
        .peek(Util::delay)
        .toList();
  }

  @SneakyThrows
  @GetMapping("/usernames")
  public List<UserRecord> getUserNames() {

    return identityService.findIdentities().stream()
        .map(UserRecord::from)
        .peek(Util::delay)
        .toList();
  }
}
