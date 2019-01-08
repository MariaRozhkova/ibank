package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentOperationDto extends BaseDto{
    private String name;
}