package models;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public interface Model {
  void createUser(String userName, float balance) throws IOException;

  void loadUser(String userName) throws IOException, ParseException;


  void createPortfolio(String portfolioName);

  float getTotalValue(String date);

  StringBuilder getComposition();

  void addStockToPortfolio( String symbol, int shares);

  void loadPortfolio(String portfolioName);

  void save() throws IOException;
}
