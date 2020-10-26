package com.orikik.learningwords.repository;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.entity.WordEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class RepetitionRepositoryTest extends TestBase {
    private Long userId;
    private RepetitionEntity repetitionEntity1;
    private RepetitionEntity repetitionEntity2;
    private UserEntity userEntity;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RepetitionRepository repetitionRepository;

    @Before
    public void init() {
        userEntity = new UserEntity();
        userEntity.setUsername("testUsername");
        userEntity.setPassword("testPassword");
        userEntity = userRepository.save(userEntity);
        userId = userEntity.getId();

        WordEntity wordEntity1 = new WordEntity();
        wordEntity1.setId(1L);

        WordEntity wordEntity2 = new WordEntity();
        wordEntity2.setId(2L);

        repetitionEntity1 = new RepetitionEntity(userEntity, wordEntity1);
        repetitionRepository.save(repetitionEntity1);
        repetitionEntity2 = new RepetitionEntity(userEntity, wordEntity2);
        repetitionRepository.save(repetitionEntity2);
    }

    @Test
    public void testFindByUserEntityId() {
        List<RepetitionEntity> repetitionEntities = repetitionRepository.findByUserEntityId(userId).get();
        assertEquals(2, repetitionEntities.size());
    }
}
