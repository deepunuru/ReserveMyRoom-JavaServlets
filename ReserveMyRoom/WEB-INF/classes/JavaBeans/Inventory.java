package JavaBeans;

import java.io.Serializable;

public class Inventory implements Serializable {
	
	private String hotelID;
	private String hotelName;
	private String roomName;
	private double roomPrice;
	private int roomCount;
	private boolean onSale;
	private boolean rebate;
	
	public Inventory() {
		
	}
	
	public Inventory(String hotelID,String hotelName,String roomName,double roomPrice,int roomCount,boolean onSale,boolean rebate) {
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.roomName = roomName;
		this.roomPrice = roomPrice;
		this.roomCount = roomCount;
		this.onSale = onSale;
		this.rebate = rebate;
	}
	
	public String getHotelID() {
		return this.hotelID;
	}
	
	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}
	
	public String getHotelName() {
		return this.hotelName;
	}
	
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getRoomName() {
		return this.roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public double getRoomPrice() {
		return this.roomPrice;
	}
	
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	public int getRoomCount() {
		return this.roomCount;
	}
	
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
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