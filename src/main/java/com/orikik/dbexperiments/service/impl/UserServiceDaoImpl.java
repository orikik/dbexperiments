package com.orikik.dbexperiments.service.impl;

import com.orikik.dbexperiments.converter.UserConverter;
import com.orikik.dbexperiments.dao.UserDao;
import com.orikik.dbexperiments.dto.UserDto;
import com.orikik.dbexperiments.entity.UserEntity;
import com.orikik.dbexperiments.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userServiceDao")
@Transactional
public class UserServiceDaoImpl implements UserService {
    @Resource(name = "userDaoJdbcImpl")
    private UserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userDao.getAllUsers();
        return UserConverter.convert(userEntities);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userDao.getUserById(id);
        return UserConverter.convert(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userDao.createNewUser(UserConverter.convert(userDto));
        return UserConverter.convert(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userDao.updateUserById(UserConverter.convert(userDto));
        return UserConverter.convert(userEntity);
    }
}
