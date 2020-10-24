package com.orikik.dbexperiments.service;

import com.orikik.dbexperiments.dto.WordDto;

import java.util.List;

public interface WordService {
    List<WordDto> getWords();

    WordDto getWordById(Long id);

    WordDto createNewWord(WordDto wordDto);

    void deleteWordById(Long id);

    WordDto updateWordById(WordDto wordDto);
}
