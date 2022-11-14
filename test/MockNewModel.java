import java.io.IOException;
import java.text.ParseException;

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
   * @param inputLog String Builder to test if the input send by the controller are correct
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
  public void createPortfolio(String portfolioName,boolean isFlexible) {
    inputLog.append(portfolioName + " ");
    outputLog.append("createPortfolio ");

  }

  @Override
  public void buyStock(String symbol, String date, float numberOfShares,float transactionCost) {

  }

  @Override
  public void sellStock(String symbol, String date, float numberOfShares,float transactionCost) {

  }

  @Override
  public StringBuilder getPlot(String startDate, String endDate) {
    return null;
  }

  @Override
  public float getTotalValue(String date) {
    inputLog.append(date + " ");
    outputLog.append("getTotalValue ");
    return 0;
  }

  @Override
  public StringBuilder getComposition(String date) {
    return null;
  }

  @Override
  public float getCostBasis(String date) {
    return 0;
  }

  @Override
  public boolean isFlexiblePortfolio() {
    return false;
  }

  @Override
  public StringBuilder getComposition() {
    inputLog.append("");
    outputLog.append("getComposition ");
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
}
