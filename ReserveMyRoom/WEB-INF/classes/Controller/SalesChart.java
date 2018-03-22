package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JavaBeans.Inventory;
import JavaBeans.Sales;
import DataAccess.MySQLDataStoreUtilities;

import com.google.gson.Gson;

public class SalesChart extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	
	public SalesChart() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Sales sale = new Sales();
			List<Sales> barChartList = new ArrayList<Sales>();
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				barChartList = dbObj.getSalesListDB();
			}
			else {
				sale = new Sales("DBError","DBError","DBError",0.00,0,0.0,null);
				barChartList.add(sale);
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