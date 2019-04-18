package com.mifinity.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

  @NotBlank(message = "Username is mandatory")
  @Id
  private String username;

  @NotBlank(message = "Password is mandatory")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles;

  public User(String username) {
    this.username = username;
  }
}
