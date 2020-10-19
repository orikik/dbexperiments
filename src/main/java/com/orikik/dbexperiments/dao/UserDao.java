package com.orikik.dbexperiments.dao;

import com.orikik.dbexperiments.entity.UserEntity;

import java.util.List;

public interface UserDao {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    void deleteUserById(Long id);

    UserEntity createNewUser(UserEntity userEntity);

    UserEntity updateUserById(UserEntity userEntity);
}
