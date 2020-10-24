package com.orikik.dbexperiments.service;

import com.orikik.dbexperiments.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserDto userDto);

    void deleteUserById(Long id);

    UserDto updateUser(UserDto userDto);
}
