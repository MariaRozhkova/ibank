package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.mock.MockDataUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserConverterTest {

    private final BankCardConverter bankCardConverter = new BankCardConverter();

    private final BankAccountConverter bankAccountConverter = new BankAccountConverter(bankCardConverter);

    private final UserConverter userConverter = new UserConverter(bankAccountConverter);

    @Test
    public void convertToDto() {
        final UserEntity userEntity = MockDataUser.userEntity();
        final UserDto userDto = userConverter.convertToDto(userEntity);
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getPatronymic(), userDto.getPatronymic());
        assertEquals(userEntity.getPassportNumber(), userDto.getPassportNumber());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getLogin(), userDto.getLogin());
    }

    @Test
    public void convertToDbo() {
        final UserDto userDto = MockDataUser.userDto();
        final UserEntity userEntity = userConverter.convertToDbo(userDto);
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
        assertEquals(userDto.getPatronymic(), userEntity.getPatronymic());
        assertEquals(userDto.getPassportNumber(), userEntity.getPassportNumber());
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        assertEquals(userDto.getLogin(), userEntity.getLogin());
    }
}