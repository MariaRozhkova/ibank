package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "saved_payment")
public class SavedPaymentEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "bank_card")
    private BankCardEntity bankCard;
    @NotNull
    @Column(name = "money_amount")
    private Double moneyAmount;
    @NotNull
    @Column(name = "payment_account")
    private String paymentAccount;
    @ManyToOne
    @JoinColumn(name = "payment_operation")
    private PaymentOperationEntity paymentOperation;
}