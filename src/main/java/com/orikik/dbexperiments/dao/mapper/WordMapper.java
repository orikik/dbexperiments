package com.orikik.dbexperiments.dao.mapper;

import com.orikik.dbexperiments.entity.WordEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordMapper implements RowMapper<WordEntity> {
    private static WordMapper INSTANCE = null;

    private WordMapper() {
    }

    public static WordMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WordMapper();
        }
        return INSTANCE;
    }

    @Override
    public WordEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setId(rs.getLong("id"));
        wordEntity.setWord(rs.getString("word"));
        wordEntity.setDescription(rs.getString("description"));
        return wordEntity;
    }
}
