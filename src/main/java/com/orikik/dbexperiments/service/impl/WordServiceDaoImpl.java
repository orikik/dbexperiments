package com.orikik.dbexperiments.service.impl;

import com.orikik.dbexperiments.converter.WordConverter;
import com.orikik.dbexperiments.dao.WordDao;
import com.orikik.dbexperiments.dto.WordDto;
import com.orikik.dbexperiments.entity.WordEntity;
import com.orikik.dbexperiments.service.WordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("wordServiceDao")
@Transactional
public class WordServiceDaoImpl implements WordService {
    @Resource(name = "wordDaoJdbcImpl")
    private WordDao wordDao;

    @Override
    public List<WordDto> getWords() {
        List<WordEntity> wordEntities = wordDao.getAllWords();
        return WordConverter.convert(wordEntities);
    }

    @Override
    public WordDto getWordById(Long id) {
        WordEntity wordEntity = wordDao.getWordById(id);
        return WordConverter.convert(wordEntity);
    }

    @Override
    public void deleteWordById(Long id) {
        wordDao.deleteWordById(id);
    }

    @Override
    public WordDto createNewWord(WordDto wordDto) {
        WordEntity wordEntity = wordDao.createNewWord(WordConverter.convert(wordDto));
        return WordConverter.convert(wordEntity);
    }

    @Override
    public WordDto updateWordById(WordDto wordDto) {
        WordEntity wordEntity = wordDao.updateWordById(WordConverter.convert(wordDto));
        return WordConverter.convert(wordEntity);
    }
}
