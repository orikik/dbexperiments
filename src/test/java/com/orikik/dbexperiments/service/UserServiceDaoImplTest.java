package com.orikik.dbexperiments.service;

import com.orikik.dbexperiments.TestBase;
import com.orikik.dbexperiments.dao.UserDao;
import com.orikik.dbexperiments.dto.UserDto;
import com.orikik.dbexperiments.entity.UserEntity;
import com.orikik.dbexperiments.service.impl.UserServiceDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class UserServiceDaoImplTest extends TestBase {

    @MockBean(name = "userDaoJdbcImpl")
    private UserDao userDao;
    @Autowired
    private UserServiceDaoImpl userServiceDaoImpl;

    @Test
    public void getUsersTest() {
        List<UserEntity> expected = createUserEntityList();
        when(userDao.getAllUsers()).thenReturn(expected);

        List<UserDto> actually = userServiceDaoImpl.getAllUsers();

        assertEquals(2, expected.size());
        assertEquals(2, actually.size());
        assertUser(actually.get(0), expected.get(0), "testname1", "testblabalatest1");
        assertUser(actually.get(1), expected.get(1), "testname2", "testblabalatest2");
    }

    @Test
    public void getUserByIdTest() {
        UserEntity userEntity = generateUserEntity("testname", "testblabalatest");
        when(userDao.getUserById(any())).thenReturn(userEntity);

        UserDto res = userServiceDaoImpl.getUserById(1L);

        assertUser(res, userEntity, "testname", "testblabalatest");
    }

    @Test
    public void deleteUserByIdTest() {
        userServiceDaoImpl.deleteUserById(1L);
        verify(userDao).deleteUserById(1L);
    }

    @Test
    public void createNewUserTest() {
        UserEntity userEntity = generateUserEntity("testname", "testblabalatest");
        when(userDao.createNewUser(any())).thenReturn(userEntity);

        UserDto res = userServiceDaoImpl.createUser(generateUserDto());

        assertUser(res, userEntity, "testname", "testblabalatest");
    }

    @Test
    public void updateUserByIdTest() {
        UserEntity userEntity = generateUserEntity("testname", "testblabalatest");
        when(userDao.updateUserById(any())).thenReturn(userEntity);

        UserDto res = userServiceDaoImpl.updateUser(generateUserDto());

        assertUser(res, userEntity, "testname", "testblabalatest");
    }

    private void assertUser(UserDto dto, UserEntity entity, String word, String description) {
        assertNotNull(dto);
        assertNotNull(entity);
        assertEquals(word, dto.getUsername());
        assertEquals(description, dto.getPassword());
        assertEquals(entity.getUsername(), dto.getUsername());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertNull(entity.getId());
        assertEquals(entity.getId(), dto.getId());
    }

    private UserDto generateUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testname");
        userDto.setPassword("testblabalatest");
        return userDto;
    }

    private List<UserEntity> createUserEntityList() {
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(generateUserEntity("testname1", "testblabalatest1"));
        userEntities.add(generateUserEntity("testname2", "testblabalatest2"));
        return userEntities;
    }

    private UserEntity generateUserEntity(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        return userEntity;
    }
}
