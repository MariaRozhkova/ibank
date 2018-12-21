package com.gpsolutions.ibank.dbo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccountDbo {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "amount_of_money")
  private Double moneyAmount;
  @NotNull
  @Column(name = "user_id")
  private Long userId;
  @NotNull
  @Column(name = "account_number")
  private String accountNumber;
}
