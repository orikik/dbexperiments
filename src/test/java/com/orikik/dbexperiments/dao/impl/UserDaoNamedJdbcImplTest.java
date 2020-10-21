package com.orikik.dbexperiments.dao.impl;

import com.orikik.dbexperiments.TestBase;
import com.orikik.dbexperiments.dao.UserDao;
import com.orikik.dbexperiments.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class UserDaoNamedJdbcImplTest extends TestBase {
    private Long updateId;
    private Long deleteId;

    @Resource(name = "userDaoNamedJdbcImpl")
    private UserDao userDao;

    @Before
    public void init() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("test1");
        userEntity1.setPassword("test1");
        userDao.createNewUser(userEntity1);
        updateId = userEntity1.getId();

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("test2");
        userEntity2.setPassword("test2");
        userDao.createNewUser(userEntity2);
        deleteId = userEntity2.getId();
    }

    @Test
    public void createNewUserTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword("test");
        userDao.createNewUser(userEntity);
        userEntity = userDao.getUserById(userEntity.getId());
        assertNotNull(userEntity);
    }

    @Test
    public void getUserTest() {
        UserEntity actually = userDao.getUserById(updateId);
        assertNotNull(actually);
    }

    @Test
    public void updateUserByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testupdate");
        userEntity.setPassword("testupdate");
        userEntity.setId(updateId);

        userDao.updateUserById(userEntity);

        UserEntity actually = userDao.getUserById(updateId);

        assertUser(userEntity, actually);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteUserTest() {
        userDao.deleteUserById(deleteId);
        userDao.getUserById(deleteId);
    }

    private void assertUser(UserEntity expected, UserEntity actually) {
        assertNotNull(expected);
        assertNotNull(actually);
        assertEquals("testupdate", expected.getUsername());
        assertEquals("testupdate", expected.getPassword());
        assertEquals(actually.getUsername(), expected.getUsername());
        assertEquals(actually.getPassword(), expected.getPassword());
        assertEquals(actually.getId(), expected.getId());
    }
}