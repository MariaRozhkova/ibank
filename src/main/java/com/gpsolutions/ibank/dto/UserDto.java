package com.gpsolutions.ibank.dto;

import com.gpsolutions.ibank.entity.BankAccountEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto{
    private String firstName;
    private String lastName;
    private String patronymic;
    private String passportNumber;
    private String email;
    private String login;
    private String password;
    private Boolean enabled;
    private List<BankAccountEntity> bankAccount;
}