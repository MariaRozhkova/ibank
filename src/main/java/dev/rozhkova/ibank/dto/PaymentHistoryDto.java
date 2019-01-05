package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentHistoryDto extends BaseDto{
    private BankCardDto bankCard;
    private Date dateOperation;
    private Double moneyAmount;
    private String paymentAccount;
    private PaymentOperationDto paymentOperation;
}