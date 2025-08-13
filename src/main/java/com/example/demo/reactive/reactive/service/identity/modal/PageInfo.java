package com.example.demo.reactive.reactive.service.identity.modal;

import com.example.demo.reactive.reactive.modal.User;
import java.util.List;

public record PageInfo(List<User> users, Integer total, Integer skip, Integer limit) {}
