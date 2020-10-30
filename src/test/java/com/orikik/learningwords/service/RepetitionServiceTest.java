package com.orikik.learningwords.service;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.entity.WordEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class RepetitionServiceTest extends TestBase {
    @Autowired
    private RepetitionService repetitionService;

    @Test
    public void superMemo2Algorithm_quality0() {
        Integer quality = 0;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test
    public void superMemo2Algorithm_quality1() {
        Integer quality = 1;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test
    public void superMemo2Algorithm_quality2() {
        Integer quality = 2;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test
    public void superMemo2Algorithm_quality3() {
        Integer quality = 3;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test
    public void superMemo2Algorithm_quality4() {
        Integer quality = 4;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test
    public void superMemo2Algorithm_quality5() {
        Integer quality = 5;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test(expected = IllegalArgumentException.class)
    public void superMemo2Algorithm_quality6() {
        Integer quality = 6;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    @Test(expected = IllegalArgumentException.class)
    public void superMemo2Algorithm_qualityMinus1() {
        Integer quality = -1;
        RepetitionEntity repetitionEntity = repetitionService
                .superMemo2Algorithm(generateRepetitionEntity(), quality);
        assertRepetition(repetitionEntity, quality);
    }

    private void assertRepetition(RepetitionEntity repetitionEntity, Integer quality) {
        Double easiness = 2.5 + 0.1 - (5.0 - quality) * (0.08 + (5.0 - quality) * 0.02);
        easiness = Math.min(2.5, easiness);
        easiness = Math.max(1.3, easiness);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        assertNotNull(repetitionEntity);
        assertNull(repetitionEntity.getDateOfStudyingTheWord());
        assertEquals(1, repetitionEntity.getRepetitionId().getUserId().intValue());
        assertEquals(1, repetitionEntity.getRepetitionId().getWordId().intValue());
        assertEquals(easiness, repetitionEntity.geteFactor());
        assertEquals(quality, repetitionEntity.getQuality());
        if (quality < 3) {
            assertEquals(0, repetitionEntity.getRepeatNumber().intValue());
        } else {
            assertEquals(1, repetitionEntity.getRepeatNumber().intValue());
        }

        assertEquals((Integer) 1, repetitionEntity.getInterval());
        assertEquals(tomorrow, repetitionEntity.getNextRepeatDate());
        assertFalse(repetitionEntity.getWordIsLearned());
    }

    private RepetitionEntity generateRepetitionEntity() {
        return new RepetitionEntity(generateUserEntity(), generateWordEntity());
    }

    private WordEntity generateWordEntity() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setId(1L);
        wordEntity.setWord("testWord");
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
}
