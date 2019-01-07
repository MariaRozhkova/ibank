package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.converter.BankCardConverter;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.mock.MockDataUser;
import dev.rozhkova.ibank.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final BankCardConverter bankCardConverter = new BankCardConverter();

    private final BankAccountConverter bankAccountConverter = new BankAccountConverter(bankCardConverter);

    @Spy
    private final UserConverter userConverter = new UserConverter(bankAccountConverter);


    @Test
    public void findByLogin() {
    }

    @Test
    public void create() throws UserException {
        final UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("mary");
        userEntity.setLastName("mary");
        userEntity.setPatronymic("mary");
        userEntity.setPassportNumber("23456789");
        userEntity.setEmail("marymary@mail.ru");
        userEntity.setLogin("mary");
        userEntity.setPassword("mary");

        doReturn(userEntity).when(userRepository).save(any(UserEntity.class));

        userService.create(userEntity);

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void getAllUsers() throws UserException {
        final List<UserEntity> findAllResult = new ArrayList<>();
        final UserEntity userEntity = MockDataUser.userEntity();
        findAllResult.add(userEntity);
        findAllResult.add(userEntity);
        doReturn(findAllResult).when(userRepository).findAll();

        final List<UserDto> userList = userService.getAllUsers();
        verify(userRepository, times(1)).findAll();

        assertEquals(findAllResult.size(), userList.size());
        for (final UserDto userDto : userList) {
            assertEquals(userEntity.getFirstName(), userDto.getFirstName());
            assertEquals(userEntity.getLastName(), userDto.getLastName());
            assertEquals(userEntity.getPatronymic(), userDto.getPatronymic());
            assertEquals(userEntity.getPassportNumber(), userDto.getPassportNumber());
            assertEquals(userEntity.getEmail(), userDto.getEmail());
            assertEquals(userEntity.getLogin(), userDto.getLogin());
        }
    }

    @Test
    public void getUserById() throws UserException {
        /*final UserEntity userEntity = MockDataUser.userEntity();
        doReturn(userEntity).when(userRepository).findById(1L);
        final UserDto userDto = userService.getUserById(1L);
        verify(userRepository, times(1)).findById(1L);*/
    }

    @Test
    public void removeUser() throws UserException {
        doNothing().when(userRepository).deleteById(1L);
        userService.removeUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateUser() {
    }
}