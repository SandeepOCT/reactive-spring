package com.example.demo.reactive.reactive.controller;

import static com.example.demo.reactive.Constants.DELAY;

import com.example.demo.reactive.reactive.modal.User;
import com.example.demo.reactive.reactive.modal.UserRecord;
import com.example.demo.reactive.reactive.service.identity.IdentityServiceReactive;
import java.time.Duration;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reactive")
public class UserControllerReactive {

  private final IdentityServiceReactive identityServiceReactive;

  public UserControllerReactive(IdentityServiceReactive identityServiceReactive) {
    this.identityServiceReactive = identityServiceReactive;
  }

  @GetMapping(path = "/users", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<User> getUsers() {
    return identityServiceReactive
        .findIdentities()
        // Simulate a processing delay
        .delayElements(Duration.ofMillis(DELAY));
  }

  @SneakyThrows
  @GetMapping(path = "/usernames", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<UserRecord> getUserNames() {

    return identityServiceReactive
        .findIdentities()
        .map(UserRecord::from)
        .delayElements(Duration.ofMillis(DELAY));
  }
}
