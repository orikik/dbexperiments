package com.orikik.dbexperiments.converter;

import com.orikik.dbexperiments.dto.WordDto;
import com.orikik.dbexperiments.entity.WordEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WordConverterTest {
    @Test
    public void convertTest_entityToDto() {
        WordEntity entity = generateWordEntity("testword1", "testblabalatest1");
        WordDto actually = WordConverter.convert(entity);
        assertWord(actually, entity, "testword1", "testblabalatest1");
    }

    @Test
    public void convertTest_nullEntityToDto() {
        assertNull(WordConverter.convert((WordEntity) null));
    }

    @Test
    public void convertListTest_entitiesToDtos() {
        List<WordEntity> expected = createWordEntityList();
        List<WordDto> actually = WordConverter.convert(expected);
        assertEquals(2, expected.size());
        assertEquals(2, actually.size());
        assertWord(actually.get(0), expected.get(0), "testword1", "testblabalatest1");
        assertWord(actually.get(1), expected.get(1), "testword2", "testblabalatest2");
    }

    @Test
    public void convertListTest_entitiesNullToDtos() {
        List<WordDto> result = WordConverter.convert((List<WordEntity>) null);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void convertTest_dtoToEntity() {
        WordDto dto = generateWordDto();
        WordEntity actually = WordConverter.convert(dto);
        assertWord(dto, actually, "wordtest", "testblablatest");
    }

    @Test
    public void convertTest_nullDtoToEntity() {
        assertNull(WordConverter.convert((WordDto) null));
    }

    private void assertWord(WordDto dto, WordEntity entity, String word, String description) {
        assertNotNull(dto);
        assertNotNull(entity);
        assertEquals(word, dto.getWord());
        assertEquals(description, dto.getDescription());
        assertEquals(entity.getWord(), dto.getWord());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertNull(entity.getId());
        assertEquals(entity.getId(), dto.getId());
    }

    private WordDto generateWordDto() {
        WordDto wordDto = new WordDto();
        wordDto.setWord("wordtest");
        wordDto.setDescription("testblablatest");
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
