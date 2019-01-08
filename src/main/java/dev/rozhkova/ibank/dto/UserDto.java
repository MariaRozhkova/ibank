package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto{
    private String firstName;
    private String lastName;
    private String patronymic;
    private String passportNumber;
    private String email;
    private String login;
    private Boolean enabled;
    private List<BankAccountDto> bankAccount;
}