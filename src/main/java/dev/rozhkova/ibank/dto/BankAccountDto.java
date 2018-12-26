package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BankAccountDto extends BaseDto{
    private Double moneyAmount;
    private UserEntity user;
    private String accountNumber;
    private List<BankCardEntity> bankCardEntity;
    private Boolean enabled;
}