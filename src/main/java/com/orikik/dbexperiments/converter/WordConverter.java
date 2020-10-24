package com.orikik.dbexperiments.converter;

import com.orikik.dbexperiments.dto.WordDto;
import com.orikik.dbexperiments.entity.WordEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordConverter {
    private WordConverter() {
    }

    public static WordDto convert(WordEntity wordEntity) {
        if (wordEntity == null) {
            return null;
        }
        WordDto wordDto = new WordDto();
        wordDto.setWord(wordEntity.getWord());
        wordDto.setDescription(wordEntity.getDescription());
        wordDto.setId(wordEntity.getId());
        return wordDto;
    }

    public static List<WordDto> convert(List<WordEntity> wordEntities) {
        if (CollectionUtils.isEmpty(wordEntities)) {
            return Collections.emptyList();
        }
        return wordEntities.stream().map(WordConverter::convert).collect(Collectors.toList());
    }

    public static WordEntity convert(WordDto wordDto) {
        if (wordDto == null) {
            return null;
        }
        WordEntity wordEntity = new WordEntity();
        wordEntity.setWord(wordDto.getWord());
        wordEntity.setDescription(wordDto.getDescription());
        wordEntity.setId(wordDto.getId());
        return wordEntity;
    }
}