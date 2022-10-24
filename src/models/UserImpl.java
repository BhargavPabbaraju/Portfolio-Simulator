package models;

import java.util.HashMap;

/**
 * This class represents a single user.
 */
public class UserImpl implements User {
  final String userName;
  float balance;

  final static float DEFAULTBALANCE = 1000.00F;

  Portfolio activePortfolio;

  HashMap<String,Portfolio> portfolioList;

  /**
   * This constructor creates a user with a given username.
   *
   * @param userName
   * @throws IllegalArgumentException if a userName is already taken.
   */

  public UserImpl(String userName) throws IllegalArgumentException {
    //Throw Exception if userName already exists
    this.userName = userName;
    balance = DEFAULTBALANCE;

  }

  /**
   * This constructor creates a user with given username and initial balance.
   *
   * @param userName
   * @param balance
   * @throws IllegalArgumentException if username is already taken or if the balance is invalid.
   */
  UserImpl(String userName, float balance) throws IllegalArgumentException {
    //Throw exception if userName already exists
    //Throw exception if balance is negative
    this.userName = userName;
    this.balance = balance;
  }


  @Override
  public void createPortfolio(String portfolioName) {

  }

  @Override
  public Portfolio loadPortfolio(String portfolioName) {
    return null;
  }

  @Override
  public float getBalance() {
    return this.balance;
  }

  @Override
  public void deductFromBalance(float amount) {
    if(amount<=this.balance){
      this.balance-=amount;
    }
  }

  @Override
  public void addToBalance(float amount) {
    this.balance+=amount;
  }
}
