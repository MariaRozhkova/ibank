package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "bank_account")
public class BankAccountEntity extends BaseEntity{
  @NotNull
  @Column(name = "account_number")
  private String accountNumber;
  @NotNull
  @Column(name = "money_amount")
  private Double moneyAmount;
  @ManyToOne
  @JoinColumn(name = "user")
  private UserEntity user;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "bankAccount")
  private List<BankCardEntity> bankCardEntity;
  private Boolean enabled;
}
