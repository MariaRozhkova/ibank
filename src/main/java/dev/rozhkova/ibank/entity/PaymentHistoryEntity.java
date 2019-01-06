package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "payment_history")
public class PaymentHistoryEntity extends BaseEntity {
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "user")
    private UserEntity user;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "bank_card")
    private BankCardEntity bankCard;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_operation")
    private Date dateOperation;
    @NotNull
    @Column(name = "money_amount")
    private Double moneyAmount;
    @NotNull
    @Column(name = "payment_account")
    //@Fetch(value = FetchMode.SUBSELECT)
    private String paymentAccount;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "payment_operation")
    private PaymentOperationEntity paymentOperation;
}