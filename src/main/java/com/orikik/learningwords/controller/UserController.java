package com.orikik.learningwords.controller;

import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/user/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/user/update_passwod")
    public UserDto updateUserPassword(@RequestBody UserDto userDto) throws RuntimeException {
        return userService.updateUserPassword(userDto);
    }
}


