package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.HashMap;

import controller.Features;
import controller.ValidationResult;
import controller.Validator;


public class NewViewImpl extends JFrame implements NewView{

  private JPanel footerPanel;
  private JPanel menuPanel;
  private JPanel centerPanel;

  private JButton saveButton;
  private JButton exitButton;




  private JLabel displayMessage;

  private HashMap<ScreenNames,JPanel> screens;
  private ScreenNames currentScreen;


  private Validator validator;
  private Features features;
  private HashMap<String,ValidationResult> validationResults;
  private HashMap<String,JButton> buttons;


  @Override
  public void setValidator(Validator validator){
    this.validator = validator;
  }

  @Override
  public void setFeatures(Features features){
    this.features = features;
  }

  private boolean isCenterScreen(){
    switch(currentScreen){
      case MAIN_SCREEN:
      case INITIAL_SCREEN:
      case CREATE_USER_SCREEN:
      case LOAD_USER_SCREEN:
        return false;
      default:
        return  true;
    }
  }

  @Override
  public void showMainMenu() {
    JPanel screen = new JPanel();

    screen.setLayout(new BorderLayout());
    centerPanel = new JPanel();
    initializeDisplayMessage(centerPanel);



    initializeMenu();

    screen.add(menuPanel,BorderLayout.LINE_START);
    screen.add(centerPanel,BorderLayout.CENTER);
    screen.add(footerPanel,BorderLayout.PAGE_END);
    setMinimumSize(new Dimension(900,500));
    screen.setPreferredSize(new Dimension(900,500));

    setScreen(ScreenNames.MAIN_SCREEN,screen);
  }

  @Override
  public void showMessage(String message,boolean error) {
    displayMessage.setText(message);
    Color color = error ? Color.RED : Color.BLACK;
    displayMessage.setForeground(color);
  }

  @Override
  public void showInitialMenu() {
    JPanel screen = new JPanel();
    screen.setLayout(new GridLayout(0,1));

    setButtons(
            new String[] {
                    "Create user",
                    "Load user",
                    "Exit"
            },
            screen
    );
    buttons.get("Create user").addActionListener(e->new CreateUserScreen());
    buttons.get("Load user").addActionListener(e->new LoadUserScreen());
    initializeDisplayMessage(screen);
    setScreen(ScreenNames.INITIAL_SCREEN,screen);
  }


  public NewViewImpl(){
    super();
    setTitle("Portfolio Application");
    setSize(300, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    screens = new HashMap<>();

    showInitialMenu();





  }




  private ValidationResult validate(String content, String type, JLabel errorLabel){
    ValidationResult validationResult;
    switch(type){

      case "float":
        validationResult = validator.validateFloat(content);
        break;

      case "newUserName":
        validationResult = validator.validateUserName(content,false);
        break;

      case "oldUserName":
        validationResult = validator.validateUserName(content,true);
        break;

      case "newPortfolioName":
        validationResult = validator.validatePortfolioName(content,false);
        break;

      default:
        validationResult = validator.validateName(content);
        break;
    }

    errorLabel.setText(validationResult.errorMessage);
    return validationResult;
  }






  private void setScreen(ScreenNames screenName,JPanel screen){
    screens.put(screenName,screen);
    currentScreen = screenName;
    if(isCenterScreen()){
      centerPanel.removeAll();
      centerPanel.add(screen);
      centerPanel.revalidate();

    }else{
      this.add(screen);
    }

    setScreenVisibilities(screenName);
  }

  private void setScreenVisibilities(ScreenNames screenName){
    this.setVisible(false);
    for(ScreenNames screen:screens.keySet()){
      screens.get(screen).setVisible(false);
    }
    if(isCenterScreen()){
      screens.get(ScreenNames.MAIN_SCREEN).setVisible(true);
    }
    screens.get(screenName).setVisible(true);
    this.setVisible(true);
  }

  private void initializeMenu(){
    menuPanel = new JPanel();
    menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.PAGE_AXIS));

    JPanel panel = new JPanel(new GridLayout(0,2));
    panel.setPreferredSize(new Dimension(320, 300));
    panel.setBackground(Color.LIGHT_GRAY);
    menuPanel.setBackground(Color.LIGHT_GRAY);

    setButtons(
            new String[] {
                    "Create portfolio",
                    "Load portfolio",
                    "Buy stocks",
                    "Sell stocks",
                    "Get Cost basis",
                    "Get Value",
                    "Invest on a date",
                    "Dollar Cost-Averaging",
                    "View Performance Plot",
                    "View List of portfolios"


            },
            panel
    );


    buttons.get("Create portfolio").addActionListener(e->new CreatePortfolioScreen());


