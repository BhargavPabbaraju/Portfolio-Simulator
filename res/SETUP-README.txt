Folder Structure
—-------------------
The Jar file must be inside res folder. The res folder should also contain a data folder. This data folder contains data.csv.

Instructions to format file to load data
—----------------------------------------------
The data should be in the below format.

{
  "name": "user1",
  "balance": 40000.0,
  "portfolios": [
    {
      "name": "college",
      "stocks": [
        {
          "symbol": "AAPL",
          "shares": 10.0,
          "date": "2022-10-28"
        },
        {
          "symbol": "TSLA",
          "shares": 20.0,
          "date": "2022-10-28"
        }
      ]
    },
    {
      "name": "retirement",
      "stocks": [
        {
          "symbol": "MSFT",
          "shares": 30.0,
          "date": "2022-10-28"
        },
        {
          "symbol": "META",
          "shares": 30.0,
          "date": "2022-10-28"
        },
        {
          "symbol": "GOOGL",
          "shares": 30.0,
          "date": "2022-10-28"
        }
      ]
    }
  ]
}

There must be a balance field denoting the initial balance of the user. 
Each portfolio must have name field and stocks field. 
Each stock should have a valid symbol (refer to the list of symbols in data.csv) , number of shares that must be a positive integer value and a date field denoting the date on which this particular stock was bought. Date must be in “yyyy-mm-dd” format. Date cannot be a weekend as stock market is closed on weekends.

If any of the above constraints are violated , the file will be deemed as incorrectly formatted and the program will not load the file.

Please make sure the brackets and commas are correctly formatted and none of the fields should contain any of “{}[]:,” characters.

This file must be saved as “username”.json (the same username mentioned in the file.) inside the data folder.

Instructions to run jar file
—-----------------------------
Use the command line interface to run the jar file from res folder.
As soon as you run the file, it’ll prompt you to either create a user or load a user , like so.
	>>1.Create User
	>>2.Load User

Enter 1 To create a user or enter 2 to load a file.

Instructions to create a user 
—----------------------------------
Enter 1 in the initial menu. Then the program will ask you to enter username and initial balance. Please make sure the username you enter does not contain any of “{}[],:” characters and the initial balance is a non negative floating point number.

An example flow of the above operation:
	>>1.Create User
	>>2.Load User
	>>1
	>>Enter username
	>>user
	>>Enter initial balance
	>>2022.11
	>>User successfully created

Instructions to create a portfolio
----------------------------------
After successful creation of a user , the main menu is displayed as shown below.
	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit

Enter 1 to create a portfolio. You will be asked to enter the portfolio name.Please note that the portfolio name must not contain any of "{}[],:" characters.
A portfolio must contain at least one stock. So, if the portfolio name is valid , you will be prompted to enter a stock symbol and the number of shares you hold in this stock. Make sure the symbol is valid.(A list of valid stock symbols can be foud in data.csv in the data folder). Make sure the number of shares is a positive integer.

An example of the above operation:

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>1
	>>Enter portfolio name
	>>retirement
	>>Enter Stock Symbol
	>>IBM
	>>Enter number of shares
	>>10
	>>1.Add new stock
	>>2.Back to main menu


Enter 1 to add a new stock or enter 2 to go back to main menu.

Example of adding two more stocks:
	>>1.Add new stock
	>>2.Back to main menu
	>>1
	>>Enter Stock Symbol
	>>ORCL
	>>Enter number of shares
	>>20
	>>1.Add new stock
	>>2.Back to main menu
	>>1
	>>Enter Stock Symbol
	>>AMZN
	>>Enter number of shares
	>>50
	>>1.Add new stock
	>>2.Back to main menu
	>>2

Instructions to view Composition of current portfolio
------------------------------------------------------
From the main menu , enter 3 to view the composition of the current portfolio.

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>3
	>>                    retirement
	>>IBM                                     10.00
	>>ORCL                                    20.00
	>>AMZN                                    50.00



Instructions to add another portfolio
-------------------------------------
To create a new portfolio , enter 1 in the main menu and follow the above steps. Make sure the name of the new portfolio is different from the name of the portfolio previously created.

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>1
	>>Enter portfolio name
	>>college
	>>Enter Stock Symbol
	>>AMZN
	>>Enter number of shares
	>>10
	>>1.Add new stock
	>>2.Back to main menu
	>>1
	>>Enter Stock Symbol
	>>MSFT
	>>Enter number of shares
	>>20
	>>1.Add new stock
	>>2.Back to main menu
	>>2
	>>
	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>3
	>>                    college
	>>MSFT                                    20.00
	>>AMZN                                    10.00


Instructions to get total value of current portfolio
----------------------------------------------------
To get the total value of the current portfolio, enter 4 in the main menu. Enter the date on which you want to get the value. The date must be in "yyyy-mm-dd" format and must not be a weekend since the stock market is closed on weekends.
The date should also not be a future date since we cannot predict the value on a future date accurately.

It may take a couple of seconds to fetch data from the api the first time this operation is performed. The operation will be faster in subsequent runs.

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>4
	>>Enter date(yyyy-mm-dd)
	>>2022-11-02
	>>
	>>Total Value on 2022-11-02 is 5760.6006

Note: The oldest date you can perform this query on  is exactly 20 years ago from the current date. For example,
if today is 2022-11-02 , the oldest date the program can support is 1999-11-02.
Note: Some stocks may not have data upto 20 years back in cases where companies were found less than 20 years ago.


Instructions to switch to a different portfolio
-----------------------------------------------
To perform operations on a different portfolio , or to switch the current portfolio , Enter 2 in the main menu
and enter the name of the portfolio you wish to switch to.

The get value operation might take a couple of seconds on the initial run because this portfolio might have different stocks.

Example of switching portfolio and getting its composition and value:

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>2
	>>Enter portfolio name
	>>retirement
	>>
	>>Loaded retirement
	>>
	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>3
	>>                    retirement
	>>IBM                                     10.00
	>>ORCL                                    20.00
	>>AMZN                                    50.00
	>>
	>>
	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Get Composition
	>>4.Get Total Value on certain date
	>>5.Save
	>>6.Exit
	>>4
	>>Enter date(yyyy-mm-dd)
	>>2022-11-02
	>>
	>>Total Value on 2022-11-02 is 8200.1


Instructions to save and load data
----------------------------------
In order to persist data , Enter 5 in the main menu to save your data. 

WARNING: If you exit without saving, your data will not be persisted.

The data will be saved as "username".json in the data folder.

To load your data , exit the application by entering 6 and run it again.

This time, in the initial menu , enter 2 to load user and enter your username.

	>>1.Create User
	>>2.Load User
	>>2
	>>Enter username
	>>user
	>>
	>>User successfully loaded

You can now load your portfolios and perform operations on them.