package JavaBeans;

import java.io.Serializable;

public class Cities implements Serializable {
	private String cityName;
	private String stateID;
	private String stateName;
	private int zipCode;
	
	public Cities() {
		
	}
	
	public Cities(String cityName,String stateID,String stateName,int zipCode) {
		this.cityName = cityName;
		this.stateID = stateID;
		this.stateName = stateName;
		this.zipCode = zipCode;
	}
	
	public String getCityName() {
		return this.cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getStateID() {
		return this.stateID;
	}
	
	public void setStateID(String stateID) {
		this.stateID = stateID;
	}
	
	public String getStateName() {
		return this.stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public int getZipCode() {
		return this.zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
}