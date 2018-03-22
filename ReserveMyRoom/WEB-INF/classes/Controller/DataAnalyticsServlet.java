package Controller;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;

import org.bson.Document;
import org.bson.conversions.*;

import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.*;

import DataAccess.*;
import JavaBeans.*;

public class DataAnalyticsServlet extends HttpServlet {
	MySQLDataStoreUtilities dbObj;
	MongoDBDataStoreUtilities mDBObj;
	
	public DataAnalyticsServlet() {
		dbObj = new MySQLDataStoreUtilities();
		mDBObj = new MongoDBDataStoreUtilities();
	}
	
	public void displayScript(String idName,String city,PrintWriter pWriter,String i) {
		pWriter.println("<script>\n" +
				"var dataVisualization" + i + " = {\n" + 
				"\n" + 
				" /*returns visualization data*/\n" + 
				" getBarChartData: function (jsonInputData) {\n" + 
				"\n" + 
				"  var bar, inputData = [], data = new google.visualization.DataTable();\n" + 
				"\n" + 
				"  data.addColumn('string', 'HotelName');\n" + 
				"\n" + 
				"  data.addColumn('number', 'ReviewCount');\n" + 
				"\n" + 
				"  data.addColumn({\n" + 
				"    type: 'string',\n" + 
				"    role: 'tooltip',\n" + 
				"    'p': {\n" + 
				"     'html': true\n" + 
				"    }\n" + 
				"   });\n" + 
				"\n" + 
				"  $.each(jsonInputData, function (i, obj) {\n" + 
				"\n" + 
				"   bar = 'ReviewCount : ' + obj.reviewCount + '';\n" + 
				"\n" + 
				"   inputData.push([obj.hotelName, obj.reviewCount, dataVisualization" + i + ".returnTooltip(bar)]);\n" + 
				"  });\n" + 
				"\n" + 
				"  data.addRows(inputData);\n" + 
				"\n" + 
				"  return data;\n" + 
				" },\n" + 
				" getBarChartOptions: function (inputdata) {\n" + 
				"	 \n" + 
				"  var data = dataVisualization" + i + ".getBarChartData(inputdata);\n" + 
				"  var options = {\n" + 
				"   animation: {\n" + 
				"    duration: 2000,\n" + 
				"    easing: 'out'\n" + 
				"   },\n" + 
				"   hAxis: {\n" + 
				"	title:'Review Count',\n" + 
				"    baselineColor: '#ccc'\n" + 
				"   },\n" + 
				"   vAxis: {\n" + 
				"	title:'Hotel Name',\n" + 
				"    baselineColor: '#ccc',\n" + 
				"    gridlineColor: '#fff'\n" + 
				"   },\n" + 
				"\n" + 
				"   isStacked: false,\n" + 
				"   height: data.getNumberOfRows() * 30 + 80 + 80,\n" + 
				"   width:800,\n" + 
				"   chartArea: {\n" + 
				"	 left:470,\n" + 
				"	 height: data.getNumberOfRows() * 30 + 80 + 50,\n" + 
				"	 width:330\n" + 
				"   },\n" + 
				"   backgroundColor: '#fff',\n" + 
				"   colors: ['#87CEFF'],\n" + 
				"   fontName: 'roboto',\n" + 
				"   fontSize: 13,\n" + 
				"   bar: { groupWidth: '90%' },\n" + 
				"   legend: {\n" + 
				"    position: 'bottom',\n" + 
				"    alignment: 'end',\n" + 
				"    textStyle: {\n" + 
				"     color: '#b3b8bc',\n" + 
				"     fontName: 'roboto',\n" + 
				"     fontSize: 13\n" + 
				"    }\n" + 
				"   },\n" + 
				"   tooltip: {\n" + 
				"    isHtml: true,\n" + 
				"    showColorCode: true,\n" + 
				"    isStacked: false\n" + 
				"   }\n" + 
				"  };\n" + 
				"  return options;\n" + 
				" },\n" + 
				" drawBarChart: function (inputdata) {\n" + 
				"\n" + 
				"  var barOptions = dataVisualization" + i + ".getBarChartOptions(inputdata),\n" + 
				"\n" + 
				"   data = dataVisualization" + i + ".getBarChartData(inputdata),\n" + 
				"\n" + 
				"   barChart = new google.visualization.BarChart(document.getElementById('" + idName + "'));\n" + 
				"\n" + 
				"  barChart.draw(data, barOptions);\n" + 
				"  /*To redraw upon window resize*/\n" + 
				"  $(window).resize(function () {\n" + 
				"\n" + 
				"   barChart.draw(data, barOptions);\n" + 
				"  });\n" + 
				" },\n" + 
				" /*For displaying tooltip*/\n" + 
				" returnTooltip: function (point) {\n" + 
				"\n" + 
				"  return \"<div style='height:20px;width:150px;font:11px,roboto;padding:5px 5px 5px 5px;border-radius:3px;'>\" +\n" + 
				"   \"<span style='color:#87CEFF;font:11px,roboto;padding-right:20px;'>\" + point + \"</span></div>\";\n" + 
				" },\n" + 
				" /*Makes call to servlet and downloads data */\n" + 
				" getData: function () {\n" + 
				"\n" + 
				"  $.ajax({\n" + 
				"\n" + 
				"    url: 'TrendingChart?var=" + city + "',\n" + 
				"\n" + 
				"    dataType: 'JSON',\n" + 
				"\n" + 
				"    success: function (data) {\n" + 
				"\n" + 
				"     dataVisualization" + i + ".drawBarChart(data);\n" + 
				"    }\n" + 
				"   });\n" + 
				" }\n" + 
				"};\n" + 
				"\n" + 
				"google.load('visualization', '1', {\n" + 
				"  packages: ['corechart']\n" + 
				" });\n" + 
				"\n" + 
				"$(document).ready(function () {\n" + 
				"\n" + 
				" dataVisualization" + i + ".getData();\n" + 
				"})\n;" +
				"</script>");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession userSession = request.getSession(true);
			String manager = (String)userSession.getAttribute("managerKey");
			if(manager!=null) {
				String op = request.getParameter("op");
				
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				if(op!=null) {
					if(op.equals("home")) {
						engine.generateHtml("HeaderManager",request,"/EWAProject/Controller/DataAnalyticsServlet?var=home","POST");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:79%;padding-left:50px;vertical-align:baseline;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='4' style='text-align:center;height:40px;'><div align='center'>Data Analytics on Review Data</div></th> \n" +
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='4' style='height:25px;'>\n" +
												"<p></p>\n" +
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +	
											"<td colspan='4' style='text-align:right;padding:20px' class='rsvBtn'>\n" +
												"<a style='width:200px;text-align:center;' href='/EWAProject/Controller/DataAnalyticsServlet?op=dataVisual' method='GET'>Trending Reviews</a>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='4' style='text-align:center;padding:20px'>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:right;padding:20px'>\n" +
												"<input type='checkbox' name='selectChkBox' value='HotelName'> Select <br>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px'>\n" +
												"<label for='hotelName'>Hotel Name : </label>\n" +
											"</td>\n" +
											"<td colspan='2' style='text-align:left;padding:20px'>\n" +
												"<select name='hotelName' id='hotelName' style='width:300px;'>\n" +
													"<option value='AllHotels'>All Hotels</option>");
												HashMap<String, String> dbStatus = new HashMap<String, String>();
												dbStatus = dbObj.checkDBConnection();
												if(dbStatus.get("status").equals("true")) {
													for(Map.Entry<String, Hotels> w : dbObj.getHotelsMap().entrySet()) {					
														pWriter.println("<option value='" + w.getValue().getName() + "'>" + w.getValue().getName() + "</option>");
													}
												}
												else {
													pWriter.println("<option value=''>" + dbStatus.get("msg") + "</option>");
												}
												
											pWriter.println("</select>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='4' style='text-align:center;padding:20px'>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:right;padding:20px'>\n" +
												"<input type='checkbox' name='selectChkBox' value='RoomPrice'> Select <br>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px'>\n" +
												"<label for='RoomPrice'>Room Price : </label>\n" +
											"</td>\n" +
											"<td style='text-align:left;width:200px;padding:20px'>\n" +
												"<p><input type='text' id='roomPrice' style='width:200px;text-align:left;' name='roomPrice' value='0'/></p>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px;padding-left:15px;'>\n" +
												"<input type='radio' name='priceEquality' value='Equals' checked>Equals<br>\n" + 
												"<input type='radio' name='priceEquality' value='GreaterThan'>GreaterThan<br>\n" + 
												"<input type='radio' name='priceEquality' value='LessThan'>LessThan<br>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='4' style='text-align:center;padding:20px'>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:right;padding:20px'>\n" +
												"<input type='checkbox' name='selectChkBox' value='ReviewRating'> Select <br>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px'>\n" +
												"<label for='ReviewRating'>Review Rating : </label>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px'>\n" +														
												"<select name='reviewRating' id='reviewRating' style='width:200px;'>\n" + 
													"<option value='1'>1</option>\n" + 
													"<option value='2'>2</option>\n" + 
													"<option value='3'>3</option>\n" + 
													"<option value='4'>4</option>\n" +
													"<option value='5'>5</option>\n" +
												"</select>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px;padding-left:15px;'>\n" +
												"<input type='radio' name='ratingEquality' value='Equals' checked>Equals<br>\n" + 
												"<input type='radio' name='ratingEquality' value='GreaterThan'>GreaterThan<br>\n" + 
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='4' style='text-align:center;padding:20px'>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:right;padding:20px'>\n" +
												"<input type='checkbox' name='selectChkBox' value='RetailerCity'> Select <br>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px'>\n" +
												"<label for='RetailerCity'>Retailer City : </label>\n" +
											"</td>\n" +
											"<td style='text-align:left;width:200px;padding:20px'>\n" +														
												"<p><input type='text' id='retailerCity' style='width:200px;' name='retailerCity'/></p>\n" +
											"</td>\n" +
											"<td>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:right;padding:20px'>\n" +
												"<input type='checkbox' name='selectChkBox' value='GroupBy'> Select <br>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px'>\n" +
												"<label for='GroupBy'>Group By : </label>\n" +
											"</td>\n" +
											"<td style='text-align:left;width:200px;padding:20px'>\n" +														
												"<select name='groupBy' id='groupBy' style='width:200px;'>\n" + 
													"<option value='City'>City</option>\n" + 
													"<option value='ReviewCount'>ReviewCount</option>\n" + 
													"<option value='ZipCode'>ZipCode</option>\n" + 
												"</select>\n" +
											"</td>\n" +
											"<td style='text-align:left;padding:20px;padding-left:15px;'>\n" +
												"<input type='radio' name='sortType' value='Ascending' checked>Ascending<br>\n" + 
												"<input type='radio' name='sortType' value='Descending'>Descending<br>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='2' style='text-align:right;padding:20px'>\n" +
												"<label for='Limit'>Limit :&nbsp;&nbsp;</label>\n" +
											"</td>\n" +
											"<td colspan='2' style='text-align:left;padding:20px'>\n" +
												"<input type='text' id='limit' style='width:120px;' name='limit' value='10'/>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td colspan='4' style='text-align:center;padding:7px;' class='date_btn'>\n" +
												"<input type='submit' value='Submit' style='width: 120px;'>\n" + 
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=ManagerHome' method='get'>Account</a>\n" +
											"</td>\n" +
										"</tr>\n" +
									"</table> \n" + 
								"</td>\n" + 
								"<td style='text-align:left;width:3%'>\n" + 
								"</td>\n" + 
							"</tr>\n" + 
						"</table>\n" + 
						"</div> \n" + 
						"</form>	\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>	\n" + 
						"<!--start main -->");
						engine.generateHtml("Footer",request);
					}
					else if(op.equals("dataVisual")) {
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:69%;padding-left:50px;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th style='text-align:center;height:40px;'><div align='center'>Trending Reviews Bar Chart</div></th> \n" +
										"</tr>");
						int i=0;
						HashMap<String, String[][]> trendingMap = mDBObj.getTrendingReviews();
						for(Map.Entry<String, String[][]> w : trendingMap.entrySet()) {
							String k = String.valueOf(i);
							String city = w.getKey();
							pWriter.println("<tr>\n" + 
										"<td style='height:25px;'>\n" +
											"<p></p>");
											displayScript("hotelBarChart" + String.valueOf(i),city,pWriter,k);
							pWriter.println("</td> \n" + 
										"</tr>\n" +
										"<tr>\n" + 
											"<td style='text-align:center;'>\n" +
												"<div id='hotelBarChart" + String.valueOf(i) + "'></div>\n" +
											"</td> \n" + 
										"</tr>"); 
							i++;
						}
										
						pWriter.println("<tr>\n" +
										"<td style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
											"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a>\n" +
										"</td>\n" +
										"</tr>\n" +
									"</table> \n" + 
								"</td>\n" + 
								"<td style='text-align:left;width:13%'>\n" + 
								"</td>\n" + 
							"</tr>\n" + 
						"</table>\n" + 
						"</div> \n" + 
						"</form>	\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>	\n" + 
						"<!--start main -->");
						engine.generateHtml("Footer",request);
					}
				}
				else {
					response.sendRedirect("/EWAProject/Controller/HomeServlet");	
				}
			}
			else {
				response.sendRedirect("/EWAProject/Controller/LoginServlet");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String var = request.getParameter("var");
		try {
			switch(var) {
				case "home":{
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					
					String selectChkBox[] = request.getParameterValues("selectChkBox");
					String reviewRating = request.getParameter("reviewRating");
					String ratingEquality = request.getParameter("ratingEquality");
					
					String hotelName = request.getParameter("hotelName");
					
					String roomPrice = request.getParameter("roomPrice");
					String priceEquality = request.getParameter("priceEquality");
					
					String city = request.getParameter("retailerCity");
					String limitVal = request.getParameter("limit");
					
					BasicDBObject query = new BasicDBObject();
					
					Bson bList = null;
					Bson sortObj = null;
					Bson limitObj = null;
					
					String groupBy = request.getParameter("groupBy");
					String sortType = request.getParameter("sortType");
					
					boolean filterEnabled = false;
					boolean aggregationEnabled = false;
					
					if(selectChkBox!=null) {
						for(String s: selectChkBox) {
							switch(s) {
								case "HotelName":{
									filterEnabled = true;
									if(hotelName.equals("AllHotels")) {
										filterEnabled = false;
									}
									else {
										query.put("hotelName", hotelName);
									}
									break;
								}
								case "RoomPrice":{
									filterEnabled = true;
									if(priceEquality.equals("Equals")) {
										query.put("roomPrice", Double.parseDouble(roomPrice));
									}
									else if(priceEquality.equals("GreaterThan")) {
										query.put("roomPrice", new BasicDBObject("$gt",Double.parseDouble(roomPrice)));
									}
									else {
										query.put("roomPrice", new BasicDBObject("$lt",Double.parseDouble(roomPrice)));
									}
									break;
								}
								case "ReviewRating":{
									filterEnabled = true;
									if(ratingEquality.equals("Equals")) {
										query.put("reviewRating", Integer.parseInt(reviewRating));
									}
									else {
										query.put("reviewRating", new BasicDBObject("$gt",Integer.parseInt(reviewRating)));
									}
									break;
								}
								case "RetailerCity":{
									filterEnabled = true;
									if(!city.isEmpty()) {
										query.put("city", city);
									}
									else {
										filterEnabled = false;
									}
									break;
								}
								case "GroupBy":{
									aggregationEnabled = true;
									filterEnabled = false;
									if(groupBy.equals("City")) {
										bList = Aggregates.group("$city", Accumulators.sum("ReviewValue", 1));
									}
									else if(groupBy.equals("ReviewCount")) {
										bList = Aggregates.group("$hotelName", Accumulators.sum("ReviewValue", 1));
									}
									else {
										bList = Aggregates.group("$zipCode", Accumulators.sum("ReviewValue", 1));
									}
									
									if(sortType.equals("Ascending")) {
										sortObj = Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.ascending("ReviewValue")));
									}
									else {
										sortObj = Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.descending("ReviewValue")));
									}
									limitObj = Aggregates.limit(Integer.parseInt(limitVal));
									break;
								}
							}
						}
					}
					else {
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td style='height:15px;'>\n" +
												"<p>Please select a valid option to proceed further.</p>\n" +	
											"</td> \n" + 
										"</tr>\n" +
										"<tr>\n" +
											"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a>\n" +
											"</td>\n" +
										"</tr>\n" +
									"</table> \n" + 
								"</td>\n" + 
								"<td style='text-align:left;width:23%'>\n" + 
								"</td>\n" + 
							"</tr>\n" + 
						"</table>\n" + 
						"</div> \n" + 
						"</form>	\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>	\n" + 
						"<!--start main -->");
						engine.generateHtml("Footer",request);
						break;
					}
					
					HashMap<String, String> dbStatus = new HashMap<String, String>();
					dbStatus = mDBObj.checkMongoDB();
					if(dbStatus.get("status").equals("true")) {
						MongoCollection<Document> myReviews = mDBObj.getCollection();
						FindIterable<Document> docs=null;
						AggregateIterable<Document> iterable=null;
						try {
							if(aggregationEnabled == true) {
								iterable = myReviews.aggregate(Arrays.asList(bList,sortObj,limitObj));
							}
							else {
								if(filterEnabled == false) {
									docs = myReviews.find().limit(Integer.parseInt(limitVal));
								}
								else {
									docs = myReviews.find(query).limit(Integer.parseInt(limitVal));
								}
							}
						}
						catch(Exception ex) {	
							ex.printStackTrace();
						}
						
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:69%;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<table style='width:100%;'>\n" + 
										"<tr>\n" + 
											"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='4' style='text-align:center;height:40px;'><div align='center'>Hotel List</div></th> \n" +
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='4' style='height:25px;'>\n" +
												"<p></p>\n" +
											"</td> \n" + 
										"</tr>");
						MongoCursor<Document> cursor;
						
						if(aggregationEnabled == true) {
							cursor = iterable.iterator();
							if(!cursor.hasNext()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='4' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>No Results to Display</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								try {
									while (cursor.hasNext()) {
										Document doc = cursor.next();
										pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<td colspan='2' style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>");
													if(groupBy.equals("ZipCode")) {
														pWriter.println("<p>GroupID: " + doc.getInteger("_id") + "</p>");
													}
													else {
														pWriter.println("<p>GroupID: " + doc.getString("_id") + "</p>");
													}
													pWriter.println("</td>\n" +
												"<td colspan='2' style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>Count: " + doc.getInteger("ReviewValue") + "</p>\n" +
												"</td>\n" +
												"</tr>\n" +
												"<tr>\n" +
													"<td colspan='4'>\n" +
														"<p></p>\n" +
													"</td>\n" +
												"</tr>");		
									}
								}
								finally {
									cursor.close();
								}
							}
						}
						else {
							cursor = docs.iterator();
							if(!cursor.hasNext()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='4' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>No Reviews to Display</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								try {
									while (cursor.hasNext()) {
										Document doc = cursor.next();
										pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>Product Name:&nbsp;&nbsp;" + doc.getString("hotelName") + "</p>\n" +
													"</td>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>Product Type:&nbsp;&nbsp;" + String.valueOf(doc.getDouble("roomPrice")) + "</p>\n" +
													"</td>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>Username:&nbsp;&nbsp;" + doc.getString("username") + "</p>\n" +
													"</td>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>ZipCode:&nbsp;&nbsp;" + String.valueOf(doc.getInteger("zipCode")) + "</p>\n" +
													"</td>\n" +
												"</tr>\n" +
												"<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +									
														"<p>Review Rating:&nbsp;&nbsp;" + String.valueOf(doc.getInteger("reviewRating")) + "</p>\n" +
													"</td>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>Review Date:&nbsp;&nbsp;" + doc.getString("reviewDate") + "</p>\n" +
													"</td>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>Review Text:&nbsp;&nbsp;" + doc.getString("reviewText") + "</p>\n" +
													"</td>\n" +
													"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
														"<p>City:&nbsp;&nbsp;" + doc.getString("city") + "</p>\n" +
													"</td>\n" +
												"</tr>\n" +
												"<tr>\n" +
													"<td colspan='4' style='padding:10px;'>\n" +
														"<p></p>\n" +
													"</td>\n" +
												"</tr>");
									}
								}
								finally {
									cursor.close();
								}
							}
						}
						mDBObj.disconnectMongoDB();
						pWriter.println("<tr>\n" +
								"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
									"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a>\n" +
								"</td>\n" +
								"</tr>\n" +
							"</table> \n" + 
						"</td>\n" + 
						"<td style='text-align:left;width:13%'>\n" + 
						"</td>\n" + 
					"</tr>\n" + 
				"</table>\n" + 
				"</div> \n" + 
				"</form>	\n" + 
				"</div>\n" + 
				"</div>\n" + 
				"</div>	\n" + 
				"<!--start main -->");
						engine.generateHtml("Footer",request);
					}
					else {
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td style='height:15px;'>\n" +
												"<p>" + dbStatus.get("msg") + "</p>\n" +
											"</td> \n" + 
										"</tr>\n" +
										"<tr>\n" +
											"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a>\n" +
											"</td>\n" +
										"</tr>\n" +
									"</table> \n" + 
								"</td>\n" + 
								"<td style='text-align:left;width:23%'>\n" + 
								"</td>\n" + 
							"</tr>\n" + 
						"</table>\n" + 
						"</div> \n" + 
						"</form>	\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>	\n" + 
						"<!--start main -->");
						engine.generateHtml("Footer",request);
					}
					break;
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}