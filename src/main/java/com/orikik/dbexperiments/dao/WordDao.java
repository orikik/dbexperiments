package com.orikik.dbexperiments.dao;

import com.orikik.dbexperiments.entity.WordEntity;

import java.util.List;

public interface WordDao {
    List<WordEntity> getAllWords();

    WordEntity getWordById(Long id);

    void deleteWordById(Long id);

    WordEntity createNewWord(WordEntity wordEntity);

    WordEntity updateWordById(WordEntity wordEntity);
}
