package com.orikik.learningwords.converter;

import com.orikik.learningwords.dto.UserDto;
import com.orikik.learningwords.entity.UserEntity;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserConverterTest {
    @Test
    public void convertTest_entityToDto() {
        UserEntity userEntity = generateUserEntity();
        UserDto actually = UserConverter.convert(userEntity);
        assertUser(actually, userEntity);
    }

    @Test
    public void convertTest_entityNullToDto() {
        assertNull(UserConverter.convert((UserEntity) null));
    }

    @Test
    public void convertTest_dtoToEntity() {
        UserDto expected = generateUserDto();
        UserEntity actually = UserConverter.convert(expected);
        assertUser(expected, actually);
    }

    @Test
    public void convertTest_nullDtoToEntity() {
        assertNull(UserConverter.convert((UserDto) null));
    }

    private UserDto generateUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("testName");
        userDto.setPassword("testPassword");
        return userDto;
    }

    private UserEntity generateUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testName");
        userEntity.setPassword("testPassword");
        return userEntity;
    }

    private void assertUser(UserDto dto, UserEntity entity) {
        assertNotNull(dto);
        assertNotNull(entity);
        assertEquals(1, dto.getId().longValue());
        assertEquals("testName", dto.getUsername());
        assertEquals("testPassword", dto.getPassword());
        assertEquals(entity.getUsername(), dto.getUsername());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getId(), dto.getId());
    }
}