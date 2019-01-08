package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankCardDto extends BaseDto {
    private String cardNumber;
    private String cardHolderName;
    private String date;
    private int cvv;
    private Boolean enabled;
}