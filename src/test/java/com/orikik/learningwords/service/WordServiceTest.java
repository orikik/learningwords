package com.orikik.learningwords.service;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.dao.WordDao;
import com.orikik.learningwords.dto.WordDto;
import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.entity.WordEntity;
import com.orikik.learningwords.repository.RepetitionRepository;
import com.orikik.learningwords.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class WordServiceTest extends TestBase {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RepetitionRepository repetitionRepository;
    @MockBean
    private RepetitionService repetitionService;
    @MockBean
    private WordDao wordDao;
    @Autowired
    private WordService wordService;

    @Test
    public void getWordsForRepeatTest() {
        UserEntity userEntity = generateUserEntity();
        when(userRepository.findByUsername(any()))
                .thenReturn(java.util.Optional.of(userEntity));
        when(repetitionRepository.findByUserEntityId(any()))
                .thenReturn(java.util.Optional.of(createRepetitionEntityList()));
        when(wordDao.getNewWordsForRepetitonByUserId(any(), any()))
                .thenReturn(createWordEntityList(6, 11));
        List<WordDto> wordEntities = wordService.getWordsForRepeat(userEntity.getUsername());
        assertNotNull(wordEntities);
        assertEquals(10, wordEntities.size());
    }

    @Test
    public void getOldWordsForRepeatTest() {
        UserEntity userEntity = generateUserEntity();
        when(repetitionRepository.findByUserEntityId(any()))
                .thenReturn(java.util.Optional.of(createRepetitionEntityList()));
        List<WordEntity> wordEntities = wordService.getOldWordsForRepeat(userEntity);
        assertNotNull(wordEntities);
        assertEquals(4, wordEntities.size());
        assertEquals(2, wordEntities.get(0).getId().longValue());
    }

    @Test
    public void getNewWordsForRepeatTest() {
        UserEntity userEntity = generateUserEntity();
        when(wordDao.getNewWordsForRepetitonByUserId(any(), any()))
                .thenReturn(createWordEntityList(6, 11));
        List<WordEntity> wordEntities = wordService.getNewWordsForRepeat(6, userEntity);
        assertNotNull(wordEntities);
        assertEquals(6, wordEntities.size());
    }

    @Test
    public void setNewQualityTest() {
        UserEntity userEntity = generateUserEntity();
        RepetitionEntity repetitionEntity = generateRepetitionEntity();
        when(userRepository.findByUsername(any()))
                .thenReturn(java.util.Optional.of(userEntity));
        when(repetitionRepository.findByRepetitionId(any()))
                .thenReturn(java.util.Optional.of(repetitionEntity));
        wordService.setNewQuality(generateWordDto(), userEntity.getUsername(), 5);
        verify(repetitionService).superMemo2Algorithm(repetitionEntity, 5);
    }

    private List<RepetitionEntity> createRepetitionEntityList() {
        List<RepetitionEntity> repetitionEntities = new ArrayList<>();
        UserEntity userEntity = generateUserEntity();
        List<WordEntity> wordEntityList = createWordEntityList(1, 5);
        for (WordEntity wordEntity : wordEntityList) {
            RepetitionEntity repetitionEntity = new RepetitionEntity(userEntity, wordEntity);
            repetitionEntities.add(repetitionEntity);
        }
        repetitionEntities.get(0).setNextRepeatDate(LocalDate.now().plusDays(10));
        repetitionEntities.get(1).setNextRepeatDate(LocalDate.now().minusDays(10));
        repetitionEntities.get(2).setNextRepeatDate(LocalDate.now().minusDays(10));
        return repetitionEntities;
    }

    private List<WordEntity> createWordEntityList(long start, long count) {
        List<WordEntity> wordEntities = new ArrayList<>();
        for (long n = start; n <= count; n++) {
            String word = "testWord" + n;
            wordEntities.add(generateWordEntity(n, word));
        }
        return wordEntities;
    }

    private WordEntity generateWordEntity(Long id, String word) {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setId(id);
        wordEntity.setWord(word);
        wordEntity.setDescription("testDescription");
        wordEntity.setWordType("testWordType");
        return wordEntity;
    }

    private UserEntity generateUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testName");
        userEntity.setPassword("testPassword");
        return userEntity;
    }

    private WordDto generateWordDto() {
        WordDto wordDto = new WordDto();
        wordDto.setId(1L);
        wordDto.setWord("testWord");
        wordDto.setDescription("testDescription");
        wordDto.setWordType("testWordType");
        return wordDto;
    }

    private RepetitionEntity generateRepetitionEntity() {
        return new RepetitionEntity(generateUserEntity(), generateWordEntity(1L, "testWord"));
    }
}

