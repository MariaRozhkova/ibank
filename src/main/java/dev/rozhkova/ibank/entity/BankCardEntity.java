package dev.rozhkova.ibank.entity;

import dev.rozhkova.ibank.utils.DateUtility;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "bank_card")
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "bank_account")
    private BankAccountEntity bankAccount;
    private Boolean enabled;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "bankCard")
    private List<PaymentHistoryEntity> paymentHistory;

    public boolean getEnabled() {
        final LocalDate cardDate = DateUtility.parse(date);
        if (cardDate == null || cardDate.isBefore((LocalDate.now()))) {
            enabled = false;
        }
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        final LocalDate cardDate = DateUtility.parse(date);
        if (cardDate == null || cardDate.isBefore((LocalDate.now()))) {
            this.enabled = false;
        } else {
            this.enabled = enabled;
        }
    }
}
