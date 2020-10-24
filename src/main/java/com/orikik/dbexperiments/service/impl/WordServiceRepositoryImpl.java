package com.orikik.dbexperiments.service.impl;

import com.orikik.dbexperiments.converter.WordConverter;
import com.orikik.dbexperiments.dto.WordDto;
import com.orikik.dbexperiments.entity.WordEntity;
import com.orikik.dbexperiments.repository.WordRepository;
import com.orikik.dbexperiments.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("wordServiceRepository")
@Transactional
public class WordServiceRepositoryImpl implements WordService {
    @Autowired
    private WordRepository wordRepository;

    @Override
    public List<WordDto> getWords() {
        List<WordEntity> wordEntities = wordRepository.findAll();
        return WordConverter.convert(wordEntities);
    }

    @Override
    public WordDto getWordById(Long id) {
        Optional<WordEntity> entityOptional = wordRepository.findById(id);
        if (!entityOptional.isPresent()) {
            throw new EntityNotFoundException();
        }
        return WordConverter.convert(entityOptional.get());
    }

    @Override
    public void deleteWordById(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public WordDto createNewWord(WordDto wordDto) {
        WordEntity wordEntity = wordRepository.save(WordConverter.convert(wordDto));
        return WordConverter.convert(wordEntity);
    }

    @Override
    public WordDto updateWordById(WordDto wordDto) {
        WordEntity wordEntity = wordRepository.save(WordConverter.convert(wordDto));
        return WordConverter.convert(wordEntity);
    }
}

