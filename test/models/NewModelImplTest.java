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
    m.buyStock("AAPL","2022-01-01",50);
    m.sellStock("AAPL","2022-01-10",40);
    m.sellStock("AAPL","2022-01-05",5);
    m.buyStock("AAPL","2021-12-28",10);
    System.out.println(m.getComposition("2022-11-01"));

  }
}