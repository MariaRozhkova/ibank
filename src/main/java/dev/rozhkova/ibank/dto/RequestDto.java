package dev.rozhkova.ibank.dto;

import lombok.Data;

@Data
public class RequestDto {
    private String email;
    private String accountNumber;
}
