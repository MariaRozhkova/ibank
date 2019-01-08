package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankDto extends BaseDto {
    private String name;
    private String address;
    private String phone;
}