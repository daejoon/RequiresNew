package com.ddoong2.requiresnew.controller;

import com.ddoong2.requiresnew.model.User;
import com.ddoong2.requiresnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void saveUser(@RequestBody User user) {
        userService.save(user.getName(), user.getNickname(), user.getAge());
    }
}
