import java.io.IOException;
import java.text.ParseException;

import models.ApiType;
import models.NewModel;

/**
 * This class is used to test the controller in isolation.
 */
public class MockNewModel implements NewModel {
  private StringBuilder inputLog;
  private StringBuilder outputLog;

  /**
   * This constructor creates a mock model to test the controller in isolation.
   *
   * @param inputLog  String Builder to test if the input send by the controller are correct
   * @param outputLog String Builder to test if correct method is called
   */
  public MockNewModel(StringBuilder inputLog, StringBuilder outputLog) {
    this.inputLog = inputLog;
    this.outputLog = outputLog;
  }

  @Override
  public void createUser(String userName, float balance) {
    inputLog.append(userName + " " + balance + " ");
    outputLog.append("createUser ");
  }

  @Override
  public void loadUser(String userName) throws IOException, ParseException {
    inputLog.append(userName + " ");
    outputLog.append("loadUser ");

  }

  @Override
  public void createPortfolio(String portfolioName, boolean isFlexible) {
    inputLog.append(portfolioName + " " + isFlexible + " ");
    outputLog.append("createPortfolio ");

  }

  @Override
  public void buyStock(String symbol, String date, float numberOfShares, float transactionCost) {
    inputLog.append(symbol + " " + date + " " + numberOfShares + " " + transactionCost + " ");
    outputLog.append("buyStock ");

  }

  @Override
  public void sellStock(String symbol, String date, float numberOfShares, float transactionCost) {
    inputLog.append(symbol + " " + date + " " + numberOfShares + " " + transactionCost + " ");
    outputLog.append("sellStock ");

  }

  @Override
  public StringBuilder getPlot(String startDate, String endDate, ApiType apiType) {
    inputLog.append(startDate + " " + endDate + " ");
    outputLog.append("getPlot ");
    return null;
  }

  @Override
  public float getTotalValue(String date, ApiType apiType) {
    inputLog.append(date + " ");
    outputLog.append("getTotalValue ");
    return 0;
  }

  @Override
  public StringBuilder getComposition() {
    inputLog.append("");
    outputLog.append("getComposition ");
    return null;
  }


  @Override
  public StringBuilder getComposition(String date) {
    inputLog.append(date + " ");
    outputLog.append("getComposition ");
    return null;
  }

  @Override
  public float getCostBasis(String date, ApiType apiType) {
    inputLog.append(date + " ");
    outputLog.append("getCostBasis ");
    return 0;
  }

  @Override
  public boolean isFlexiblePortfolio() {
    inputLog.append("");
    outputLog.append("isFlexiblePortfolio ");
    return true;
  }

  @Override
  public boolean userExists(String userName) {
    inputLog.append(userName + " ");
    outputLog.append("userExists ");
    return true;
  }

  @Override
  public boolean stockExists(String symbol) {
    inputLog.append(symbol + " ");
    outputLog.append("stockExists ");
    return true;
  }

  @Override
  public boolean portfolioExists(String portfolioName) {
    inputLog.append(portfolioName + " ");
    outputLog.append("portfolioExists ");
    return true;
  }

  @Override
  public boolean portfolioExists() {
    inputLog.append("");
    outputLog.append("portfolioExists ");
    return true;
  }

  @Override
  public StringBuilder getListOfPortfolios() {
    inputLog.append("");
    outputLog.append("getListOfPortfolios ");
    return null;
  }


  @Override
  public void addStockToPortfolio(String symbol, int shares) {
    inputLog.append(symbol + " " + shares + " ");
    outputLog.append("addStockToPortfolio ");

  }

  @Override
  public void loadPortfolio(String portfolioName) {
    inputLog.append(portfolioName + " ");
    outputLog.append("loadPortfolio ");

  }

  @Override
  public void save() throws IOException {
    inputLog.append("");
    outputLog.append("save ");

  }

  @Override
  public boolean isValidDate(String date) {
    inputLog.append("");
    outputLog.append("isValidDate ");
    return true;
  }
}
