package com.gpsolutions.ibank.dbo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleDbo {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String role;
}
