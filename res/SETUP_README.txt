Folder Structure
—-------------------
The Jar file must be inside res folder. The res folder should also contain a data folder. This data folder contains data.csv.

Instructions to format file to load data
—----------------------------------------------
The data should be in the below format.

{
  "name": "user5",
  "balance": 12000.0,
  "portfolios": [
    {
      "name": "college",
      "flexible": false,
      "stocks": [
        {
          "symbol": "MSFT",
          "shares": 30.0,
          "date": "2022-11-11"
        }
      ]
    },
    {
      "name": "retir",
      "flexible": true,
      "stocks": {
        "GOOG": {
          "2022-08-01": {
            "shares": 20.0,
            "transactionCost": 10.0
          },
          "2022-11-01": {
            "shares": -10.0,
            "transactionCost": 10.0
          }
        },
        "ORCL": {
          "2022-08-01": {
            "shares": 10.0,
            "transactionCost": 10.0
          }
        }
      }
    }
  ]
}

·There must be a balance field denoting the initial balance of the user. 
·Each portfolio must have name field ,flexible field and stocks field. 
.Flexible field deontes whether this portfolio is flexible or not. Should only contain true or false values.

Stocks of Inflexible portfolios:
·Each stock should have a valid symbol (refer to the list of symbols in data.csv),
·Number of shares that must be a positive integer value and 
·A date field denoting the date on which this particular stock was bought. 
·Date must be in “yyyy-mm-dd” format. 
·Date cannot be a weekend as stock market is closed on weekends.

Stocks of Flexibke portfolios:
.Each stock should have an object with key as the stock symbol (refer to the list of valid symbols in data.csv),
.Each stock symbol should have an object with dates as the keys and transaction details as values.
.Each transaction should have the shares bought or sold(Sold shares are indicated with a negative sign.) and a transaction cost.

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
	

Instructions to create a flexible portfolio
-------------------------------------------
After successful creation of a user , the main menu is displayed as shown below.
	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

Enter 1 to create a portfolio. 
->You will be asked to enter the portfolio name.Please note that the portfolio name must not contain any of "{}[],:" characters. 
->You will be prompted to select which kind of portfolio you wish to create (whether flexible or inflexible).

Enter 1 to create a flexible portfolio.

A portfolio must contain at least one stock. So, if the portfolio name is valid:
->You will be prompted to enter a stock symbol.Make sure the symbol is valid.(A list of valid stock symbols can be foud in data.csv in the data folder). 
->You will be prompted to enter the transaction cost.(Transaction cost must be a valid positive floating point number).
->You will be prompted to enter the date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)
->You will be prompted to enter the number of shares.(Number of shares must be a valid positive integer.)



An example of the above operation:

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>1
	>>Enter portfolio name
	>>retir
	>>Select which kind of portfolio you wish to create
	>>1.Flexible Portfolio
	>>2.Inflexible Portfolio
	>>1
	>>Enter Stock Symbol
	>>GOOG
	>>Enter transaction cost
	>>10
	>>Enter date(yyyy-mm-dd)
	>>2022-11-01
	>>Enter number of shares
	>>50
	>>
	>>Successfully bought stocks


Instructions to buy stocks
--------------------------
You must have created at least one portfolio before you can buy stocks. To buy stocks:
->Enter 3 in the main menu.
->You will be prompted to enter a stock symbol.Make sure the symbol is valid.(A list of valid stock symbols can be foud in data.csv in the data folder). 
->You will be prompted to enter the transaction cost.(Transaction cost must be a valid positive floating point number).
->You will be prompted to enter the date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)
->You will be prompted to enter the number of shares.(Number of shares must be a valid positive integer.)

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>3
	>>Enter Stock Symbol
	>>ORCL
	>>Enter transaction cost
	>>10
	>>Enter date(yyyy-mm-dd)
	>>2022-11-02
	>>Enter number of shares
	>>30
	>>
	>>Successfully bought stocks


Instructions to sell stocks
---------------------------
You must have created at least one portfolio before you can buy stocks. To sell stocks:
->Enter 4 in the main menu.
->You will be prompted to enter a stock symbol.Make sure the symbol is valid.(A list of valid stock symbols can be foud in data.csv in the data folder). 
->You will be prompted to enter the transaction cost.(Transaction cost must be a valid positive floating point number).
->You will be prompted to enter the date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)
->You will be prompted to enter the number of shares.(Number of shares must be a valid positive integer.)

Note: You should have enough shares of that stock in your portfolio to sell those shares and this transaction must be consistent with previous transactions.

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>4
	>>Enter Stock Symbol
	>>GOOG
	>>Enter transaction cost
	>>10
	>>Enter date(yyyy-mm-dd)
	>>2022-11-03
	>>Enter number of shares
	>>20
	>>
	>>Successfully sold stocks


