package com.orikik.learningwords.controller;

import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.dto.WordDto;
import com.orikik.learningwords.exception.ErrorCodeEnum;
import com.orikik.learningwords.exception.LearningWordsException;
import com.orikik.learningwords.service.UserService;
import com.orikik.learningwords.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PutMapping("/user/update")
    public UserDto updateUser(Principal principal, @RequestBody UserDto userDto) throws RuntimeException {
        if (!principal.getName().equals(userDto.getUsername())) {
            LOG.error("user={} try to change {}"
                    , principal.getName(), userDto.getUsername());
            throw new LearningWordsException(ErrorCodeEnum.OPERATION_PROHIBITED_EXCEPTION);
        }

        return userService.updateUserPassword(userDto);
    }

    @GetMapping("/user/words/repeat")
    public List<WordDto> getWords(Principal principal) throws IllegalAccessException {
        return wordService.getWordsForRepeat(principal.getName());
    }

    @PutMapping("/user/quality/{quality}")
    public void setQuality(Principal principal, @PathVariable Integer quality, @RequestBody WordDto wordDto) {
        wordService.setNewQuality(wordDto, principal.getName(), quality);
    }
}


