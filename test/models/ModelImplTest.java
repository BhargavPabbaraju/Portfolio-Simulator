package models;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is used to test the model in isolation.
 */
public class ModelImplTest {
  Model model;

  @Before
  public void setUp() {
    model = new ModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void createUserInvalidName() throws IOException {
    model.createUser("[]{}/adeifnd;,:", 1900);
    fail("Exception is expected for invalid characters in username");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createUserNegativeBalance() throws IOException {
    model.createUser("user8", -1900);
    fail("Exception is expected for negative balance");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createExistingUser() throws IOException {
    model.createUser("user1", 1900);
    fail("Exception is expected for existing user");
  }

  @Test
  public void createUser() throws IOException {
    model.createUser("user4", 1900);
    assertEquals("{}", model.toString());
  }

  @Test
  public void loadUser() throws IOException, ParseException {
    model.loadUser("user1");
    assertEquals("{college={AAPL=AAPL 10.0 2022-10-28, TSLA=TSLA 20.0 2022-10-28}, " +
            "retirement={MSFT=MSFT 30.0 2022-10-28, META=META 30.0 2022-10-28, GOOGL=GOOGL 30.0" +
            " 2022-10-28, IBM=IBM 20.0 2022-10-28, ORCL=ORCL 40.0 2022-10-28, MRNA=MRNA 30.0 " +
            "2022-10-28, AMZN=AMZN 30.0 2022-10-28}}", model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void loadNonExistingUser() throws IOException, ParseException {
    model.loadUser("user1020");
    fail("Exception is expected for non existing user");
  }

  @Test
  public void createPortfolio() throws IOException {
    model.createUser("user101", 1900);
    model.createPortfolio("personal");
    assertEquals("{personal={}}", model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createExistingPortfolio() throws IOException, ParseException {
    model.loadUser("user1");
    model.createPortfolio("retirement");
    fail("Exception is expected for existing portfolio.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createPortfolioInvalidCharacters() throws IOException, ParseException {
    model.loadUser("user1");
    model.createPortfolio("[]{},;wjwihfwnf");
    fail("Exception is expected for invalid portfolio name.");
  }


  @Test
  public void getTotalValue() throws IOException, ParseException {
    model.loadUser("user1");
    assertEquals(6063.400390625, model.getTotalValue("2022-10-18"), 0.001);
  }

  @Test
  public void getComposition() throws IOException, ParseException {
    model.loadUser("user1");
    model.loadPortfolio("retirement");
    String expectedResult = "                    retirement\n" +
            "MSFT                                    30.00\n" +
            "META                                    30.00\n" +
            "GOOGL                                   30.00\n" +
            "IBM                                     20.00\n" +
            "ORCL                                    40.00\n" +
            "MRNA                                    30.00\n" +
            "AMZN                                    30.00\n";
    assertEquals(expectedResult, model.getComposition().toString());
  }

  @Test
  public void addStockToPortfolio() throws IOException {
    model.createUser("user101", 1900);
    model.createPortfolio("personal");
    model.addStockToPortfolio("ORCL", 10);
    assertEquals("{personal={ORCL=ORCL 10.0 2022-10-31}}", model.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void addStockToPortfolioInvalidSymbol() throws IOException {
    model.createUser("user101", 1900);
    model.createPortfolio("personal");
    model.addStockToPortfolio("AAPL", 10);
    fail("Exception is expected for invalid stock symbol.");

  }

  @Test(expected = IllegalArgumentException.class)
  public void loadNonExistingPortfolio() throws IOException {
    model.createUser("user101", 1900);
    model.loadPortfolio("retirement");
    fail("Exception is expected for loading non existing portfolio.");

  }

  @Test
  public void loadPortfolio() throws IOException, ParseException {
    model.loadUser("user1");
    model.loadPortfolio("college");
    String expected =
            "                    college\n" +
                    "AAPL                                    10.00\n" +
                    "TSLA                                    20.00\n";

    assertEquals(expected, model.getComposition().toString());
  }

  @Test
  public void save() throws IOException, ParseException {
    model.createUser("user4", 1900);
    model.createPortfolio("college");
    model.addStockToPortfolio("IBM", 10);
    model.save();
    model.loadUser("user4");
    assertEquals("{college={IBM=IBM 10.0 2022-10-31}}", model.toString());

  }
}