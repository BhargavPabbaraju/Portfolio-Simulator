package models;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.*;
public class ProgressBarExample extends JFrame{
  JProgressBar jb;
  JLabel label;
  int i=0,num=0;
  ProgressBarExample(){
    jb=new JProgressBar(0,2000);
    jb.setBounds(40,40,160,30);
    jb.setValue(0);
    jb.setStringPainted(true);
    add(jb);
    setSize(250,150);
    setLayout(new GridLayout(0,1));
    this.label = new JLabel("");
    add(label);


  }


  float getValue() throws IOException, ParseException {
    NewerModel m = new NewerModelImpl();
    m.loadUser("user9");
    return m.getTotalValue("2022-11-21", ApiType.ALPHA_VANTAGE);

  }
//  public float iterate() throws IOException, ParseException {
//    label.setText("Loading");
//    return getValue();
//  }

  public void iterate() throws IOException, ParseException {
    label.setText("Loading");

  }

  public static void main(String[] args) throws IOException, ParseException {


    ProgressBarExample m=new ProgressBarExample();
    m.setVisible(true);
    m.iterate();

  }
}