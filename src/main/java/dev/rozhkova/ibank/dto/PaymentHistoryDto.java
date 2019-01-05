package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentHistoryDto extends BaseDto{
    private BankCardDto bankCard;
    private Date dateOperation;
    private Double moneyAmount;
    private String paymentAccount;
    private PaymentOperationDto paymentOperation;
}