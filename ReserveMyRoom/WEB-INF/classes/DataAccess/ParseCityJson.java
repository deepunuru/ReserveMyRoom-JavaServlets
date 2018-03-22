package DataAccess;

import java.io.*;
import java.net.URL;
import java.lang.*;
import java.util.*;
import javax.servlet.ServletContext;
import com.google.gson.*;
import javax.servlet.http.*;

import JavaBeans.Cities;

public class ParseCityJson {
	
	public void getJsonData(String absoluteDiskPath,String stateName) {
		
		Cities city = new Cities();
		try {			
			MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
			HashMap<String, Cities> cityList = new HashMap<String, Cities>();
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(new FileReader(absoluteDiskPath));
			JsonObject obj1 = el.getAsJsonObject();
			JsonArray array1 = obj1.getAsJsonArray("result");
			for (JsonElement pa : array1) {
			    JsonObject tmpObj = pa.getAsJsonObject();
			    city = new Cities(tmpObj.get("City").getAsString(),tmpObj.get("State").getAsString(),stateName,tmpObj.get("Zipcode").getAsInt());
			    cityList.put(city.getCityName(), city);
			}
			dbObj.loadCities(cityList);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("CityName - " + city.getCityName() + "StateName - " + city.getStateName() + "ZipCode - " + city.getZipCode());
		}
	}
}

