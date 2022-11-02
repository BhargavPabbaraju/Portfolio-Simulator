DESIGN
-------

1.The Underlying design of our implementation is MVC.
  -Initially main method creates the model and view and gives the control to the controller.
  -Controller then handles the input's and call the methods from model and view to run an interactive text based application.

Understanding Model's Design
--------------------------

1.The ModelImpl is the main model which interacts with the controller.
	- As our application needs a user as portfolios are specific to user, the ModelImpl creates a user object at this level.
	- Creating a user and loading a existing user from file system takes place in ModelImpl rest all the operations are delegated 
	  to underlying classes.

2.The UserImpl class represents a single user and this is used by the ModelImpl(Main Model) to create a user.
	- This class contains the following fields:
		1.userName : name of the user which is unique to each user
		2.balance : represents money which the user has(not necessary as of now but might be useful while adding buy and sell functionality)
		3.portfolioList: This is a HashMap which has all the portfolios of the current user. Key will be portfolio and value will be portfolio object.
				The reason for using hashmap is it is efficient in searching eg. to check if a portfolio already exists.
		4.activePortfolio : Whenever a user uses "Load Portfolio" option and enters portfolio name this field is set to that portfolio so the operations
				    Such as getTotal value and composition can be performed on it.
	- Operations like createPortfolio is done at this level by creating a portfolio object and adding it to the portfolioList HashMap and making it as active 
	  portfolio. Rest of the operations are delegated to the underlying classes.

3.The PortfolioImpl class represents a single portfolio and its objects are created by the UserImpl as mentioned above.
	- This class contains the following fields:
		1.portfolioName: name of the portfolio
		2.stockList : This is a HashMap which has all the stock's of the current portfolio. Key will be stock symbol and value will be stock's object.
			      The reason for using hashmap is it is efficient in searching eg. to check if a stock already exists in a portfolio.
	- Operations like createStock and add Stock is done here.

4. The StockImpl class represents a single stock and its objects are created by PortfolioImpl as mentioned above.
	- This is the bottom level of the hierarchy and this is the class which interacts with the API to get the value of a stock.
	- This class contains the following fields:
		1.numberOfShares: Number of shares for that stock which the user entered.
		2.date : Date on which the stock was purchased.
		3.symbol: Symbol of the Stock.
	- All the operations such as getTotalValue on a certain date composition etc. are performed at this level and are aggregated at the portfolio level to show the result.

5. The ApiCallImpl class is responsible for interacting with third party API. we are using AlphaVantage API(API Limit : 5 calls per minute)
	- We are using caching to reduce the API Limit problem.
	- Before making a API call it will always check for cached data and it will only make call if the data is not found in cache.
        - After making a API call the data is stored in the cache.
	- If the API limit is reached we are also storing default share values(provided my NASDAQ) of a symbol in cache data so the we will always have a fallback option.

6. The CacheImpl class represents cache data which is used by ApiCallImpl before making a call. 
	- The CacheImpl has the following fields:
		1.cacheList: This is the HashMap of cached data after making the API call. key is the Stock symbol and value is the API data.
		2.symbolList : This is a HashMap of cached symbol and their default share values. Key is the symbol and value is the default share value. This is fall back just
			       in case the api fails. This list is also used to validate symbols.
	- Initially when the program is loaded cache data is loaded from the file system. All the cache data is stored in "res/data/cachedata/" directory. This data is generated 
	  on the go so program gets efficient as it executed. As the data is stored in the file system it is persisted even if the program is closed.
	  
