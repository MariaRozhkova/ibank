package dev.rozhkova.ibank.entity;

import dev.rozhkova.ibank.validator.ValidEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity{
  @NotNull
  @Column(name = "first_name")
  private String firstName;
  @NotNull
  @Column(name = "last_name")
  private String lastName;
  private String patronymic;
  @NotNull
  @Column(name = "passport_number", unique = true)
  private String passportNumber;
  @NotNull
  @ValidEmail
  @Column(unique = true)
  private String email;
  @NotNull
  @Column(unique = true)
  private String login;
  @NotNull
  private String password;
  @Column(name = "enabled", columnDefinition = "boolean default true")
  private Boolean enabled;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<BankAccountEntity> bankAccount;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<PaymentHistoryEntity> paymentHistory;
  @Column(name = "role", columnDefinition = "varchar(50) default ROLE_USER")
  private String role;

  public UserEntity(final String james, final String alan, final String hetfield, final String hc1235685,
                    final String s, final String user1, final String user11, final ArrayList<BankAccountEntity> bankAccountEntities, final ArrayList<PaymentHistoryEntity> paymentHistoryEntities, final String role_user) {
  }
}
