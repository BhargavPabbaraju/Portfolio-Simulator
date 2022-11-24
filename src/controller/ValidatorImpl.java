package controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import models.NewerModel;

public class ValidatorImpl implements Validator {

  private String errorMessage;
  private final NewerModel model;

  ValidatorImpl(NewerModel model) {
    this.model = model;
  }


  private ValidationResult emptyValidation(String content) {
    if (content.equals("") || content == null) {
      return new ValidationResult(false, "cannot be empty", content);
    }
    return new ValidationResult(true, "", content);
  }

  @Override
  public ValidationResult validateFloat(String content) {
    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      return new ValidationResult(true, "", 1000f);
    }

    float result;
    String errorMessage = "Must be a positive floating point number";
    try {
      result = Float.parseFloat(content);
      if (result > 0) {
        return new ValidationResult(true, "", result);
      } else {
        return new ValidationResult(false, errorMessage, content);
      }
    } catch (Exception e) {
      return new ValidationResult(false, errorMessage, content);
    }
  }

  @Override
  public ValidationResult validateDate(String content) {
    try {
      LocalDate date = LocalDate.parse(content,
              DateTimeFormatter.ISO_LOCAL_DATE);
      if (isWeekend(date)) {
        return new ValidationResult(false, "Date cannot be Weekend", content);

      }
      if (futureDate(date)) {
        return new ValidationResult(false, "Future date cannot be entered", content);
      }
      return new ValidationResult(true, "", content);

    } catch (Exception e) {
      return new ValidationResult(false, "Invalid date format ", content);
    }

  }

  @Override
  public ValidationResult validateName(String content) {

    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      return empty;
    }

    if (content.contains(" ")) {
      return new ValidationResult(false, "Name cannot contain spaces", content);
    }

    errorMessage = "Name cannot contain any of []{}:, characters";

    String[] invalidCharacters = {"[", "]", "{", "}", ":", ","};
    for (String s : invalidCharacters) {
      if (content.contains(s)) {
        return new ValidationResult(false, errorMessage, content);
      }
    }
    return new ValidationResult(true, "", content);

  }

  @Override
  public ValidationResult validateFutureDate(String content) {
    try {
      LocalDate date = LocalDate.parse(content,
              DateTimeFormatter.ISO_LOCAL_DATE);
      if (isWeekend(date)) {
        return new ValidationResult(false, "Date cannot be Weekend", content);

      }
      return new ValidationResult(true, "", content);

    } catch (Exception e) {
      return new ValidationResult(false, "Invalid date format ", content);
    }
  }

  @Override
  public ValidationResult validateInt(String content) {
    return null;
  }

  private ValidationResult validateNewUserName(boolean condition, String content, String errorMessage) {
    if (condition) {
      return new ValidationResult(false, errorMessage, content);
    } else {
      return new ValidationResult(true, "", content);
    }
  }

  @Override
  public ValidationResult validateUserName(String content, boolean loading) {
    ValidationResult validationResult = validateName(content);
    if (validationResult.result) {
      if (loading) {
        return validateNewUserName(!model.userExists(content), content,
                "User does not exist");
      } else {
        return validateNewUserName(model.userExists(content), content,
                "User already exists");
      }
    } else {
      return validationResult;
    }
  }

  @Override
  public ValidationResult validatePortfolioName(String content, boolean loading) {
    ValidationResult validationResult = validateName(content);
    if (validationResult.result) {
      if (loading) {
        return validateNewUserName(!model.portfolioExists(content), content,
                "Portfolio does not exist");
      } else {
        return validateNewUserName(model.portfolioExists(content), content,
                "Portfolio already exists");
      }
    } else {
      return validationResult;
    }
  }

  private boolean isWeekend(LocalDate date) {
    DayOfWeek day = date.getDayOfWeek();
    return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
  }

  private boolean futureDate(LocalDate date) {
    LocalDate today = LocalDate.now();
    if (date.compareTo(today) > 0) {
      return true;
    } else {
      return false;
    }
  }


}
