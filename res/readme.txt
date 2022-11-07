
Features
--------
1. Creation of multiple portfolios with shares of one or more stock.
	-This feature works completely. 
	-A user can create multiple portfolios with shares of one or more stock.
	-During creation, if the user adds more shares for a stock already added , the shares will be combined. 
	-For example, if a user adds stocks AMZN 10 , ORCL 20 , AMZN 5 in that order,
	-then the resulting portfolio will contain AMZN 15, ORCL 20.
	-Once a portfolio is created , there is no option to edit it. It can only be loaded.


2. Examine the composition of a portfolio
	-This feature works completely.
	-When a user enters option 3 in the main menu, the composition of the current portfolio is displayed.
	-The user has to load a portfolio by entering its name if the composition for a different portfolio is to be 	 	 displayed.
	-Composition of a portfolio simply displays the stocks present in this portfolio with their symbols and shares.
	-Example of how the composition will look like:

		>>                    retirement
		>>IBM                                     10.00
		>>ORCL                                    20.00
		>>AMZN                                    50.00


3. Determine the total value of a portfolio on a certain date.
	-This feature works completely.
	-A user must load a portfolio to specify which portfolio this operation must be performed on.
	-The date must be in 'yyyy-mm-dd' format. 
	-The date must not be a future date.
	-The date must not be a weekend.
	-The date should be no older than 20 years ago from the current date.
	  For example, if the current date is 2022-11-02 , the oldest date a user can enter is 		1999-11-02.
	Note: Some stocks may not have data upto 20 years back in cases where companies were found less than 20 years ago.
	-This operation takes a couple of seconds (could go upto 11 seconds) to display the total value the first time  
	 this method is called. This is because of an api call in the backend. However , this operation is considerably 
	 faster(around a second or less) in the subsequent calls of this method.


4. Persist a portfolio so that it can be saved and loaded.
	-This feature works completely.
	-A user must enter 5 on the main menu to save data before exitting the application.
	-A user cannot save until he has created at least one portfolio.
	-Data is persisted in data folder saved as "username".json.
	-User can load his file as soon as he/she opens the application and can access all the portfolios he/she 	 created.
	 




