package controller;

public class ValidationResult<T>{
  public final boolean result;

  public final T data;
  public final String errorMessage;

  ValidationResult(boolean result,String errorMessage,T data){
    this.result = result;
    this.errorMessage = errorMessage;
    this.data = data;
  }

  @Override
  public String toString(){
    return this.result + " "+this.errorMessage + " "+this.data;
  }
}