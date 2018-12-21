package com.gpsolutions.ibank.dbo;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class UserDbo {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "first_name")
  private String firstName;
  @NotNull
  @Column(name = "last_name")
  private String lastName;
  private String patronymic;
  @NotNull
  @UniqueElements
  @Column(name = "passport_number")
  private String passportNumber;
  @NotNull
  private Boolean unlocked;
  @NotNull
  @UniqueElements
  private String login;
  @NotNull
  private String password;
  @NotNull
  private Long role_id;

  public UserDbo() {
  }

  public UserDbo(String firstName, String lastName, String patronymic, @UniqueElements String passportNumber, @UniqueElements String login, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.patronymic = patronymic;
    this.passportNumber = passportNumber;
    this.login = login;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
