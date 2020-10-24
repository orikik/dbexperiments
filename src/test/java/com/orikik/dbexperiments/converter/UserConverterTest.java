package com.orikik.dbexperiments.converter;

import com.orikik.dbexperiments.dto.UserDto;
import com.orikik.dbexperiments.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserConverterTest {
    @Test
    public void convertTest_entityToDto() {
        UserEntity userEntity = generateUserEntity("testname", "testblabalatest");
        UserDto actually = UserConverter.convert(userEntity);
        assertUser(actually, userEntity, "testname", "testblabalatest");
    }

    @Test
    public void convertTest_entityNullToDto() {
        Assert.assertNull(UserConverter.convert((UserEntity) null));
    }

    @Test
    public void convertListTest_entitiesToDtos() {
        List<UserEntity> expected = createUserEntityList();
        List<UserDto> actually = UserConverter.convert(expected);
        assertEquals(2, expected.size());
        assertEquals(2, actually.size());
        assertUser(actually.get(0), expected.get(0), "testname1", "testblabalatest1");
        assertUser(actually.get(1), expected.get(1), "testname2", "testblabalatest2");
    }

    @Test
    public void convertListTest_entitiesNullToDtos() {
        List<UserDto> result = UserConverter.convert((List<UserEntity>) null);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void convertTest_dtoToEntity() {
        UserDto expected = generateUserDto();
        UserEntity actually = UserConverter.convert(expected);
        assertUser(expected, actually, "testname", "testblabalatest");
    }

    @Test
    public void reconvertTest_nullDtoToEntity() {
        assertNull(UserConverter.convert((UserDto) null));
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
