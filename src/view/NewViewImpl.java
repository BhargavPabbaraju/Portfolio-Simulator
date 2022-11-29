package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.ArrayList;
import java.util.HashMap;

import controller.Features;
import controller.ValidationResult;
import controller.Validator;


public class NewViewImpl extends JFrame implements NewView {

  private JPanel footerPanel;
  private JPanel menuPanel;
  private JPanel centerPanel;

  private JPanel addStocksPanel;

  private JScrollPane scrollPane;

  private JButton saveButton;
  private JButton exitButton;


  private JLabel displayMessage;

  private HashMap<ScreenNames, JPanel> screens;
  private ScreenNames currentScreen;


  private Validator validator;
  private Features features;
  private HashMap<String, ValidationResult> validationResults;
  private HashMap<String, JButton> buttons;

  private String portfolioName;

  private ArrayList<JTextField> symbols;
  private ArrayList<JTextField> weights;

  private int scrollMaxValue;

  private final int WIDTH = 1000;
  private final int HEIGHT = 500;


  @Override
  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  @Override
  public void setFeatures(Features features) {
    this.features = features;
  }

  private boolean isCenterScreen() {
    switch (currentScreen) {
      case MAIN_SCREEN:
      case INITIAL_SCREEN:
      case CREATE_USER_SCREEN:
      case LOAD_USER_SCREEN:
      case NEW_USER_SCREEN:
        return false;
      default:
        return true;
    }
  }

  @Override
  public void showMainMenu() {
    if (!screens.containsKey(ScreenNames.MAIN_SCREEN)) {
      setScreen(ScreenNames.MAIN_SCREEN, mainMenuHelper(false));
    } else {
      mainMenuHelper(false);
    }

  }

  private JPanel mainMenuHelper(boolean creating) {
    if (screens.containsKey(ScreenNames.MAIN_SCREEN)) {
      setScreenVisibilities(ScreenNames.MAIN_SCREEN);
      initializeDisplayMessage(centerPanel);
      return null;
    } else if (creating && screens.containsKey(ScreenNames.NEW_USER_SCREEN)) {
      setScreenVisibilities(ScreenNames.NEW_USER_SCREEN);
      initializeDisplayMessage(centerPanel);
      return null;
    }
    JPanel screen = new JPanel();

    screen.setLayout(new BorderLayout());
    centerPanel = new JPanel();


    centerPanel.setSize(new Dimension(WIDTH - 320, HEIGHT));
    initializeDisplayMessage(centerPanel);


    initializeMenu(creating);

    screen.add(menuPanel, BorderLayout.LINE_START);
    screen.add(centerPanel, BorderLayout.CENTER);
    screen.add(footerPanel, BorderLayout.PAGE_END);
    setMinimumSize(new Dimension(WIDTH, HEIGHT));
    screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    return screen;

  }

  @Override
  public void showMessage(String message, boolean error) {

    displayMessage.setText(message);
    Color color = error ? Color.RED : Color.BLACK;
    displayMessage.setForeground(color);

  }

  @Override
  public void showInitialMenu() {
    JPanel screen = new JPanel();
    screen.setLayout(new GridLayout(0, 1));

    setButtons(
            new String[]{
                    "Create user",
                    "Load user",
                    "Exit"
            },
            screen
    );
    buttons.get("Create user").addActionListener(e -> new CreateUserScreen());
    buttons.get("Load user").addActionListener(e -> new LoadUserScreen());
    buttons.get("Exit").addActionListener(e -> System.exit(0));
    initializeDisplayMessage(screen);
    setScreen(ScreenNames.INITIAL_SCREEN, screen);
  }

  @Override
  public void showNewUserMenu() {
    setScreen(ScreenNames.NEW_USER_SCREEN, mainMenuHelper(true));
    showMessage("Successfully created user", false);
  }


  public NewViewImpl() {
    super();
    setTitle("Portfolio Application");
    setMinimumSize(new Dimension(HEIGHT - 100, HEIGHT - 100));
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    screens = new HashMap<>();

    showInitialMenu();


  }


