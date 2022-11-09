package models;

import java.time.LocalDate;

public interface FlexibleStock {
  StringBuilder getPlot(LocalDate startDate, LocalDate endDate);
  float getValue(LocalDate date);

  float getCostBasis(LocalDate date);

  float getShares();
}
