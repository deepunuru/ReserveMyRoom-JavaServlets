package JavaBeans;

import java.io.Serializable;
import java.util.HashMap;

public class Hotels implements Serializable{
	
	private String id;
	private String retailer;
	private String name;
	private boolean wifi;
	private boolean restaurant;
	private int zipCode;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String phoneNumber;
	private HashMap<String, Rooms> rooms = new HashMap<String, Rooms>();
	private HashMap<String, String> hotelImages = new HashMap<String, String>();
	private boolean onSale;
	private boolean rebate;
	
	public Hotels() {
		
	}
	
	public Hotels(String id,String retailer,String name,boolean wifi,boolean restaurant,int zipCode,String address1,String address2,String city,String state,String phoneNumber,HashMap<String, Rooms> rooms,HashMap<String, String> hotelImages) {
		this.id = id;
		this.retailer = retailer;
		this.name = name;
		this.wifi = wifi;
		this.restaurant = restaurant;
		this.zipCode = zipCode;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.rooms = rooms;
		this.hotelImages = hotelImages;
	}
	
	public Hotels(String id,String retailer,String name,boolean wifi,boolean restaurant,int zipCode,String address1,String address2,String city,String state,String phoneNumber,HashMap<String, Rooms> rooms,HashMap<String, String> hotelImages,boolean onSale,boolean rebate) {
		this.id = id;
		this.retailer = retailer;
		this.name = name;
		this.wifi = wifi;
		this.restaurant = restaurant;
		this.zipCode = zipCode;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.rooms = rooms;
		this.hotelImages = hotelImages;
		this.onSale = onSale;
		this.rebate = rebate;
	}
	
	public String getID() {
		return this.id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getRetailer() {
		return this.retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getWifi() {
		return this.wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public boolean getRestaurant() {
		return this.restaurant;
	}
	public void setRestaurant(boolean restaurant) {
		this.restaurant = restaurant;
	}
	public int getZipCode() {
		return this.zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress1() {
		return this.address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return this.address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public HashMap<String, Rooms> getRooms(){
		return this.rooms;
	}
	public void setRooms(HashMap<String, Rooms> rooms) {
		this.rooms = rooms;
	}
	public HashMap<String, String> getHotelImages(){
		return this.hotelImages;
	}
	public void setHotelImages(HashMap<String, String> hotelImages) {
		this.hotelImages = hotelImages;
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
}