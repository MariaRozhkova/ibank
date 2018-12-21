package com.gpsolutions.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankAccountDto {
    private Long id;
    private Double moneyAmount;
    private Long userId;
    private String accountNumber;
}