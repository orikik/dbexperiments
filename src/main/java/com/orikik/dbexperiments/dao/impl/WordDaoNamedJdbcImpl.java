package com.orikik.dbexperiments.dao.impl;

import com.orikik.dbexperiments.dao.WordDao;
import com.orikik.dbexperiments.dao.mapper.WordMapper;
import com.orikik.dbexperiments.entity.WordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wordDaoNamedJdbcImpl")
public class WordDaoNamedJdbcImpl implements WordDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<WordEntity> getAllWords() {
        return namedParameterJdbcTemplate.query("select word, description, id from words", WordMapper.getInstance());
    }

    @Override
    public WordEntity getWordById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("select word, description, id from words where id=:id",
                params, WordMapper.getInstance());
    }

    @Override
    public void deleteWordById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM words WHERE id =:id", params);
    }

    @Override
    public WordEntity createNewWord(WordEntity wordEntity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("word", wordEntity.getWord());
        params.addValue("description", wordEntity.getDescription());
        Long id = namedParameterJdbcTemplate.queryForObject("select nextval('words_seq')", params, Long.class);
        params.addValue("id", id);

        namedParameterJdbcTemplate.update("insert into words (id, word, description) values (:id,:word,:description)",
                params);
        wordEntity.setId(id);
        return wordEntity;
    }

    @Override
    public WordEntity updateWordById(WordEntity wordEntity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", wordEntity.getId());
        params.addValue("new_word", wordEntity.getWord());
        params.addValue("new_description", wordEntity.getDescription());
        namedParameterJdbcTemplate.update("UPDATE words SET word=:new_word, description=:new_description where id=:id",
                params);
        return wordEntity;
    }
}
