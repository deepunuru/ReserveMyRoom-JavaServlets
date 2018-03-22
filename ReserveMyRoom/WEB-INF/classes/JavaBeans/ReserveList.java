package JavaBeans;

import java.io.*;
import java.util.*;

public class ReserveList implements Serializable {
	private String username;
	private ArrayList<String> hotelList = new ArrayList<String>();
	private int listSize;
	private String[] hotelIDList = new String[30];
	private String[] hotelNameList = new String[30];
	private String[] roomTypeList = new String[30];
	private Date[] checkInDates = new Date[30];
	private Date[] checkOutDates = new Date[30];
	private double[] roomPrices = new double[30];
	private int[] guestCounts = new int[30];
	
	public ReserveList() {
		
	}
	
	public ReserveList(String username,ArrayList<String> hotelList,int listSize,String[] hotelIDList,String[] hotelNameList,String[] roomTypeList,Date[] checkInDates,Date[] checkOutDates,double[] roomPrices,int[] guestCounts) {
		this.username = username;
		this.hotelList = hotelList;
		this.listSize = listSize;
		this.hotelIDList = hotelIDList;
		this.hotelNameList = hotelNameList;
		this.roomTypeList = roomTypeList;
		this.checkInDates = checkInDates;
		this.checkOutDates = checkOutDates;
		this.guestCounts = guestCounts;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ArrayList<String> getHotelList(){
		return this.hotelList;
	}
	
	public void setHotelList(ArrayList<String> hotelList) {
		this.hotelList = hotelList;
	}
	
	public int getListSize() {
		return this.listSize;
	}
	
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
	public String[] getHotelIDList() {
		return this.hotelIDList;
	}
	
	public void setHotelIDList(String[] hotelIDList) {
		this.hotelIDList = hotelIDList;
	}
	
	public String[] getHotelNameList() {
		return this.hotelNameList;
	}
	
	public void setHotelNameList(String[] hotelNameList) {
		this.hotelNameList = hotelNameList;
	}
	
	public String[] getRoomTypeList() {
		return this.roomTypeList;
	}
	
	public void setRoomTypeList(String[] roomTypeList) {
		this.roomTypeList = roomTypeList;
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
	
	public double[] getRoomPrices() {
		return this.roomPrices;
	}
	
	public void setRoomPrices(double[] roomPrices) {
		this.roomPrices = roomPrices;
	}
	
	public int[] getGuestCounts() {
		return this.guestCounts;
	}
	
	public void setGuestCounts(int[] guestCounts) {
		this.guestCounts = guestCounts;
	}
}