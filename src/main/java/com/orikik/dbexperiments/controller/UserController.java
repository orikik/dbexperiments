package com.orikik.dbexperiments.controller;

import com.orikik.dbexperiments.dto.UserDto;
import com.orikik.dbexperiments.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource(name = "userServiceRepository")
    private UserService userService;

    @GetMapping("/user/all")
    public List<UserDto> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserDto userById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/user/update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
