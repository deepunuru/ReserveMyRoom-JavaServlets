package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Controller.HtmlEngine;
import JavaBeans.*;
import DataAccess.MySQLDataStoreUtilities;

public class MyListServlet extends HttpServlet {
	public static MySQLDataStoreUtilities dbObj;
	
	public MyListServlet() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void printCarousel(PrintWriter pWriter,ReserveList rl) {
		ArrayList<String> hotelList = rl.getHotelList();
		int listSize = rl.getListSize();
		HashMap<String, String> dbStatus = new HashMap<String, String>();
		dbStatus = dbObj.checkDBConnection();
		if(dbStatus.get("status").equals("true")) {
			HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
			Hotels hotel = new Hotels();
			Rooms room = new Rooms();
			int i=0;
			String[] hotelImages = new String[30];
			String[] roomPrices = new String[30];
			String[] hotelNames = new String[30];
			String[] roomNames = new String[30];
			HashMap<String, String> hotelImageMap = new HashMap<String, String>();
			HashMap<String, Rooms> roomsMap = new HashMap<String, Rooms>();
			for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
				if(i>=7) {
					break;
				}
				hotel = h.getValue();
				hotelNames[i] = hotel.getName();
				hotelImageMap =	hotel.getHotelImages();
				for(Map.Entry<String, String> img : hotelImageMap.entrySet()) {
					hotelImages[i] = img.getValue();
					break;
				}
				roomsMap = hotel.getRooms();
				for(Map.Entry<String, Rooms> r : roomsMap.entrySet()) {
					room = r.getValue();
					roomNames[i] = room.getRoomName();
					roomPrices[i] = String.valueOf(room.getRoomPrice());
					break;
				}
				i++;
			}
			
			pWriter.println("<table id='carouselTable'>\n" +
					"<tr>\n" +
						"<td style='width:920px;height:370px;vertical-align: baseline;text-align:center;'>\n" +
							"<div id='myCarousel1' class='carousel slide' data-ride='carousel'>\n" +
								"<div class='carousel-inner'>\n" +
									"<div class='item active'>\n" +
										"<table id='carouselTable'>\n" +
											"<tr>\n" +
												"<td style='width:460px;padding:15px;'>\n" +
													"<p style='text-align:center;'><img src='/EWAProject/" + hotelImages[0] + "' style='width:150px;height:150px' alt='" + hotelImages[0] + "'></p>\n" +
													"<br />\n" +
													"<p style='text-align:center;'><a href='#'>Hotel Name:&nbsp;&nbsp;" + hotelNames[0] + "</a></p>\n" +
													"<br />\n" +
													"<p style='text-align:center;'><a href='#'>Room Type:&nbsp;&nbsp;" + roomNames[0] + "</a></p>\n" +
													"<br />\n" +
													"<p style='text-align:center;'><a href='#'>Room Price:&nbsp;&nbsp;$" + roomPrices[0] + "</a></p>\n" +
													"<br />\n" +
												"</td>\n" +
												"<td style='width:460px;padding:15px;'>\n" +
													"<p style='text-align:center;'><img src='/EWAProject/" + hotelImages[1] + "' style='width:150px;height:150px' alt='" + hotelImages[1] + "'></p>\n" +
													"<br />\n" +
													"<p style='text-align:center;'><a href='#'>Hotel Name:&nbsp;&nbsp;" + hotelNames[1] + "</a></p>\n" +
													"<br />\n" +
													"<p style='text-align:center;'><a href='#'>Room Type:&nbsp;&nbsp;" + roomNames[1] + "</a></p>\n" +
													"<br />\n" +
													"<p style='text-align:center;'><a href='#'>Room Price:&nbsp;&nbsp;$" + roomPrices[1] + "</a></p>\n" +
													"<br />\n" +
												"</td>\n" +
											"</tr>\n" +
										"</table>\n" +
									"</div>");
			
			for(int c=2;c<i-1;c+=2) {
				pWriter.println("<div class='item'>\n" +
						"<table id='carouselTable'>\n" +
						"<tr>\n" +
							"<td style='width:460px;padding:15px;'>\n" +
								"<p style='text-align:center;'><img src='/EWAProject/" + hotelImages[c] + "' style='width:150px;height:150px' alt='" + hotelImages[c] + "'></p>\n" +
								"<br />\n" +
								"<p style='text-align:center;'><a href='#'>Hotel Name:&nbsp;&nbsp;" + hotelNames[c] + "</a></p>\n" +
								"<br />\n" +
								"<p style='text-align:center;'><a href='#'>Room Type:&nbsp;&nbsp;" + roomNames[c] + "</a></p>\n" +
								"<br />\n" +
								"<p style='text-align:center;'><a href='#'>Room Price:&nbsp;&nbsp;$" + roomPrices[c] + "</a></p>\n" +
								"<br />\n" +
							"</td>\n" +
							"<td style='width:460px;padding:15px;'>\n" +
								"<p style='text-align:center;'><img src='/EWAProject/" + hotelImages[c+1] + "' style='width:150px;height:150px' alt='" + hotelImages[0] + "'></p>\n" +
								"<br />\n" +
								"<p style='text-align:center;'><a href='#'>Hotel Name:&nbsp;&nbsp;" + hotelNames[c+1] + "</a></p>\n" +
								"<br />\n" +
								"<p style='text-align:center;'><a href='#'>Room Type:&nbsp;&nbsp;" + roomNames[c+1] + "</a></p>\n" +
								"<br />\n" +
								"<p style='text-align:center;'><a href='#'>Room Price:&nbsp;&nbsp;$" + roomPrices[c+1] + "</a></p>\n" +
								"<br />\n" +
							"</td>\n" +
						"</tr>\n" +
					"</table>\n" +
					"</div>");
			}
			
			pWriter.println("<div class='item'>\n" +
					"<table id='carouselTable'>\n" +
					"<tr>\n" +
						"<td style='width:920px;padding:15px;'>\n" +
							"<p style='text-align:center;'><img src='/EWAProject/" + hotelImages[i-1] + "' style='width:150px;height:150px' alt='" + hotelImages[i-1] + "'></p>\n" +
							"<br />\n" +
							"<p style='text-align:center;'><a href='#'>Hotel Name:&nbsp;&nbsp;" + hotelNames[i-1] + "</a></p>\n" +
							"<br />\n" +
							"<p style='text-align:center;'><a href='#'>Room Type:&nbsp;&nbsp;" + roomNames[i-1] + "</a></p>\n" +
							"<br />\n" +
							"<p style='text-align:center;'><a href='#'>Room Price:&nbsp;&nbsp;$" + roomPrices[i-1] + "</a></p>\n" +
							"<br />\n" +
						"</td>\n" +
					"</tr>\n" +
					"</table>\n" +
					"</div>\n" +				
				"</div>\n" +
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
		else {
			pWriter.println("<table id='carouselTable'>\n" +
					"<tr>\n" +
						"<td><p>" + dbStatus.get("msg") + "</p></td>\n" +
					"</tr>\n" +
					"</table>");
		}
	}
	
	public void printPaymentDetails(PrintWriter pWriter,String[] hotelNames,Date[] checkInDate,Date[] checkOutDate,String[] roomNames,double[] roomPrice,double totalPrice) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		pWriter.println("</td>\n" + 
				"<td style='text-align:left;width:59%;padding-left: 50px;'>\n" + 
					"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"<tr>\n" +	
							"<td colspan='2' style='height:40px;'><p></p></td>\n" +
						"</tr>\n" +
						"<tr>\n" +	
							"<th colspan='2' style='padding:30px;'><div align='center'>Payment Details</div></th>\n" +
						"</tr>");
		for(int i=0;i<hotelNames.length;i++) {
			if(hotelNames[i]!=null) {
				pWriter.println("<tr>\n" +
						"<td style='text-align:left;padding-left:40px;width:40%'>\n" +							
							"<p>Hotel Name:&nbsp;&nbsp;" + hotelNames[i] + "</p>\n" +
						"</td>\n" +
						"<td style='text-align:left;padding-left:20px;width:60%'>\n" +
							"<p>Room Type:&nbsp;&nbsp;" + roomNames[i] + "</p>\n" +							
						"</td>\n" +
					"</tr>\n" +
					"<tr>\n" +
						"<td style='text-align:left;padding-left:40px;padding-bottom:10px;width:50%'>\n" +
							"<p>Check-In Date:&nbsp;&nbsp;'" + formatter.format(checkInDate[i]) + "</p>\n" +
						"</td>\n" +
						"<td style='text-align:left;padding-left:20px;padding-bottom:10px;width:50%'>\n" +
							"<p>Check-Out Date:&nbsp;&nbsp;" + formatter.format(checkOutDate[i]) + "</p>\n" +
						"</td>\n" +
						"</tr>");
			}
		}
		pWriter.println("<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='TotalPrice'>Total Price : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='totalPrice' style='width:300px;background-color:#bfbfbf;' name='totalPrice' value='" + String.valueOf(totalPrice) + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='customerName'>Customer Name : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='customerName' style='width:300px' name='customerName' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='CardType'>Card Type : </label>\n" +
							"</td>\n" +
							"<td style='text-align:left;padding:7px;width:60%'>\n" +
								"<input type='radio' name='cardType' value='VISA'>&nbsp;&nbsp;<img src='/EWAProject/images/VISALogo.jpg' style='width:40px;height:30px' alt='VISALogo'>&nbsp;&nbsp;\n" +  
								"<input type='radio' name='cardType' value='MasterCard'>&nbsp;&nbsp;<img src='/EWAProject/images/MasterCardLogo.jpg' style='width:40px;height:30px' alt='MasterCardLogo'>&nbsp;&nbsp;\n" +
							  	"<input type='radio' name='cardType' value='AmericanExpress'>&nbsp;&nbsp;<img src='/EWAProject/images/AmericanExpress.jpg' style='width:40px;height:30px' alt='AmericanExpress'>&nbsp;&nbsp;\n" +
							  	"<input type='radio' name='cardType' value='Discover'>&nbsp;&nbsp;<img src='/EWAProject/images/Discover.jpg' style='width:40px;height:30px' alt='Discover'>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='CardNumber'>Card Number : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='number' id='cardnumber' style='width:300px' name='cardnumber' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='CVV'>CVV : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='number' id='cvv' style='width:300px' name='cvv' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='CardholderName'>Cardholder Name : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='cardholdername' style='width:300px' name='cardholdername' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='State'>State : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='state' style='width:300px' name='state' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='Address1'>Address 1 : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='address1' style='width:300px' name='address1' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='Address2'>Address 2 : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='address2' style='width:300px' name='address2'/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='City'>City : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='text' id='city' style='width:300px' name='city' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='ZipCode'>Zip Code : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='number' id='zipCode' style='width:300px' name='zipCode' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='MobileNumber'>Mobile Number : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='number' id='mobileNumber' style='width:300px' name='mobileNumber' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:right;padding:20px;width:40%'>\n" +
								"<label for='EmailAddress'>Email Address : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;width:60%'>\n" +
								"<input type='email' id='emailAddress' style='width:300px' name='emailAddress' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:7px;' class='date_btn'>\n" +
								"<input type='submit' value='Submit' style='width: 120px;'>\n" + 
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" + 
							"<td colspan='2' style='height:25px;text-align:right;padding:15px;'>\n" +
								"<a href='/EWAProject/Controller/LoginServlet?op=CustomerHome'>Account</a>\n" + 	
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession userSession = request.getSession(true);
			String username = (String)userSession.getAttribute("customerKey");
			if(username!=null) {
				ReserveList rl = (ReserveList)userSession.getAttribute("ReserveList");
				String op = request.getParameter("op");
				if(op==null) {
					//Code to display List
					if(rl!=null) {
						Date[] checkInDates = rl.getCheckInDates();
						Date[] checkOutDates = rl.getCheckOutDates();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						if(checkInDates[0]==null && checkOutDates[0]==null) {
							Date date = new Date();
							
							checkInDates[0] = formatter.parse(formatter.format(date));
							Calendar dateCalender = Calendar.getInstance();
							dateCalender.setTime(date);
							dateCalender.add(Calendar.DAY_OF_MONTH, 2);
							checkOutDates[0] = dateCalender.getTime();
						}
						int listSize = rl.getListSize();
						ArrayList<String> hotelList = rl.getHotelList();
						
						
						response.setContentType("text/html");
						PrintWriter pWriter = response.getWriter();
						HtmlEngine engine = new HtmlEngine(pWriter);
						if(hotelList!=null && listSize>0) {
							engine.generateHtml("Header", request,"/EWAProject/Controller/MyListServlet","POST");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<th colspan='4' style='text-align:center;height:30px;'><p>My Lists</p></th> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<td colspan='4' style='text-align:center;height:30px;'>\n" +
													"<p><h2>You have " + listSize + " hotels in your cart</h2></p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +	
												//"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th>\n" +
												"<th colspan='2' style='text-align:center;padding:33px;'><div align='center'>Hotel Name</div></th>\n" +
												"<th style='text-align:center;'><div align='center'>Room Type</div></th>\n" +
												"<th style='text-align:center;'><div align='center'></div></th>\n" +
											"</tr>");
							
							HashMap<String, String> dbStatus = new HashMap<String, String>();
							dbStatus = dbObj.checkDBConnection();
							if(dbStatus.get("status").equals("true")) {
								Iterator<String> listItr = hotelList.iterator();
								int i=1;
								while(listItr.hasNext()) {
									String hotelID = listItr.next().toString();
									Hotels hotel = new Hotels();
									hotel = dbObj.getHotel(hotelID);
									HashMap<String, Rooms> roomsMap = hotel.getRooms();
									Rooms room = new Rooms();
									pWriter.println("<tr>\n" +
											"<td class='MyListTd' colspan='2' style='text-align:center;margin:2%;padding-left:13px;'>\n" +
												"<input type='hidden' style='width:200px;background-color:#bfbfbf;' name='hotelID" + String.valueOf(i) + "' value='" + hotel.getID() + "' readonly/>\n" +
												"<input type='text' style='width:200px;background-color:#bfbfbf;' name='hotelName" + String.valueOf(i) + "' value='" + hotel.getName() + "' readonly/>\n" +
												//"<label style='padding:20px;' name='hotelID" + String.valueOf(i) + "'>" + hotel.getID() + "</label>\n" +
											"</td>\n" +
											"<td style='text-align:center;'>\n" +
												"<select id='roomType' name='roomType" + String.valueOf(i) + "'>");
									for(Map.Entry<String, Rooms> r : roomsMap.entrySet()) {
										pWriter.println("<option value='" + r.getKey() + "'>" + r.getKey() + "</option>");
									}
									pWriter.println("</select>\n" + 
											"</td>\n" +
											"<td style='padding:20px;' class='rsvBtn'>\n" +
												"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/MyListServlet?op=delete&id=" + hotelID + "'>Delete</a>\n" +
												"<script>\n" + 
													"$(function() {\n" + 
														"$( '#checkInDate" + String.valueOf(i) + ",#checkOutDate" + String.valueOf(i) + "' ).datepicker();\n" + 
													"});\n" + 
												"</script>\n" +
											"</td>\n" +
										"</tr>\n" + 	
										"<tr>\n" +	
											"<td colspan='2' style='text-align:center;' class='MyList btm'>\n" +
												"<p>Check-In Date:</p>\n" +
												"<input class='date' id='checkInDate" + String.valueOf(i) + "' name='checkInDate" + String.valueOf(i) + "' type='text' value='" + formatter.format(checkInDates[0]) + "'>\n" +
											"</td>\n" +
											"<td colspan='2' style='text-align:center;' class='MyList btm'>\n" +
												"<p>Check-Out Date:</p>\n" +
												"<input class='date' id='checkOutDate" + String.valueOf(i) + "' name='checkOutDate" + String.valueOf(i) + "' type='text' value='" + formatter.format(checkOutDates[0]) + "'>\n" +
											"</td>\n" +
										"</tr>");
									i++;
								}
							}
							else {
								pWriter.println("<tr>\n" +
										"<td colspan='4' style='text-align:center;'>\n" +
											"<p>" + dbStatus.get("msg") + "</p>\n" +
									"</td>\n" +
									"</tr>");
							}
							
							pWriter.println("<tr>\n" +
												"<td colspan='4' style='text-align:center;'>\n" +
													"<p></p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +	
												"<td colspan='4' class='date_btn' style='text-align:right;padding:20px;'>\n" +
													"<input type='submit' style='width: 120px;padding:3px;' value='Checkout'>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +	
												"<td colspan='4' style='text-align:center;padding:20px;'>");
													printCarousel(pWriter,rl);
								pWriter.println("</td>\n" +
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
							engine.generateHtml("Header",request);
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;'><p>My Lists</p></th> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<td colspan='2' style='text-align:center;padding:10px;'>\n" +
													"<p><h2>List is empty. Please add hotels to proceed further.</h2></p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;padding:10px;'>\n" +
													"<p>Please use the link below to go back to Home Page.</p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +	
												"<td colspan='2' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
													"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/LoginServlet?op=CustomerHome' method='get'>Customer Home</a>\n" +
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
					}
					else {
						response.sendRedirect("/EWAProject/Controller/LoginServlet");
					}
				}
				else if(op.equals("delete")) {
					//Code to delete from List
					String id = request.getParameter("id");
					int listSize = rl.getListSize();
					ArrayList<String> hotelList = rl.getHotelList();
					if(hotelList!=null) {
						hotelList.remove(id);
						listSize = listSize-1;
					}
					rl.setListSize(listSize);
					rl.setHotelList(hotelList);
					rl.setUsername(username);
					userSession.setAttribute("ReserveList", rl);
					//After Deletion
					response.sendRedirect("/EWAProject/Controller/MyListServlet");
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
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		try {
			HttpSession userSession = request.getSession(true);
			ReserveList rl = (ReserveList)userSession.getAttribute("ReserveList");
			if(rl!=null) {
				int listSize = rl.getListSize();
				ArrayList<String> hotelList = rl.getHotelList();
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = dbObj.checkDBConnection();
				if(dbStatus.get("status").equals("true")) {
					Iterator<String> listItr = hotelList.iterator();
					int i=1;					
					String[] hotelIDList = new String[30];
					String[] hotelNameList = new String[30];
					String[] roomTypeList = new String[30];
					Date[] checkInDates = new Date[30];
					Date[] checkOutDates = new Date[30];
					double[] roomPrices = new double[30];
					int[] guestCounts = new int[30];
					
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					boolean dateFlag = false;
					double totalPrice = 0.0;
					while(listItr.hasNext()) {
						String hotelID = listItr.next().toString();
						hotelIDList[i] = request.getParameter("hotelID" + String.valueOf(i));
						System.out.println(hotelIDList[i]);
						hotelNameList[i] = request.getParameter("hotelName" + String.valueOf(i));
						System.out.println(hotelNameList[i]);
						roomTypeList[i] = request.getParameter("roomType" + String.valueOf(i));
						System.out.println(roomTypeList[i]);
						guestCounts[i] = 2;
						
						Hotels hotel = dbObj.getHotel(hotelIDList[i]);
						HashMap<String, Rooms> roomsMap = hotel.getRooms();
						Rooms roomObj = roomsMap.get(roomTypeList[i]);
						roomPrices[i] = roomObj.getRoomPrice();
						totalPrice = totalPrice + roomPrices[i];
						
						checkInDates[i] = formatter.parse(request.getParameter("checkInDate" + String.valueOf(i)));
						checkOutDates[i] = formatter.parse(request.getParameter("checkOutDate" + String.valueOf(i)));
						if(!checkOutDates[i].after(checkInDates[i])) {
							dateFlag = true;
						}
						i++;
					}
					if(dateFlag==false) {
						rl.setHotelIDList(hotelIDList);
						rl.setHotelNameList(hotelNameList);
						rl.setRoomTypeList(roomTypeList);
						rl.setCheckInDates(checkInDates);
						rl.setCheckOutDates(checkOutDates);
						rl.setRoomPrices(roomPrices);
						rl.setGuestCounts(guestCounts);
						
						userSession.setAttribute("ReserveList", rl);
						engine.generateHtml("Header",request,"/EWAProject/Controller/BookHotel?op=multipleHotels","POST");
						engine.generateHtml("LeftNav",request);
						
						printPaymentDetails(pWriter,hotelNameList,checkInDates,checkOutDates,roomTypeList,roomPrices,totalPrice);
						
						engine.generateHtml("Footer",request);
					}		
					else {
						engine.generateHtml("Header",request);
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:59%'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td style='height:25px;'>\n" +
												"<p>Invalid CheckIn/CheckOut Dates. Please correct these values and try again.</p>\n" +	
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td style='height:25px;text-align:right;'>\n" +
												"<a href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +	
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
						engine.generateHtml("Footer",request);
					}
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
					engine.generateHtml("Footer",request);
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