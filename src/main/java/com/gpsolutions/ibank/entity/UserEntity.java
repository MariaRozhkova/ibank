package com.gpsolutions.ibank.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpsolutions.ibank.validator.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
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
  /*@UniqueElements*/
  @Column(name = "passport_number")
  private String passportNumber;
 /* @UniqueElements*/
  @NotNull
  @ValidEmail
  private String email;
  @NotNull
  /*@UniqueElements*/
  private String login;
  @NotNull
  private String password;
  /*@Column(columnDefinition = "boolean default 1")*/
  private Boolean enabled;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  @JsonIgnore
  private List<BankAccountEntity> bankAccount;

    public UserEntity(@NotNull String firstName, @NotNull String lastName, String patronymic, @NotNull String passportNumber, @NotNull String email, @NotNull String login, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.passportNumber = passportNumber;
        this.email = email;
        this.login = login;
        this.password = password;
        this.enabled = true;
    }
}
