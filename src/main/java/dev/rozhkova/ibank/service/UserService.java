package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserConverter userConverter;

    public UserEntity findByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    public void create(final UserEntity userEntity) throws UserException {
        //final UserEntity userEntity = userConverter.convertToDbo(userDto);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole("ROLE_USER");
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
    }

    public List<UserDto> getAllUsers() throws UserException {
       return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(final Long id) throws UserException {
        return userConverter.convertToDto(userRepository.findById(id).orElse(new UserEntity()));
    }

    public UserEntity getUserEntityById(final Long id) throws UserException {
        return userRepository.findById(id).orElse(new UserEntity());
    }

    public void removeUser(final Long id) throws UserException {
        userRepository.deleteById(id);
    }

    public void updateUser(final Long id, final UserDto userAfterUpdate) throws UserException {
        final UserEntity userBeforeUpdate = userRepository.findById(id).orElse(new UserEntity());
        final UserEntity userAfterUpdateDbo = userConverter.convertToDbo(userAfterUpdate);
        if (!userAfterUpdateDbo.getFirstName().equals(userBeforeUpdate.getFirstName())) {
            userBeforeUpdate.setFirstName(userAfterUpdateDbo.getFirstName());
        } else if (!userAfterUpdateDbo.getLastName().equals(userBeforeUpdate.getLastName())) {
            userBeforeUpdate.setFirstName(userAfterUpdateDbo.getLastName());
        } else if (!userAfterUpdateDbo.getPatronymic().equals(userBeforeUpdate.getPatronymic())) {
            userBeforeUpdate.setPatronymic(userAfterUpdateDbo.getPatronymic());
        } else if (!userAfterUpdateDbo.getPassportNumber().equals(userBeforeUpdate.getPassportNumber())) {
            userBeforeUpdate.setPassportNumber(userAfterUpdateDbo.getPassportNumber());
        } else if (!userAfterUpdateDbo.getEmail().equals(userBeforeUpdate.getEmail())) {
            userBeforeUpdate.setEmail(userAfterUpdateDbo.getEmail());
        } else if (!userAfterUpdateDbo.getLogin().equals(userBeforeUpdate.getLogin())) {
            userBeforeUpdate.setLogin(userAfterUpdateDbo.getLogin());
        } else if (!userAfterUpdateDbo.getPassword().equals(userBeforeUpdate.getPassword())) {
            userBeforeUpdate.setPassword(userAfterUpdateDbo.getPassword());
        }
        userRepository.save(userBeforeUpdate);
    }
}