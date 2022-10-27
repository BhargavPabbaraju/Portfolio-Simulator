package models;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

class Loader {
  public static User loadFile(String userName) throws IOException, ParseException {
    JsonObject json = JsonParser.parse("data//" + userName + ".json");
    System.out.println(json);
    float balance = Float.parseFloat(json.get("balance").toString());
    User user = new UserImpl(userName);
    user.addToBalance(balance);
    loadPortfolioList(json,user);
    return user;

  }
  private static void loadPortfolioList(JsonObject json,User user) throws ParseException {
    JsonObject portfolios = json.get("portfolios");
    JsonObject portfolio;
    int length = portfolios.length();

    for(int i=length-1;i>=0;i--){
      portfolio = portfolios.get(i+"");
      user.createPortfolio(portfolio.get("name").toString());
      loadStocksList(portfolio.get("stocks"),user);

    }

  }
  private static void loadStocksList(JsonObject json,User user) throws ParseException {
    int length = json.length();
    JsonObject stock;
    for(int i=length-1;i>=0;i--) {
      stock = json.get(i+"");
      LocalDate date = LocalDate.parse("2022-12-01",
              DateTimeFormatter.ISO_LOCAL_DATE);
      
      user.addStockToPortfolio(stock.get("symbol").toString(),
              Float.parseFloat(stock.get("shares").toString()),date);




    }


  }




}
