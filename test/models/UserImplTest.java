package models;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class UserImplTest {
  @Test
  public void testUserCreation() throws IOException, InterruptedException {
    User user1 = new UserImpl("user3");
    user1.createPortfolio("college");
    user1.addStockToPortfolio("AAPL",2);
    user1.addStockToPortfolio("AAPL",3);
    user1.addStockToPortfolio("AAPL",2);
    user1.addStockToPortfolio("AAPL",3);
    user1.addStockToPortfolio("MSFT",3);
    user1.addStockToPortfolio("MSFT",2);
    user1.addStockToPortfolio("MSFT",3);
    user1.addStockToPortfolio("MSFT",3);
    System.out.println(user1.getTotalValue("2022-10-17"));
    System.out.println(user1.toString());
    ApiCallImpl api = new ApiCallImpl();

  }

  @Test
  public void remove() throws IOException {
    ApiCallImpl.removeMetaDataInFile("IBM");
  }

  @Test
  public void testLoadData() throws IOException, ParseException {
    //UserImpl user1 =  new UserImpl("goog");
    User user = Loader.loadFile("goog");

    user.createPortfolio("other2");
    user.addStockToPortfolio("IBM",10);
    System.out.println(user);
    user.save();

  }


}