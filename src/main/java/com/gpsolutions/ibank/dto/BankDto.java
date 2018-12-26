package com.gpsolutions.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankDto extends BaseDto {
    private String name;
    private String address;
    private String phone;
}