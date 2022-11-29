package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
      boolean flexible = portfolio.get("flexible").toString().equals("true");
      user.createPortfolio(portfolio.get("name").toString(), flexible);

      if (flexible) {
        loadFlexibleStocksList(portfolio.get("stocks"), user);
        loadDollarCost(portfolio.get("dollarCost"),user);
      } else {
        loadStocksList(portfolio.get("stocks"), user);
      }


    }


  }

  private static void loadDollarCost(JsonObject json,User user){
    for(String key:json.getKeys()){
      JsonObject dollarCost = json.get(key);
      String startDate = dollarCost.get("start").toString();
      String  endDate = dollarCost.get("end").toString();

      if(endDate == "null"){
        endDate = "";
      }
      int interval = Integer.parseInt(dollarCost.get("interval").toString());
      float transactionCost = Float.parseFloat(dollarCost.get("transactionCost").toString());
      float amount = Float.parseFloat(dollarCost.get("amount").toString());

      HashMap<String,Float> stocksList = new HashMap<>();
      JsonObject stocks = dollarCost.get("stocks");

      for(String index:stocks.getKeys()){
        JsonObject stock = stocks.get(index);
        for(String symbol:stock.getKeys()){

          stocksList.put(symbol, Float.valueOf(stock.get(symbol).toString()));
        }
      }
      user.createDollarCostStrategyPortfolio(startDate,endDate,interval,amount,transactionCost,
              stocksList);
    }

  }

  private static void loadFlexibleStocksList(JsonObject json, User user) {
    int length = json.length();
    JsonObject stockList;
    JsonObject stock;
    for (String key : json.getKeys()) {
      stockList = json.get(key);
      for (String date : stockList.getKeys()) {
        stock = stockList.get(date);
        float shares = Float.parseFloat(stock.get("shares").toString());
        float transactionCost = Float.parseFloat(stock.get("transactionCost").toString());
        if (shares < 0) {
          user.sellStock(key, date, shares, transactionCost);
        } else {
          user.buyStock(key, date, shares, transactionCost);
        }
      }
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
    ArrayList<String> quoted = new ArrayList<>(Arrays.asList("start","date","name","end"));
    String escapeChars = "{}[]";
    if(escapeChars.contains(key)){
      return false;
    }
    return !quoted.contains(key);



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

  private static int writeStocks(AbstractPortfolio portfolio, BufferedWriter writer, int tabs)
          throws IOException {

    tabs = writeKeyValue("flexible", String.valueOf(portfolio.flexible), tabs, writer,
            true, 0);


    if (portfolio.flexible) {
      return writeFlexibleStocksList(portfolio, writer, tabs);
    } else {
      return writeInFlexibleStocksList(portfolio, writer, tabs);
    }

  }

  private static int writeFlexibleStocksList(AbstractPortfolio portfolio, BufferedWriter writer,
                                             int tabs) throws IOException {

    HashMap<String, FlexibleStocksList> stocksList = portfolio.getStocksList();
    tabs = writeKeyValue("stocks", "{", tabs, writer, false, 2);
    int i = 0;
    int size = stocksList.size();
    for (String symbol : stocksList.keySet()) {
      tabs = writeKeyValue(symbol, "{", tabs, writer, false, 2);
      tabs = writeFlexibleStocks(stocksList.get(symbol), writer, tabs);
      i++;
      if (i == size) {
        tabs = writeKeyValue("}", "", tabs, writer, false, -2);
      } else {
        tabs = writeKeyValue("}", "", tabs, writer, true, 0);
      }
    }


    ArrayList<DollarCostStock> dollarCostList = portfolio.getDollarCostStocks();
    if(!dollarCostList.isEmpty()){
      tabs = writeKeyValue("}", "", tabs, writer, true, 0);
      tabs = writeDollarCost(dollarCostList,writer,tabs);
    }else{
      tabs = writeKeyValue("}", "", tabs, writer, false, -2);
    }

    return tabs;
  }

  private static int writeDollarCost(ArrayList<DollarCostStock> stocksList, BufferedWriter writer,
                                     int tabs)
          throws IOException {
    int i=1;
    int size = stocksList.size();
    if(!stocksList.isEmpty()){
      tabs=writeKeyValue("dollarCost","[",tabs,writer,false,2);
      for(DollarCostStock stock:stocksList){
        tabs=writeKeyValue("{","",tabs,writer,false,2);
        tabs = writeDollarCostStock(stock,writer,tabs);
        if(i==size){
          tabs=writeKeyValue("}","",tabs,writer,false,-2);
        }else{
          tabs=writeKeyValue("}","",tabs,writer,true,0);
        }
        i++;
      }
      tabs=writeKeyValue("]","",tabs,writer,false,-2);
    }
    return tabs;
  }

  private static int writeDollarCostStock(DollarCostStock stock, BufferedWriter writer, int tabs)
          throws IOException {
    tabs=writeKeyValue("start",stock.getStartDate().toString(),tabs,writer,true,
            0);

    String endDate;
    if( stock.getEndDate()==null){
      endDate="null";
    }else{
      endDate = stock.getEndDate().toString();
    }


    tabs=writeKeyValue("end",endDate,tabs,writer,true,
            0);


    tabs=writeKeyValue("transactionCost", String.valueOf(stock.getTransactionCost()),tabs,
            writer,true, 0);

    tabs=writeKeyValue("amount", String.valueOf(stock.getAmount()),tabs,
            writer,true, 0);

    tabs=writeKeyValue("interval", String.valueOf(stock.getInterval()),tabs,
            writer,true, 0);

    tabs=writeKeyValue("stocks", "[",tabs,
            writer,false, 2);

    HashMap<String, Float> stocks = stock.getStocks();
    int size = stocks.size();
    int i=1;
    for(String symbol:stocks.keySet()){
      tabs=writeKeyValue("{","",tabs,writer,false,2);
      tabs=writeKeyValue(symbol, String.format("%.2f",stocks.get(symbol)),
              tabs,writer,false,-2);

      if(i==size){
        tabs=writeKeyValue("}","",tabs,writer,false,-2);
      }else{
        tabs=writeKeyValue("}","",tabs,writer,true,0);
      }

      i++;
    }
    tabs=writeKeyValue("]","",tabs,writer,false,-2);

    return tabs;

  }

  private static int writeFlexibleStocks(FlexibleStocksList flexibleStocksList,
                                         BufferedWriter writer, int tabs) throws IOException {

    HashMap<LocalDate, FlexibleStock> stocks = flexibleStocksList.getStocks();
    int i = 0;
    int size = stocks.size();
    for (LocalDate date : stocks.keySet()) {
      tabs = writeKeyValue(date.toString(), "{", tabs, writer, false, 2);
      tabs = writeFlexibleStock(stocks.get(date), writer, tabs);
      i++;
      if (i == size) {
        tabs = writeKeyValue("}", "", tabs, writer, false, -2);
      } else {
        tabs = writeKeyValue("}", "", tabs, writer, true, 0);
      }
    }


    return tabs;
  }

  private static int writeFlexibleStock(FlexibleStock flexibleStock, BufferedWriter writer,
                                        int tabs) throws IOException {

    String shares = String.valueOf(flexibleStock.getShares());
    String transactionCost = String.valueOf(flexibleStock.getTransactionCost());
    tabs = writeKeyValue("shares", shares, tabs, writer, true, 0);
    tabs = writeKeyValue("transactionCost", transactionCost, tabs,
            writer, false, -2);

    return tabs;
  }

  private static int writeInFlexibleStocksList(AbstractPortfolio portfolio, BufferedWriter writer,
                                               int tabs) throws IOException {
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
    HashMap<String, AbstractPortfolio> portfolioList = user.getPortfolios();
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

  /**
   * This method to save the user into File System.
   *
   * @param user user object.
   * @throws IOException if a save fails.
   */
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

  /**
   * This method checks if the name is in valid format.
   *
   * @param name String which is unique for a user.
   * @return a boolean result of checking if input is valid.
   */
  public static boolean isInvalidName(String name) {
    String[] invalidCharacters = {"[", "]", "{", "}", ":", ","};
    for (String s : invalidCharacters) {
      if (name.contains(s)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if the user already existed.
   *
   * @param userName String which is unique for a user.
   * @return a boolean result of checking if user already exists.
   */
  public static boolean userNameExits(String userName) {
    return Files.exists(Path.of("data" + File.separator + userName + ".json"));
  }
}
