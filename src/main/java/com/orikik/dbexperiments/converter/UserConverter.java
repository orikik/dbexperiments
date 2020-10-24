package com.orikik.dbexperiments.converter;

import com.orikik.dbexperiments.dto.UserDto;
import com.orikik.dbexperiments.entity.UserEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    private UserConverter() {
    }

    public static UserDto convert(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setId(userEntity.getId());
        return userDto;
    }

    public static List<UserDto> convert(List<UserEntity> userEntities) {
        if (CollectionUtils.isEmpty(userEntities)) {
            return Collections.emptyList();
        }
        return userEntities.stream().map(UserConverter::convert).collect(Collectors.toList());
    }

    public static UserEntity convert(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setId(userDto.getId());
        return userEntity;
    }
}
