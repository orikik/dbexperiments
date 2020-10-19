package com.orikik.dbexperiments.dao.impl;

import com.orikik.dbexperiments.dao.WordDao;
import com.orikik.dbexperiments.dao.mapper.WordMapper;
import com.orikik.dbexperiments.entity.WordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("wordDaoJdbcImpl")
public class WordDaoJdbcImpl implements WordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<WordEntity> getAllWords() {
        return jdbcTemplate.query("select word, description, id from words", WordMapper.getInstance());
    }

    @Override
    public WordEntity getWordById(Long id) {
        return jdbcTemplate.queryForObject("select word, description, id from words where id=?",
                WordMapper.getInstance(), id);
    }

    @Override
    public void deleteWordById(Long id) {
        jdbcTemplate.update("DELETE FROM words WHERE id =?", id);
    }

    @Override
    public WordEntity createNewWord(WordEntity wordEntity) {
        Long id = jdbcTemplate.queryForObject("select nextval('users_seq')", Long.class);

        jdbcTemplate.update("insert into words (id, word, description) values (?,?,?)", id, wordEntity.getWord(),
                wordEntity.getDescription());
        wordEntity.setId(id);
        return wordEntity;
    }

    @Override
    public WordEntity updateWordById(WordEntity wordEntity) {
        jdbcTemplate.update("UPDATE words SET word=?, description=? where id=?", wordEntity.getWord(),
                wordEntity.getDescription(), wordEntity.getId());
        return wordEntity;
    }
}
