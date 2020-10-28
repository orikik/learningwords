package com.orikik.learningwords.service;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class UserServiceTest extends TestBase {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        UserEntity userEntity = generateUserEntity();
        when(userRepository.save(any())).thenReturn(userEntity);

        UserDto res = userService.createUser(generateUserDto());

        assertUser(res, userEntity);
    }

    @Test
    public void updateUserByUsernameTest() {
        UserEntity userEntity = generateUserEntity();
        when(userRepository.findByUsername(any())).thenReturn(java.util.Optional.of(userEntity));
        when(userRepository.save(any())).thenReturn(userEntity);
        UserDto res = userService.updateUserPassword(generateUserDto());

        assertUser(res, userEntity);
    }

    private UserEntity generateUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testName");
        userEntity.setPassword("testPassword");
        return userEntity;
    }

    private UserDto generateUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("testName");
        userDto.setPassword("testPassword");
        return userDto;
    }

    private void assertUser(UserDto dto, UserEntity entity) {
        assertNotNull(dto);
        assertNotNull(entity);
        assertEquals(1L, entity.getId().longValue());
        assertEquals("testName", dto.getUsername());
        assertEquals("testPassword", dto.getPassword());
        assertEquals(entity.getUsername(), dto.getUsername());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getId(), dto.getId());
    }
}