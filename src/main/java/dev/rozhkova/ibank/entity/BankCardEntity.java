package dev.rozhkova.ibank.entity;

import dev.rozhkova.ibank.utils.DateUtility;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
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

    public boolean getEnabled() {
        LocalDate cardDate = DateUtility.parse(date);
        if (cardDate == null || cardDate.isBefore((LocalDate.now()))) {
            enabled = false;
        }
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        LocalDate cardDate = DateUtility.parse(date);
        if (cardDate == null || cardDate.isBefore((LocalDate.now()))) {
            this.enabled = false;
        } else {
            this.enabled = enabled;
        }
    }

}