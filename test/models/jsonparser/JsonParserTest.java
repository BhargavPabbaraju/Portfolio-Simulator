package models.jsonparser;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class JsonParserTest {

  @Test
  public void testOne() throws FileNotFoundException {
    JsonObject result = JsonParser.parse("data/goog.json");
    assertEquals("goog",result.get("name").toString());
    JsonObject portfolio2 = result.get("portfolios").get("1");
    assertEquals("college",portfolio2.get("name").toString());
    JsonObject stock = portfolio2.get("stocks").get("0");
    assertEquals("BSE",stock.get("symbol").toString());
  }
}