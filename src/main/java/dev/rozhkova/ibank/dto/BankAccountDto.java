package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BankAccountDto {
    private String accountNumber;
    private Double moneyAmount;
    private List<BankCardDto> bankCardEntity;
    private Boolean enabled;
}