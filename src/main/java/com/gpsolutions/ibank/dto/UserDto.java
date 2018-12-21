package com.gpsolutions.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String passportNumber;
    private Boolean unlocked;
    private String login;
    private String password;
    private Long role_id;
}