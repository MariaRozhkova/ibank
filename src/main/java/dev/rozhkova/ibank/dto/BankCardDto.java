package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.BankAccountEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class BankCardDto extends BaseDto {
    private String cardNumber;
    private String cardHolderName;
    private String date;
    private int cvv;
    private BankAccountEntity bankAccount;
    private Boolean enabled;
}