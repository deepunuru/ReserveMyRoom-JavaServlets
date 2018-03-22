package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import DataAccess.MySQLDataStoreUtilities;
import JavaBeans.*;

public class HotelListServlet extends HttpServlet {
	MySQLDataStoreUtilities dbObj;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			dbObj = new MySQLDataStoreUtilities();
			List<String> list = new ArrayList<String>();
			String json = null;
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			Hotels hotel = new Hotels();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				for(Map.Entry<String, Hotels> w : dbObj.getHotelsMap().entrySet()) {
					hotel = w.getValue();
					list.add(hotel.getID());
				}				
			}
			else {
				list.add("Select Hotel");
			}
			json = new Gson().toJson(list);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}