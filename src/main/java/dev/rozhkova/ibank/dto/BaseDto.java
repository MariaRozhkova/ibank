package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseDto {
    private Long id;
}