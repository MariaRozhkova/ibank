package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity getAllUsers() {
        try {
            final List<UserDto> allUser = userService.getAllUsers();
            if (allUser != null) {
                return new ResponseEntity<>(allUser, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody final UserDto userDto) {
        try {
            userService.create(userDto);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable final Long id) {
        try {
            final UserDto user = userService.getUserById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.FOUND);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity removeUser(@PathVariable final Long id) {
        try {
            userService.removeUser(id);
            return new ResponseEntity<>("User removed", HttpStatus.OK);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity updateUser(@PathVariable final Long id, @RequestBody final UserDto userAfterUpdate) {
        try {
            userService.updateUser(id, userAfterUpdate);
            return new ResponseEntity<>("User updated", HttpStatus.OK);
        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}