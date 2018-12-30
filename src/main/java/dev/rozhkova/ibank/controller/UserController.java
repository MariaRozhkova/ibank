package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
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
    private final BankAccountService bankAccountService;

    @GetMapping("/list")
    public ResponseEntity getAllUsers() {
        try {
            final List<UserDto> allUser = userService.getAllUsers();
            if (allUser != null) {
                return new ResponseEntity<>(allUser, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody final UserDto userDto) {
        try {
            userService.create(userDto);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable final Long id) {
        try {
            UserDto user = userService.getUserById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.FOUND);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lockUser/{id}")
    public ResponseEntity lockUserAccounts(@PathVariable final Long id) {
        UserEntity user;
        try {
            user = userService.getUserEntityById(id);
            user.setEnabled(false);
            userService.saveUser(user);
            bankAccountService.lockAllBankAccountEntityByUser(user);
            return new ResponseEntity("Accounts has been locked successfully", HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unlockUser/{id}")
    public ResponseEntity unlockUserAccounts(@PathVariable final Long id) {
        UserEntity user;
        try {
            user = userService.getUserEntityById(id);
            user.setEnabled(true);
            userService.saveUser(user);
            bankAccountService.unlockAllBankAccountEntityByUser(user);
            return new ResponseEntity("Accounts has been locked successfully", HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeUser(@RequestBody final UserDto userDto) {
        try {
            userService.removeUser(userDto);
            return new ResponseEntity("User removed", HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userAfterUpdate) {
        try {
            userService.updateUser(id, userAfterUpdate);
            return new ResponseEntity("User updated", HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}