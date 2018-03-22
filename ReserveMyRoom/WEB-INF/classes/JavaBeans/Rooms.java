package JavaBeans;

import java.io.Serializable;
import java.util.HashMap;

public class Rooms implements Serializable{
	
	private String roomName;
	private int roomCount;
	private double roomPrice;
	private HashMap<String, String> roomImages = new HashMap<String, String>();
	
	public Rooms(String roomName,int roomCount,double roomPrice,HashMap<String, String> roomImages) {
		this.roomName = roomName;
		this.roomCount = roomCount;
		this.roomPrice = roomPrice;
		this.roomImages = roomImages;
	}
	
	public Rooms() {
		
	}
	
	public String getRoomName() {
		return this.roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getRoomCount() {
		return this.roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	public double getRoomPrice() {
		return this.roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public HashMap<String, String> getRoomImages(){
		return this.roomImages;
	}
	public void setRoomImages(HashMap<String, String> roomImages) {
		this.roomImages = roomImages;
	}
}