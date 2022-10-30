package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;



/**
 * This class represents a single user.
 */
public class UserImpl implements User {
  final String userName;
  float balance;

  final static float DEFAULTBALANCE = 10000F;

  Portfolio activePortfolio;

  HashMap<String, Portfolio> portfolioList;

  /**
   * This constructor creates a user with a given username.
   * Note: Username must not contain the following characters: {}[],:"\
   * @param userName
   * @throws IllegalArgumentException if a userName is already taken.
   */

  public UserImpl(String userName) throws IllegalArgumentException {
    //Throw Exception if file not found.
    if (!userNameExits(userName)) {
      throw new IllegalArgumentException("User doesn't exists");
    }


    this.userName = userName;
    balance = DEFAULTBALANCE;
    this.portfolioList = new HashMap<>();

  }

  /**
   * This constructor creates a user with given username and initial balance.
   *
   * @param userName
   * @param balance
   * @throws IllegalArgumentException if username is already taken or if the balance is invalid.
   */
  public UserImpl(String userName, float balance) throws IllegalArgumentException, IOException {
    //Throw exception if userName already exists
    //Throw exception if balance is negative
    if (userNameExits(userName)) {
      throw new IllegalArgumentException("User Already exists");
    }if(Loader.isInvalidName(userName)){
      throw new IllegalArgumentException("Username must not contain any of \\\"{}[],: characters.");
    }
    if(userName == null || userName.equals("")){
      throw new IllegalArgumentException("Username cannot  be empty");
    }
    if (balance < 0) {
      throw new IllegalArgumentException("Balance cannot be negative");

    }
    createFile(userName);
    this.userName = userName;
    this.balance = balance;
    this.portfolioList = new HashMap<>();

  }



  private boolean userNameExits(String userName) {
    return Files.exists(Path.of("data" + File.separator + userName + ".json"));
  }

  private void createFile(String userName) throws IOException {
    String path = "data" + File.separator + userName + ".json";
    File f = new File(path);
    f.getParentFile().mkdirs();
    f.createNewFile();
  }



  @Override
  public void addStockToPortfolio(String symbol, float numberOfShares) throws IllegalArgumentException {
    if(numberOfShares<0){
      throw new IllegalArgumentException("Number of Shares cannot be negative");
    }
    this.activePortfolio.createStock(symbol,numberOfShares);
  }

  @Override
  public void addStockToPortfolio(String symbol, float numberOfShares, LocalDate date) {
    this.activePortfolio.createStock(symbol,numberOfShares,date);
  }

  @Override
  public float getTotalValue(String date) {
    return this.activePortfolio.getTotalValue(date);
  }

  @Override
  public StringBuilder getComposition() {
    return this.activePortfolio.getComposition();
  }

  @Override
  public void save() throws IOException {
    Loader.save(this);
  }

  @Override
  public String getName() {
    return this.userName;
  }

  @Override
  public HashMap<String, Portfolio> getPortfolios() {
    return this.portfolioList;
  }


  @Override
  public void createPortfolio(String portfolioName) throws IllegalArgumentException {
    if (portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio already exists");
    } else {
      Portfolio portfolioObj = new PortfolioImpl(portfolioName, this);
      this.activePortfolio = portfolioObj;
      this.portfolioList.put(portfolioName, portfolioObj);
    }
  }

  @Override
  public Portfolio loadPortfolio(String portfolioName) {
    if(!this.portfolioList.containsKey(portfolioName)){
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
    this.activePortfolio = this.portfolioList.get(portfolioName);
    return this.activePortfolio;
  }

  @Override
  public float getBalance() {
    return this.balance;
  }

  @Override
  public void deductFromBalance(float amount) {
    if (amount <= this.balance) {
      this.balance -= amount;
    }
  }

  @Override
  public void addToBalance(float amount) {
    this.balance += amount;
  }

  @Override
  public String toString(){
    return this.portfolioList.toString();
  }
}
