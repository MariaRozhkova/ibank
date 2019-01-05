package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserConverter implements DtoDboConverter<UserDto, UserEntity> {

    private final BankAccountConverter bankAccountConverter;

    @Override
    public UserDto convertToDto(final UserEntity dbo) {
        final UserDto userDto = new UserDto();
        final List<BankAccountDto> bankAccountDtos = bankAccountConverter.convertToDto(dbo.getBankAccount());
        userDto.setId(dbo.getId());
        userDto.setFirstName(dbo.getFirstName());
        userDto.setLastName(dbo.getLastName());
        userDto.setPatronymic(dbo.getPatronymic());
        userDto.setPassportNumber(dbo.getPassportNumber());
        userDto.setEmail(dbo.getEmail());
        userDto.setLogin(dbo.getLogin());
        userDto.setEnabled(dbo.getEnabled());
        userDto.setBankAccount(bankAccountDtos);
        return userDto;
    }

    @Override
    public UserEntity convertToDbo(final UserDto dto) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity);
        return userEntity;
    }
}