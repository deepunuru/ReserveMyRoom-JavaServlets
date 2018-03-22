package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MySQLDataStoreUtilities;
import Controller.HtmlEngine;
import JavaBeans.*;

public class CityAutoComplete extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	
	public CityAutoComplete() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public StringBuffer readAjaxMapData(String searchId) {
		try {
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			StringBuffer sb = new StringBuffer();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
				ArrayList<Cities> cityList = dbObj.getCitiesList(searchId);
				if(cityList == null || cityList.isEmpty()) {
					sb.append("<city>Cities table is empty</city>");
				}
				else {
					Cities city = new Cities();
					Iterator listItr = cityList.iterator();
					while(listItr.hasNext()) {
						city = (Cities)listItr.next();
						sb.append("<city>");
						sb.append("<cityName>" + city.getCityName() + "</cityName>");
						sb.append("<stateName>" + city.getStateName() + "</stateName>");
						sb.append("<stateID>" + city.getStateID() + "</stateID>");
						sb.append("<zipCode>" + String.valueOf(city.getZipCode()) + "</zipCode>");
						sb.append("</city>");
					}
				}
			}
			else {
				sb.append("<city>" + dbStatus.get("msg") + "</city>");
			}
			return sb;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String city = request.getParameter("city");
			String action = request.getParameter("action");
			StringBuffer sb = new StringBuffer();
			boolean flag = false;
			if(action.equals("complete")) {
				if(!city.equals("")) {
					sb = readAjaxMapData(city);
					if(sb!=null || !sb.equals("")) {
						flag = true;
					}
					if(flag) {
						response.setContentType("text/xml");
						response.getWriter().write("<cities>" + sb.toString() + "</cities>");
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
