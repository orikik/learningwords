package com.orikik.learningwords.controller;

import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.dto.WordDto;
import com.orikik.learningwords.service.UserService;
import com.orikik.learningwords.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private WordService wordService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/user/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/user/update_passwod")
    public UserDto updateUserPassword(@RequestBody UserDto userDto) {
        return userService.updateUserPassword(userDto);
    }

    @GetMapping("/user/words/repeat/{username}")
    public List<WordDto> getWords(@PathVariable String username) {
        return wordService.getWordsForRepeat(username);
    }

    @PutMapping("/user/quality/{username}/{quality}")
    public void setQuality(@PathVariable String username, @PathVariable Integer quality, @RequestBody WordDto wordDto) {
        wordService.setNewQuality(wordDto, username, quality);
    }
}


