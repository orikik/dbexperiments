package com.orikik.dbexperiments.service.impl;

import com.orikik.dbexperiments.converter.UserConverter;
import com.orikik.dbexperiments.dto.UserDto;
import com.orikik.dbexperiments.entity.UserEntity;
import com.orikik.dbexperiments.repository.UserRepository;
import com.orikik.dbexperiments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("userServiceRepository")
@Transactional
public class UserServiceRepositoryImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserConverter.convert(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<UserEntity> entityOptional = userRepository.findById(id);
        if (!entityOptional.isPresent()) {
            throw new EntityNotFoundException();
        }
        return UserConverter.convert(entityOptional.get());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(UserConverter.convert(userDto));
        return UserConverter.convert(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(UserConverter.convert(userDto));
        return UserConverter.convert(userEntity);
    }
}

