package view;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import controller.Features;
import customDataType.PlotPair;

final class GetPlotScreen {
  private final Float maxValue;
  private final Float minValue;
  JPanel screen;
  JPanel window;

  JPanel plot;

  Features features;

  Graphics2D g;

  private int labelOffsetX = 80;
  private int labelOffsetY = 30;
  private int rows = 10;
  private int cols = 7;

  private int maximumPlots = 10;

  private PlotPair datesValues;

  private int minimumPlotY = 10;

  private int maximumPlotY = 10;
  private ArrayList<Float> points;

  private String title;


  public GetPlotScreen(Features features,String startDate,String endDate) {
    this.features = features;
    datesValues = features.getPlot(startDate,endDate,maximumPlots);
    setTitle(startDate,endDate);
    maxValue = Collections.max(datesValues.values);
    minValue = minimumValue(datesValues.values);
    rows = datesValues.size+1;
    screen = new JPanel(new BorderLayout());
    window = new JPanel(){
      @Override
      protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        drawLines(g,this);
      }
    };
    window.setLayout(new BorderLayout());
    JLabel titleLabel = new JLabel(title,JLabel.CENTER);
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    window.setBackground(Color.WHITE);
    screen.add(new JPanel(),BorderLayout.LINE_START);
    screen.add(new JPanel(),BorderLayout.LINE_END);
    screen.add(titleLabel,BorderLayout.PAGE_START);
    screen.add(new JPanel(),BorderLayout.PAGE_END);
    screen.add(window,BorderLayout.CENTER);

  }

  private String formatDate(String dateString){
    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
    return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
  }

  private void setTitle(String startDate, String endDate) {
    if(startDate.equals(endDate)){
      this.title = String.format("Performance of %s on %s",features.getActivePortfolio(),
              formatDate(startDate));
    }else{
      this.title = String.format("Performance of %s from %s to %s",features.getActivePortfolio(),
              formatDate(startDate),formatDate(endDate));
    }

  }

  private void drawLines(Graphics g, JPanel jPanel){
    cols = (jPanel.getHeight()-labelOffsetY) / 50;
    Graphics2D g2d = (Graphics2D) g.create();

    drawGrid(g2d,jPanel);

    drawLabels(g2d,jPanel);
  }

  private void drawDash(Graphics2D g2d,JPanel jPanel,int x1,int y1,boolean horizontal){
    int dashSize = 7;
    Color defaultColor = g2d.getColor();
    g2d.setColor(Color.BLACK);
    Stroke defaultStroke = g2d.getStroke();
    g2d.setStroke(new BasicStroke(2f));
    if(horizontal){
      g2d.drawLine(x1,y1,x1+dashSize,y1);
    }else{
      g2d.drawLine(x1,y1,x1,y1-dashSize);
    }
    g2d.setStroke(defaultStroke);
    g2d.setColor(defaultColor);
  }
  private void drawGrid(Graphics2D g2d, JPanel jPanel) {
    g2d.setColor(Color.lightGray);

    int initialX = labelOffsetX;
    int initialY = 0;
    int yOffset = (jPanel.getHeight()-labelOffsetY)/cols;
    int xOffset = (jPanel.getWidth()-labelOffsetX)/rows;
    for(int i=0;i<cols;i++){
      int x=initialX;
      int y=initialY+yOffset*i;
      g2d.drawLine(x,y,x+jPanel.getWidth(),y);


    }
    for(int i=0;i<rows;i++){
      int x=initialX+xOffset*i;
      int y=initialY;
      g2d.drawLine(x,y,x,jPanel.getHeight()-labelOffsetY);

    }
    g2d.setColor(Color.BLACK);
  }

  private void drawLabels(Graphics2D g2d, JPanel jPanel) {

    drawXLabels(g2d,jPanel);

    plotGraph(g2d,jPanel);

    drawYLabels(g2d,jPanel);









  }




  private void drawYLabels(Graphics2D g2d, JPanel jPanel) {




    g2d.drawLine(labelOffsetX,0,labelOffsetX,jPanel.getHeight()-labelOffsetY);



    int lastDashY = jPanel.getHeight() - labelOffsetY;
    float a = labelOffsetY*2;
    float b = lastDashY;
    FontMetrics fontMetrics = g2d.getFontMetrics();
    int yOffset = (jPanel.getHeight()-labelOffsetY)/cols;
    for(int i=cols-1;i>0;i--){
      int y=yOffset*i;
      float scaledValue;
      if(onlyOneValue()){
        scaledValue = scaledValue(yOffset*(cols-i+1),a,b,minValue/2,maxValue*2);
      }else{
       scaledValue = scaledValue(yOffset*(cols-i+1),a,b,minValue,maxValue);
      }

      String value = String.format("$%.2f",scaledValue);
      int x = labelOffsetX - fontMetrics.stringWidth(value) - 5;
      g2d.drawString(value,x,y+5);
      drawDash(g2d,jPanel,labelOffsetX,y,true);
    }

    String value="$0.00";
    int x = labelOffsetX - fontMetrics.stringWidth(value) - 5;
    g2d.drawString(value,x,lastDashY+5);
    drawDash(g2d,jPanel,labelOffsetX,lastDashY,true);









  }
  private boolean onlyOneValue(){
    return maxValue-minValue == 0f;
  }

  private void plotGraph(Graphics2D g2d, JPanel jPanel){

    Stroke defaultStroke = g2d.getStroke();



    int xOffset = (jPanel.getWidth()-labelOffsetX)/rows;
    points = new ArrayList<>();
    g2d.setStroke(new BasicStroke(2f));
    g2d.setColor(Color.BLUE);
    int i=0;
    float a = labelOffsetY*2;
    float b = jPanel.getHeight() - labelOffsetY;
    int previousX = 0;
    int previousY = 0;
    for(float value: datesValues.values){
      int x = xOffset*(i+1) + labelOffsetX;
      int pointY;
      float scaled = 0;


      if(value==0){
        scaled = labelOffsetY;
        pointY = jPanel.getHeight() - labelOffsetY;
      }else{
        if(onlyOneValue()){
          scaled = (b-a)/2;
        }else{
          scaled =scaledValue(value,minValue,maxValue,a,b);
        }

      }
      pointY = jPanel.getHeight() - (int)scaled;
      points.add(scaled);
      if(i>0){
        g2d.drawLine(previousX,previousY,x,pointY);
      }else{
        g2d.drawLine(x,pointY,x,pointY);
      }
      previousX = x;
      previousY = pointY;
      //System.out.println(value+" "+" "+x+" "+pointY);
      i++;
    }
    if(points.size()==1){
      g2d.setStroke(new BasicStroke(10f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
      g2d.drawLine(previousX,previousY,previousX,previousY);
    }
    g2d.setColor(Color.BLACK);
    g2d.setStroke(defaultStroke);


  }

  private float scaledValue(float x,float minValue , float maxValue,float a,float b){
    float scaledX = (b-a) * (x - minValue);
    scaledX = scaledX / (maxValue - minValue) + a;
    return scaledX;
  }




  private Float minimumValue(ArrayList<Float> values) {
    float min = Float.MAX_VALUE;
    for(Float value:values){
      if(value>0){
        min = Math.min(value,min);
      }
    }
    if(min==Float.MAX_VALUE){
      return 0f;
    }else{
      return min;
    }
  }

  private void drawXLabels(Graphics2D g2d, JPanel jPanel) {
    g2d.drawLine(labelOffsetX,jPanel.getHeight()-labelOffsetY,jPanel.getWidth(),
            jPanel.getHeight()-labelOffsetY);


    Stroke defaultStroke = g2d.getStroke();
    int xOffset = (jPanel.getWidth()-labelOffsetX)/rows;
    int i=1;
    int dashSize = 7;
    for(String date: datesValues.dates){
      int length = date.length();
      int x = xOffset*i + labelOffsetX;
      g2d.drawString( date,x-length*3,jPanel.getHeight()-labelOffsetY/2);
      drawDash(g2d,jPanel,x,jPanel.getHeight() - labelOffsetY,false);
      i++;
    }


  }


  JPanel getScreen(){
    return this.screen;
  }
}
