package controller;

import java.util.HashMap;

/**
 * This interface represents validators which are used to validate user input.
 */
public interface Validator {

  /**
   * This method is used validate if a given input is float.
   *
   * @param content  String which is the input to be validated.
   * @param required boolean to tell if this field is mandatory.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateFloat(String content, boolean required);

  /**
   * This method is used to validate date including invalid formats, weekend and future date.
   *
   * @param content String which is the input to be validated.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateDate(String content);

  /**
   * This method is used to validate name entered by the user.
   *
   * @param content String which is the input to be validated.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateName(String content);

  /**
   * This method is used to validate date including invalid formats and weekend.
   *
   * @param content  String which is the input to be validated.
   * @param required boolean to tell if this field is mandatory.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateFutureDate(String content, boolean required);

  /**
   * This method is used validate if a given input is int.
   *
   * @param content String which is the input to be validated.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateInt(String content);

  /**
   * This method is used to validate username entered by the user.
   *
   * @param content String which is the input to be validated.
   * @param loading boolean to tell if this loading a user from file system.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateUserName(String content, boolean loading);

  /**
   * This method is used to validate portfolio entered by the user.
   *
   * @param content String which is the input to be validated.
   * @param loading boolean to tell if this loading a portfolio.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validatePortfolioName(String content, boolean loading);

  /**
   * This method is used to validate the stock symbol.
   *
   * @param content String which is the input to be validated.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateSymbol(String content);

  /**
   * This method is used to validate the weights entered by the user.
   *
   * @param stocks HashMap which contains symbol as the key and weight as value.
   * @return returns the result after validation which is of type ValidationResult.
   */
  ValidationResult validateWeights(HashMap<String, Float> stocks);
}
