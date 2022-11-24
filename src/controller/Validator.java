package controller;

import java.util.HashMap;

public interface Validator {
  ValidationResult validateFloat(String content);

  ValidationResult validateDate(String content);

  ValidationResult validateName(String content);

  ValidationResult validateFutureDate(String content);

  ValidationResult validateInt(String content);



  ValidationResult validateUserName(String content, boolean loading);

  ValidationResult validatePortfolioName(String content, boolean loading);
}
