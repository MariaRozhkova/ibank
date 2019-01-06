package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    /*@Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

   /* @Spy
    private UserConverter userConverter;*/


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

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
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword("mary");
        /*userEntity.setEnabled(true);
        userEntity.setRole("ROLE_USER");*/
        /*final List<BankAccountEntity> bankAccountEntities = new ArrayList<>();
        userEntity.setBankAccount(bankAccountEntities);
        final List<PaymentHistoryEntity> paymentHistoryEntities = new ArrayList<>();
        userEntity.setPaymentHistory(paymentHistoryEntities);*/

        doReturn(userEntity).when(userRepository).save(userEntity);

        userService.create(userEntity);

        //verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void getAllUsers() throws UserException {
        /*final List<UserEntity> findAllResult = new ArrayList<>();
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
*/

    }

    @Test
    public void getUserById() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void findByLogin1() {
    }
}