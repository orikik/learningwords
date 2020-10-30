package com.orikik.learningwords.repository;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.RepetitionKey;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

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
        assertEquals(1, repetitionEntities.get(0).getRepetitionId().getWordId().intValue());
        assertEquals(2, repetitionEntities.get(1).getRepetitionId().getWordId().intValue());
    }

    @Test
    public void findByRepetitionIdTest() {
        Optional<RepetitionEntity> optionalRepetitionEntity = repetitionRepository
                .findByRepetitionId(repetitionEntity1.getRepetitionId());
        RepetitionEntity repetitionEntityActually = optionalRepetitionEntity.get();
        assertNotNull(repetitionEntityActually);
        assertEquals(1, repetitionEntityActually.getRepetitionId().getWordId().intValue());
        assertEquals(userId, repetitionEntityActually.getRepetitionId().getUserId());
    }

    @Test
    public void findBy2RepetitionIdTest() {
        Optional<RepetitionEntity> optionalRepetitionEntity1 = repetitionRepository
                .findByRepetitionId(repetitionEntity1.getRepetitionId());
        Optional<RepetitionEntity> optionalRepetitionEntity2 = repetitionRepository
                .findByRepetitionId(repetitionEntity2.getRepetitionId());
        RepetitionEntity repetitionEntityActually1 = optionalRepetitionEntity1.get();
        RepetitionEntity repetitionEntityActually2 = optionalRepetitionEntity2.get();

        assertNotNull(repetitionEntityActually1);
        assertEquals(1, repetitionEntityActually1.getRepetitionId().getWordId().intValue());
        assertEquals(userId, repetitionEntityActually1.getRepetitionId().getUserId());
        assertNotNull(repetitionEntityActually2);
        assertEquals(2, repetitionEntityActually2.getRepetitionId().getWordId().intValue());
        assertEquals(userId, repetitionEntityActually2.getRepetitionId().getUserId());
    }

    @Test(expected = NoSuchElementException.class)
    public void findByRepetitionFakeIdTest() {
        Optional<RepetitionEntity> optionalRepetitionEntity = repetitionRepository
                .findByRepetitionId(new RepetitionKey(9L, 8L));
        RepetitionEntity repetitionEntityActually = optionalRepetitionEntity.get();
        assertNull(repetitionEntityActually);
    }
}
