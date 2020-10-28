package com.orikik.learningwords.repository;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class UserRepositoryTest extends TestBase {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testname");
        userEntity.setPassword("testblabalatest");
        userRepository.save(userEntity);
        Long id = userEntity.getId();

        UserEntity res = userRepository.findById(id).get();

        assertUser(res, userEntity);
    }

    @Test
    public void getFindByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testname");
        userEntity.setPassword("testblabalatest");
        userRepository.save(userEntity);

        UserEntity res = userRepository.findByUsername(userEntity.getUsername()).get();

        assertUser(res, userEntity);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteUserByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testname");
        userEntity.setPassword("testblabalatest");
        userRepository.save(userEntity);
        Long id = userEntity.getId();

        userRepository.deleteById(id);

        userRepository.findById(id).get();
    }


    private void assertUser(UserEntity expected, UserEntity actually) {
        assertNotNull(expected);
        assertNotNull(actually);
        assertEquals("testname", expected.getUsername());
        assertEquals("testblabalatest", expected.getPassword());
        assertEquals(actually.getUsername(), expected.getUsername());
        assertEquals(actually.getPassword(), expected.getPassword());
        assertEquals(actually.getId(), expected.getId());
    }

}
