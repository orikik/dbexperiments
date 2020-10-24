package com.orikik.dbexperiments.service;

import com.orikik.dbexperiments.TestBase;
import com.orikik.dbexperiments.dao.WordDao;
import com.orikik.dbexperiments.dto.WordDto;
import com.orikik.dbexperiments.entity.WordEntity;
import com.orikik.dbexperiments.service.impl.WordServiceDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
public class WordServiceDaoImplTest extends TestBase {
    @MockBean(name = "wordDaoJdbcImpl")
    private WordDao wordDao;
    @Autowired
    private WordServiceDaoImpl wordServiceDaoImpl;

    @Test
    public void getWordsTest() {
        List<WordEntity> expected = createWordEntityList();
        when(wordDao.getAllWords()).thenReturn(expected);

        List<WordDto> actually = wordServiceDaoImpl.getWords();

        assertEquals(2, expected.size());
        assertEquals(2, actually.size());
        assertWord(actually.get(0), expected.get(0), "testword1", "testblabalatest1");
        assertWord(actually.get(1), expected.get(1), "testword2", "testblabalatest2");

    }

    @Test
    public void getWordByIdTest() {
        WordEntity wordEntity = generateWordEntity("testword", "testblabalatest");
        when(wordDao.getWordById(any())).thenReturn(wordEntity);

        WordDto res = wordServiceDaoImpl.getWordById(1L);

        assertWord(res, wordEntity, "testword", "testblabalatest");

    }

    @Test
    public void deleteWordByIdTest() {
        wordServiceDaoImpl.deleteWordById(1L);
        verify(wordDao).deleteWordById(1L);
    }

    @Test
    public void createNewWordTest() {
        WordEntity wordEntity = generateWordEntity("testword", "testblabalatest");
        when(wordDao.createNewWord(any())).thenReturn(wordEntity);

        WordDto res = wordServiceDaoImpl.createNewWord(generateWordDto());

        assertWord(res, wordEntity, "testword", "testblabalatest");
    }

    @Test
    public void updateWordByIdTest() {
        WordEntity wordEntity = generateWordEntity("testword", "testblabalatest");
        when(wordDao.updateWordById(any())).thenReturn(wordEntity);

        WordDto res = wordServiceDaoImpl.updateWordById(generateWordDto());

        assertWord(res, wordEntity, "testword", "testblabalatest");
    }

    private void assertWord(WordDto dto, WordEntity entity, String word, String description) {
        assertNotNull(dto);
        assertNotNull(entity);
        assertEquals(word, dto.getWord());
        assertEquals(description, dto.getDescription());
        assertEquals(entity.getWord(), dto.getWord());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getId(), dto.getId());
    }

    private WordDto generateWordDto() {
        WordDto wordDto = new WordDto();
        wordDto.setWord("testword");
        wordDto.setDescription("testblabalatest");
        return wordDto;
    }

    private List<WordEntity> createWordEntityList() {
        List<WordEntity> wordEntities = new ArrayList<>();
        wordEntities.add(generateWordEntity("testword1", "testblabalatest1"));
        wordEntities.add(generateWordEntity("testword2", "testblabalatest2"));
        return wordEntities;
    }

    private WordEntity generateWordEntity(String word, String description) {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord(word);
        wordEntity.setDescription(description);
        return wordEntity;
    }
}

