package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JavaBeans.TrendingReviews;
import DataAccess.*;

import com.google.gson.Gson;

public class TrendingChart extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	MongoDBDataStoreUtilities mdbObj;
	
	public TrendingChart() {
		dbObj = new MySQLDataStoreUtilities();
		mdbObj = new MongoDBDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			TrendingReviews obj = new TrendingReviews();
			String[][] result = new String[3][2];
			
			List<TrendingReviews> barChartList = new ArrayList<TrendingReviews>();
			
			String city = request.getParameter("var");
			if(city!=null) {
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = dbObj.checkDBConnection();
				if(dbStatus.get("status").equals("true")) {
					result = mdbObj.getTrendingReviews().get(city);
					obj = new TrendingReviews(result[0][0],Long.parseLong(result[0][1]));
					barChartList.add(obj);
					obj = new TrendingReviews(result[1][0],Long.parseLong(result[1][1]));
					barChartList.add(obj);
					obj = new TrendingReviews(result[2][0],Long.parseLong(result[2][1]));
					barChartList.add(obj);
				}
				else {
					obj = new TrendingReviews("",0);
					barChartList.add(obj);
				}
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