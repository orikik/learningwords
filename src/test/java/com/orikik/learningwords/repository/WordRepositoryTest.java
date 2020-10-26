package com.orikik.learningwords.repository;

import com.orikik.learningwords.TestBase;
import com.orikik.learningwords.entity.WordEntity;
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
public class WordRepositoryTest extends TestBase {
    @Autowired
    private WordRepository wordRepository;

    @Test
    public void getWordByIdTest() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord("word");
        wordEntity.setDescription("description");
        wordRepository.save(wordEntity);
        Long id = wordEntity.getId();

        WordEntity res = wordRepository.findById(id).get();

        assertUser(res, wordEntity);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteWordByIdTest() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord("word");
        wordEntity.setDescription("description");
        wordRepository.save(wordEntity);
        Long id = wordEntity.getId();

        wordRepository.deleteById(id);

        wordRepository.findById(id).get();
    }


    private void assertUser(WordEntity expected, WordEntity actually) {
        assertNotNull(expected);
        assertNotNull(actually);
        assertEquals("word", expected.getWord());
        assertEquals("description", expected.getDescription());
        assertEquals(actually.getWord(), expected.getWord());
        assertEquals(actually.getDescription(), expected.getDescription());
        assertEquals(actually.getId(), expected.getId());
    }

}
