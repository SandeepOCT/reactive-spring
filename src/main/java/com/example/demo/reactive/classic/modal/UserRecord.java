package com.example.demo.reactive.classic.modal;

import lombok.Builder;

@Builder
public record UserRecord(String name) {

  public static UserRecord from(User user) {

    return UserRecord.builder().name(user.getFullName()).build();
  }
}
