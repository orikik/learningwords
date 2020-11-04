package com.orikik.learningwords.service;

import com.orikik.learningwords.converter.UserConverter;
import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = userRepository.save(UserConverter.convert(userDto));
        return UserConverter.convert(userEntity);
    }

    public UserDto updateUserPassword(UserDto userDto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDto.getUsername());
        if (!userEntityOptional.isPresent()) {
            LOG.error("user={} not found", userDto.getUsername());
            throw new UsernameNotFoundException("User not found");
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity = userRepository.save(userEntity);
        userEntity.setPassword(userDto.getPassword());
        return UserConverter.convert(userEntity);
    }
}
