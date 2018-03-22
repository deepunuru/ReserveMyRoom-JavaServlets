package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MySQLDataStoreUtilities;
import Controller.HtmlEngine;
import JavaBeans.*;

public class DealMatches extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	
	public DealMatches() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public HashMap<String, Hotels> readDealMatches(String pathName){
		try {
			HashMap<String, Hotels> selectedHotels = new HashMap<String, Hotels>();
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				Hotels hotel = new Hotels();
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
				if(hotelsMap!=null) {
					for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
						hotel = h.getValue();
						File fN = new File(pathName);
						if(fN.exists()==true) {
							BufferedReader reader = new BufferedReader(new FileReader(fN));
							try {
								String line = reader.readLine();
								if(line==null) {
									selectedHotels.put("No Offers Found", null);
									break;
								}
								else {
									do {
										if(selectedHotels.size()<2 && !selectedHotels.containsKey(line) && !selectedHotels.containsValue(hotel)) {
											if(line.contains(hotel.getName())) {
												selectedHotels.put(line, hotel);
											}
										}
									}
									while((line = reader.readLine()) != null);
								}
							}
							catch(Exception ex) {
								ex.printStackTrace();
								selectedHotels.put("Invalid File Path", null);
								break;
							}
						}
						else {
							System.out.println("Invalid File Path");
							//dealsList.add("Invalid File Path");
							selectedHotels.put("Invalid File Path", null);
							break;
						}
					}
				}
				else {
					selectedHotels.put("Hotel List is null", null);
				}
			}
			else {
				//dealsList.add("MySQL server is not up and running");
				selectedHotels.put("MySQL server is not up and running", null);
			}
			return selectedHotels;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}