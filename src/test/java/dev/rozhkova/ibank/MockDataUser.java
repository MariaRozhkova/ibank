package dev.rozhkova.ibank;

import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.UserEntity;

public class MockDataUser {
    public static UserEntity userEntity() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("userEntity firstName");
        userEntity.setLastName("userEntity lastName");
        userEntity.setPatronymic("userEntity patronymic");
        userEntity.setPassportNumber("userEntity passportNumber");
        userEntity.setEmail("bdfdbsf@mail.ru");
        userEntity.setLogin("admin");
        userEntity.setPassword("admin");
        return userEntity;
    }

    public static UserDto userDto() {
        final UserDto userDto = new UserDto();
        userDto.setFirstName("userDto firstName");
        userDto.setLastName("userDto lastName");
        userDto.setPatronymic("userDto patronymic");
        userDto.setPassportNumber("userDto passportNumber");
        userDto.setEmail("bdfdbsf@mail.ru");
        userDto.setLogin("admin");
        return userDto;
    }
}