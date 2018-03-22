package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MySQLDataStoreUtilities;
import Controller.HtmlEngine;
import JavaBeans.*;

public class AjaxUtility extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	
	public AjaxUtility() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public StringBuffer readAjaxMapData(String searchId) {
		try {
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			StringBuffer sb = new StringBuffer();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
				if(hotelsMap == null || hotelsMap.isEmpty()) {
					sb.append("<hotel>Hotels table is empty</hotel>");
				}
				else {
					Hotels hotel = new Hotels();
					for(Map.Entry<String, Hotels> w : hotelsMap.entrySet()) {
						hotel = w.getValue();
						if(hotel.getName().toLowerCase().startsWith(searchId)) {
							sb.append("<hotel>");
							sb.append("<id>" + hotel.getID() + "</id>");
							sb.append("<hotelName>" + hotel.getName() + "</hotelName>");
							sb.append("</hotel>");
						}
					}
				}
			}
			else {
				sb.append("<hotel>" + dbStatus.get("msg") + "</hotel>");
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
			String searchId = request.getParameter("searchId");
			String action = request.getParameter("action");
			StringBuffer sb = new StringBuffer();
			boolean flag = false;
			if(action.equals("complete")) {
				if(!searchId.equals("")) {
					sb = readAjaxMapData(searchId);
					if(sb!=null || !sb.equals("")) {
						flag = true;
					}
					if(flag) {
						response.setContentType("text/xml");
						response.getWriter().write("<hotels>" + sb.toString() + "</hotels>");
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}