package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiCallImpl implements ApiCall {
  private static String apiKey;

  /**
   * It Constructs the apikey which is used to make api calls with alpha vantage API.
   */
  public ApiCallImpl() {
    apiKey = "3YCR9C5VHZEI9WFZ";
  }

  @Override
  public float getData(String stockSymbol, String date) throws IllegalArgumentException {
    String output = this.getCsvFormat(stockSymbol);
    return parseCSV(output, date);

  }

  private String getCsvFormat(String stockSymbol) {
    URL url = getURL(stockSymbol);
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return output.toString();
  }

  private float parseCSV(String output, String date) {
    String line = "";
    try {
      BufferedReader br = new BufferedReader(new StringReader(output));
      while ((line = br.readLine()) != null) {
        String[] employee = line.split(",");
        if (employee[0].equals(date)) {
          float sum = Float.parseFloat(employee[1]) + Float.parseFloat(employee[2]) + Float.parseFloat(employee[3]) + Float.parseFloat(employee[4]);
          float average = sum / 4;
          System.out.println(average);
          return average;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    throw new IllegalArgumentException("Date Not found");
  }

  private URL getURL(String stockSymbol) {
    URL url = null;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=compact"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alpha vantage API has either changed or "
              + "no longer works");
    }
    return url;

  }
}
