package com.orikik.dbexperiments.dao.impl;

import com.orikik.dbexperiments.TestBase;
import com.orikik.dbexperiments.dao.WordDao;
import com.orikik.dbexperiments.entity.WordEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class WordDaoJdbcImplTest extends TestBase {
    private Long updateId;
    private Long deleteId;

    @Resource(name = "wordDaoJdbcImpl")
    private WordDao wordDao;

    @Before
    public void init() {
        WordEntity wordEntity1 = new WordEntity();
        wordEntity1.setWord("test1");
        wordEntity1.setDescription("test1");
        wordDao.createNewWord(wordEntity1);
        updateId = wordEntity1.getId();

        WordEntity wordEntity2 = new WordEntity();
        wordEntity2.setWord("test2");
        wordEntity2.setDescription("test2");
        wordDao.createNewWord(wordEntity2);
        deleteId = wordEntity2.getId();
    }

    @Test
    public void createNewWordTest() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord("test");
        wordEntity.setDescription("test");
        wordDao.createNewWord(wordEntity);
        wordEntity = wordDao.getWordById(wordEntity.getId());
        assertNotNull(wordEntity);
    }

    @Test
    public void getWordTest() {
        WordEntity actually = wordDao.getWordById(updateId);
        assertNotNull(actually);
    }

    @Test
    public void updateWordByIdTest() {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord("testupdate");
        wordEntity.setDescription("testupdate");
        wordEntity.setId(updateId);

        wordDao.updateWordById(wordEntity);

        WordEntity actually = wordDao.getWordById(updateId);

        assertWord(wordEntity, actually);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteWordByIdTest() {
        wordDao.deleteWordById(deleteId);
        wordDao.getWordById(deleteId);
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