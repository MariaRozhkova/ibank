package com.gpsolutions.ibank.converter;

import com.gpsolutions.ibank.dbo.UserDbo;
import com.gpsolutions.ibank.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoDboConverter<UserDto, UserDbo> {
    @Override
    public UserDto convertToDto(UserDbo dbo) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(dbo, userDto);
        return userDto;
    }

    @Override
    public UserDbo convertToDbo(UserDto dto) {
        final UserDbo userDbo = new UserDbo();
        BeanUtils.copyProperties(dto, userDbo);
        return userDbo;
    }
}