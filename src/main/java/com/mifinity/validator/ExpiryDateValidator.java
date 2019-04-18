package com.mifinity.validator;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExpiryDateValidator implements ConstraintValidator<ExpiryDate, Date> {

  @Override
  public boolean isValid(Date value, ConstraintValidatorContext context) {

    if (value == null) {
      return false;
    }

    Calendar calendarExpiry = Calendar.getInstance();
    Calendar calendarNow = Calendar.getInstance();

    calendarExpiry.setTime(value);

    if (calendarExpiry.get(Calendar.YEAR) > calendarNow.get(Calendar.YEAR)) {
      return true;
    } else {
      if (calendarExpiry.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
          && calendarExpiry.get(Calendar.MONTH) >= calendarNow.get(Calendar.MONTH)) {
        return true;
      }
    }

    return false;
  }
}
