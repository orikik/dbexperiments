package com.orikik.dbexperiments.repository;

import com.orikik.dbexperiments.TestBase;
import com.orikik.dbexperiments.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class UserRepositoryTest extends TestBase {
    private Long updateId;
    private Long deleteId;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("test1");
        userEntity1.setPassword("test1");
        userRepository.save(userEntity1);
        updateId = userEntity1.getId();

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("test2");
        userEntity2.setPassword("test2");
        userRepository.save(userEntity2);
        deleteId = userEntity2.getId();
    }

    @Test
    public void createNewUserTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword("test");
        userRepository.save(userEntity);
        userEntity = userRepository.findById(userEntity.getId()).get();
        assertNotNull(userEntity);
    }

    @Test
    public void getUserTest() {
        UserEntity actually = userRepository.findById(updateId).get();
        assertNotNull(actually);
    }

    @Test
    public void updateUserByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testupdate");
        userEntity.setPassword("testupdate");
        userEntity.setId(updateId);

        userRepository.save(userEntity);

        UserEntity actually = userRepository.findById(updateId).get();

        assertUser(userEntity, actually);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteUserTest() {
        userRepository.deleteById(deleteId);
        userRepository.findById(deleteId).get();
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
