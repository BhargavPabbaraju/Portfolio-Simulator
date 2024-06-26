package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

/**
 * This class represents a Json loader that loads a well formatted json file creates a user from
 * that file and loads all the portfolios present in that file.
 */
class Loader {
  /**
   * This method creates a user from a well formatted json file with the same name as the username
   * provided and loads all the portfolios present in it.
   *
   * @param userName String which is unique for a user.
   * @return a user object which consists of all the data loaded from the file.
   * @throws IOException    if a file with this username doesn't exist.
   * @throws ParseException if the file is of invalid format.
   */
  public static User loadFile(String userName) throws IOException, ParseException {
    User user = new UserImpl(userName);
    JsonObject json = JsonParser.parse("data" + File.separator + userName + ".json");
    float balance = Float.parseFloat(json.get("balance").toString());
    user.addToBalance(balance);
    loadPortfolioList(json, user);
    return user;


  }

  private static void loadPortfolioList(JsonObject json, User user) throws ParseException {
    JsonObject portfolios = json.get("portfolios");
    JsonObject portfolio;
    int length = portfolios.length();

    for (int i = length - 1; i >= 0; i--) {
      portfolio = portfolios.get(i + "");
      user.createPortfolio(portfolio.get("name").toString());
      loadStocksList(portfolio.get("stocks"), user);

    }

  }

  private static void loadStocksList(JsonObject json, User user) {
    int length = json.length();
    JsonObject stock;

    for (int i = length - 1; i >= 0; i--) {
      stock = json.get(i + "");
      LocalDate date;
      try {
        date = LocalDate.parse(stock.get("date").toString(),
                DateTimeFormatter.ISO_LOCAL_DATE);
      } catch (Exception e) {
        date = LocalDate.now();
      }


      user.addStockToPortfolio(stock.get("symbol").toString(),
              Float.parseFloat(stock.get("shares").toString()), date);

    }
  }

  private static boolean dontPutQuotes(String key, String value) {
    return key.equals("shares") || key.equals("balance") || value.equals("[");
  }

  private static int writeKeyValue(String key, String value, int tabs, BufferedWriter writer,
                                   boolean next, int increment) throws IOException {
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < tabs; i++) {
      line.append(" ");
    }
    if (dontPutQuotes(key, value)) {
      line.append(String.format("\"%s\": %s", key, value));
    } else if (!value.equals("")) {
      line.append(String.format("\"%s\": \"%s\"", key, value));
    } else {
      line.append(key);
    }

    if (next) {
      line.append(",");
    }
    writer.write(line.toString());
    writer.newLine();
    return tabs + increment;
  }

  private static int writeUser(User user, BufferedWriter writer, int tabs) throws IOException {

    tabs = writeKeyValue("{", "", tabs, writer, false, 2);
    tabs = writeKeyValue("name", user.getName(), tabs, writer, true, 0);
    tabs = writeKeyValue("balance", String.valueOf(user.getBalance()), tabs, writer,
            true, 0);
    tabs = writeKeyValue("portfolios", "[", tabs, writer, false, 2);


    return tabs;
  }

  private static int writeStock(Stock stock, BufferedWriter writer, int tabs) throws IOException {
    tabs = writeKeyValue("shares", String.valueOf(stock.getShares()), tabs, writer,
            true, 0);
    tabs = writeKeyValue("date", stock.getDate().toString(), tabs, writer,
            false, -2);
    return tabs;
  }

  private static int writeStocks(Portfolio portfolio, BufferedWriter writer, int tabs)
          throws IOException {
    HashMap<String, Stock> stocksList = portfolio.getStocks();
    tabs = writeKeyValue("stocks", "[", tabs, writer, false, 2);

    int size = stocksList.size();
    int i = 0;
    for (String key : stocksList.keySet()) {
      tabs = writeKeyValue("{", "", tabs, writer, false, 2);
      tabs = writeKeyValue("symbol", key, tabs, writer, true, 0);
      tabs = writeStock(stocksList.get(key), writer, tabs);
      i++;
      if (i == size) {
        tabs = writeKeyValue("}", "", tabs, writer, false, -2);
      } else {
        tabs = writeKeyValue("}", "", tabs, writer, true, 0);
      }


    }
    tabs = writeKeyValue("]", "", tabs, writer, false, -2);
    return tabs;
  }

  private static int writePortfolios(User user, BufferedWriter writer, int tabs)
          throws IOException {
    HashMap<String, Portfolio> portfolioList = user.getPortfolios();
    int size = portfolioList.size();
    int i = 0;
    for (String key : portfolioList.keySet()) {
      tabs = writeKeyValue("{", "", tabs, writer, false, 2);
      tabs = writeKeyValue("name", key, tabs, writer, true, 0);
      tabs = writeStocks(portfolioList.get(key), writer, tabs);
      i++;
      if (i == size) {
        tabs = writeKeyValue("}", "", tabs, writer, false, -2);
      } else {
        tabs = writeKeyValue("}", "", tabs, writer, true, 0);
      }

    }

    return tabs;
  }

  public static void save(User user) throws IOException {
    int tabs = 0;
    String userName = user.getName();
    BufferedWriter writer = new BufferedWriter(new FileWriter("data//" + userName
            + ".json"));
    tabs = writeUser(user, writer, tabs);
    tabs = writePortfolios(user, writer, tabs);
    tabs = writeKeyValue("]", "", tabs, writer, false, -2);
    tabs = writeKeyValue("}", "", tabs, writer, false, -2);

    writer.close();

  }

  public static boolean isInvalidName(String name) {
    String[] invalidCharacters = {"[", "]", "{", "}", ":", ","};
    for (String s : invalidCharacters) {
      if (name.contains(s)) {
        return true;
      }
    }
    return false;
  }
}
