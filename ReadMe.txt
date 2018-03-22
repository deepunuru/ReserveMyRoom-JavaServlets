ReserveMyRoom Web Application ReadMe v1.0
--------------------------------------
Below is a list of features available through ReserveMyRoom web application.

Deal Matches Feature:
---------------------
The deal match component uses DealMatches.java servlet and the provided python script to extract deals from twitter, cross check them with products in the MySQL server and write them into a text file - DealMatches.txt.
The DealMatches.java servlet will be used to check and display deal offers and relevant product deals on the home page of ReserveMyRoom Web Application.

/EWAProject/DealMatches.txt
/EWAProject/EWAProjectHotelDeals.ipynb
/EWAProject/WEB-INF/classes/Controller/DealMatches.java

Auto-Complete Feature:
----------------------
autoCompleteJS.js javascript file along with AjaxUtility.java servlet have been used to implement the auto complete feature in this system.
File Locations:
/EWAProject/autoCompleteJS.js
/EWAProject/WEB-INF/classes/Controller/AjaxUtility.java

ChoroPleth Maps Implementation:
-------------------------------
Below mentioned javascript files and servlets have been used to implement the choropleth maps in this system under Data Exploration tab of Store Manager role.

/EWAProject/choroPlethAvgPrices.js
/EWAProject/choroPlethTotalReviews.js
/EWAProject/choroPlethTotalSales.js
/EWAProject/choroPlethTotPrices.js
/EWAProject/choroPlethTotRev5.js

/csj/WEB-INF/classes/Controller/DataExplorationUtility.java
/csj/WEB-INF/classes/Controller/DataExplorationServlet.java

All class files within this project has been divided across 3 vital tiers.
Tier-1 : Controller
-------------------
All classes essential for displaying HTML UI of the web application are within this tier.
Controller directory is as follows.
Directory : /EWAProject/WEB-INF/classes/Controller/

Tier-2 : DataAccess
-------------------
All classes essential for writing/reading data to or from the database are included within this tier.
Directory: /EWAProject/WEB-INF/classes/DataAccess/

Tier-3 : JavaBeans
-------------------
All JavaBeans essential for storing and retrieving data objects are located within this tier.
Directory: /EWAProject/WEB-INF/classes/JavaBeans/

Other Files:
------------
The ReserveMyRoom web application uses *.json files within /EWAProject/cities directory to retrieve and store City Names, State Names and zip codes from all over USA. 
Overall there are approximately 29,000 city names within this system which are stored into the database during application startup by using a separate thread.
One of the special features of this web application is its ability to auto complete and search from over 29,000 city names.

All necessary css and javascript files are located within /EWAProject/css and /EWAProject/js directories respectively.
This web application is configured to use css/javascript and HotelCatalog.xml files from below
application directories. The application may get errors if any of these files are missing.

CSS Directory: /EWAProject/css
Javascript Directory: /EWAProject/js
Images Directory: /EWAProject/images
Hotels Directory: /EWAProject/Hotels
Fonts Directory: /EWAProject/fonts
Cities Directory: /EWAProject/cities
ProductCatalog Directory: /EWAProject/WEB-INF/classes/HotelCatalog.xml

-------------
Database:
-------------
Please edit the below mentioned variables within /EWAProject/WEB-INF/classes/DataAccess/MySQLDataStoreUtilities.java in order to connect to the database.

String conString = "jdbc:mysql://localhost:1111/ReserveMyRoomDB?useSSL=false";
String username = "";
String pwd = "";

A customer account with below mentioned credentials will be created automatically to insert sample orders into the database.
	username - user1
	pwd - user1

All DDL statements used within this application are provided in "DDL Statements.txt".
All 4 necessary libraries are included within /EWAProject/WEB-INF/lib directory.

The application is designed to detect and create database and tables if they do not exist within the mysql database.
Path must be set for the below mentioned jar files in order for the application to run properly.

gson-2.8.2.jar
mongo-java-driver-3.2.2.jar
mysql-connector-java-5.1.39-bin.jar

----------------
Compilation:
----------------
Browse to the directory - /EWAProject/WEB-INF/classes/ and use the following command to compile any of the class files included within this application.
Example: 
/EWAProject/WEB-INF/classes/>javac Controller/HomeServlet.java

------------------------------
Steps to Run the application:
------------------------------
Step-1: Copy EWAProject directory into webapps directory of tomcat server
Step-2: Set username and pwd variables of MySQLDataStoreUtilities.java file and compile it to create a class file.
Step-3: Start MySQL Server
Step-4: Start MongoDB Server
Step-5: Browse http://localhost/EWAProject in a browser to access the application.


--xx--End of ReadMe File--xx--