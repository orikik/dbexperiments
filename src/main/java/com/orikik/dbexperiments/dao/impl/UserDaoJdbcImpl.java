package com.orikik.dbexperiments.dao.impl;

import com.orikik.dbexperiments.dao.UserDao;
import com.orikik.dbexperiments.dao.mapper.UserMapper;
import com.orikik.dbexperiments.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserEntity> getAllUsers() {
        return jdbcTemplate.query("select username, password, id from users", UserMapper.getInstance());

    }

    @Override
    public UserEntity getUserById(Long id) {
        return jdbcTemplate.queryForObject("select username, password, id from users where id=?",
                UserMapper.getInstance(), id);
    }

    @Override
    public void deleteUserById(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public UserEntity createNewUser(UserEntity userEntity) {
        Long id = jdbcTemplate.queryForObject("select nextval('users_seq')", Long.class);
        jdbcTemplate.update("insert into users (id, username, password) values (?,?,?);", id,
                userEntity.getUsername(), userEntity.getPassword());
        userEntity.setId(id);
        return userEntity;
    }

    @Override
    public UserEntity updateUserById(UserEntity userEntity) {
        jdbcTemplate.update("UPDATE users SET username=?, password=? where id=?", userEntity.getUsername(),
                userEntity.getPassword(), userEntity.getId());
        return userEntity;
    }
}
