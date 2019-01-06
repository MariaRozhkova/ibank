package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.MockDataUser;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.UserEntity;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;

public class UserConverterTest {

    private final BankCardConverter bankCardConverter = new BankCardConverter();

    private final BankAccountConverter bankAccountConverter = new BankAccountConverter(bankCardConverter);

    private final UserConverter userConverter = new UserConverter(bankAccountConverter);

    @Test
    public void convertToDto() {
        /*final UserEntity userEntity = MockDataUser.userEntity();
        final UserDto chapterDto = userConverter.convertToDto(userEntity);
        assertEquals(userEntity.getFirstName(), chapterDto.getFirstName());
        assertEquals(userEntity.getLastName(), chapterDto.getLastName());
        assertEquals(userEntity.getPatronymic(), chapterDto.getPatronymic());
        assertEquals(userEntity.getPassportNumber(), chapterDto.getPassportNumber());
        assertEquals(userEntity.getEmail(), chapterDto.getEmail());
        assertEquals(userEntity.getLogin(), chapterDto.getLogin());*/
    }

    @Test
    public void convertToDbo() {

    }
}