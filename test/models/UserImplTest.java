package models;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class UserImplTest {
  @Test
  public void testUserCreation() throws IOException {
    User user1 = new UserImpl("user3");
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