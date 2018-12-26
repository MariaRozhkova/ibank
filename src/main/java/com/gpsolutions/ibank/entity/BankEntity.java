package com.gpsolutions.ibank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "bank")
@NoArgsConstructor
public class BankEntity extends BaseEntity {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
}