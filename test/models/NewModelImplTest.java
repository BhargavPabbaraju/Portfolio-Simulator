package models;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class NewModelImplTest extends TestCase {

  @Test
  public void testOne() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user1");
    m.createPortfolio("flex",true);
    m.buyStock("AAPL","2022-01-03",50);
    m.sellStock("AAPL","2022-01-10",40);
    m.sellStock("AAPL","2022-01-05",5);
    m.buyStock("AAPL","2021-12-28",10);
    m.buyStock("IBM","2022-01-03",50);
    m.sellStock("IBM","2022-01-10",40);
    m.sellStock("IBM","2022-01-05",5);
    m.buyStock("IBM","2021-03-11",10);
    System.out.println(m.getCostBasis("2022-02-11"));

  }

  @Test
  public void testGettotalValue() throws IOException, ParseException {
    NewModel m = new NewModelImpl();
    m.loadUser("user1");
    m.createPortfolio("flex",true);
    m.buyStock("AAPL","2022-01-03",50);
    m.sellStock("AAPL","2022-01-10",40);
    m.sellStock("AAPL","2022-01-05",5);
    m.buyStock("AAPL","2021-12-28",10);
    m.buyStock("IBM","2022-01-03",50);
    m.sellStock("IBM","2022-01-10",40);
    m.sellStock("IBM","2022-01-05",5);
    m.buyStock("IBM","2021-03-11",10);
    System.out.println(m.getTotalValue("2021-02-11"));

  }
}