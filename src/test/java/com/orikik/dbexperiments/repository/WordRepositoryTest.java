package com.orikik.dbexperiments.repository;

import com.orikik.dbexperiments.TestBase;
import com.orikik.dbexperiments.entity.WordEntity;
import org.junit.Before;
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
    private Long updateId;
    private Long deleteId;

    @Autowired
    private WordRepository wordRepository;

    @Before
    public void init() {
        WordEntity wordEntity1 = new WordEntity();
        wordEntity1.setWord("test1");
        wordEntity1.setDescription("test1");
        wordRepository.save(wordEntity1);
        updateId = wordEntity1.getId();

        WordEntity wordEntity2 = new WordEntity();
        wordEntity2.setWord("test2");
        wordEntity2.setDescription("test2");
        wordRepository.save(wordEntity2);
        deleteId = wordEntity2.getId();
    }

    @Test
    public void createNewWordTest() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord("test");
        wordEntity.setDescription("test");
        wordRepository.save(wordEntity);
        wordEntity = wordRepository.findById(wordEntity.getId()).get();
        assertNotNull(wordEntity);
    }

    @Test
    public void getWordTest() {
        WordEntity actually = wordRepository.findById(updateId).get();
        assertNotNull(actually);
    }

    @Test
    public void updateWordByIdTest() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord("testupdate");
        wordEntity.setDescription("testupdate");
        wordEntity.setId(updateId);

        wordRepository.save(wordEntity);

        WordEntity actually = wordRepository.findById(updateId).get();

        assertWord(wordEntity, actually);

    }

    @Test(expected = NoSuchElementException.class)
    public void deleteWordByIdTest() {
        wordRepository.deleteById(deleteId);
        wordRepository.findById(deleteId).get();
    }

    private void assertWord(WordEntity expected, WordEntity actually) {
        assertNotNull(expected);
        assertNotNull(actually);
        assertEquals("testupdate", expected.getWord());
        assertEquals("testupdate", expected.getDescription());
        assertEquals(actually.getWord(), expected.getWord());
        assertEquals(actually.getDescription(), expected.getDescription());
        assertEquals(actually.getId(), expected.getId());
    }
}
