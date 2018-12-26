package com.gpsolutions.ibank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "payment_history")
public class PaymentHistoryEntity extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;
    @NotNull
    @Column(name = "date_operation")
    @CreationTimestamp
    private LocalDateTime dateOperation;
    @NotNull
    @Column(name = "money_amount")
    private Double moneyAmount;

    //добавить другие поля
}