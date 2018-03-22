package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Controller.HtmlEngine;
import JavaBeans.*;
import DataAccess.MySQLDataStoreUtilities;

public class DisplayHotel extends HttpServlet {
	MySQLDataStoreUtilities dbObj;
	
	public DisplayHotel() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void displayCarousel(PrintWriter pWriter,HashMap<String, String> imagesMap) {
		String[] imgList = new String[30];
		int n=0;
		int imageCount = imagesMap.size();
		for(Map.Entry<String, String> w : imagesMap.entrySet()) {
			imgList[n] = w.getValue();
			n++;
		}
		pWriter.println("<table id='carouselTable'>\n" +
				"<tr>\n" +
				"<td>\n" +
				"<div id='myCarousel1' class='carousel slide' data-ride='carousel' style='width:100%;height:420px;opacity: 0.8;'>\n" + 
				"<ol class='carousel-indicators'>"); 
				for(int i=0;i<imagesMap.size();i++) {
					if(i==0) {
						pWriter.println("<li data-target='#myCarousel1' data-slide-to='0' class='active'></li>");
					}
					else {
						pWriter.println("<li data-target='#myCarousel1' data-slide-to='" + i + "'></li>");
					}
				}
				pWriter.println("</ol>\n" + 
				"<!-- Wrapper for slides -->\n" + 
				"<div class='carousel-inner'>");
				for(int i=0;i<imagesMap.size();i++) {
					if(i==0) {
						pWriter.println("<div class='item active'>\n" + 
											"<img src='/EWAProject/" + imgList[0] + "' alt='" + imgList[0] + "' style='width:600px;height:420px;padding-left:20px;padding-right:20px;'/>\n" + 
										"</div>"); 
					}
					else {
						pWriter.println("<div class='item'>\n" + 
											"<img src='/EWAProject/" + imgList[i] + "' alt='" + imgList[i] + "' style='width:600px;height:420px;padding-left:20px;padding-right:20px;'/>\n" + 
										"</div>");
					}
				}					
				
				pWriter.println("</div>\n" + 
				"<!-- Left and right controls -->\n" + 
				"<a class='left carousel-control' href='#myCarousel1' data-slide='prev'>\n" + 
				"<span class='glyphicon glyphicon-chevron-left'></span>\n" + 
				"<span class='sr-only'>Previous</span>\n" + 
				"</a>\n" + 
				"<a class='right carousel-control' href='#myCarousel1' data-slide='next'>\n" + 
				"<span class='glyphicon glyphicon-chevron-right'></span>\n" + 
				"<span class='sr-only'>Next</span>\n" + 
				"</a>\n" + 
				"</div>\n" +
				"</td>\n" +
				"</tr>\n" +
				"</table>"); 
	}
	
