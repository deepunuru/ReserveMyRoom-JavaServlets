package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MySQLDataStoreUtilities;
import DataAccess.MongoDBDataStoreUtilities;
import Controller.HtmlEngine;
import JavaBeans.*;

public class InventorySalesServlet extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	MongoDBDataStoreUtilities mDBObj;
	
	public InventorySalesServlet() {
		dbObj = new MySQLDataStoreUtilities();
		mDBObj = new MongoDBDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession userSession = request.getSession(true);
			String manager = (String)userSession.getAttribute("managerKey");
			if(manager!=null) {
				String var = request.getParameter("var");
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				switch(var) {
					case "inventoryHome":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%;padding-left:50px;vertical-align:baseline;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Inventory Home</div></th> \n" +
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:25px;'>\n" +
												"<p></p>\n" +
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +
											"<td colspan='2' style='text-align:center;'>\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=inventory&type=list' method='get'>Hotel Inventory List</a></p>\n" +
												"<br />\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=inventory&type=barchart' method='get'>Hotel Inventory Bar Chart</a></p>\n" +
												"<br />\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=onSale' method='get'>Hotels On Sale</a></p>\n" +
												"<br />\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=rebate' method='get'>Hotels With Rebate</a></p>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
										"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
											"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=ManagerHome' method='get'>Account</a>\n" +
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
					case "inventory":{
						String type = request.getParameter("type");
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							if(type.equals("list")) {
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:69%;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<table style='width:100%;'>\n" + 
												"<tr>\n" + 
													"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='4' style='text-align:center;height:40px;'><div align='center'>Hotel Inventory List</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='4' style='height:25px;'>\n" +
														"<p></p>\n" +
													"</td> \n" + 
												"</tr>\n" + 
												"<tr>\n" +	
													"<th><div align='left'>Hotel Name</div></th>\n" +
													"<th><div align='left'>Room Name</div></th>\n" +
													"<th><div align='left'>Price</div></th>\n" +
													"<th><div align='center'>Rooms Available</div></th>\n" +
												"</tr>\n" +
												"<tr>\n" + 
													"<td colspan='4' style='height:25px;'>\n" +
														"<p></p>\n" +
													"</td> \n" + 
												"</tr>");
								ArrayList<Inventory> inventoryList = dbObj.getInventoryListDB();
								if(inventoryList==null | inventoryList.isEmpty()) {
									pWriter.println("<tr>\n" +	
											"<td colspan='4' style='text-align:center;'>\n" +
												"<label for='orderStatusLbl'>Inventory is Empty</label>\n" +
											"</td>\n" +
										"</tr>");
								}
								else {
									Iterator<Inventory> iterator = inventoryList.iterator();
									Inventory in = new Inventory();
									while (iterator.hasNext()) {
										in = (Inventory)iterator.next();
										pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + in.getHotelName() + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + in.getRoomName() + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>$" + String.valueOf(in.getRoomPrice()) + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + String.valueOf(in.getRoomCount()) + "</p>\n" +
												"</td>\n" +
												"</tr>\n" +
												"<tr>\n" +
													"<td colspan='4'>\n" +
														"<p></p>\n" +
													"</td>\n" +
												"</tr>");	
									}
								}
												
												pWriter.println("<tr>\n" +
												"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
													"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
							}
							else if(type.equals("barchart")){
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:69%;padding-left:50px;'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th style='text-align:center;height:40px;'><div align='center'>Hotel Inventory Bar Chart</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td style='height:25px;'>\n" +
														"<p></p>\n" +
														"<script type='text/javascript' src='/EWAProject/js/inventoryChart.js'></script>\n" +
													"</td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<td style='text-align:center;'>\n" +
														"<div id='hotelBarChart'></div>\n" +
													"</td> \n" + 
												"</tr>\n" +
												"<tr>\n" +
												"<td style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
													"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
							}
						}
						else {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Inventory</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
						}
						engine.generateHtml("Footer",request);
						break;
					}
					case "onSale":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:69%;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='5' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='5' style='text-align:center;height:40px;'><div align='center'>Hotel OnSale List</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='5' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<th><div align='left'>Hotel Name</div></th>\n" +
												"<th><div align='left'>Room Name</div></th>\n" +
												"<th><div align='center'>Price</div></th>\n" +
												"<th><div align='center'>Hotels On Sale</div></th>\n" +
												"<th><div align='center'>Hotels With Rebate</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='5' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>");
							ArrayList<Inventory> onSaleList = dbObj.getOnSaleListDB();
							if(onSaleList==null | onSaleList.isEmpty()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='5' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>OnSale list is Empty</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								Iterator<Inventory> iterator = onSaleList.iterator();
								Inventory in = new Inventory();
								while (iterator.hasNext()) {
									in = (Inventory)iterator.next();
									String onSaleStatus = null;
									if(in.getOnSale()) {
										onSaleStatus = "Yes";
									}
									else {
										onSaleStatus = "No";
									}
									String rebateStatus = null;
									if(in.getRebate()) {
										rebateStatus = "Yes";
									}
									else {
										rebateStatus = "No";
									}
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + in.getHotelName() + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + in.getRoomName() + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>$" + String.valueOf(in.getRoomPrice()) + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + onSaleStatus + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + rebateStatus + "</p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='5'>\n" +
													"<p></p>\n" +
												"</td>\n" +
											"</tr>");	
								}
							}
											
											pWriter.println("<tr>\n" +
											"<td colspan='5' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
						}
						else {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotels OnSale List</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
						}
						engine.generateHtml("Footer",request);
						break;
					}
					case "rebate":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:69%;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='5' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='5' style='text-align:center;height:40px;'><div align='center'>Hotels Rebate List</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='5' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<th><div align='left'>Hotel Name</div></th>\n" +
												"<th><div align='left'>Room Name</div></th>\n" +
												"<th><div align='center'>Price</div></th>\n" +
												"<th><div align='center'>Hotels On Sale</div></th>\n" +
												"<th><div align='center'>Hotels With Rebate</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='5' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>");
							ArrayList<Inventory> rebateList = dbObj.getRebateListDB();
							if(rebateList==null | rebateList.isEmpty()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='5' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>OnSale list is Empty</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								Iterator<Inventory> iterator = rebateList.iterator();
								Inventory in = new Inventory();
								while (iterator.hasNext()) {
									in = (Inventory)iterator.next();
									String onSaleStatus = null;
									if(in.getOnSale()) {
										onSaleStatus = "Yes";
									}
									else {
										onSaleStatus = "No";
									}
									String rebateStatus = null;
									if(in.getRebate()) {
										rebateStatus = "Yes";
									}
									else {
										rebateStatus = "No";
									}
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + in.getHotelName() + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + in.getRoomName() + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>$" + String.valueOf(in.getRoomPrice()) + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + onSaleStatus + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
													"<p>" + rebateStatus + "</p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='5'>\n" +
													"<p></p>\n" +
												"</td>\n" +
											"</tr>");	
								}
							}
											
											pWriter.println("<tr>\n" +
											"<td colspan='5' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
						}
						else {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotels Rebate List</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory Home</a>\n" +
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
						}
						engine.generateHtml("Footer",request);
						break;
					}
					case "salesReportHome":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%;padding-left:50px;vertical-align:baseline;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Sales Report Home</div></th> \n" +
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:25px;'>\n" +
												"<p></p>\n" +
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +
											"<td colspan='2' style='text-align:center;'>\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=salesReport' method='get'>Hotel Reservations Report</a></p>\n" +
												"<br />\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=salesChart' method='get'>Hotel Reservations Bar Chart</a></p>\n" +
												"<br />\n" +
												"<p><a href='/EWAProject/Controller/InventorySalesServlet?var=dailySales' method='get'>Total Daily Reservations</a></p>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
										"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
											"<a style='width: 120px;padding:7px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=ManagerHome' method='get'>Account</a>\n" +
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
					case "salesReport":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:69%;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='4' style='text-align:center;height:40px;'><div align='center'>Hotel Reservations Report</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='4' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<th><div align='left'>Hotel Name</div></th>\n" +
												"<th><div align='left'>Room Price</div></th>\n" +
												"<th><div align='left'>Reservations Count</div></th>\n" +
												"<th><div align='center'>Total Sales</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='4' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>");
							ArrayList<Sales> salesList = dbObj.getSalesListDB();
							if(salesList==null | salesList.isEmpty()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='4' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>Reservation List is Empty</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								Iterator<Sales> iterator = salesList.iterator();
								Sales sale = new Sales();
								while (iterator.hasNext()) {
									sale = (Sales)iterator.next();
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + sale.getHotelName() + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>$" + String.valueOf(sale.getRoomPrice()) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + String.valueOf(sale.getSalesCount()) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>$" + String.valueOf(sale.getTotalSales()) + "</p>\n" +
											"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='4'>\n" +
													"<p></p>\n" +
												"</td>\n" +
											"</tr>");	
								}
							}
											
											pWriter.println("<tr>\n" +
											"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Reservations Report Home</a>\n" +
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
						}
						else {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Reservations Report</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Reservations Report Home</a>\n" +
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
						}
						engine.generateHtml("Footer",request);
						break;
					}
					case "salesChart":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th style='text-align:center;height:40px;'><div align='center'>Hotel Reservations Bar Chart</div></th> \n" +
										"</tr>\n" + 
										"<tr>\n" + 
											"<td style='height:25px;'>\n" +
												"<p></p>\n" +
												"<script type='text/javascript' src='/EWAProject/js/salesChart.js'></script>\n" +
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td style='text-align:center;'>\n" +
												"<div id='reservationsBarChart'></div>\n" +
											"</td> \n" + 
										"</tr>\n" +
										"<tr>\n" +
										"<td style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
											"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Reservations Report Home</a>\n" +
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
					case "dailySales":{
						engine.generateHtml("HeaderManager",request,"","");
						engine.generateHtml("LeftNav",request);
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:69%;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Total Daily Reservations</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<th><div align='center'>Reservation Date</div></th>\n" +
												"<th><div align='center'>Total Sales</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>");
							ArrayList<Sales> salesList = dbObj.getDailySalesList();
							if(salesList==null | salesList.isEmpty()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='2' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>Reservation List is Empty</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								Iterator<Sales> iterator = salesList.iterator();
								Sales sale = new Sales();
								while (iterator.hasNext()) {
									sale = (Sales)iterator.next();
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + sale.getReservationDate() + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>$" + String.valueOf(sale.getTotalSales()) + "</p>\n" +
											"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='2'>\n" +
													"<p></p>\n" +
												"</td>\n" +
											"</tr>");	
								}
							}
											
											pWriter.println("<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Reservations Report Home</a>\n" +
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
						}
						else {
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Reservations Report</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Reservations Report Home</a>\n" +
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
						}
						engine.generateHtml("Footer",request);
						break;
					}
					default:{
						response.sendRedirect("/EWAProject/Controller/HomeServlet");
						break;
					}
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
	
}