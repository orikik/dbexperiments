package com.orikik.dbexperiments.dao.mapper;

import com.orikik.dbexperiments.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserEntity> {
    private static UserMapper INSTANCE = null;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
    }

    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(rs.getLong("id"));
        userEntity.setUsername(rs.getString("username"));
        userEntity.setPassword(rs.getString("password"));
        return userEntity;
    }
}