  private ValidationResult validate(String content, String type, JLabel errorLabel, boolean required) {
    ValidationResult validationResult;
    switch (type) {

      case "float":
        validationResult = validator.validateFloat(content, required);
        break;

      case "newUserName":
        validationResult = validator.validateUserName(content, false);
        break;

      case "oldUserName":
        validationResult = validator.validateUserName(content, true);
        break;

      case "newPortfolioName":
        validationResult = validator.validatePortfolioName(content, false);
        break;

      case "oldPortfolioName":
        validationResult = validator.validatePortfolioName(content, true);
        break;

      case "date":
        validationResult = validator.validateDate(content);
        break;

      case "futureDate":
        validationResult = validator.validateFutureDate(content,required);
        break;

      case "symbol":
        validationResult = validator.validateSymbol(content);
        break;

      case "int":
        validationResult = validator.validateInt(content);
        break;

      default:
        validationResult = validator.validateName(content);
        break;
    }
    errorLabel.setText(validationResult.errorMessage);
    return validationResult;
  }


  private JLabel centerAlignLabel(String text, JPanel screen) {
    JLabel label = new JLabel(text, JLabel.CENTER);
    label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    screen.add(label);
    return label;
  }


  private void setScreen(ScreenNames screenName, JPanel screen) {
    screens.put(screenName, screen);
    setCurrentScene(screenName);
    if (isCenterScreen()) {
      centerPanel.removeAll();
      centerPanel.setLayout(new FlowLayout());
      if (screenName.equals(ScreenNames.GET_PLOT_SCREEN)) {
        centerPanel.setLayout(new BorderLayout());
      }
      centerPanel.add(screen);
      centerPanel.revalidate();

    } else {
      this.add(screen);
    }

    setScreenVisibilities(screenName);
  }

  private void setScreenVisibilities(ScreenNames screenName) {
    this.setVisible(false);
    for (ScreenNames screen : screens.keySet()) {
      screens.get(screen).setVisible(false);
    }
    if (isCenterScreen()) {
      if (screens.containsKey(ScreenNames.MAIN_SCREEN)) {
        screens.get(ScreenNames.MAIN_SCREEN).setVisible(true);
      } else if (screens.containsKey(ScreenNames.NEW_USER_SCREEN)) {
        screens.get(ScreenNames.NEW_USER_SCREEN).setVisible(true);
      }
    }
    screens.get(screenName).setVisible(true);
    this.setVisible(true);
  }

  private void initializeMenu(boolean creating) {
    menuPanel = new JPanel();
    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

    int cols = 2;
    if (creating) {
      cols = 1;
    }
    JPanel panel = new JPanel(new GridLayout(0, cols));
    panel.setPreferredSize(new Dimension(320, 300));
    panel.setBackground(Color.LIGHT_GRAY);
    menuPanel.setBackground(Color.LIGHT_GRAY);

    footerPanel = new JPanel(new GridLayout());
    footerPanel.setBackground(Color.LIGHT_GRAY);

    if (creating) {
      setButtons(
              new String[]{
                      "Create portfolio",
              },
              panel


      );

    } else {
      setButtons(
              new String[]{
                      "Create portfolio",
                      "Load portfolio",
                      "Buy stock",
                      "Sell stock",
                      "Get Cost basis",
                      "Get Value",
                      "Invest on a date",
                      "Dollar Cost-Averaging",
                      "View Performance Plot",
                      "View List of portfolios"


              },
              panel
      );
      buttons.get("Get Cost basis").addActionListener(e -> new GetCostBasisScreen());
      buttons.get("Buy stock").addActionListener(e -> new BuyStocksScreen(false));
      buttons.get("Invest on a date").addActionListener(e -> new InvestOnDateScreen(false));
      buttons.get("Sell stock").addActionListener(e -> new SellStocksScreen());
      buttons.get("Load portfolio").addActionListener(e -> new LoadPortfolioScreen());
      buttons.get("Get Value").addActionListener(e -> new GetTotalValue());
      buttons.get("View List of portfolios").addActionListener(e -> new ListOfPortfoliosScreen());
      buttons.get("View Performance Plot").addActionListener(e -> new GetPlotInputsScreen());
      buttons.get("Dollar Cost-Averaging").addActionListener(e -> new DollarCostAverage(false));
      saveButton = new JButton("Save");
      saveButton.addActionListener(e -> features.save());
      footerPanel.add(saveButton);
    }
    buttons.get("Create portfolio").addActionListener(e -> new CreatePortfolioScreen());

    menuPanel.add(panel);


    exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    footerPanel.add(exitButton);


  }