Instructions to query cost basis on a certain date
--------------------------------------------------
You must have created at least one portfolio before you can query cost basis.
->Enter 6 in the main menu.
->You will be prompted to enter the date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>6
	>>Enter date(yyyy-mm-dd)
	>>2022-11-03
	>>
	>>Cost basis till 2022-11-03 is $6798.6




Instructions to view Composition of flexible portfolio on a certain date
------------------------------------------------------------------------
You must have created at least one portfolio before you can view composition.
->Enter 7 in the main menu.
->You will be prompted to enter the date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)


	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>7
	>>Enter date(yyyy-mm-dd)
	>>2022-11-03
	>> Composition of retir upto 2022-11-03
	>>GOOG                                    30.00
	>>ORCL                                    30.00


Instructions to query total value on certain date
-------------------------------------------------
You must have created at least one portfolio before you can view composition.
->Enter 8 in the main menu.
->You will be prompted to enter the date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)
->It might take a few seconds to display the total value the first time(because of an api call in the backend). It is considerably faster in the subsequent runs.

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>8
	>>Enter date(yyyy-mm-dd)
	>>2022-11-03
	>>
	>>Total Value on 2022-11-03 is $4725.6


Insturtcions to plot performance of portfolio
---------------------------------------------
You must have created at least one portfolio before you can plot its performance.
->Enter 9 in the main menu.
->You will be prompted to enter the start date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)
->You will be prompted to enter the end date.(Date must be in yyyy-mm-dd format and must not be a weekend or a future date.)
->It might take a few seconds to display the plot on the initial run.

	>>1.Create Portfolio
	>>2.Load Portfolio
	>>3.Buy Stock
	>>4.Sell Stock
	>>5.View List of Portfolios
	>>6.Get Cost basis on certain date
	>>7.Get Composition on certain date
	>>8.Get Total Value on certain date
	>>9.Get Plot within a certain date range
	>>10.Save
	>>11.Exit

	>>9
	>>Enter start date(yyyy-mm-dd)
	>>2022-10-28
	>>Enter end date(yyyy-mm-dd)
	>>2022-11-10
	>>Performance of portfolio retir from Oct 28 to Nov 10
	>>Oct 28 :
	>>Oct 31 :
	>>Nov 01 : *
	>>Nov 02 : *
	>>Nov 03 : *
	>>Nov 04 : *
	>>Nov 07 : *
	>>Nov 08 : *
	>>Nov 09 : *
	>>Nov 10 : *
	>>Scale: * = $4000

Note: If the range(maxium value - minimum value) of the portfolio on any date is too huge(goes beyond 50 *'s), then the plot is truncated.
Only 50 *s are displayed with the actual value beside it.

Example:
	>>1Performance of portfolio retir from Oct 31 to Nov 10
	>>Oct 31 :
	>>Nov 01 : *
	>>Nov 02 : *
	>>Nov 03 : *
	>>Nov 04 : *
	>>Nov 07 : **************************************************($4437421.50)
	>>Nov 08 : **************************************************($4450396.50)
	>>Nov 09 : **************************************************($4374852.00)
	>>Nov 10 : **************************************************($4713600.00)
	>>Scale: * = $4000
	>>Maximum plot length is 50 *'s. Remaining *'s are truncated


Instructions to add another portfolio(inflexible)
-------------------------------------------------
To create a new portfolio , enter 1 in the main menu.
Select 2 to create an inflexible portfolio.

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
	>>5.View list of portfolios
	>>6.Save
	>>7.Exit
	>>5
	>>Portfolio                               Type
	>>college                                 Inflexible
	>>retir                                   Flexible




Instructions to switch to a different portfolio
-----------------------------------------------
To perform operations on a different portfolio , or to switch the current portfolio , Enter 2 in the main menu
and enter the name of the portfolio you wish to switch to.

The get value operation might take a couple of seconds on the initial run because this portfolio might have different stocks.


Instructions to save and load data
----------------------------------
In order to persist data , Enter 10 in the main menu(for flexible portfolio , 6 for inflexible) to save your data. 

WARNING: If you exit without saving, your data will not be persisted.

The data will be saved as "username".json in the data folder.

To load your data , exit the application by entering 11(flexible) or 7(inflexible) and run it again.

This time, in the initial menu , enter 2 to load user and enter your username.

	>>1.Create User
	>>2.Load User
	>>2
	>>Enter username
	>>user
	>>
	>>User successfully loaded

You can now load your portfolios and perform operations on them.