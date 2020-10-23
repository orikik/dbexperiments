package com.orikik.dbexperiments.dao.impl;

import com.orikik.dbexperiments.dao.UserDao;
import com.orikik.dbexperiments.dao.mapper.UserMapper;
import com.orikik.dbexperiments.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<UserEntity> getAllUsers() {
        return namedParameterJdbcTemplate.query("select username, password, id from users", UserMapper.getInstance());
    }

    @Override
    public UserEntity getUserById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("select username, password, id from users where id=:id",
                params, UserMapper.getInstance());
    }

    @Override
    public void deleteUserById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM users WHERE id = :id", params);
    }

    @Override
    public UserEntity createNewUser(UserEntity userEntity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("password", userEntity.getPassword());
        params.addValue("username", userEntity.getUsername());
        Long id = namedParameterJdbcTemplate.queryForObject("select nextval('users_seq')", params, Long.class);
        params.addValue("id", id);
        namedParameterJdbcTemplate.update("insert into users (id, username, password) values (:id,:username,:password)",
                params);
        userEntity.setId(id);
        return userEntity;
    }

    @Override
    public UserEntity updateUserById(UserEntity userEntity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userEntity.getId());
        params.addValue("new_username", userEntity.getUsername());
        params.addValue("new_password", userEntity.getPassword());
        namedParameterJdbcTemplate
                .update("UPDATE users SET username=:new_username, password=:new_password where id=:id", params);
        return userEntity;
    }
}
