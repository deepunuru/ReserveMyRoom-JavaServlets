package JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;

public class TrendingReviews implements Serializable {
	
	private String hotelName;
	private long reviewCount;
	
	/*private String hotelName1;
	private long reviewCount1;
	
	private String hotelName2;
	private long reviewCount2;
	
	private String hotelName3;
	private long reviewCount3;*/
	
	
	
	public TrendingReviews() {
		
	}
	
	public TrendingReviews(String hotelName,long reviewCount) {
		this.hotelName = hotelName;
		this.reviewCount = reviewCount;
	}
	
	/*public TrendingReviews(String hotelName1,String hotelName2,String hotelName3,long reviewCount1,long reviewCount2,long reviewCount3) {
		this.hotelName1 = hotelName1;
		this.hotelName2 = hotelName2;
		this.hotelName3 = hotelName3;
		
		this.reviewCount1 = reviewCount1;
		this.reviewCount2 = reviewCount2;
		this.reviewCount3 = reviewCount3;
	}*/
	
	public String getHotelName() {
		return this.hotelName;
	}
	
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public long getReviewCount() {
		return this.reviewCount;
	}
	
	public void setReviewCount(long reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	/*
	public String getHotelName1() {
		return this.hotelName1;
	}
	
	public void setHotelName1(String hotelName1) {
		this.hotelName1 = hotelName1;
	}
	
	public String getHotelName2() {
		return this.hotelName2;
	}
	
	public void setHotelName2(String hotelName2) {
		this.hotelName2 = hotelName2;
	}
	
	public String getHotelName3() {
		return this.hotelName3;
	}
	
	public void setHotelName3(String hotelName3) {
		this.hotelName3 = hotelName3;
	}
	
	public long getReviewCount1() {
		return this.reviewCount1;
	}
	
	public void setReviewCount1(long reviewCount1) {
		this.reviewCount1 = reviewCount1;
	}
	
	public long getReviewCount2() {
		return this.reviewCount2;
	}
	
	public void setReviewCount2(long reviewCount2) {
		this.reviewCount2 = reviewCount2;
	}
	
	public long getReviewCount3() {
		return this.reviewCount3;
	}
	
	public void setReviewCount3(long reviewCount3) {
		this.reviewCount3 = reviewCount3;
	}*/
}