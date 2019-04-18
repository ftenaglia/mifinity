package com.mifinity.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

import com.mifinity.validator.ExpiryDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

  @CreditCardNumber @NotBlank @Id private String number;
  @NotBlank private String name;

  @NotNull
  @ExpiryDate
  @DateTimeFormat(pattern = "yy/MM")
  private Date expiryDate;

  @ManyToOne private User owner;
}
