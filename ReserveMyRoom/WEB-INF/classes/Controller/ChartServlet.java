package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JavaBeans.Inventory;
import DataAccess.MySQLDataStoreUtilities;

import com.google.gson.Gson;

public class ChartServlet extends HttpServlet {
	MySQLDataStoreUtilities dbObj;
	
	public ChartServlet(){
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Inventory inventory = new Inventory();
			List<Inventory> barChartList = new ArrayList<Inventory>();
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				barChartList = dbObj.getInventoryListDB();
			}
			else {
				Inventory i1 = new Inventory("DBError","DBError","DBError",0.00,0,true,true);
				barChartList.add(i1);
			}
			Gson gson = new Gson();
			String jsonString = gson.toJson(barChartList);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}