package com.orikik.learningwords.dao;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.entity.WordEntity;
import com.orikik.learningwords.repository.RepetitionRepository;
import com.orikik.learningwords.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class WordDaoTest extends TestBase {
    private Long userId;
    @Autowired
    private WordDao wordDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RepetitionRepository repetitionRepository;

    @Before
    public void init() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUsername");
        userEntity.setPassword("testPassword");
        userEntity = userRepository.save(userEntity);
        userId = userEntity.getId();

        WordEntity wordEntity = new WordEntity();
        wordEntity.setId(1L);

        RepetitionEntity repetitionEntity = new RepetitionEntity(userEntity, wordEntity);
        repetitionRepository.save(repetitionEntity);
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();
    }

    @Test
    public void getListOfNewWordsByUserIdTest() {
        List<WordEntity> wordEntityList = wordDao.getNewWordsForRepetitonByUserId(userId, 50);
        assertNotNull(wordEntityList);
        Set<Long> wordIds = wordEntityList.stream().map(WordEntity::getId).collect(Collectors.toSet());
        assertEquals(49, wordIds.size());
        assertEquals(wordIds.size(), wordEntityList.size());
        assertFalse(wordIds.contains(1));
    }

}
