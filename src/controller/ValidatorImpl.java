package controller;


import models.NewerModel;

public class ValidatorImpl implements Validator {

  private String errorMessage;
  private final NewerModel model;

  ValidatorImpl(NewerModel model){
    this.model = model;
  }


  private ValidationResult emptyValidation(String content){
    if(content.equals("")||content==null){
      return new ValidationResult(false,"cannot be empty",content);
    }
    return new ValidationResult(true,"",content);
  }
  @Override
  public ValidationResult validateFloat(String content) {
    ValidationResult empty = emptyValidation(content);
    if(!empty.result){
      return empty;
    }

    float result;
    String errorMessage ="Must be a positive floating point number";
    try{
      result = Float.parseFloat(content);
      if(result>0){
        return new ValidationResult(true,"",result);
      }else{
        return new ValidationResult(false,errorMessage,content);
      }
    }catch(Exception e){
      return new ValidationResult(false,errorMessage,content);
    }
  }

  @Override
  public ValidationResult validateDate(String content) {
    return null;
  }

  @Override
  public ValidationResult validateName(String content) {

    ValidationResult empty = emptyValidation(content);
    if(!empty.result){
      return empty;
    }

    if(content.contains(" ")){
      return new ValidationResult(false,"Name cannot contain spaces",content);
    }

    errorMessage = "Name cannot contain any of []{}:, characters";

    String[] invalidCharacters = {"[", "]", "{", "}", ":", ","};
    for (String s : invalidCharacters) {
      if (content.contains(s)) {
        return new ValidationResult(false,errorMessage,content);
      }
    }
    return new ValidationResult(true,"",content);

  }

  @Override
  public ValidationResult validateFutureDate(String content) {
    return null;
  }

  @Override
  public ValidationResult validateInt(String content) {
    return null;
  }

  private ValidationResult validateNewUserName(boolean condition,String content,String errorMessage){
    if(condition){
      return new ValidationResult(false,errorMessage,content);
    }else{
      return new ValidationResult(true,"",content);
    }
  }

  @Override
  public ValidationResult validateUserName(String content, boolean loading) {
    ValidationResult validationResult = validateName(content);
    if(validationResult.result){
       if(loading){
         return validateNewUserName(!model.userExists(content),content,
                 "User does not exist" );
       }else{
         return validateNewUserName(model.userExists(content),content,
                 "User already exists" );
       }
    }else{
      return validationResult;
    }
  }

  @Override
  public ValidationResult validatePortfolioName(String content, boolean loading) {
    ValidationResult validationResult = validateName(content);
    if(validationResult.result){
      if(loading){
        return validateNewUserName(!model.portfolioExists(content),content,
                "Portfolio does not exist" );
      }else{
        return validateNewUserName(model.portfolioExists(content),content,
                "Portfolio already exists" );
      }
    }else{
      return validationResult;
    }
  }


}
