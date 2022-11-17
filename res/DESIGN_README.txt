DESIGN CHANGES
***************

1)CONTROLLER CHANGES
---------------------
1)New methods are being added to support new functionality and controller will now have an option to send information about the API call
	reason: This gives the controller power to determine which third party api should we use as our program is now able to support 
		multiple third party API's



2) MODEL CHANGES
-----------------
Existing methods are almost avoided for modification and have not changed the overall design of the project but We added 
new classes to support the new functionality which follow the similar design flow which we have earlier. So, the user will 
have both the functionalities. 

1. To accomodate the new requirements and continue to support old functionalities we have created a NewModel interface which 
   extends the previous model interface and added new methods which support the new functionality of flexible portfolio. The 
   NewModelImpl class implements this new interface and extends previous ModelImpl class.
   reason: By doing this our previous model will still be available to clients and they can only use old funtionality and if 
           they want new functionality along with old then they can use new model. This obeys open-close principles of SOLID 
	   principles and this will also avoid modification of old interface.

2. Added a new Interface for FlexiblePortfolio with all the new functionalities it needs to support. Added a new AbstractPortfolio 
   class implemeting both portfolio(old interface) and FlexiblePortfolio.
   reason: As our functionality is changing at the portfolio level. By using AbstractPortfolio user need not keep a track of which 
	   portfolio is being used. User will interact with the AbstractPortfilio which can either be Flexible portfilio or Inflexible
	   portfolio which is taken care by dynamic dispatch.

3. From the portfolio level our design will have separate implmentations for flexible and inflexible portfolio's i.e
   for Flexibleportfolio(FlexibleportfolioImpl->FlexibleStockListImpl->FlexibleStockImpl) and
   for InflexiblePortfolio(old-flow)(PortfolioImpl->StockImpl).Our AbstractPortolio can be either of the above two portfolios and uses dynamic 
   dispatch to delegate methods.
   reason: We created separate implmentations for these because the data representation in both the portfolios is different as
           flexible portfolio uses dates and user can buy same stock on different dates.

4. Method signature for getValue on date changed, we added a new parameter called ApiType.
   reason: As we need API data for this method the controller will send information about the API call to this method as we are now able
           to support multiple third party API's.

__________________________________________________________________________________________________________________________________________________

DESIGN OVERVIEW
****************
1.The Underlying design of your implementation is MVC.
  -Initially main method creates the model and view and gives the control to the controller.
  -Controller then handles the input's and call the methods from model and view to run a interactive text based application.

2.The NewModelImpl is the main model which interacts with the controller.
	- The NewModelImpl extends previous ModelImpl and implements NewModel interface. 
	- Creating a user and loading a exsisting user from file system takes place in ModelImpl rest all the operations are delegated 
	  to underlying classes.

3.The UserImpl class represents a single user and this is used by the NewModelImpl(Main Model) to create a user.
	- This class contains the following fields:
		1.userName : name of the user which is unique to each user
		2.balance : represents money which the user has(not necessary as of now but might be userful while adding buy and sell funtionality)
		3.portfolioList: This is a HashMap which has all the AbstractPortfolio's(Both flexible and inflexible) of the current user. Key will be portfolio and value will be portfolio object.
				The reason for using hashmap is it is effecient in searching eg. to check if a portfolio already exists.
		4.activePortfolio : Whenever a user uses "Load Portfolio" option and enters portfolio name this field is set to that portfolio and user menu will change based on selected 
				    portfolio(flexible or inflexible).
	- Operations like createPortfolio is done at this level by creating a porfolio object and adding it to the portfolioList HashMap and making it as active 
	  portfolio. Rest of the operations are delegated to the underlying classes.

4.The AbtractPortfolio class implemets both portfolio(old interface) and FlexiblePortfolio the UserImpl only interacts with this class. By using AbstractPortfolio user 
  need not keep a track of which portfolio is being used. User will load a particular portfolio and depending on which type pf portfolio it is AbstractPortfilio will delegate
  the operations to that class. 
	Now, if the portfolio selected is Flexible then the flow will be - FlexibleportfolioImpl->FlexibleStockListImpl->FlexibleStockImpl.
	and if the portfolio selected is InFlexible then the flow will be - PortfolioImpl->StockImpl


3.The FlexibleportfolioImpl class represents a single Flexible portfolio.
	- This class contains the following fields:
		1.portfolioName: name of the portfolio
		2.stockList : This is a HashMap which has all the stock's of the current portfolio. Key will be stock symbol and value will be FlexibleStockListImpl's object.
			      The reason for using hashmap is it is effecient in searching eg. to check if a stock already exists in a portfolio.

4.The FlexibleStockListImpl class represents all the purchases and sell operations made on a particular stock on different dates for a flexible portfolio.
	- This class contains the following fields:
		1.stocksList: name of the
		2.stockList : This is a HashMap which has all the purchases and sales made for a stock the current portfolio. Key will be date and value will be FlexibleStockImpl's object.
	- It delegates its operations to FlexibleStockImpl based on the date.


5.The FlexibleStockImpl class represents a single stock in Flexible portfolio.
	- This is the bottom level of the heirarchy and this is the class which interacts with the API to get the value of a stock.
	- This class contains the following fields:
		1.numberOfShares: Number of shares for that stock which the user entered.
		2.date : Date on which the stock was purchased.
		3.symbol: Symbol of the Stock.
		4.transactionCost: Transaction cost associted with buying this stock
	- All the operations like composition, cost basis, get value etc, are performed at this level and are aggregated at the above level to show the result. 


6.The PortfolioImpl class represents a single portfolio for Inflexible portfolio(Old class).
	- This class contains the following fields:
		1.portfolioName: name of the portfolio
		2.stockList : This is a HashMap which has all the stock's of the current portfolio. Key will be stock symbol and value will be stock's object.
			      The reason for using hashmap is it is effecient in searching eg. to check if a stock already exists in a portfolio.
	- Operations like createStock and add Stock is done here.

7. The StockImpl class represents a single stock and its objects are created by PortfolioImpl as mentioned above for inflexible portfolio(Old class).
	- This is the bottom level of the heirarchy and this is the class which interacts with the API to get the value of a stock.
	- This class contains the following fields:
		1.numberOfShares: Number of shares for that stock which the user entered.
		2.date : Date on which the stock was purchased.
		3.symbol: Symbol of the Stock.
	- All the operations such as getTotalValue on a certain date compostion etc. are performed at this level and are aggregated at the portfolio level to show the result. 
  	

8. The ApiCallImpl class is responsible for interating with third party API. We are using AlphaVantage API and our API is static class which can support any third pary API.
	- We are using caching to reduce the API Limit problem.
	- Before making a API call it will always check for cached data and it will only make call if the data is not found in cache.
        - After making a API call the data is stored in the cache.
	- If the API limit is reached we are also storing defauld share values(provided my NASDAQ) of a symbol in cache data so the we will always have a fallback option.

9. The CacheImpl class represents cache data which is used by ApiCallImpl before making a call. 
	- The CacheImpl has the following fields:
		1.cacheList: This is the HashMap of cached data after making the API call. key is the Stock symbol and value is the API data.
		2.symbolList : This is a HashMap of cached symbol and their default share values. Key is the sumbol and value is the default share value. This is fall back just
			       in case the api fails. This list is also used to validate symbols.
	- Initially when the program is loaded cache data is loaded from the file system. All the cache data is stored in "res/data/cachedata/" directory. This data is generated 
	  on the go so program gets efficent as it executed. As the data is stored in the file system it is persisted even if the program is closed.


 