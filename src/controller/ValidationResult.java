package controller;

/**
 * This class represents result of the validation.
 */
public class ValidationResult<T> {
  public final boolean result;

  public final T data;
  public final String errorMessage;

  /**
   * Constructs Validation result which is the result after validation.
   *
   * @param result which tells if the content is valid.
   * @param errorMessage which is the message after the validation.
   * @param data the result after validation.
   */
  ValidationResult(boolean result, String errorMessage, T data) {
    this.result = result;
    this.errorMessage = errorMessage;
    this.data = data;
  }

  @Override
  public String toString() {
    return this.result + " " + this.errorMessage + " " + this.data;
  }
}