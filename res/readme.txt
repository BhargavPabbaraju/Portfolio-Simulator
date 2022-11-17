
Features
--------
1. Purchase a specific number of shares of a specific stock on a specified date,and add them to the portfolio.
	-This feature works completely. 
	-A user can buy shares of a stock on any date(as long as the date is neither a weekend nor a future date).
	-A user can create multiple portfolios with shares of one or more stock.
	-If a user buys shares of same stock on a date he already bought , the shares will be combined.
	-For example, if a user buys stocks AMZN 10 on 2022-11-01 , ORCL 20 on 2022-11-01 , AMZN 5 on 2022-11-01 in that 	order,then the resulting portfolio will contain AMZN 15 on 2022-11-01, ORCL 20 on 2022-11-01
	-Once a portfolio is created, stocks can be bought and sold at any time.
	-A user must enter the transaction cost each time he tries to buy.


2. Sell a specific number of shares of a specific stock on a specified date from a given portfolio.
	-This feature works completely.
	-A user can sell shares of a stock on any date(as long as the date is neither a weekend nor a future date) but
	only if this transaction is consistent with previously done transactions.
	-A user cannot sell stocks on a date when he hasn't bought any stocks yet.
	-A user can only sell shares of a stock if he has enough shares of that stock on that date.
	-A user must enter the transaction cost each time he tries to sell.


3. Determine the cost basis of a portfolio by a certain date.
	-This feature works completely.
	-Cost basis includes the purchase cost of the shares till that date as well as the transaction cost for each 	purchase.
	-This operation takes a couple of seconds (could go upto 11 seconds) to display the cost basis the first time  
	 this method is called. This is because of an api call in the backend. However , this operation is considerably 
	 faster(around a second or less) in the subsequent calls of this method.


4. Determine the value of a portfolio on a specific date.
	-This feature works completely.
	-The value will be determined based on the stocks present on that date.
	-The value of a portfolio before any purchase is 0.

5. Plot the portfolio performance over time.
	-This feature works completely.
	-A user must enter the start date and end date (both in yyyy-mm-dd formats and cannot be future dates)
	-The plot ranges are daily,monthly or yearly based on the number of days between the start and end dates.
	Note: If the range(maxium value - minimum value) of the portfolio on any date is too huge(goes beyond 50 *'s), 	then the plot is truncated.
		Only 50 *s are displayed with the actual value beside it.

	Example:
		>>1Performance of portfolio retir from Oct 31 to Nov 08
		>>Oct 31 :
		>>Nov 01 : *
		>>Nov 02 : *
		>>Nov 03 : *
		>>Nov 04 : *
		>>Nov 07 : **************************************************($4437421.50)
		>>Nov 08 : **************************************************($4450396.50)
		>>Scale: * = $4000
		>>Maximum plot length is 50 *'s. Remaining *'s are truncated

	 
 



