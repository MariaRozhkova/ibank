package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankAccountDto extends BaseDto{
    private String accountNumber;
    private Double moneyAmount;
    private List<BankCardDto> bankCardDtos;
    private Boolean enabled;
}