package controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import models.NewerModel;

/**
 * This class represents validators which are used to validate user input.
 */
public class ValidatorImpl implements Validator {

  private String errorMessage;
  private final NewerModel model;

  /**
   * Constructs ValidatorImpl which is used to validate the user inputs..
   *
   * @param model takes in the NewerModel object
   */
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
  public ValidationResult validateFloat(String content, boolean required) {
    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      if (!required) {
        return new ValidationResult(true, "", 0f);
      } else {
        return new ValidationResult(false, "cannot be empty", 0f);
      }

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
    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      return empty;
    }
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
  public ValidationResult validateFutureDate(String content, boolean required) {
    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      return new ValidationResult(true, "", "");
    }
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
    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      return empty;
    }

    int result;
    String errorMessage = "Must be a positive integer";
    try {
      result = Integer.parseInt(content);
      if (result > 0) {
        return new ValidationResult(true, "", result);
      } else {
        return new ValidationResult(false, errorMessage, content);
      }
    } catch (Exception e) {
      return new ValidationResult(false, errorMessage, content);
    }
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

  @Override
  public ValidationResult validateSymbol(String content) {
    ValidationResult empty = emptyValidation(content);
    if (!empty.result) {
      return empty;
    } else {
      if (model.isValidSymbol(content)) {
        return new ValidationResult(true, "", content);
      } else {
        return new ValidationResult(false, "Invalid symbol", content);
      }
    }

  }

  @Override
  public ValidationResult validateWeights(HashMap<String, Float> stocks) {
    float sum = 0;
    for (String symbol : stocks.keySet()) {
      sum += stocks.get(symbol);
    }
    if (sum == 100f) {
      return new ValidationResult(true, "", "");
    }
    return new ValidationResult(false, "Weights do not add to 100", "");

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
