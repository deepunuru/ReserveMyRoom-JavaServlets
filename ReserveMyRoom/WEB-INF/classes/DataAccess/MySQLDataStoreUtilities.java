package DataAccess;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import JavaBeans.*;

public class MySQLDataStoreUtilities {
	String conString = "jdbc:mysql://localhost:1111/ReserveMyRoomDB?useSSL=false";
	String dbUser = "";
	String dbPwd = "";
	
	public MySQLDataStoreUtilities() {
		
	}
	
	public void createDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:1111/?useSSL=false", dbUser, dbPwd); 
			Statement s = conn.createStatement();
			s.executeUpdate("CREATE DATABASE IF NOT EXISTS ReserveMyRoomDB;");
			conn.close();
			
			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ReserveMyRoomDB?useSSL=false", dbUser, dbPwd);
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS users(userType varchar(255) NOT NULL,username varchar(255) NOT NULL,firstName varchar(255) DEFAULT NULL,lastName varchar(255) DEFAULT NULL,address varchar(255) DEFAULT NULL,zipcode int(11) DEFAULT NULL,emailID varchar(255) DEFAULT NULL,dob varchar(20) DEFAULT NULL,password varchar(255) NOT NULL,PRIMARY KEY(username));");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS hotels(hotelID varchar(255) NOT NULL,retailer varchar(255) NOT NULL,hotelName varchar(255) NOT NULL,wifi TINYINT(1) NOT NULL,restaurant TINYINT(1) NOT NULL,zipcode int(11) NOT NULL,address1 varchar(255) NOT NULL,address2 varchar(255) DEFAULT NULL,city varchar(255) NOT NULL,state varchar(255) NOT NULL,phoneNumber varchar(255) DEFAULT NULL,onSale TINYINT(1) NOT NULL,rebate TINYINT(1) NOT NULL,PRIMARY KEY(hotelID));");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS rooms(hotelID varchar(255) NOT NULL,roomName varchar(255) NOT NULL,roomCount int(11) NOT NULL,roomPrice double NOT NULL);");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS roomimages(hotelID varchar(255) NOT NULL,roomName varchar(255) NOT NULL,image varchar(255) NOT NULL);");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS hotelimages(hotelID varchar(255) NOT NULL,image varchar(255) NOT NULL);");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS reservations(customerName varchar(255) DEFAULT NULL,username varchar(255) NOT NULL,reservationDate date NOT NULL,reservationID int(20) NOT NULL,totalPrice double(20,2) NOT NULL,state varchar(255) DEFAULT NULL,address1 varchar(255) DEFAULT NULL,address2 varchar(255) DEFAULT NULL,city varchar(255) DEFAULT NULL, zipCode int(11) DEFAULT NULL,mobileNumber varchar(255) DEFAULT NULL,emailAddress varchar(255) DEFAULT NULL,PRIMARY KEY(reservationID));");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS reservationhotelslist(reservationID int(20) NOT NULL,hotelID varchar(255) NOT NULL,roomName varchar(255) NOT NULL,checkInDate date NOT NULL,checkOutDate date NOT NULL,guestCount int(11) NOT NULL);");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS cities(cityName varchar(255) NOT NULL,stateID varchar(255) NOT NULL,stateName varchar(255) NOT NULL,zipCode int(11) NOT NULL,PRIMARY KEY(cityName,zipCode));");
			
			s = conn1.createStatement();
			s.executeUpdate("CREATE OR REPLACE VIEW inventory as select hotels.hotelID,hotelName,roomName,roomPrice,roomCount,onSale,rebate from hotels,rooms where hotels.hotelID = rooms.hotelID;");
			
			conn1.close();
		}
		catch(Exception ex) {
			System.out.println("Message" + ex.getMessage());
		}
	}
	
	public HashMap<String,String> checkDBConnection() {
		HashMap<String, String> dbStatus = new HashMap<String, String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
	         // Open a connection
	         Connection conn = DriverManager.getConnection(conString, dbUser, dbPwd);
	         dbStatus.put("status","true");
	         dbStatus.put("msg", "Connected");
			return dbStatus;
		}
		catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException ex) {
			dbStatus.put("status","false");
			String msg = "MySQL server is not up and running";
			dbStatus.put("msg", msg);
			return dbStatus;
		}
		catch(Exception ex) {
			System.out.println("Message - " + ex.getMessage());
			dbStatus.put("status","false");
	        dbStatus.put("msg", ex.getMessage());			
			return dbStatus;
		}
	}
	
	public String[][] getTotalHotelSales(){
		try {
			String[][] totalSalesArray = new String[50][2];
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query="select state,sum(1) as TotalHotelSales from reservations group by state;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int rowCount=0;
			while(rs.next())
			{
				totalSalesArray[rowCount][0] = rs.getString("state");
				totalSalesArray[rowCount][1] = String.valueOf(rs.getInt("TotalHotelSales"));
				rowCount++;
			}
			con.close();
			return totalSalesArray;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public String[][] getAverageHotelsReservedPrices(){
		try {
			String[][] averageSalesArray = new String[100][2];
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd);
			String query = "select state,avg(totalPrice) as AverageHotelPrices from reservations group by state;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int rowCount=0;
			while(rs.next()) {
				averageSalesArray[rowCount][0] = rs.getString("state");
				averageSalesArray[rowCount][1] = String.valueOf(rs.getDouble("AverageHotelPrices"));
				rowCount++;
			}
			con.close();
			return averageSalesArray;	
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public String[][] getTotalHotelReservedPrices(){
		try {
			String[][] totalSalesArray = new String[50][2];
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select state,sum(totalPrice) as TotalHotelPrices from reservations group by state;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int rowCount=0;
			while(rs.next()) {
				totalSalesArray[rowCount][0] = rs.getString("state");
				totalSalesArray[rowCount][1] = String.valueOf(rs.getDouble("TotalHotelPrices"));				
				rowCount++;
			}
			con.close();
			return totalSalesArray;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public boolean checkCityTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "SELECT COUNT(*) as CityRowCount FROM cities;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			boolean status = false;
			if(rs.next()) {
				if(rs.getInt("CityRowCount")>0) {
					status = true;
				}
			}
			else {
				status = false;
			}
			con.close();
			return status;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return false;
		}
	}
	
	
	public void loadCities(HashMap<String, Cities> cityList) {
		Cities city = new Cities();
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "INSERT INTO cities(cityName,stateID,stateName,zipCode) VALUES(?,?,?,?);";
			PreparedStatement pst = null;
			
			for(Map.Entry<String, Cities> c : cityList.entrySet()) {
				city = c.getValue();
				pst = con.prepareStatement(query);
				pst.setString(1, city.getCityName());
				pst.setString(2, city.getStateID());
				pst.setString(3, city.getStateName());
				pst.setInt(4, city.getZipCode());
				pst.execute();
			}
			con.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			System.out.println("CityName - " + city.getCityName() + "StateName - " + city.getStateName() + "ZipCode - " + city.getZipCode());
		}
	}
	
	public ArrayList<Cities> getCityDetails(){
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			ArrayList<Cities> cityList = new ArrayList<Cities>();
			Cities city = new Cities();
			String query = "SELECT cityName,stateName,zipCode FROM cities ORDER BY RAND() LIMIT 50;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				city = new Cities();
				city.setCityName(rs.getString("cityName"));
				city.setStateName(rs.getString("stateName"));
				city.setZipCode(rs.getInt("zipCode"));
				cityList.add(city);
			}
			con.close();
			return cityList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Cities> getCitiesList(String searchID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			ArrayList<Cities> cityList = new ArrayList<Cities>();
			Cities city = new Cities();
			String query = "SELECT * FROM cities WHERE LCASE(cityName) LIKE ? limit 30;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, searchID.toLowerCase() + "%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				city = new Cities(rs.getString("cityName"),rs.getString("stateID"),rs.getString("stateName"),rs.getInt("zipCode"));
				cityList.add(city);
			}
			con.close();
			return cityList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void insertSampleUser() {
		try {
			System.out.println("Inserting SampleUser");
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			//Checking if sample user already exists
			
			String query = "SELECT COUNT(*) FROM users WHERE username=? AND userType=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "user1");
			pst.setString(2, "Customer");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(1) > 0) {
					query = "UPDATE users SET firstName=?,lastName=?,address=?,zipcode=?,emailID=?,dob=?,password=? where username=?";
					pst = con.prepareStatement(query);
					pst.setString(1, "FirstName");
					pst.setString(2, "LastName");
					pst.setString(3, "Address");
					pst.setInt(4, 60616);
					pst.setString(5, "EmailID");
					pst.setString(6, "11/11/1111");
					pst.setString(7, "user1");
					pst.setString(8, "user1");
				}
				else {
					query = "INSERT INTO users(userType,username,firstName,lastName,address,zipcode,emailID,dob,password) VALUES(?,?,?,?,?,?,?,?,?);";
					pst = con.prepareStatement(query);
					pst.setString(1, "Customer");
					pst.setString(2, "user1");
					pst.setString(3, "FirstName");
					pst.setString(4, "LastName");
					pst.setString(5, "Address");
					pst.setInt(6, 60616);
					pst.setString(7, "EmailID");
					pst.setString(8, "11/11/1111");
					pst.setString(9, "user1");
				}
			}
			boolean result = pst.execute();
			con.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
		}
	}
	
	public Users getUserProfile(String username) {
		try {
			Users user = null;
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select * from users where username=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				user = new Users(rs.getString("userType"),rs.getString("username"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("address"),rs.getInt("zipcode"),rs.getString("emailID"),rs.getString("dob"),rs.getString("password"));
			}
			con.close();
			return user;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
		
	public boolean writeUserProfile(Users profile) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "INSERT INTO users(userType,username,firstName,lastName,address,zipcode,emailID,dob,password) VALUES(?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, profile.getUserType());
			pst.setString(2, profile.getUsername());
			pst.setString(3, profile.getFirstName());
			pst.setString(4, profile.getLastName());
			pst.setString(5, profile.getAddress());
			pst.setInt(6, profile.getZipCode());
			pst.setString(7, profile.getEmailID());
			pst.setString(8, profile.getDOB());
			pst.setString(9, profile.getPWD());
			boolean result = pst.execute();
			con.close();
			return result;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return false;
		}
	}
	
	public void populateDatabase(SaxParserDataStore datastoreObj) {
		try {
			System.out.println("Database Populating");
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd);
			
			//Clear hotels table
			String query = "DELETE FROM hotels;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.execute();
			//Clear rooms table
			query = "DELETE FROM rooms;";
			pst = con.prepareStatement(query);
			pst.execute();
			//Clear roomimages table
			query = "DELETE FROM roomimages;";
			pst = con.prepareStatement(query);
			pst.execute();
			//Clear hotelimages table
			query = "DELETE FROM hotelimages;";
			pst = con.prepareStatement(query);
			pst.execute();
			
			Hotels hotel = new Hotels();
			for(Map.Entry<String, Hotels> w : datastoreObj.hotelMap.entrySet()) {
				hotel = w.getValue();
				query = "INSERT INTO hotels(hotelID,retailer,hotelName,wifi,restaurant,zipcode,address1,address2,city,state,phoneNumber,onSale,rebate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
				pst = con.prepareStatement(query);
				pst.setString(1, hotel.getID());
				pst.setString(2, hotel.getRetailer());
				pst.setString(3, hotel.getName());
				pst.setBoolean(4, hotel.getWifi());
				pst.setBoolean(5, hotel.getRestaurant());
				pst.setInt(6, hotel.getZipCode());
				pst.setString(7, hotel.getAddress1());
				pst.setString(8, hotel.getAddress2());
				pst.setString(9, hotel.getCity());
				pst.setString(10, hotel.getState());
				pst.setString(11, hotel.getPhoneNumber());
				pst.setBoolean(12, hotel.getOnSale());
				pst.setBoolean(13, hotel.getRebate());
				pst.execute();
				
				//Loading hotelImages				
				for(Map.Entry<String, String> k : hotel.getHotelImages().entrySet()) {
					query = "INSERT INTO hotelimages(hotelID,image) VALUES(?,?);";
					pst = con.prepareStatement(query);
					pst.setString(1, hotel.getID());
					pst.setString(2, k.getValue());
					pst.execute();
				}
				
				//loading rooms
				Rooms room = new Rooms();
				HashMap<String, String> roomImages = new HashMap<String,String>();
				for(Map.Entry<String, Rooms> j : hotel.getRooms().entrySet()) {
					room = j.getValue();
					roomImages = room.getRoomImages();
					for(Map.Entry<String, String> l : roomImages.entrySet()) {
						query = "INSERT INTO roomimages(hotelID,roomName,image) VALUES(?,?,?);";
						pst = con.prepareStatement(query);
						pst.setString(1, hotel.getID());
						pst.setString(2, room.getRoomName());
						pst.setString(3, l.getValue());
						pst.execute();
					}
					query = "INSERT INTO rooms(hotelID,roomName,roomCount,roomPrice) VALUES(?,?,?,?);";
					pst = con.prepareStatement(query);
					pst.setString(1, hotel.getID());
					pst.setString(2, room.getRoomName());
					pst.setInt(3, room.getRoomCount());
					pst.setDouble(4, room.getRoomPrice());
					pst.execute();
				}
			}
			
			con.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
		}
	}
	
	public ArrayList<Inventory> getInventoryListDB(){
		try {
			Inventory inventory = new Inventory();
			ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String query = "select hotels.hotelID,hotelName,roomName,roomPrice,roomCount,onSale,rebate from hotels,rooms where hotels.hotelID = rooms.hotelID;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				inventory = new Inventory(rs.getString("hotelID"),rs.getString("hotelName"),rs.getString("roomName"),rs.getDouble("roomPrice"),rs.getInt("roomCount"),rs.getBoolean("onSale"),rs.getBoolean("rebate"));
				inventoryList.add(inventory);
			}
			con.close();
			return inventoryList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public ArrayList<Inventory> getOnSaleListDB(){
		try {
			Inventory inventory = new Inventory();
			ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String query = "select hotels.hotelID,hotelName,roomName,roomPrice,roomCount,onSale,rebate from hotels,rooms where hotels.hotelID = rooms.hotelID AND onSale=TRUE;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				inventory = new Inventory(rs.getString("hotelID"),rs.getString("hotelName"),rs.getString("roomName"),rs.getDouble("roomPrice"),rs.getInt("roomCount"),rs.getBoolean("onSale"),rs.getBoolean("rebate"));
				inventoryList.add(inventory);
			}
			con.close();
			return inventoryList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public ArrayList<Inventory> getRebateListDB(){
		try {
			Inventory inventory = new Inventory();
			ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String query = "select hotels.hotelID,hotelName,roomName,roomPrice,roomCount,onSale,rebate from hotels,rooms where hotels.hotelID = rooms.hotelID AND rebate=TRUE;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				inventory = new Inventory(rs.getString("hotelID"),rs.getString("hotelName"),rs.getString("roomName"),rs.getDouble("roomPrice"),rs.getInt("roomCount"),rs.getBoolean("onSale"),rs.getBoolean("rebate"));
				inventoryList.add(inventory);
			}
			con.close();
			return inventoryList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public ArrayList<Sales> getSalesListDB(){
		try {
			Sales sale = new Sales();
			ArrayList<Sales> salesList = new ArrayList<Sales>();
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String query = "select r.hotelID,i.hotelName,roomPrice,sum(1) as SalesCount,(roomPrice * sum(1)) as TotalSales from reservationhotelslist r,inventory i where r.hotelID = i.hotelID and r.roomName = i.roomName group by r.hotelID;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				sale = new Sales(rs.getString("hotelID"),rs.getString("hotelName"),null,rs.getDouble("roomPrice"),rs.getInt("SalesCount"),rs.getDouble("TotalSales"),null);
				salesList.add(sale);
			}
			con.close();
			return salesList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public ArrayList<Sales> getDailySalesList(){
		try {
			Sales sale = new Sales();
			ArrayList<Sales> salesList = new ArrayList<Sales>();
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd);
			
			String query = "select reservationDate,sum(totalPrice) as TotalSales from reservations group by reservationDate;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				sale = new Sales();
				sale.setReservationDate(rs.getDate("reservationDate"));
				sale.setTotalSales(rs.getDouble("TotalSales"));
				salesList.add(sale);
			}
			con.close();
			return salesList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public boolean addHotel(Hotels hotel) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd);
			String query = "INSERT INTO hotels(hotelID,retailer,hotelName,wifi,restaurant,zipcode,address1,address2,city,state,phoneNumber,onSale,rebate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, hotel.getID());
			pst.setString(2, hotel.getRetailer());
			pst.setString(3, hotel.getName());
			pst.setBoolean(4, hotel.getWifi());
			pst.setBoolean(5, hotel.getRestaurant());
			pst.setInt(6, hotel.getZipCode());
			pst.setString(7, hotel.getAddress1());
			pst.setString(8, hotel.getAddress2());
			pst.setString(9, hotel.getCity());
			pst.setString(10, hotel.getState());
			pst.setString(11, hotel.getPhoneNumber());
			pst.setBoolean(12, hotel.getOnSale());
			pst.setBoolean(13, hotel.getRebate());
			pst.execute();
			
			//Loading hotelImages				
			for(Map.Entry<String, String> k : hotel.getHotelImages().entrySet()) {
				query = "INSERT INTO hotelimages(hotelID,image) VALUES(?,?);";
				pst = con.prepareStatement(query);
				pst.setString(1, hotel.getID());
				pst.setString(2, k.getValue());
				pst.execute();
			}
			
			//loading rooms
			Rooms room = new Rooms();
			HashMap<String, String> roomImages = new HashMap<String,String>();
			for(Map.Entry<String, Rooms> j : hotel.getRooms().entrySet()) {
				room = j.getValue();
				roomImages = room.getRoomImages();
				for(Map.Entry<String, String> l : roomImages.entrySet()) {
					query = "INSERT INTO roomimages(hotelID,roomName,image) VALUES(?,?,?);";
					pst = con.prepareStatement(query);
					pst.setString(1, hotel.getID());
					pst.setString(2, room.getRoomName());
					pst.setString(3, l.getValue());
					pst.execute();
				}
				query = "INSERT INTO rooms(hotelID,roomName,roomCount,roomPrice) VALUES(?,?,?,?);";
				pst = con.prepareStatement(query);
				pst.setString(1, hotel.getID());
				pst.setString(2, room.getRoomName());
				pst.setInt(3, room.getRoomCount());
				pst.setDouble(4, room.getRoomPrice());
				pst.execute();
			}
			con.close();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean updateHotel(Hotels hotel) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd);
			String query = "UPDATE hotels SET retailer=?,hotelName=?,wifi=?,restaurant=?,zipcode=?,address1=?,address2=?,city=?,state=?,phoneNumber=?,onSale=?,rebate=? WHERE hotelID=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, hotel.getRetailer());
			pst.setString(2, hotel.getName());
			pst.setBoolean(3, hotel.getWifi());
			pst.setBoolean(4, hotel.getRestaurant());
			pst.setInt(5, hotel.getZipCode());
			pst.setString(6, hotel.getAddress1());
			pst.setString(7, hotel.getAddress2());
			pst.setString(8, hotel.getCity());
			pst.setString(9, hotel.getState());
			pst.setString(10, hotel.getPhoneNumber());
			
			pst.setBoolean(11, hotel.getOnSale());
			pst.setBoolean(12, hotel.getRebate());
			pst.setString(13, hotel.getID());
			pst.execute();
			
			/*System.out.println("Result - " + result);
			//Updating hotelImages				
			for(Map.Entry<String, String> k : hotel.getHotelImages().entrySet()) {
				query = "UPDATE hotelimages SET image=? WHERE hotelID=?;";
				pst = con.prepareStatement(query);
				pst.setString(1, k.getValue());
				pst.setString(2, hotel.getID());
				result = pst.execute();
				System.out.println("hotelImage - " + k.getValue());
			}
			
			//Updating rooms
			Rooms room = new Rooms();
			HashMap<String, String> roomImages = new HashMap<String,String>();
			for(Map.Entry<String, Rooms> j : hotel.getRooms().entrySet()) {
				room = j.getValue();
				roomImages = room.getRoomImages();
				for(Map.Entry<String, String> l : roomImages.entrySet()) {
					query = "UPDATE roomimages SET image=? WHERE hotelID=? AND roomName=?;";
					pst = con.prepareStatement(query);
					pst.setString(1, l.getValue());
					pst.setString(2, hotel.getID());
					pst.setString(3, room.getRoomName());
					result = pst.execute();
					System.out.println("roomImage - " + l.getValue());
				}
				query = "UPDATE rooms SET roomName=?,roomCount=?,roomPrice=? WHERE hotelID=?;";
				pst = con.prepareStatement(query);
				pst.setString(1, room.getRoomName());
				pst.setInt(2, room.getRoomCount());
				pst.setDouble(3, room.getRoomPrice());
				pst.setString(4, hotel.getID());
				result = pst.execute();
				System.out.println("RoomName - " + room.getRoomName());
			}*/
			con.close();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteHotel(String hotelID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd);
			String query = "DELETE FROM hotels WHERE hotelID=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, hotelID);
			pst.execute();
			
			query = "DELETE FROM hotelimages WHERE hotelID=?;";
			pst = con.prepareStatement(query);
			pst.setString(1, hotelID);
			pst.execute();
			
			query = "DELETE FROM rooms WHERE hotelID=?;";
			pst = con.prepareStatement(query);
			pst.setString(1, hotelID);
			pst.execute();
			
			query = "DELETE FROM roomimages WHERE hotelID=?;";
			pst = con.prepareStatement(query);
			pst.setString(1, hotelID);
			boolean res = pst.execute();
			
			con.close();
			return res;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public List<String> getRoomTypes() {
		try {
			List<String> roomList = new ArrayList<String>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select distinct roomName from rooms;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				roomList.add(rs.getString("roomName"));
			}
			con.close();
			return roomList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public List<String> getRoomTypes(String hotelID) {
		try {
			List<String> roomList = new ArrayList<String>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select roomName from rooms where hotelID=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, hotelID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				roomList.add(rs.getString("roomName"));
			}
			con.close();
			return roomList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public HashMap<String, Hotels> getHotelsMap(String city,String state){
		try {
			HashMap<String, Hotels> hotelsMap = new HashMap<String, Hotels>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select * from hotels where city=? and state=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, city);
			pst.setString(2, state);
			ResultSet rs = pst.executeQuery();
			Hotels hotel = new Hotels();
			while(rs.next()) {
				hotel.setID(rs.getString("hotelID"));
				hotel.setRetailer(rs.getString("retailer"));
				hotel.setName(rs.getString("hotelName"));
				hotel.setWifi(rs.getBoolean("wifi"));
				hotel.setRestaurant(rs.getBoolean("restaurant"));
				hotel.setZipCode(rs.getInt("zipCode"));
				hotel.setAddress1(rs.getString("address1"));
				hotel.setAddress2(rs.getString("address2"));
				hotel.setCity(rs.getString("city"));
				hotel.setState(rs.getString("state"));
				hotel.setPhoneNumber(rs.getString("phoneNumber"));
				hotel.setOnSale(rs.getBoolean("onSale"));
				hotel.setRebate(rs.getBoolean("rebate"));
				
				pst = con.prepareStatement("select * from hotelimages where hotelID=?;");
				pst.setString(1, hotel.getID());
				ResultSet rs1 = pst.executeQuery();
				HashMap<String, String> hotelImages = new HashMap<String, String>();
				int i=0;
				while(rs1.next()) {
					hotelImages.put("HotelImage-" + i, rs1.getString("image"));
					i++;
				}
				hotel.setHotelImages(hotelImages);
				
				pst = con.prepareStatement("select * from rooms where hotelID=?;");
				pst.setString(1, hotel.getID());
				rs1 = pst.executeQuery();
				HashMap<String, Rooms> rooms = new HashMap<String, Rooms>();
				Rooms room = new Rooms();
				while(rs1.next()) {
					room.setRoomName(rs1.getString("roomName"));
					room.setRoomCount(rs1.getInt("roomCount"));
					room.setRoomPrice(rs1.getDouble("roomPrice"));
					pst = con.prepareStatement("select * from roomimages where hotelID=? and roomName=?;");
					pst.setString(1, hotel.getID());
					pst.setString(2, room.getRoomName());
					ResultSet rs2 = pst.executeQuery();
					HashMap<String, String> roomImages = new HashMap<String, String>();
					int j=0;
					while(rs2.next()) {
						roomImages.put("RoomImage-" + j, rs2.getString("image"));
						j++;
					}
					room.setRoomImages(roomImages);
					rooms.put(room.getRoomName(), room);
					room = new Rooms();
				}
				hotel.setRooms(rooms);
				hotelsMap.put(hotel.getID(), hotel);
				hotel = new Hotels();
			}
			con.close();
			return hotelsMap;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public HashMap<String, Hotels> getHotelsMap(){
		try {
			HashMap<String, Hotels> hotelsMap = new HashMap<String, Hotels>();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select * from hotels;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			Hotels hotel = new Hotels();
			while(rs.next()) {
				hotel.setID(rs.getString("hotelID"));
				hotel.setRetailer(rs.getString("retailer"));
				hotel.setName(rs.getString("hotelName"));
				hotel.setWifi(rs.getBoolean("wifi"));
				hotel.setRestaurant(rs.getBoolean("restaurant"));
				hotel.setZipCode(rs.getInt("zipCode"));
				hotel.setAddress1(rs.getString("address1"));
				hotel.setAddress2(rs.getString("address2"));
				hotel.setCity(rs.getString("city"));
				hotel.setState(rs.getString("state"));
				hotel.setPhoneNumber(rs.getString("phoneNumber"));
				hotel.setOnSale(rs.getBoolean("onSale"));
				hotel.setRebate(rs.getBoolean("rebate"));
				
				pst = con.prepareStatement("select * from hotelimages where hotelID=?;");
				pst.setString(1, hotel.getID());
				ResultSet rs1 = pst.executeQuery();
				HashMap<String, String> hotelImages = new HashMap<String, String>();
				int i=0;
				while(rs1.next()) {
					hotelImages.put("HotelImage-" + i, rs1.getString("image"));
					i++;
				}
				hotel.setHotelImages(hotelImages);
				
				pst = con.prepareStatement("select * from rooms where hotelID=?;");
				pst.setString(1, hotel.getID());
				rs1 = pst.executeQuery();
				HashMap<String, Rooms> rooms = new HashMap<String, Rooms>();
				Rooms room = new Rooms();
				while(rs1.next()) {
					room.setRoomName(rs1.getString("roomName"));
					room.setRoomCount(rs1.getInt("roomCount"));
					room.setRoomPrice(rs1.getDouble("roomPrice"));
					pst = con.prepareStatement("select * from roomimages where hotelID=? and roomName=?;");
					pst.setString(1, hotel.getID());
					pst.setString(2, room.getRoomName());
					ResultSet rs2 = pst.executeQuery();
					HashMap<String, String> roomImages = new HashMap<String, String>();
					int j=0;
					while(rs2.next()) {
						roomImages.put("RoomImage-" + j, rs2.getString("image"));
						j++;
					}
					room.setRoomImages(roomImages);
					rooms.put(room.getRoomName(), room);
					room = new Rooms();
				}
				hotel.setRooms(rooms);
				hotelsMap.put(hotel.getID(), hotel);
				hotel = new Hotels();
			}
			con.close();
			return hotelsMap;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public Hotels getHotel(String id){
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select * from hotels where hotelID=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			Hotels hotel = new Hotels();
			if(rs.next()) {
				hotel.setID(rs.getString("hotelID"));
				hotel.setRetailer(rs.getString("retailer"));
				hotel.setName(rs.getString("hotelName"));
				hotel.setWifi(rs.getBoolean("wifi"));
				hotel.setWifi(rs.getBoolean("restaurant"));
				hotel.setZipCode(rs.getInt("zipCode"));
				hotel.setAddress1(rs.getString("address1"));
				hotel.setAddress2(rs.getString("address2"));
				hotel.setCity(rs.getString("city"));
				hotel.setState(rs.getString("state"));
				hotel.setPhoneNumber(rs.getString("phoneNumber"));
				hotel.setOnSale(rs.getBoolean("onSale"));
				hotel.setRebate(rs.getBoolean("rebate"));
				
				pst = con.prepareStatement("select * from hotelimages where hotelID=?;");
				pst.setString(1, id);
				ResultSet rs1 = pst.executeQuery();
				HashMap<String, String> hotelImages = new HashMap<String, String>();
				int i=0;
				while(rs1.next()) {
					hotelImages.put("HotelImage-" + i, rs1.getString("image"));
					i++;
				}
				hotel.setHotelImages(hotelImages);
				
				pst = con.prepareStatement("select * from rooms where hotelID=?;");
				pst.setString(1, id);
				rs1 = pst.executeQuery();
				HashMap<String, Rooms> rooms = new HashMap<String, Rooms>();
				Rooms room = new Rooms();
				while(rs1.next()) {
					room.setRoomName(rs1.getString("roomName"));
					room.setRoomCount(rs1.getInt("roomCount"));
					room.setRoomPrice(rs1.getDouble("roomPrice"));
					pst = con.prepareStatement("select * from roomimages where hotelID=? and roomName=?;");
					pst.setString(1, id);
					pst.setString(2, room.getRoomName());
					ResultSet rs2 = pst.executeQuery();
					HashMap<String, String> roomImages = new HashMap<String, String>();
					int j=0;
					while(rs2.next()) {
						roomImages.put("RoomImage-" + j, rs2.getString("image"));
						j++;
					}
					room.setRoomImages(roomImages);
					rooms.put(room.getRoomName(), room);
					room = new Rooms();
				}
				hotel.setRooms(rooms);
			}
			else {
				hotel = null;
			}
			con.close();
			return hotel;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}
	}
	
	public boolean writeReservation(Reservations reservation) {
		try {
			java.sql.Date reservationDate = new java.sql.Date(reservation.getReservationDate().getTime());
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String[] hotelIDs = reservation.getHotelIDs();
			String[] roomNames = reservation.getRoomNames();
			Date[] checkInDates = reservation.getCheckInDates();
			Date[] checkOutDates = reservation.getCheckOutDates();
			int[] guestCounts = reservation.getGuestCounts();
			int[] roomCounts = new int[100];
			boolean flag = true;
			int i=0;
			int count=0;
			if(hotelIDs!=null) {
				for(i=0;i<hotelIDs.length;i++) {
					if(hotelIDs[i]!=null) {
						String query = "SELECT roomCount FROM rooms WHERE hotelID=? AND roomName=?;";
						PreparedStatement pst = con.prepareStatement(query);					
						pst.setString(1, hotelIDs[i]);
						pst.setString(2, roomNames[i]);
						ResultSet rs = pst.executeQuery();
						if(rs.next()) {
							roomCounts[i] = rs.getInt("roomCount");
							System.out.println("RoomCount-" + String.valueOf(rs.getInt("roomCount")));
							count++;
						}
					}
				}
			}
			for(int j=0;j<count;j++) {
				if(roomCounts[j]<1) {
					flag = false;
				}
			}
			
			if(hotelIDs!=null) {
				String query = "INSERT INTO reservations(customerName,username,reservationDate,reservationID,totalPrice,state,address1,address2,city,zipCode,mobileNumber,emailAddress) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1,reservation.getCustomerName());
				pst.setString(2, reservation.getUsername());
				pst.setDate(3, reservationDate);
				pst.setInt(4, reservation.getReservationID());
				pst.setDouble(5, reservation.getTotalPrice());
				pst.setString(6, reservation.getState());
				pst.setString(7, reservation.getAddress1());
				pst.setString(8, reservation.getAddress2());
				pst.setString(9, reservation.getCity());
				pst.setInt(10, reservation.getZipCode());
				pst.setString(11, reservation.getMobileNumber());
				pst.setString(12, reservation.getEmailAddress());
				boolean result = pst.execute();
				
				for(i=0;i<hotelIDs.length;i++) {
					if(hotelIDs[i]!=null) {
						query = "INSERT INTO reservationhotelslist(reservationID,hotelID,roomName,checkInDate,checkOutDate,guestCount) VALUES(?,?,?,?,?,?);";
						pst = con.prepareStatement(query);
						pst.setInt(1, reservation.getReservationID());
						pst.setString(2, hotelIDs[i]);
						pst.setString(3, roomNames[i]);
						java.sql.Date checkInDate = new java.sql.Date(checkInDates[i].getTime());
						pst.setDate(4, checkInDate);
						java.sql.Date checkOutDate = new java.sql.Date(checkOutDates[i].getTime());
						pst.setDate(5, checkOutDate);
						pst.setInt(6, guestCounts[i]);
						result = pst.execute();
						
						query = "UPDATE rooms SET roomCount=? WHERE hotelID=? AND roomName=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, roomCounts[i]-1);
						pst.setString(2, hotelIDs[i]);
						pst.setString(3, roomNames[i]);
						pst.execute();
					}
				}
			}
			con.close();
			return flag;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return false;
		}
	}
	
	public boolean updateReservation(Reservations reservation) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "UPDATE reservations SET customerName=?,totalPrice=?,state=?,address1=?,address2=?,city=?,zipCode=?,mobileNumber=?,emailAddress=? WHERE reservationID=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,reservation.getCustomerName());
			pst.setDouble(2, reservation.getTotalPrice());
			pst.setString(3, reservation.getState());
			pst.setString(4, reservation.getAddress1());
			pst.setString(5, reservation.getAddress2());
			pst.setString(6, reservation.getCity());
			pst.setInt(7, reservation.getZipCode());
			pst.setString(8, reservation.getMobileNumber());
			pst.setString(9, reservation.getEmailAddress());
			pst.setInt(10, reservation.getReservationID());
			boolean result = pst.execute();
			
			String[] hotelIDs = reservation.getHotelIDs();
			String[] roomNames = reservation.getRoomNames();
			Date[] checkInDates = reservation.getCheckInDates();
			Date[] checkOutDates = reservation.getCheckOutDates();
			int[] guestCounts = reservation.getGuestCounts();
			
			for(int i=0;i<hotelIDs.length;i++) {
				if(hotelIDs[i]!=null) {
					query = "UPDATE reservationhotelslist SET checkInDate=?,checkOutDate=?,guestCount=? WHERE reservationID=? AND hotelID=? AND roomName=?;";
					pst = con.prepareStatement(query);
					
					java.sql.Date checkInDate = new java.sql.Date(checkInDates[i].getTime());
					pst.setDate(1, checkInDate);
					java.sql.Date checkOutDate = new java.sql.Date(checkOutDates[i].getTime());
					pst.setDate(2, checkOutDate);
					pst.setInt(3, guestCounts[i]);
					pst.setInt(4, reservation.getReservationID());
					pst.setString(5, hotelIDs[i]);
					pst.setString(6, roomNames[i]);
					result = pst.execute();
				}
			}
			con.close();
			return result;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return false;
		}
	}
	
	public HashMap<String, Reservations> getReservations(String username){
		try {
			HashMap<String, Reservations> reservationsMap = new HashMap<String, Reservations>();
			Reservations reservation = new Reservations();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select * from reservations where username=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String[] hotelIDs = new String[30];
				String[] roomNames = new String[30];
				Date[] checkInDates = new Date[30];
				Date[] checkOutDates = new Date[30];
				int[] guestCounts = new int[30];
				query = "SELECT * FROM reservationhotelslist WHERE reservationID=?";
				pst = con.prepareStatement(query);
				pst.setString(1, rs.getString("reservationID"));
				ResultSet rs1 = pst.executeQuery();
				int i=0;
				while(rs1.next()) {
					hotelIDs[i] = rs1.getString("hotelID");
					roomNames[i] = rs1.getString("roomName");
					checkInDates[i] = rs1.getDate("checkInDate");
					checkOutDates[i] = rs1.getDate("checkOutDate");
					guestCounts[i] = rs1.getInt("guestCount");
					i++;
				}
				reservation = new Reservations(rs.getString("customerName"),rs.getString("username"),rs.getDate("reservationDate"),checkInDates,checkOutDates,rs.getInt("reservationID"),hotelIDs,roomNames,guestCounts,rs.getDouble("totalPrice"),rs.getString("state"),rs.getString("address1"),rs.getString("address2"),rs.getString("city"),rs.getInt("zipCode"),rs.getString("mobileNumber"),rs.getString("emailAddress"));
				reservationsMap.put(String.valueOf(reservation.getReservationID()), reservation);
			}
			con.close();
			return reservationsMap;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}	
	}
	
	public HashMap<String, Reservations> getReservations(){
		try {
			HashMap<String, Reservations> reservationsMap = new HashMap<String, Reservations>();
			Reservations reservation = new Reservations();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			String query = "select * from reservations;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String[] hotelIDs = new String[30];
				String[] roomNames = new String[30];
				Date[] checkInDates = new Date[30];
				Date[] checkOutDates = new Date[30];
				int[] guestCounts = new int[30];
				query = "SELECT * FROM reservationhotelslist WHERE reservationID=?";
				pst = con.prepareStatement(query);
				pst.setString(1, rs.getString("reservationID"));
				ResultSet rs1 = pst.executeQuery();
				int i=0;
				while(rs1.next()) {
					hotelIDs[i] = rs1.getString("hotelID");
					roomNames[i] = rs1.getString("roomName");
					checkInDates[i] = rs1.getDate("checkInDate");
					checkOutDates[i] = rs1.getDate("checkOutDate");
					guestCounts[i] = rs1.getInt("guestCount");
					i++;
				}
				reservation = new Reservations(rs.getString("customerName"),rs.getString("username"),rs.getDate("reservationDate"),checkInDates,checkOutDates,rs.getInt("reservationID"),hotelIDs,roomNames,guestCounts,rs.getDouble("totalPrice"),rs.getString("state"),rs.getString("address1"),rs.getString("address2"),rs.getString("city"),rs.getInt("zipCode"),rs.getString("mobileNumber"),rs.getString("emailAddress"));
				reservationsMap.put(String.valueOf(reservation.getReservationID()), reservation);
			}
			con.close();
			return reservationsMap;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return null;
		}	
	}
	
	public boolean deleteReservation(int resID){
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String hotelID = null;
			String roomName = null;
			int roomCount = 0;
			
			String query = "SELECT hotelID,roomName from reservationhotelslist WHERE reservationID=?;";
			PreparedStatement pst = con.prepareStatement(query);					
			pst.setInt(1, resID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				hotelID = null;
				roomName = null;
				roomCount = 0;
				
				hotelID = rs.getString(1);
				roomName = rs.getString(2);
				pst = con.prepareStatement("select roomCount from rooms where hotelID=? AND roomName=?;");	
				pst.setString(1, hotelID);
				pst.setString(2, roomName);
				ResultSet rs1 = pst.executeQuery();
				if(rs1.next()) {
					roomCount = rs1.getInt(1);
				}
				roomCount = roomCount + 1;
				
				pst = con.prepareStatement("UPDATE rooms SET roomCount=? WHERE hotelID=? AND roomName=?;");
				pst.setInt(1, roomCount);
				pst.setString(2, hotelID);
				pst.setString(3, roomName);
				pst.execute();
			}
			
			query = "DELETE FROM reservations WHERE reservationID=?;";
			pst = con.prepareStatement(query);
			pst.setInt(1, resID);
			boolean result = pst.execute();
			
			query="DELETE FROM reservationhotelslist WHERE reservationID=?;";
			pst = con.prepareStatement(query);
			pst.setInt(1, resID);
			result = pst.execute();
			
			con.close();
			return result;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
			return false;
		}	
	}
	
	public void insertSampleReservations() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(conString,dbUser,dbPwd); 
			
			String query = "SELECT reservationID FROM reservations WHERE username='user1';";
			PreparedStatement pst = con.prepareStatement(query);					
			ResultSet rsDel = pst.executeQuery();
			while(rsDel.next()) {
				query = "DELETE FROM reservationhotelslist WHERE reservationID=?;";
				pst = con.prepareStatement(query);
				pst.setInt(1, rsDel.getInt("reservationID"));
				pst.execute();
			}
			query = "DELETE FROM reservations WHERE username='user1';";
			pst = con.prepareStatement(query);
			pst.execute();
			
			Hotels hotel = new Hotels();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			
			Random rand = new Random();
			
			ArrayList<Cities> cityList = getCityDetails();
			int minLimit = 0;
			int maxLimit = cityList.size()-1;
			int randomCityIndex = 0;
			for(int j=0;j<5;j++) {
				for(Map.Entry<String, Hotels> w : getHotelsMap().entrySet()) {	
					hotel = w.getValue();
					
					int  reservationID = rand.nextInt(999999999) + 99999999;
					
					int min = 1;
					int max = 4;
					int guestCount = rand.nextInt((max - min) + 1) + min;
					
					double totalPrice = 0.0;
					double tax = 5.60;
					totalPrice = totalPrice + tax;
					boolean flag = true;
					String[] roomNames = new String[30];
					int[] roomCounts = new int[30];
					double[] roomPrices = new double[30];
					
					randomCityIndex = rand.nextInt((maxLimit - minLimit) + 1) + minLimit;
					Cities city = cityList.get(randomCityIndex);
					
					int minDays = 5;
					int maxDays = 14;
					int noOfDays = rand.nextInt((maxDays - minDays) + 1) + minDays;
					
					int minIndex = -14;
					int maxIndex = -2;
					int reserveIndex = rand.nextInt((maxIndex - minIndex) + 1) + minIndex;
					
					Calendar dateCalender = Calendar.getInstance();
					dateCalender.setTime(date);
					dateCalender.add(Calendar.DAY_OF_MONTH, reserveIndex);
					String currentDate = formatter.format(dateCalender.getTime());
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);            
					calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
					Date checkOutDate = calendar.getTime();
					
					query = "select roomName,roomPrice,roomCount from inventory where hotelID=?";
					pst = con.prepareStatement(query);					
					pst.setString(1, hotel.getID());
					ResultSet rs = pst.executeQuery();
					int i=0;
					while(rs.next()) {
						roomCounts[i] = rs.getInt("roomCount");
						roomPrices[i] = rs.getDouble("roomPrice");
						totalPrice = totalPrice + roomPrices[i];
						roomNames[i] = rs.getString("roomName");
						if(roomCounts[i]<1) {
							flag = false;
						}
						i++;
					}
					
					if(flag) {
						query = "INSERT INTO reservations(customerName,username,reservationDate,reservationID,totalPrice,state,address1,address2,city,zipCode,mobileNumber,emailAddress) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
						pst = con.prepareStatement(query);
						pst.setString(1,"user1");
						pst.setString(2, "user1");
						java.sql.Date currentDateSQL = new java.sql.Date(formatter.parse(currentDate).getTime());
						pst.setDate(3, currentDateSQL);
						pst.setInt(4, reservationID);
						pst.setDouble(5, totalPrice);
						pst.setString(6, city.getStateName());
						pst.setString(7, "Address1");
						pst.setString(8, "Address2");
						pst.setString(9, city.getCityName());
						pst.setInt(10, city.getZipCode());
						int mobileNumber = rand.nextInt(999999999) + 99999999;
						pst.setString(11, String.valueOf(mobileNumber));
						pst.setString(12, "user1@gmail.com");
						boolean result = pst.execute();
						
						for(i=0;i<roomNames.length;i++) {
							if(roomNames[i]!=null) {
								query = "INSERT INTO reservationhotelslist(reservationID,hotelID,roomName,checkInDate,checkOutDate,guestCount) VALUES(?,?,?,?,?,?);";
								pst = con.prepareStatement(query);
								pst.setInt(1, reservationID);
								pst.setString(2, hotel.getID());
								pst.setString(3, roomNames[i]);
								java.sql.Date checkInDateSQL = new java.sql.Date(date.getTime());
								pst.setDate(4, checkInDateSQL);
								java.sql.Date checkOutDateSQL = new java.sql.Date(checkOutDate.getTime());
								pst.setDate(5, checkOutDateSQL);
								pst.setInt(6, guestCount);
								result = pst.execute();
								
								query = "UPDATE rooms SET roomCount=? WHERE hotelID=? AND roomName=?;";
								pst = con.prepareStatement(query);
								pst.setInt(1, roomCounts[i]-1);
								pst.setString(2, hotel.getID());
								pst.setString(3, roomNames[i]);
								pst.execute();
							}
						}
					}
				}
			}
			
			con.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Message - " + ex.getMessage());
		}	
	}
}