  private boolean checkErrors(String[] fields,
                              HashMap<String, ValidationResult> validationResults) {

    for (String field : fields) {
      ValidationResult result = validationResults.get(field);
      try {
        if (!result.result) {
          showMessage("Please fill all required fields and fix all errors", true);
          return true;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

    }
    return false;
  }

  private void validationHelper(JTextField textField, String field, String type,
                                JLabel errorLabel, boolean required) {
    String content = textField.getText();
    ValidationResult result = validate(content, type, errorLabel, required);
    validationResults.put(field, result);
  }

  private void initialScreensLoader(String[] fields, String[] types, boolean[] required,
                                    JPanel screen) {
    for (int i = 0; i < fields.length; i++) {
      JPanel fieldPanel = new JPanel();
      fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.setLayout(new FlowLayout());
      JLabel label = new JLabel("Enter " + fields[i]);

      if (types[i].contains("date") || types[i].contains("Date")) {
        label.setText(label.getText() + " (yyyy-mm-dd)");
      }
      panel.add(label);
      JLabel errorLabel = new JLabel("", JLabel.CENTER);
      errorLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      errorLabel.setForeground(Color.RED);
      JTextField textField = new JTextField(15);
      int finalI = i;
      validationResults.put(fields[i], validate("", types[i], new JLabel(), required[i]));
      textField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
          //do nothing;
        }

        @Override
        public void focusLost(FocusEvent e) {
          if (!e.isTemporary() && textField.isEnabled()) {
            validationHelper(textField, fields[finalI], types[finalI], errorLabel, required[finalI]);
            showMessage("", false);

          }
        }
      });
      panel.add(textField);
      if (required[i]) {
        JLabel star = new JLabel("*");
        star.setForeground(Color.RED);
        panel.add(star);
      }
      fieldPanel.add(panel);
      fieldPanel.add(errorLabel);
      screen.add(fieldPanel);

      if (fields[i].equals("symbol") && symbols != null) {
        symbols.add(textField);
      }
      if (fields[i].equals("weight %") && weights != null) {
        weights.add(textField);
      }

    }
    if (currentScreen != ScreenNames.INVEST_ON_DATE && currentScreen != ScreenNames.DOLLAR_COST_AVERAGE) {
      screen.add(Box.createVerticalStrut(100));
      initializeDisplayMessage(screen);
    }

  }

  private void initializeDisplayMessage(JPanel screen) {
    displayMessage = new JLabel("", JLabel.CENTER);
    displayMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    screen.add(displayMessage);
  }

  private void setButtons(String[] buttonNames, JPanel panel) {
    buttons = new HashMap<>();
    for (String buttonName : buttonNames) {
      JButton button = new JButton(buttonName);
      buttons.put(buttonName, button);
      button.setMargin(new Insets(4, 2, 4, 2));
      panel.add(button);

    }
  }

  private void initializeAddStocksPanel(JPanel screen) {

    addStocksPanel = new JPanel();

    addStocksPanel.setLayout(new BoxLayout(addStocksPanel, BoxLayout.PAGE_AXIS));
    scrollPane = new JScrollPane(addStocksPanel);
    addStocksPanel.setAutoscrolls(true);
    scrollPane.setPreferredSize(new Dimension(HEIGHT, 100));
    screen.add(scrollPane);
    symbols = new ArrayList<JTextField>();
    weights = new ArrayList<>();
    scrollMaxValue = scrollPane.getVerticalScrollBar().getMaximum();
    scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
      if (scrollMaxValue - e.getAdjustable().getMaximum() == 0) {
        return;
      }
      e.getAdjustable().setValue(e.getAdjustable().getMaximum());
      scrollMaxValue = scrollPane.getVerticalScrollBar().getMaximum();
    });

  }

  private void addStocks() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    String[] fields = new String[]{"symbol", "weight %"};
    String[] types = new String[]{"symbol", "float"};
    boolean[] required = new boolean[]{true, true};
    initialScreensLoader(fields, types, required, panel);
    addStocksPanel.add(panel);
    addStocksPanel.add(Box.createVerticalStrut(10));
    scrollPane.revalidate();

  }


  private final class CreateUserScreen {
    CreateUserScreen() {
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"username", "balance"};
      String[] types = new String[]{"newUserName", "float"};
      boolean[] required = new boolean[]{true, false};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Create user",
                      "Go back"
              },
              panel
      );
      buttons.get("Go back").addActionListener(e -> showInitialMenu());

      screen.add(panel);

      buttons.get("Create user").addActionListener(e -> buttonHandler(fields, validationResults));


      setScreen(ScreenNames.CREATE_USER_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      String userName = validationResults.get("username").data.toString();
      float balance = (float) validationResults.get("balance").data;
      features.createUser(userName, balance);
    }
  }

  private final class LoadUserScreen {
    LoadUserScreen() {
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"username"};
      String[] types = new String[]{"oldUserName"};
      boolean[] required = new boolean[]{true};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Load user",
                      "Go back"
              },
              panel
      );
      buttons.get("Go back").addActionListener(e -> showInitialMenu());

      screen.add(panel);

      buttons.get("Load user").addActionListener(e -> buttonHandler(fields, validationResults));


      setScreen(ScreenNames.LOAD_USER_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      String userName = validationResults.get("username").data.toString();
      showMessage("Loading user. This might take a few seconds...",false);
      class Worker extends SwingWorker<Float,Float>{
        float value;
        @Override
        protected Float doInBackground() throws Exception {
          features.loadUser(userName);
          return value;
        }

        @Override
        public void done(){
          portfolioName = features.getActivePortfolio();
          setActivePortfolio();
        }
      }
      new Worker().execute();

    }
  }

  private final class CreatePortfolioScreen {
    CreatePortfolioScreen() {
      setCurrentScene(ScreenNames.CREATE_PORTFOLIO_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"portfolio name"};
      String[] types = new String[]{"newPortfolioName"};
      boolean[] required = new boolean[]{true};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);

      screen.add(new Box.Filler(new Dimension(HEIGHT, 50),
              new Dimension(HEIGHT, 50),
              new Dimension(HEIGHT, 50)));

      centerAlignLabel("How do you want to add stocks to this portfolio?", screen);

      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Buy stocks",
                      "Invest on a date",
                      "Dollar cost averaging"
              },
              panel
      );


      screen.add(panel);

      buttons.get("Buy stocks").addActionListener(e -> buyStocksHandler(fields, validationResults));
      buttons.get("Invest on a date").addActionListener(e ->
              investOnDateHandler(fields, validationResults));
      buttons.get("Dollar cost averaging").addActionListener(e ->
              dollarCostHandler(fields, validationResults));


      setScreen(ScreenNames.CREATE_PORTFOLIO_SCREEN, screen);
    }

    private void dollarCostHandler(String[] fields, HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      portfolioName = validationResults.get("portfolio name").data.toString();
      new DollarCostAverage(true);
    }

    private void buyStocksHandler(String[] fields,
                                  HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      portfolioName = validationResults.get("portfolio name").data.toString();
      new BuyStocksScreen(true);

    }

    private void investOnDateHandler(String[] fields,
                                     HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      portfolioName = validationResults.get("portfolio name").data.toString();
      new InvestOnDateScreen(true);

    }

  }


  private final class LoadPortfolioScreen {

    LoadPortfolioScreen() {
      setCurrentScene(ScreenNames.LOAD_PORTFOLIO_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"portfolio name"};
      String[] types = new String[]{"oldPortfolioName"};
      boolean[] required = new boolean[]{true};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Load portfolio",
              },
              panel
      );

      screen.add(panel);

      buttons.get("Load portfolio").addActionListener(e -> buttonHandler(fields, validationResults));


      setScreen(ScreenNames.LOAD_PORTFOLIO_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      portfolioName = validationResults.get("portfolio name").data.toString();
      features.loadPortfolio(portfolioName);
      setActivePortfolio();

    }
  }


  private final class BuyStocksScreen {
    private final boolean creating;

    BuyStocksScreen(boolean creating) {
      setCurrentScene(ScreenNames.BUY_STOCKS_SCREEN);
      this.creating = creating;
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"symbol", "date", "number of shares", "commission fee"};
      String[] types = new String[]{"symbol", "date", "int", "float"};
      boolean[] required = new boolean[]{true, true, true, false};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());

      if (creating) {
        setButtons(
                new String[]{
                        "Buy stock",
                        "Go back",

                },
                panel
        );
        buttons.get("Go back").addActionListener(e -> new CreatePortfolioScreen());
      } else {
        setButtons(
                new String[]{
                        "Buy stock",

                },
                panel
        );
      }


      screen.add(panel);

      buttons.get("Buy stock").addActionListener(e -> buttonHandler(fields, validationResults));


      setScreen(ScreenNames.BUY_STOCKS_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      String symbol = validationResults.get("symbol").data.toString();
      String date = validationResults.get("date").data.toString();
      int shares = (int) validationResults.get("number of shares").data;
      float commissionFee = (float) validationResults.get("commission fee").data;
      if (creating) {
        features.createPortfolio(portfolioName);


      }
      features.buyStocks(symbol, shares, date, commissionFee, creating);
      setActivePortfolio();

    }
  }

  private void addStockHandler(String[] fields, HashMap<String, ValidationResult> validationResults) {
    if (checkErrors(fields, validationResults)) {
      return;
    }
    addStocks();

  }

  private HashMap getStocks() {

    ValidationResult result;
    HashMap<String, Float> stocks = new HashMap<>();
    for (int i = 0; i < symbols.size(); i++) {
      result = validate(symbols.get(i).getText(), "symbol", displayMessage, true);
      String symbol = result.data.toString();
      result = validate(weights.get(i).getText(), "float", displayMessage, true);
      float weight = (float) result.data;
      if (stocks.containsKey(symbol)) {
        weight += stocks.get(symbol);
      }
      stocks.put(symbol, weight);
    }
    return stocks;
  }

  private final class InvestOnDateScreen {
    private final boolean creating;

    InvestOnDateScreen(boolean creating) {
      setCurrentScene(ScreenNames.INVEST_ON_DATE);
      this.creating = creating;
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"amount", "date", "commission fee"};
      String[] types = new String[]{"float", "date", "float"};
      boolean[] required = new boolean[]{true, true, true};
      validationResults = new HashMap<>();


      initialScreensLoader(fields, types, required, screen);
      initializeAddStocksPanel(screen);
      addStocks();

      screen.add(Box.createVerticalStrut(30));
      initializeDisplayMessage(screen);
      screen.add(Box.createVerticalStrut(30));

      JPanel panel = new JPanel(new FlowLayout());
      if (creating) {
        setButtons(
                new String[]{
                        "Add a stock",
                        "Invest",
                        "Go back"

                },
                panel
        );
        buttons.get("Go back").addActionListener(e -> new CreatePortfolioScreen());
      } else {
        setButtons(
                new String[]{
                        "Add a stock",
                        "Invest"

                },
                panel
        );
      }


      screen.add(panel);

      String[] appendedFields = new String[]{"amount", "date", "commission fee", "symbol", "weight %"};
      buttons.get("Invest").addActionListener(e ->
              buttonHandler(appendedFields, validationResults));
      buttons.get("Add a stock").addActionListener(e ->
              addStockHandler(appendedFields, validationResults));


      setScreen(ScreenNames.INVEST_ON_DATE, screen);
    }


    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      float amount = (float) validationResults.get("amount").data;
      String date = validationResults.get("date").data.toString();
      float commissionFee = (float) validationResults.get("commission fee").data;


      HashMap stocks = getStocks();
      ValidationResult result = validator.validateWeights(stocks);
      if (!result.result) {
        showMessage(result.errorMessage, true);
        return;
      }


      if (creating) {
        features.createPortfolio(portfolioName);


      }

      features.investOnDate(date, amount, commissionFee, stocks, creating);
      setActivePortfolio();

    }
  }

  private final class DollarCostAverage {
    private final boolean creating;

    DollarCostAverage(boolean creating) {
      setCurrentScene(ScreenNames.DOLLAR_COST_AVERAGE);
      this.creating = creating;
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"amount", "start date", "end date", "interval (in days)", "commission fee"};
      String[] types = new String[]{"float", "date", "futureDate", "int", "float"};
      boolean[] required = new boolean[]{true, true, false, true, true};
      validationResults = new HashMap<>();
      initialScreensLoader(fields, types, required, screen);
      initializeAddStocksPanel(screen);
      addStocks();
      screen.add(Box.createVerticalStrut(30));
      initializeDisplayMessage(screen);
      screen.add(Box.createVerticalStrut(30));
      JPanel panel = new JPanel(new FlowLayout());
      if (creating) {
        setButtons(
                new String[]{
                        "Add a stock",
                        "Invest",
                        "Go back"

                },
                panel
        );
        buttons.get("Go back").addActionListener(e -> new CreatePortfolioScreen());
      } else {
        setButtons(
                new String[]{
                        "Add a stock",
                        "Invest"
                },
                panel
        );
      }


      screen.add(panel);

      String[] appendedFields = new String[]{"amount", "start date", "end date", "interval (in days)", "commission fee", "symbol", "weight %"};
      buttons.get("Invest").addActionListener(e ->
              buttonHandler(appendedFields, validationResults));
      buttons.get("Add a stock").addActionListener(e ->
              addStockHandler(appendedFields, validationResults));


      setScreen(ScreenNames.DOLLAR_COST_AVERAGE, screen);
    }


    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      float amount = (float) validationResults.get("amount").data;
      String startDate = validationResults.get("start date").data.toString();
      String endDate = validationResults.get("end date").data.toString();
      int interval = (int) validationResults.get("interval (in days)").data;
      float commissionFee = (float) validationResults.get("commission fee").data;


      HashMap stocks = getStocks();
      ValidationResult result = validator.validateWeights(stocks);
      if (!result.result) {
        showMessage(result.errorMessage, true);
        return;
      }


      if (creating) {
        features.createPortfolio(portfolioName);


      }

      features.dollarCost(startDate, endDate, interval, amount, commissionFee, stocks, creating);
      setActivePortfolio();

    }
  }

  private void setCurrentScene(ScreenNames screenName) {
    currentScreen = screenName;
  }

  private void setActivePortfolio() {
    if (portfolioName != null) {
      setTitle("Portfolio Application\t\t" + "Current Portfolio: " + portfolioName);
    }
  }

  private final class GetCostBasisScreen {
    GetCostBasisScreen() {
      setCurrentScene(ScreenNames.GET_COST_BASIS_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"date"};
      String[] types = new String[]{"futureDate"};
      boolean[] required = new boolean[]{true};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Get cost basis",

              },
              panel
      );


      screen.add(panel);

      buttons.get("Get cost basis").addActionListener(e ->
              buttonHandler(fields, validationResults));


      setScreen(ScreenNames.GET_COST_BASIS_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      String date = validationResults.get("date").data.toString();
      float costBasis = features.getCostBasis(date);
      if (costBasis >= 0) {
        showMessage("Cost basis till " + date + " is " + costBasis, false);
      }


    }
  }

  private final class GetTotalValue {
    GetTotalValue() {
      setCurrentScene(ScreenNames.GET_TOTAL_VALUE_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"date"};
      String[] types = new String[]{"date"};
      boolean[] required = new boolean[]{true};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Get total value",

              },
              panel
      );

      screen.add(panel);

      buttons.get("Get total value").addActionListener(e ->
              buttonHandler(fields, validationResults));


      setScreen(ScreenNames.GET_TOTAL_VALUE_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }

      String date = validationResults.get("date").data.toString();
      showMessage("Getting the value. This might take a few seconds...",false);
      class Worker extends SwingWorker<Float,Float>{
        float value;
        @Override
        protected Float doInBackground() throws Exception {
          value=features.getTotalValue(date);
          return value;
        }

        @Override
        public void done(){
          if (value >= 0) {
            showMessage("Total value on " + date + " is " + value, false);
          }
        }
      }
     new Worker().execute();

    }

  }



  private final class SellStocksScreen {
    SellStocksScreen() {
      setCurrentScene(ScreenNames.SELL_STOCKS_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"symbol", "date", "number of shares", "commission fee"};
      String[] types = new String[]{"symbol", "date", "int", "float"};
      boolean[] required = new boolean[]{true, true, true, false};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "Sell stock",

              },
              panel
      );


      screen.add(panel);

      buttons.get("Sell stock").addActionListener(e -> buttonHandler(fields, validationResults));


      setScreen(ScreenNames.SELL_STOCKS_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      String symbol = validationResults.get("symbol").data.toString();
      String date = validationResults.get("date").data.toString();
      int shares = (int) validationResults.get("number of shares").data;
      float commissionFee = (float) validationResults.get("commission fee").data;
      features.sellStocks(symbol, shares, date, commissionFee);

    }
  }

  private final class ListOfPortfoliosScreen {

    ListOfPortfoliosScreen() {
      setCurrentScene(ScreenNames.LIST_OF_PORTFOLIOS_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String portfoliosList = features.getListOfPortfolios();
      for (String portfolios : portfoliosList.split("\n")) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(Box.createHorizontalStrut(200));
        for (String labelText : portfolios.split("\\s+")) {
          JLabel label = new JLabel(labelText);
          label.setPreferredSize(new Dimension(150, 30));
          panel.add(label);
          panel.add(Box.createHorizontalStrut(100));
        }
        screen.add(panel);

      }


      setScreen(ScreenNames.LIST_OF_PORTFOLIOS_SCREEN, screen);
    }


  }

  private final class GetPlotInputsScreen {
    GetPlotInputsScreen() {
      setCurrentScene(ScreenNames.GET_PLOT_INPUT_SCREEN);
      JPanel screen = new JPanel();
      screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));


      String[] fields = new String[]{"start date", "end date"};
      String[] types = new String[]{"date", "date"};
      boolean[] required = new boolean[]{true, true};
      validationResults = new HashMap<>();

      initialScreensLoader(fields, types, required, screen);


      JPanel panel = new JPanel(new FlowLayout());
      setButtons(
              new String[]{
                      "View Performance Plot",

              },
              panel
      );


      screen.add(panel);

      buttons.get("View Performance Plot").addActionListener(e ->
              buttonHandler(fields, validationResults));


      setScreen(ScreenNames.GET_PLOT_INPUT_SCREEN, screen);
    }

    private void buttonHandler(String[] fields,
                               HashMap<String, ValidationResult> validationResults) {

      if (checkErrors(fields, validationResults)) {
        return;
      }
      String startDate = validationResults.get("start date").data.toString();
      String endDate = validationResults.get("end date").data.toString();

      showMessage("Plotting. This might take a couple of minutes...",false);
      class Worker extends SwingWorker<Float,Float>{
        float value;
        @Override
        protected Float doInBackground() throws Exception {
          setScreen(ScreenNames.GET_PLOT_SCREEN,
                  new GetPlotScreen(features, startDate, endDate).getScreen());
          return value;
        }

        @Override
        public void done(){

        }
      }
      new Worker().execute();



    }
  }


}


