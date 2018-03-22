package DataAccess;

import com.mongodb.*;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.model.*;
import com.mongodb.client.*;

import java.io.*;
import java.util.*;
import java.text.*;
import java.net.*;
import JavaBeans.*;
import org.bson.*;

public class MongoDBDataStoreUtilities{
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document>  reviewCol;
	
	public MongoDBDataStoreUtilities() {

	}
	
	public void connectMongoDB(){
		try {
			mongoClient = new MongoClient( "localhost" , 11111 );
			database = mongoClient.getDatabase("HotelDB");
			reviewCol = database.getCollection("Reviews");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public MongoCollection<Document> getCollection(){
		try {
			mongoClient = new MongoClient( "localhost" , 11111 );
			database = mongoClient.getDatabase("HotelDB");
			reviewCol = database.getCollection("Reviews");			
			return reviewCol;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void disconnectMongoDB(){
		try {
			mongoClient.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Review> getMostLikedHotels(){
		try {
			connectMongoDB();
			if(reviewCol.count()>0){
				MongoCursor<Document> cursor = reviewCol.find().sort(Filters.eq("reviewRating", -1)).limit(5).iterator();
				List<Review> reviewsList = new Vector<Review>();
				Review rev = new Review();
				try {
					while(cursor.hasNext()) {
						Document doc = cursor.next();
						rev = new Review(doc.getString("hotelName"),doc.getDouble("roomPrice"),doc.getString("retailer"),doc.getInteger("zipCode"),doc.getString("city"),doc.getString("state"),doc.getBoolean("onSale"),doc.getBoolean("rebate"),doc.getString("username"),doc.getInteger("age"),doc.getString("gender"),doc.getString("occupation"),doc.getInteger("reviewRating"),doc.getString("reviewDate"),doc.getString("reviewText"));
						reviewsList.add(rev);
					}
				}
				finally {
					cursor.close();
				}
				disconnectMongoDB();
				return reviewsList;
			}
			else {
				disconnectMongoDB();
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int[][] getMaxZipCodes(){
		try {
			connectMongoDB();
			int[][] res = new int[10][2];
			int i=0;
			if(reviewCol.count()>0){
				AggregateIterable<Document> iterable = reviewCol.aggregate(Arrays.asList(Aggregates.group("$zipCode", Accumulators.sum("RoomsSold", 1)),Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.descending("RoomsSold"))),Aggregates.limit(5)));
				for(Document row : iterable) {
					res[i][0] = row.getInteger("_id");
					res[i][1] = row.getInteger("RoomsSold");
					i++;
				}
				disconnectMongoDB();
				return res;
			}
			else {
				disconnectMongoDB();
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public HashMap<String, String[][]> getTrendingReviews(){
		try {			
			HashMap<String, String[][]> trendingMap = null;
			TrendingReviews obj = new TrendingReviews();
			String[][] result = new String[3][2];
			ArrayList<String> cityList = new ArrayList<String>();
			String city = "";
			connectMongoDB();
			if(reviewCol.count()>0){
				trendingMap = new HashMap<String, String[][]>();
				AggregateIterable<Document> iterable = reviewCol.aggregate(Arrays.asList(Aggregates.group("$city", Accumulators.sum("CityCount", 1)),Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.ascending("RoomsSold"))),Aggregates.limit(5)));
				for(Document row : iterable) {
					cityList.add(row.getString("_id"));
					//System.out.println("City-" + row.getString("_id"));
				}
				Iterator iter = cityList.iterator();
				
				while (iter.hasNext()) {
					city = (String)iter.next();
					ArrayList<String> hotelNames = new ArrayList<String>();
					MongoCursor<Document> cursor = reviewCol.find(Filters.eq("city",city)).sort(Filters.eq("reviewRating", -1)).limit(3).iterator();
					int i=0;
					result = new String[3][2];
					while (cursor.hasNext()) {
						Document doc = cursor.next();
						hotelNames.add(doc.getString("hotelName"));
						//System.out.println("HotelName-" + doc.getString("hotelName"));
						
						result[i][0] = doc.getString("hotelName");
						result[i][1] = String.valueOf(reviewCol.count(Filters.eq("hotelName",doc.getString("hotelName"))));
						i++;
					}
					/*
					obj = new TrendingReviews();
					obj.setHotelName1(hotelNames.get(0));
					obj.setHotelName2(hotelNames.get(1));
					obj.setHotelName3(hotelNames.get(2));
					
					obj.setReviewCount1(reviewCol.count(Filters.eq("hotelName",hotelNames.get(0))));
					obj.setReviewCount2(reviewCol.count(Filters.eq("hotelName",hotelNames.get(1))));
					obj.setReviewCount3(reviewCol.count(Filters.eq("hotelName",hotelNames.get(2))));*/
					
					trendingMap.put(city, result);
					
					//MongoCursor<Document> cursor1 = reviewCol.find(Filters.eq("hotelName",hotelNames.get(0))).iterator();
					
					//long count = reviewCol.count(Filters.eq("hotelName",hotelNames.get(0)));
					//System.out.println("HotelName-" + hotelNames.get(0) + "ReviewCount-" + count);
					
				}
			}
			return trendingMap;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}	
	}
	
	public String[][] getMostSoldHotels(){
		try {
			connectMongoDB();
			String[][] res = new String[10][2];
			int i=0;
			if(reviewCol.count()>0){
				AggregateIterable<Document> iterable = reviewCol.aggregate(Arrays.asList(Aggregates.group("$hotelName", Accumulators.sum("RoomsSold", 1)),Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.descending("RoomsSold"))),Aggregates.limit(5)));
				for(Document row : iterable) {
					res[i][0] = row.getString("_id");
					res[i][1] = String.valueOf(row.getInteger("RoomsSold"));
					i++;
				}
				disconnectMongoDB();
				return res;
			}
			else {
				disconnectMongoDB();
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public HashMap<String, String> checkMongoDB(){
		HashMap<String, String> dbStatus = new HashMap<String, String>();
		try {
			Builder builder = MongoClientOptions.builder().connectTimeout(300);  
			builder.connectTimeout(300);		    
		    builder.socketTimeout(300);		    
		    builder.serverSelectionTimeout(300);
			mongoClient = new MongoClient(new ServerAddress("localhost", 27017), builder.build());
			try {
				mongoClient.getAddress();
			}
			catch(Exception ex) {
				dbStatus.put("status","false");
		        dbStatus.put("msg", "MongoDB server is not up and running");	
		        mongoClient.close();
				return dbStatus;
			}
			mongoClient.close();
			dbStatus.put("status","true");
	        dbStatus.put("msg", "Connected");
	        return dbStatus;
		}
		catch(com.mongodb.MongoSocketOpenException ex) {
			dbStatus.put("status","false");
	        dbStatus.put("msg", "MongoDB server is not up and running");	
	        mongoClient.close();
			return dbStatus;
		}
		catch(MongoException ex) {
			dbStatus.put("status","false");
	        dbStatus.put("msg", "MongoDB server is not up and running");		
	        mongoClient.close();
			return dbStatus;
		}
		catch(Exception ex) {
			dbStatus.put("status","false");
	        dbStatus.put("msg", ex.getMessage());	
	        mongoClient.close();
			return dbStatus;
		}
	}
	
	public HashMap<String, Review> getReviews(String hotelName){
		try {
			connectMongoDB();
			if(reviewCol.count()>0){
				MongoCursor<Document> cursor = reviewCol.find(Filters.eq("hotelName",hotelName)).iterator();
				HashMap<String, Review> reviewsMap = new HashMap<String, Review>();
				Review rev = new Review();
				DBObject resultElement = null;
				try {
					int count=0;
					while (cursor.hasNext()) {
						Document doc = cursor.next();
						rev = new Review(doc.getString("hotelName"),doc.getDouble("roomPrice"),doc.getString("retailer"),doc.getInteger("zipCode"),doc.getString("city"),doc.getString("state"),doc.getBoolean("onSale"),doc.getBoolean("rebate"),doc.getString("username"),doc.getInteger("age"),doc.getString("gender"),doc.getString("occupation"),doc.getInteger("reviewRating"),doc.getString("reviewDate"),doc.getString("reviewText"));
						reviewsMap.put(doc.getString("hotelName") + String.valueOf(count), rev);
						count++;
					}
				}
				finally {
					cursor.close();
				}
				disconnectMongoDB();
				return reviewsMap;
			}
			else {
				disconnectMongoDB();
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean writeReview(Review review) {
		try {
			connectMongoDB();
			//database.createColMongoCollection<TDocument>sCollection");
			Document doc = new Document("hotelName", review.getHotelName())
	                .append("roomPrice", review.getRoomPrice())
	                .append("retailer", review.getRetailer())
	                .append("zipCode", review.getZipCode())
	                .append("city", review.getCity())
	                .append("state", review.getState())
	                .append("onSale", review.getOnSale())
	                .append("rebate", review.getRebate())
	                .append("username", review.getUsername())
	                .append("age", review.getAge())
	                .append("gender", review.getGender())
	                .append("occupation", review.getOccupation())
	                .append("reviewRating", review.getReviewRating())
	                .append("reviewDate", review.getReviewDate())
	                .append("reviewText", review.getReviewText());
			reviewCol.insertOne(doc);
			disconnectMongoDB();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public void loadSampleReviews() {
		try {
			connectMongoDB();
			if(reviewCol.count()<=1600) {
				MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
				Hotels hotel = new Hotels();
				ArrayList<Cities> cityList = dbObj.getCityDetails();
				HashMap<String, Rooms> roomsMap = new HashMap<String, Rooms>(); 
				Cities city = new Cities();
				Rooms room = new Rooms();
				Random rand = new Random();
				
				int minCount = 4;
				int maxCount = 18;
				int randIndex = rand.nextInt((maxCount - minCount) + 1) + minCount;
				
				int count=0;
				/*for(int i=0;i<randIndex;i++) {
					
				}*/
					
				for(Map.Entry<String, Hotels> w : dbObj.getHotelsMap().entrySet()) {
					count = 0;
					for(int i=0;i<randIndex;i++) {
						hotel = w.getValue();
						city = cityList.get(count);
						roomsMap = hotel.getRooms();
						room = roomsMap.entrySet().iterator().next().getValue();
						
						
						int userIndex = rand.nextInt(100);
						
						int minAge = 12;
						int maxAge = 80;
						int age = rand.nextInt((maxAge - minAge) + 1) + minAge;
						
						String[] genders = {"Male","Female"};
				        List<String> gendersList = Arrays.asList(genders);
				        int genderIndex = rand.nextInt(gendersList.size());
				        String gender = gendersList.get(genderIndex);
						
				        String[] occupations = {"Financial Adviser","Therapist","Dental Hygienist","Lawyer","Software Developer","Web Developer","Mechanical Engineer","Pharmacist","Dentist","Physician","Civil Engineer","DBA","Accountant"};
				        List<String> OccupationList = Arrays.asList(occupations);
				        int occupationIndex = rand.nextInt(OccupationList.size());
				        String occupation = OccupationList.get(occupationIndex);
				        
				        int minRating = 1;
				        int maxRating = 5;
				        int randomRating = rand.nextInt((maxRating - minRating) + 1) + minRating;
				        
				        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				        Date curdate = new Date();
				        
						Document doc = new Document("hotelName", hotel.getName())
				                .append("roomPrice", room.getRoomPrice())
				                .append("retailer", hotel.getRetailer())
				                .append("zipCode", city.getZipCode())
				                .append("city", city.getCityName())
				                .append("state", city.getStateName())
				                .append("onSale", rand.nextBoolean())
				                .append("rebate", rand.nextBoolean())
				                .append("username", "user" + String.valueOf(userIndex))
				                .append("age", age)
				                .append("gender", gender)
				                .append("occupation", occupation)
				                .append("reviewRating", randomRating)
				                .append("reviewDate", dateFormat.format(curdate))
				                .append("reviewText", hotel.getName() + " is a great Hotel. Excellent Hospitality. Very nice ambience.");
						reviewCol.insertOne(doc);
						count++;
					}
				}
			}
			disconnectMongoDB();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}