    menuPanel.add(panel);



    footerPanel = new JPanel(new GridLayout());
    footerPanel.setBackground(Color.LIGHT_GRAY);

    saveButton = new JButton("Save");
    footerPanel.add(saveButton);
    exitButton = new JButton("Exit");
    footerPanel.add(exitButton);


  }


  private  boolean checkErrors(String[] fields,
                                 HashMap<String, ValidationResult> validationResults){
    for(String field:fields){
      ValidationResult result = validationResults.get(field);
      if(!result.result){
        showMessage("Please fix all errors",true);
        return true;
      }
    }
    return false;
  }

  private void initialScreensLoader(String[] fields,String[] types,JPanel screen){
    for(int i=0;i<fields.length;i++){
      validationResults.put(fields[i],validator.validateName(""));
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.add(new JLabel("Enter "+fields[i]));
      JLabel errorLabel = new JLabel("");
      errorLabel.setForeground(Color.RED);
      JTextField textField = new JTextField(15);
      int finalI = i;
      textField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
          //do nothing;
        }

        @Override
        public void focusLost(FocusEvent e) {
          if(!e.isTemporary() && textField.isEnabled() ){
            String content = textField.getText();
            ValidationResult result = validate(content,types[finalI],errorLabel);
            validationResults.put(fields[finalI],result);
            showMessage("",false);

          }
        }
      });
      panel.add(textField);
      panel.add(errorLabel);
      screen.add(panel);
      initializeDisplayMessage(screen);

    }
  }

  private void initializeDisplayMessage(JPanel screen) {
    displayMessage = new JLabel("",JLabel.CENTER);
    displayMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    screen.add(displayMessage);
  }

  private void setButtons(String[] buttonNames,JPanel panel){
    buttons = new HashMap<>();
    for(String buttonName:buttonNames ){
      JButton button = new JButton(buttonName);
      buttons.put(buttonName,button);
      panel.add(button);
    }
  }



  private final class CreateUserScreen{
    CreateUserScreen(){
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen,BoxLayout.PAGE_AXIS));


      String [] fields = new String[] {"username","balance"};
      String [] types = new String[] {"newUserName","float"};
      validationResults = new HashMap<>();

      initialScreensLoader(fields,types,screen);



      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[] {
                      "Create user",
                      "Go back"
              },
              panel
      );
      buttons.get("Go back").addActionListener(e->showInitialMenu());

      screen.add(panel);

      buttons.get("Create user").addActionListener(e->buttonHandler(fields,validationResults));


      setScreen(ScreenNames.CREATE_USER_SCREEN,screen);
    }

    private void buttonHandler(String[] fields,
                                   HashMap<String, ValidationResult> validationResults) {

      if(checkErrors(fields,validationResults)){
        return;
      }
      String userName = validationResults.get("username").data.toString();
      float balance = (float) validationResults.get("balance").data;
      features.createUser(userName,balance);
    }
  }

  private final class LoadUserScreen{
    LoadUserScreen(){
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen,BoxLayout.PAGE_AXIS));


      String [] fields = new String[] {"username"};
      String [] types = new String[] {"oldUserName"};
      validationResults = new HashMap<>();

      initialScreensLoader(fields,types,screen);



      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[] {
                      "Load user",
                      "Go back"
              },
              panel
      );
      buttons.get("Go back").addActionListener(e->showInitialMenu());

      screen.add(panel);

      buttons.get("Load user").addActionListener(e->buttonHandler(fields,validationResults));


      setScreen(ScreenNames.LOAD_USER_SCREEN,screen);
    }

    private void buttonHandler(String[] fields,
                                   HashMap<String, ValidationResult> validationResults) {

      if(checkErrors(fields,validationResults)){
        return;
      }
      String userName = validationResults.get("username").data.toString();
     features.loadUser(userName);
    }
  }

  private final class CreatePortfolioScreen{
    CreatePortfolioScreen(){
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen,BoxLayout.PAGE_AXIS));


      String [] fields = new String[] {"portfolio name"};
      String [] types = new String[] {"newPortfolioName"};
      validationResults = new HashMap<>();

      initialScreensLoader(fields,types,screen);



      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[] {
                      "Create Portfolio",
              },
              panel
      );


      screen.add(panel);

      buttons.get("Create Portfolio").addActionListener(e->buttonHandler(fields,validationResults));


      setScreen(ScreenNames.CREATE_PORTFOLIO_SCREEN,screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if(checkErrors(fields,validationResults)){
        return;
      }
      String portfolioName = validationResults.get("portfolio name").data.toString();
      features.createPortfolio(portfolioName);
    }
  }


}