	public String getVal(boolean res) {
		if(res==true) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			
			if(id==null) {
				engine.generateHtml("Header",request);
				engine.generateHtml("LeftNav",request);
				engine.generateHtml("Content",request);
			}
			else {
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = dbObj.checkDBConnection();
				if(dbStatus.get("status").equals("true")) {
					Hotels hotel = dbObj.getHotel(id);
					engine.generateHtml("Header",request,"/EWAProject/Controller/DisplayHotel?id=" + hotel.getID(),"POST");
					engine.generateHtml("LeftNav",request);
					
					pWriter.println("</td>\n" + 
							"<td style='text-align:left;width:69%;padding-left: 50px;'>\n" + 
								"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
									"</tr>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:15px;'>\n" +
											"<p></p>\n" +	
										"</td> \n" + 
									"</tr>\n" + 
									"<tr>\n" +	
										"<td style='text-align:center;width:60%;'>");
											displayCarousel(pWriter,hotel.getHotelImages());
											pWriter.println("</td>\n" +
										"<td style='text-align:left;width:40%;padding:3%;'>\n" +
											"<label for='hotelNameLbl'>Hotel Name :</label><input type='text' id='hotelName' style='width:220px;background-color:#bfbfbf;' name='hotelName' value='" + hotel.getName() + "' readonly/>\n" +
											"<br/>\n" +
											"<label for='wifiLbl'>WiFi :</label><input type='text' id='wifi' style='width:150px;background-color:#bfbfbf;' name='wifi' value='" + getVal(hotel.getWifi()) + "' readonly/>\n" +
											"<br/>\n" +
											"<label for='restaurantLbl'>Restaurant :</label><input type='text' id='restaurant' style='width:150px;background-color:#bfbfbf;' name='restaurant' value='" + getVal(hotel.getRestaurant()) + "' readonly/>\n" +
											"<br/>\n" +
											"<label for='addres11Lbl'>Address1 :</label><input type='text' id='address1' style='width:220px;background-color:#bfbfbf;' name='address1' value='" + hotel.getAddress1() + "' readonly/>\n" +
											"<br/>\n" +
											"<label for='cityLbl'>City :</label><input type='text' id='city' style='width:220px;background-color:#bfbfbf;' name='city' value='" + hotel.getCity() + "' readonly/>\n" +
										"</td>\n" +
									"</tr>\n" +
									"<tr>\n" + 
										"<td colspan='2' style='text-align:right;padding:4%;' class='date_btn'>\n" +
											"<input type='submit' value='Book Now' style='width: 120px;padding:3px;'>&nbsp;&nbsp;<a class='accBtn' style='width: 120px;padding: 3px;border-radius: unset;' href='/EWAProject/Controller/ReserveHotel?id=" + hotel.getID() + "&op=AddToListNoCity' method='get'>Reserve</a>\n" +
										"</td> \n" + 
									"</tr>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='text-align:right;padding:4%;'>\n" +
											"<a class='accBtn' style='width: 120px;padding: 3px;border-radius: unset;' href='/EWAProject/Controller/HotelReviews?op=viewReviews&hotelName=" + hotel.getName() + "' method='get'>View Reviews</a>&nbsp;&nbsp;<a class='accBtn' style='width: 120px;padding: 3px;border-radius: unset;' href='/EWAProject/Controller/HotelReviews?op=writeReview&hotelName=" + hotel.getName() + "' method='get'>Write Reviews</a>\n" +
										"</td> \n" + 
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
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNav",request);
					pWriter.println("</td>\n" + 
							"<td style='text-align:left;width:59%'>\n" + 
								"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
									"</tr>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:15px;'>\n" +
											"<p>" + dbStatus.get("msg") + "</p>\n" +	
										"</td> \n" + 
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
			}
			engine.generateHtml("Footer",request);
		}
		catch(Exception ex) {			
			ex.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		try {
			String id = request.getParameter("id");
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			HttpSession userSession = request.getSession(true);
			String username = (String)userSession.getAttribute("customerKey");
			if(username!=null) {
				if(id==null) {
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNav",request);
					engine.generateHtml("Content",request);
				}
				else {
					HashMap<String, String> dbStatus = new HashMap<String, String>();
					dbStatus = dbObj.checkDBConnection();
					if(dbStatus.get("status").equals("true")) {
						ReserveList rl = (ReserveList)userSession.getAttribute("ReserveList");
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						Date[] checkInDates = rl.getCheckInDates();
						Date[] checkOutDates = rl.getCheckOutDates();
						
						if(checkInDates[0]==null && checkOutDates[0]==null) {
							Date date = new Date();
							checkInDates[0] = formatter.parse(formatter.format(date));
							Calendar dateCalender = Calendar.getInstance();
							dateCalender.setTime(date);
							dateCalender.add(Calendar.DAY_OF_MONTH, 2);
							checkOutDates[0] = dateCalender.getTime();
						}
						
						Hotels hotel = dbObj.getHotel(id);
						engine.generateHtml("Header",request,"/EWAProject/Controller/BookHotel","GET");
						engine.generateHtml("LeftNav",request);
						
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:69%'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;'>\n" +
												"<p></p>\n" +
											"</td>\n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' class='res_online'>\n" +
												"<h4 style='text-align:center;'>Select Rooms</h4>\n" + 	
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' class='res_online' style='padding:3%;'>\n" +
												"<label for='hotelIDLbl'>Hotel ID :</label><input type='text' id='hotelID' style='width:300px;background-color:#bfbfbf;' name='hotelID' value='" + hotel.getID() + "' readonly/>\n" +
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' class='res_online' style='padding:3%;'>\n" +
												"<label for='hotelNameLbl'>Hotel Name :</label><input type='text' id='hotelName' style='width:300px;background-color:#bfbfbf;' name='hotelName' value='" + hotel.getName() + "' readonly/>\n" +
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +	
											"<td style='text-align:center;width:50%;padding:3%;' class='book_date btm'>\n" +
												"<h4>check-in:</h4>\n" +
												"<input class='date' id='datepicker1' name='checkInDate' type='text' value='" + formatter.format(checkInDates[0]) + "'>\n" +
											"</td>\n" +
											"<td style='text-align:center;width:50%;padding:3%;' class='book_date btm'>\n" +
												"<h4>check-out:</h4>\n" +
												"<input class='date' id='datepicker2' name='checkOutDate' type='text' value='" + formatter.format(checkOutDates[0]) + "'>\n" +
											"</td>\n" +
										"</tr>");
										HashMap<String, Rooms> roomsMap = hotel.getRooms();
										Rooms room = new Rooms();
										for(Map.Entry<String, Rooms> r : roomsMap.entrySet()) {
											room = r.getValue();
											pWriter.println("<tr>\n" +	
													"<td class='tabletd' style='width:40%;padding:3%;'>\n" +
														"<p><input type='radio' name='room' value='" + room.getRoomName() + "'>" + room.getRoomName() + "<br></p>\n" +
														"Room Price:&nbsp;<input type='text' id='roomPrice' style='width:120px;background-color:#bfbfbf;' name='roomPrice' value='" + room.getRoomPrice() + "' readonly/>\n" +
														"<h4>Number of Rooms:</h4>\n" + 
														"<select id='roomCount' name='roomCount' class='frm-field required'>\n" + 
															"<option value='1'>1</option>\n" +
															"<option value='2'>2</option>\n" +
															"<option value='3'>3</option>\n" +
															"<option value='4'>4</option>\n" +
														"</select>\n" +
														"<h4>Number of Guests:</h4>\n" + 
														"<select id='guestCount' name='guestCount' class='frm-field required'>\n" + 
															"<option value='1'>1</option>\n" +
															"<option value='2'>2</option>\n" +
															"<option value='3'>3</option>\n" +
															"<option value='4'>4</option>\n" +
														"</select>\n" +
													"</td>\n" +
													"<td class='tabletd' style='text-align:center;width:60%;padding:3%;'>");
														displayCarousel(pWriter,room.getRoomImages());
														pWriter.println("</td>\n" +
												"</tr>");
										}
										pWriter.println("<tr>\n" + 
											"<td colspan='2' style='text-align:center;padding:3%;' class='date_btn'>\n" +
												"<input type='submit' value='book now' style='width: 120px;'>\n" + 
											"</td> \n" + 
										"</tr>\n" + 
									"</table> \n" + 
								"</td>\n" + 
								"<td style='text-align:left;width:13%'>\n" + 
								"</td>\n" + 
							"</tr>\n" + 
						"</table>\n" + 
						"</div> \n" + 
						"</form>\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>	\n" + 
						"<!--start main -->");
					}
					else {
						engine.generateHtml("Header",request);
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:15px;'>\n" +
												"<p>" + dbStatus.get("msg") + "</p>\n" +	
											"</td> \n" + 
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
				}
				engine.generateHtml("Footer",request);
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