package com.gpsolutions.ibank.controllers;

import com.gpsolutions.ibank.dto.UserDto;
import com.gpsolutions.ibank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /*@Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/list")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody final UserDto userDto) {
        userService.save(userDto);
        return "User created";
    }

    @GetMapping(path="/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}