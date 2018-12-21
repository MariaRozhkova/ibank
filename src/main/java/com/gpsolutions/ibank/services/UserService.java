package com.gpsolutions.ibank.services;

import com.gpsolutions.ibank.converter.UserConverter;
import com.gpsolutions.ibank.dbo.UserDbo;
import com.gpsolutions.ibank.dto.UserDto;
import com.gpsolutions.ibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, /*BCryptPasswordEncoder bCryptPasswordEncoder,*/ final UserConverter userConverter) {
        this.userRepository = userRepository;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConverter = userConverter;
    }

    public UserDbo findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void save(UserDto userDto) {
        UserDbo userDbo = userConverter.convertToDbo(userDto);
        /*userDbo.setPassword(bCryptPasswordEncoder.encode(userDbo.getPassword()));*/
        userDbo.setPassword(userDbo.getPassword());
        userRepository.save(userDbo);
    }

    public List<UserDto> getAllUsers() {
       return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userConverter.convertToDto(userRepository.findById(id).get());
    }
}