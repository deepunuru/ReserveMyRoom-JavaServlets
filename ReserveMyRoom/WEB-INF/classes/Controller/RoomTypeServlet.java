package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import DataAccess.MySQLDataStoreUtilities;
import JavaBeans.*;

public class RoomTypeServlet extends HttpServlet {
	MySQLDataStoreUtilities dbObj;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			dbObj = new MySQLDataStoreUtilities();
			String type = request.getParameter("typeVal");
			List<String> list = new ArrayList<String>();
			String json = null;
			if(type.equals("selectHotel")) {
				list.add("Select Room Type");
			}
			else {
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = dbObj.checkDBConnection();
				if(dbStatus.get("status").equals("true")) {
					list = dbObj.getRoomTypes(type);
				}
				else {
					list.add("Select Room Type");
				}
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