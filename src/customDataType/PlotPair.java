package customDataType;

import java.util.ArrayList;

public final class PlotPair {
  public final ArrayList<String> dates;
  public final ArrayList<Float> values;
  public final int size;

  public PlotPair(ArrayList<String> dates, ArrayList<Float> values) {
    size = dates.size();
    if (size != values.size()) {
      throw new IllegalArgumentException("Both lists must be of same size");
    }
    this.dates = dates;
    this.values = values;
  }


}
