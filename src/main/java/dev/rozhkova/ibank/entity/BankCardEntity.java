package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "bank_card")
@NoArgsConstructor
public class BankCardEntity extends BaseEntity {
    @NotNull
    @Column(name = "card_number")
    private String cardNumber;
    @NotNull
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @NotNull
    @Column(name = "valid_date")
    private String date;
    @NotNull
    private int cvv;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_account")
    private BankAccountEntity bankAccount;
    private Boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "bankCard")
    private List<PaymentHistoryEntity> paymentHistory;

}