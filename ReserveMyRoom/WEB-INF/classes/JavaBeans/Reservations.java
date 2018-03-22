package JavaBeans;

import java.io.Serializable;
import java.util.Date;

public class Reservations implements Serializable {
	
	private String customerName;
	private String username;
	private Date reservationDate;
	private Date[] checkInDates;
	private Date[] checkOutDates;
	private int reservationID;
	private String[] hotelIDs;
	private String[] roomNames;
	private int[] guestCounts;
	private double totalPrice;
	private String state;
	private String address1;
	private String address2;
	private String city;
	private int zipCode;
	private String mobileNumber;
	private String emailAddress;
	
	public Reservations() {
		
	}
	
	public Reservations(String customerName,String username,Date reservationDate,Date[] checkInDates,Date[] checkOutDates,int reservationID,String[] hotelIDs,String[] roomNames,int[] guestCounts,double totalPrice,String state,String address1,String address2,String city,int zipCode,String mobileNumber,String emailAddress) {
		this.username = username;
		this.customerName = customerName;
		this.reservationDate = reservationDate;
		this.checkInDates = checkInDates;
		this.checkOutDates = checkOutDates;
		this.reservationID = reservationID;
		this.hotelIDs = hotelIDs;
		this.roomNames = roomNames;
		this.guestCounts = guestCounts;
		this.totalPrice = totalPrice;
		this.state = state;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.zipCode = zipCode;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Date getReservationDate() {
		return this.reservationDate;
	}
	
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	public Date[] getCheckInDates() {
		return this.checkInDates;
	}
	
	public void setCheckInDates(Date[] checkInDates) {
		this.checkInDates = checkInDates;
	}
	
	public Date[] getCheckOutDates() {
		return this.checkOutDates;
	}
	
	public void setCheckOutDates(Date[] checkOutDates) {
		this.checkOutDates = checkOutDates;
	}
	
	public int getReservationID() {
		return this.reservationID;
	}
	
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	
	public String[] getHotelIDs() {
		return this.hotelIDs;
	}
	
	public void setHotelIDs(String[] hotelIDs) {
		this.hotelIDs = hotelIDs;
	}
	
	public String[] getRoomNames() {
		return this.roomNames;
	}
	
	public void setRoomNames(String[] roomNames) {
		this.roomNames = roomNames;
	}
	
	public int[] getGuestCounts() {
		return this.guestCounts;
	}
	
	public void setGuestCounts(int[] guestCounts) {
		this.guestCounts = guestCounts;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
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
	
	public int getZipCode() {
		return this.zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getMobileNumber() {
		return this.mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}