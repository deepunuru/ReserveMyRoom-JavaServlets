package JavaBeans;

import java.io.Serializable;

public class Review implements Serializable {
	
	private String hotelName;	
	private double roomPrice;
	private String retailer;
	private int zipCode;
	private String city;
	private String state;
	private boolean onSale;
	private boolean rebate;
	private String username;
	private int age;
	private String gender;
	private String occupation;
	private int reviewRating;
	private String reviewDate;
	private String reviewText;
	
	public Review() {
		
	}
	
	public Review(String hotelName,double roomPrice,String retailer,int zipCode,String city,String state,boolean onSale,boolean rebate,String username,int age,String gender,String occupation,int reviewRating,String reviewDate,String reviewText) {
		this.hotelName = hotelName;
		this.roomPrice = roomPrice;
		this.retailer = retailer;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.onSale = onSale;
		this.rebate = rebate;
		this.username = username;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}
	
	public String getHotelName() {
		return this.hotelName;
	}
	
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public double getRoomPrice() {
		return this.roomPrice;
	}
	
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	public String getRetailer() {
		return this.retailer;
	}
	
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	
	public int getZipCode() {
		return this.zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean getOnSale() {
		return this.onSale;
	}
	
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}
	
	public boolean getRebate() {
		return this.rebate;
	}
	
	public void setRebate(boolean rebate) {
		this.rebate = rebate;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getOccupation() {
		return this.occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public int getReviewRating() {
		return this.reviewRating;
	}
	
	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	
	public String getReviewDate() {
		return this.reviewDate;
	}
	
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public String getReviewText() {
		return this.reviewText;
	}
	
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}