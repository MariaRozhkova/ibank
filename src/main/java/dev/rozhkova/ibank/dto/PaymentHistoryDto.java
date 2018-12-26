package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentHistoryDto extends BaseDto{
    private UserEntity user;
    private LocalDateTime dateOperation;
    private Double moneyAmount;

    //добавить другие поля
}