package com.orikik.learningwords.converter;

import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.entity.UserEntity;

public class UserConverter {
    public static UserDto convert(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        return userDto;
    }

    public static UserEntity convert(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }
}
