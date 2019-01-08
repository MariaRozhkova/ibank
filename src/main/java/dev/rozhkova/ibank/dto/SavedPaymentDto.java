package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SavedPaymentDto extends BaseDto{
    private BankCardDto bankCard;
    private Double moneyAmount;
    private String paymentAccount;
    private PaymentOperationDto paymentOperation;
}
