package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoDboConverter<UserDto, UserEntity> {
    @Override
    public UserDto convertToDto(UserEntity dbo) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(dbo, userDto);
        return userDto;
    }

    @Override
    public UserEntity convertToDbo(UserDto dto) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity);
        return userEntity;
    }
}