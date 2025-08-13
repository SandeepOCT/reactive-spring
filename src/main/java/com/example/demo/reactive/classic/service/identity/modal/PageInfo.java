package com.example.demo.reactive.classic.service.identity.modal;

import com.example.demo.reactive.classic.modal.User;
import java.util.List;

public record PageInfo(List<User> users, Integer total, Integer skip, Integer limit) {}
