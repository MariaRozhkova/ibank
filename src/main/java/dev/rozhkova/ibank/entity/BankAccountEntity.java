package dev.rozhkova.ibank.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_account")
public class BankAccountEntity extends BaseEntity{
  @NotNull
  @Column(name = "account_number")
  private String accountNumber;
  @NotNull
  @Column(name = "money_amount")
  private Double moneyAmount;
  /*@NotNull*/
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bankAccount")
  @JsonIgnore
  private List<BankCardEntity> bankCard;
  private Boolean enabled;

    public BankAccountEntity(@NotNull String accountNumber, @NotNull Double moneyAmount, @NotNull UserEntity user) {
        this.accountNumber = accountNumber;
        this.moneyAmount = moneyAmount;
        this.user = user;
    }

    public BankAccountEntity(@NotNull String accountNumber, @NotNull Double moneyAmount) {
        this.accountNumber = accountNumber;
        this.moneyAmount = moneyAmount;
    }
}
