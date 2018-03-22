package JavaBeans;

import java.io.Serializable;
import java.util.Date;

public class Sales implements Serializable {
	private String hotelID;
	private String hotelName;
	private String roomName;
	private double roomPrice;
	private int salesCount;
	private double totalSales;
	private Date reservationDate;
	
	public Sales() {
		
	}
	
	public Sales(String hotelID,String hotelName,String roomName,double roomPrice,int salesCount,double totalSales,Date reservationDate) {
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.roomName = roomName;
		this.roomPrice = roomPrice;
		this.salesCount = salesCount;
		this.totalSales = totalSales;
		this.reservationDate = reservationDate;
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
	
	public int getSalesCount() {
		return this.salesCount;
	}
	
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}
	
	public double getTotalSales() {
		return this.totalSales;
	}
	
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
	public Date getReservationDate() {
		return this.reservationDate;
	}
	
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
}