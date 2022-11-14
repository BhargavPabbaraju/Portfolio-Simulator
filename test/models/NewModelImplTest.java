package models;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import models.jsonparser.JsonObject;
import models.jsonparser.JsonParser;

public class NewModelImplTest extends TestCase {

  @Test
  public void testOne() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user1");
    m.createPortfolio("flex",true);
    m.buyStock("AAPL","2022-01-03",50,10);
    m.sellStock("AAPL","2022-01-10",40,10);
    m.sellStock("AAPL","2022-01-05",5,10);
    m.buyStock("AAPL","2021-12-28",10,10);
    m.buyStock("IBM","2022-01-03",50,10);
    m.sellStock("IBM","2022-01-10",40,10);
    m.sellStock("IBM","2022-01-05",5,10);
    m.buyStock("IBM","2021-03-11",10,10);
    System.out.println(m.getCostBasis("2022-02-11"));

  }

  @Test
  public void testGettotalValue() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user1");
    m.createPortfolio("flex",true);
    m.buyStock("AAPL","2022-01-03",50,10);
    m.sellStock("AAPL","2022-01-10",40,10);
    m.sellStock("AAPL","2022-01-05",5,10);
    m.buyStock("AAPL","2021-12-28",10,10);
    m.buyStock("IBM","2022-01-03",50,10);
    m.sellStock("IBM","2022-01-10",40,10);
    m.sellStock("IBM","2022-01-05",5,10);
    m.buyStock("IBM","2021-03-11",10,10);
    System.out.println(m.getTotalValue("2021-02-11"));

  }

  @Test
  public void testGetPlot() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user1");
    m.createPortfolio("flex",true);
    m.buyStock("AAPL","2022-01-03",50,10);
    m.sellStock("AAPL","2022-01-10",40,10);
    m.sellStock("AAPL","2022-01-05",5,10);
    m.buyStock("AAPL","2021-12-28",10,10);
    m.buyStock("IBM","2022-01-03",50,10);
    m.sellStock("IBM","2022-01-04",20,10);
    m.buyStock("IBM","2022-04-01",60,10);
    m.sellStock("IBM","2022-05-02",60,10);
    System.out.println(m.getPlot("2010-11-01","2022-05-20"));



  }

  @Test
  public void testSave() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.createUser("user5",2000);
    m.createPortfolio("retir",true);
    m.buyStock("GOOG","2022-08-01",20,10);
    m.sellStock("GOOG","2022-11-01",20,10);
    m.buyStock("ORCL","2022-08-01",10,10);
    m.createPortfolio("college",false);
    m.addStockToPortfolio("MSFT",30);
    m.save();



  }

  @Test
  public void testLoad() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user5");
    m.loadPortfolio("retir");
    System.out.println(m.getComposition("2022-11-02"));
    m.loadPortfolio("college");
    System.out.println(m.getComposition());
  }


}