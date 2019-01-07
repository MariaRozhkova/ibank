package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BankAccountDto {
    private String accountNumber;
    private Double moneyAmount;
    private List<BankCardDto> bankCardDtos;
    private Boolean enabled;
}