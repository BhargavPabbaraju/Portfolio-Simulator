package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


/**
 * This class represents a single user. User has a unique name and can have portfolios.
 * Creating a user is required to perform all the operations.
 */
public class UserImpl implements User {
  final String userName;
  float balance;

  final static float DEFAULTBALANCE = 10000F;

  AbstractPortfolio activePortfolio;

  HashMap<String, AbstractPortfolio> portfolioList;

  /**
   * This constructor creates a user with a given username.
   * Note: Username must not contain the following characters: {}[],:"\
   * This constructor is user while loading a user from file system.
   *
   * @param userName String which is unique for a user.
   * @throws IllegalArgumentException if a userName is already taken.
   */

  public UserImpl(String userName) throws IllegalArgumentException {
    if (!Loader.userNameExits(userName)) {
      throw new IllegalArgumentException("User doesn't exists");
    }
    this.userName = userName;
    balance = DEFAULTBALANCE;
    this.portfolioList = new HashMap<>();

  }

  /**
   * This constructor creates a user with given username and initial balance.
   * This constructor is called which creating a user from the program.
   *
   * @param userName String username which is unique for a user.
   * @param balance  float balance which is entered by the user.
   * @throws IllegalArgumentException if username is already taken or if the balance is invalid.
   */
  public UserImpl(String userName, float balance) throws IllegalArgumentException {
    if (Loader.userNameExits(userName)) {
      throw new IllegalArgumentException("User Already exists");
    }
    if (Loader.isInvalidName(userName)) {
      throw new IllegalArgumentException("Username must not contain any of \\\"{}[],: characters.");
    }
    if (userName == null || userName.equals("")) {
      throw new IllegalArgumentException("Username cannot  be empty");
    }
    if (balance < 0) {
      throw new IllegalArgumentException("Balance cannot be negative");

    }
    //createFile(userName);
    this.userName = userName;
    this.balance = balance;
    this.portfolioList = new HashMap<>();

  }




  private void createFile(String userName) throws IOException {
    String path = "data" + File.separator + userName + ".json";
    File f = new File(path);
    f.getParentFile().mkdirs();
    f.createNewFile();
  }


  @Override
  public void addStockToPortfolio(String symbol, float numberOfShares) throws
          IllegalArgumentException {
    if (numberOfShares <= 0) {
      throw new IllegalArgumentException("Shares must be a valid positive integer");
    }
    this.activePortfolio.createStock(symbol, numberOfShares);
  }

  @Override
  public void addStockToPortfolio(String symbol, float numberOfShares, LocalDate date) {
    this.activePortfolio.createStock(symbol, numberOfShares, date);
  }

  @Override
  public float getTotalValue(String date) {
    if (this.activePortfolio == null) {
      throw new IllegalStateException("No portfolio created yet");
    }

    return this.activePortfolio.getTotalValue(date);


  }

  @Override
  public StringBuilder getComposition() {
    if (this.activePortfolio == null) {
      throw new IllegalStateException("No portfolio created yet");
    }
    return this.activePortfolio.getComposition();
  }

  private LocalDate parseDate(String date){
    try {
      return LocalDate.parse(date,
              DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (Exception e) {
      throw new IllegalArgumentException("Date must be in yyyy-mm-dd format");
    }
  }

  @Override
  public void buyStock(String symbol, String date, float numberOfShares,float transactionCost) {

    this.activePortfolio.buyStock(symbol,parseDate(date),numberOfShares,transactionCost);
  }

  @Override
  public void sellStock(String symbol, String date, float numberOfShares,float transactionCost) {
    this.activePortfolio.sellStock(symbol,parseDate(date),numberOfShares,transactionCost);
  }

  @Override
  public StringBuilder getPlot(String startDate, String endDate) {
    return this.activePortfolio.getPlot(parseDate(startDate),parseDate(endDate));
  }

  @Override
  public StringBuilder getComposition(String date) {
    return this.activePortfolio.getComposition(parseDate(date));
  }

  @Override
  public float getCostBasis(String date) {
    return this.activePortfolio.getCostBasis(parseDate(date));
  }

  @Override
  public boolean isFlexiblePortfolio() {
    if(this.activePortfolio==null){
      return false;
    }
    return  this.activePortfolio.flexible;
  }

  @Override
  public boolean portfolioExists(String portfolioName) {
    return this.portfolioList.containsKey(portfolioName);
  }

  @Override
  public StringBuilder getListOfPortfolios() {
    StringBuilder str = new StringBuilder();

    str.append(String.format("%-40s%s%n","Portfolio","Type"));
    for(String portfolioName:this.portfolioList.keySet()){
      String flexible = this.portfolioList.get(portfolioName).flexible ? "Flexible" : "Inflexible";
      str.append(String.format("%-40s%s%n",portfolioName,flexible));
    }
    return str;
  }


  @Override
  public void save() throws IOException {
    if (this.portfolioList.size() > 0) {
      Loader.save(this);
    } else {
      throw new IllegalStateException("Should have at least one portfolio to save");
    }
  }

  @Override
  public String getName() {
    return this.userName;
  }

  @Override
  public HashMap<String, AbstractPortfolio> getPortfolios() {
    return this.portfolioList;
  }


  @Override
  public void createPortfolio(String portfolioName,boolean isFlexible) throws IllegalArgumentException {
    if (portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio already exists");
    } else {
      AbstractPortfolio portfolioObj;
      if(isFlexible) {
        portfolioObj = new FlexiblePortfolioImpl(portfolioName, this);
      }else{
        portfolioObj = new PortfolioImpl(portfolioName, this);
      }

      this.activePortfolio = portfolioObj;
      this.portfolioList.put(portfolioName,  portfolioObj);
    }
  }

  @Override
  public Portfolio loadPortfolio(String portfolioName) {
    if (!this.portfolioList.containsKey(portfolioName)) {
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
  public String toString() {
    return this.portfolioList.toString();
  }
}
