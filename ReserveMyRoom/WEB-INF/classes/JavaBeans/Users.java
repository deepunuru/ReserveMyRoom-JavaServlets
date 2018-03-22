package JavaBeans;

import java.io.Serializable;

public class Users implements Serializable{
	
	private String userType;
	private String username;
	private String firstName;
	private String lastName;
	private String address;
	private int zipCode;
	private String emailID;
	private String dob;
	private String pwd;
	
	public Users(String userType,String username,String firstName,String lastName,String address,int zipCode,String emailID,String DOB,String pwd) {
		//super();
		this.userType = userType;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zipCode = zipCode;
		this.emailID = emailID;
		this.dob = DOB;
		this.pwd = pwd;
	}
	
	public Users(){
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getZipCode() {
		return this.zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getEmailID() {
		return this.emailID;
	}
	
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public String getDOB() {
		return this.dob;
	}
	
	public void setDOB(String dob) {
		this.dob = dob;
	}
	
	public String getPWD() {
		return this.pwd;
	}
	
	public void setPWD(String pwd) {
		this.pwd = pwd;
	}
}