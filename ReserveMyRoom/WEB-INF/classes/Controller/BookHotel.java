package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Controller.HtmlEngine;
import JavaBeans.*;
import DataAccess.MySQLDataStoreUtilities;

public class BookHotel extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	
	public BookHotel() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void printPaymentDetails(PrintWriter pWriter,String id,String hotelName,Date checkInDate,Date checkOutDate,String roomName,int roomCount,int guestCount,double roomPrice) {
		double totalPrice = 0.0; 
		if(guestCount<=2) {
			totalPrice = roomCount * roomPrice;
		}
		else if(guestCount>2) {
			totalPrice = 2 * roomCount * roomPrice;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		pWriter.println("</td>\n" + 
				"<td style='text-align:left;width:59%'>\n" + 
					"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"<tr>\n" +	
							"<td colspan='2' style='height:40px;'><p></p></td>\n" +
						"</tr>\n" +
						"<tr>\n" +	
							"<th colspan='2'><div align='center'>Payment Details</div></th>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='hotelID'>Hotel ID : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='hotelID' style='width:300px;background-color:#bfbfbf;' name='hotelID' value='" + id + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='RoomType'>Room Type : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='roomType' style='width:300px;background-color:#bfbfbf;' name='roomType' value='" + roomName + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='GuestCount'>Number of Guests : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='guestCount' style='width:300px;background-color:#bfbfbf;' name='guestCount' value='" + guestCount + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='checkInDate'>Check-In Date : </label>\n" +
							"</td>\n" +
							"<td style='text-align:left;padding:7px;'>\n" +
								"<input type='text' id='checkInDate' style='width:120px;background-color:#bfbfbf;' name='checkInDate' value='" + formatter.format(checkInDate) + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='checkOutDate'>Check-Out Date : </label>\n" +
							"</td>\n" +
							"<td style='text-align:left;padding:7px;'>\n" +
								"<input type='text' id='checkOutDate' style='width:120px;background-color:#bfbfbf;' name='checkOutDate' value='" + formatter.format(checkOutDate) + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='TotalPrice'>Total Price : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='totalPrice' style='width:300px;background-color:#bfbfbf;' name='totalPrice' value='" + String.valueOf(totalPrice) + "' readonly/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='customerName'>Customer Name : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='customerName' style='width:300px' name='customerName' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='CardType'>Card Type : </label>\n" +
							"</td>\n" +
							"<td style='text-align:left;padding:7px;'>\n" +
								"<input type='radio' name='cardType' value='VISA'>&nbsp;&nbsp;<img src='/EWAProject/images/VISALogo.jpg' style='width:40px;height:30px' alt='VISALogo'>&nbsp;&nbsp;\n" +  
								"<input type='radio' name='cardType' value='MasterCard'>&nbsp;&nbsp;<img src='/EWAProject/images/MasterCardLogo.jpg' style='width:40px;height:30px' alt='MasterCardLogo'>&nbsp;&nbsp;\n" +
							  	"<input type='radio' name='cardType' value='AmericanExpress'>&nbsp;&nbsp;<img src='/EWAProject/images/AmericanExpress.jpg' style='width:40px;height:30px' alt='AmericanExpress'>&nbsp;&nbsp;\n" +
							  	"<input type='radio' name='cardType' value='Discover'>&nbsp;&nbsp;<img src='/EWAProject/images/Discover.jpg' style='width:40px;height:30px' alt='Discover'>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='CardNumber'>Card Number : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='number' id='cardnumber' style='width:300px' name='cardnumber' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='CVV'>CVV : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='number' id='cvv' style='width:300px' name='cvv' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='CardholderName'>Cardholder Name : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='cardholdername' style='width:300px' name='cardholdername' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='State'>State : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='state' style='width:300px' name='state' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='Address1'>Address 1 : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='address1' style='width:300px' name='address1' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='Address2'>Address 2 : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='address2' style='width:300px' name='address2'/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='City'>City : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='text' id='city' style='width:300px' name='city' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='ZipCode'>Zip Code : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='number' id='zipCode' style='width:300px' name='zipCode' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='MobileNumber'>Mobile Number : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
								"<input type='number' id='mobileNumber' style='width:300px' name='mobileNumber' required/>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td style='text-align:center;'>\n" +
								"<label for='EmailAddress'>Email Address : </label>\n" +
							"</td>\n" +
							"<td style='text-align:center;padding:7px;'>\n" +
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
		String id = request.getParameter("hotelID");
		String hotelID = request.getParameter("hotelID");
		String hotelName = request.getParameter("hotelName");
		String checkInDateString = request.getParameter("checkInDate");
		String checkOutDateString = request.getParameter("checkOutDate");
		String room = request.getParameter("room");
		System.out.println("RoomName-" + room);
		String roomCount = request.getParameter("roomCount");
		String guestCount = request.getParameter("guestCount");
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			
			HttpSession userSession = request.getSession(true);
			String username = (String)userSession.getAttribute("customerKey");
			if(username!=null) {
				if(checkInDateString!=null && checkOutDateString!=null && !checkInDateString.equals("MM/DD/YYYY") && !checkOutDateString.equals("MM/DD/YYYY")) {
					Date checkInDate = formatter.parse(checkInDateString);
					Date checkOutDate = formatter.parse(checkOutDateString);
					if(checkOutDate.after(checkInDate)) {
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							Hotels hotel = dbObj.getHotel(id);
							HashMap<String, Rooms> roomsMap = new HashMap<String, Rooms>();
							roomsMap = hotel.getRooms();
							//System.out.println("RoomMap Size - " + roomsMap.size() + "\tIs Empty - " + roomsMap.isEmpty());
							Rooms tmp = new Rooms();
							Rooms roomObj = new Rooms();
							for(Map.Entry<String, Rooms> r : roomsMap.entrySet()) {
								tmp = r.getValue();
								if(tmp.getRoomName().equals(room)) {
									roomObj = tmp;
									break;
								}
							}
							engine.generateHtml("Header",request,"/EWAProject/Controller/BookHotel?id=" + hotel.getID(),"POST");
							engine.generateHtml("LeftNav",request);
							printPaymentDetails(pWriter,id,hotelName,checkInDate,checkOutDate,room,Integer.parseInt(roomCount),Integer.parseInt(guestCount),roomObj.getRoomPrice());
							engine.generateHtml("Footer",request);
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
				response.sendRedirect("/EWAProject/Controller/LoginServlet");
			}
		}
		catch(Exception ex) {			
			ex.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		try {
			String op = request.getParameter("op");
			if(op==null) {
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				HttpSession userSession = request.getSession(true);
				String username = (String)userSession.getAttribute("customerKey");
				String hotelID = request.getParameter("id");
				String roomType = request.getParameter("roomType");
				int guestCount = Integer.parseInt(request.getParameter("guestCount"));
				Date checkInDate = formatter.parse(request.getParameter("checkInDate"));
				Date checkOutDate = formatter.parse(request.getParameter("checkOutDate"));
				double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
				double tax = 5.60;
				totalPrice = totalPrice + tax;
				String customerName = request.getParameter("customerName");
				String state = request.getParameter("state");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String city = request.getParameter("city");
				int zipCode = Integer.parseInt(request.getParameter("zipCode"));
				String mobileNumber = request.getParameter("mobileNumber");
				String emailAddress = request.getParameter("emailAddress");
				
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				
				if(customerName!=null && state !=null && address1!=null && address2 !=null && city!=null && mobileNumber!=null && emailAddress!=null) {
					HashMap<String, String> dbStatus = new HashMap<String, String>();
					dbStatus = dbObj.checkDBConnection();
					if(dbStatus.get("status").equals("true")) {
						Random rand = new Random();
						Date date = new Date();
						String currentDate = formatter.format(date);
						int  reservationID = rand.nextInt(999999999) + 99999999;
						
						String[] hotelIDs = new String[30];
						String[] roomNames = new String[30];
						Date[] checkInDates = new Date[30];
						Date[] checkOutDates = new Date[30];
						int[] guestCounts = new int[30];
						
						hotelIDs[0] = hotelID;
						roomNames[0] = roomType;
						checkInDates[0] = checkInDate;
						checkOutDates[0] = checkOutDate;
						guestCounts[0] = guestCount;
						
						Reservations reservation = new Reservations(customerName,username,formatter.parse(currentDate),checkInDates,checkOutDates,reservationID,hotelIDs,roomNames,guestCounts,totalPrice,state,address1,address2,city,zipCode,mobileNumber,emailAddress);
						dbObj.writeReservation(reservation);
						
						//Display Reservation Details
						engine.generateHtml("Header",request);
						engine.generateHtml("LeftNav",request);
						pWriter.println("</td>\n" + 
								"<td style='text-align:left;width:69%;padding-left: 50px;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='3' style='height:30px;'><p></p></td> \n" + 
										"</tr>\n" +
										"<tr>\n" +	
											"<th colspan='3'><div align='center'>Order Details</div></th>\n" +
										"</tr>\n" +
										"<tr>\n" + 
											"<td colspan='3' style='height:25px;padding:10px;'>\n" +
												"<p>Reservation Details</p>\n" +	
											"</td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +	
											"<td colspan='3' style='height:10px;'><p></p></td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
												"<p><label for='ShippingAddress' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Shipping Address</label>\n" +
												"<br />\n" +
												"<label for='customerName'>Customer Name:&nbsp;" + reservation.getCustomerName() + "</label>\n" +
												"<br />\n" +
												"<label for='Address1'>" + reservation.getAddress1() + "</label>\n" +
												"<br />\n" +
												"<label for='Address2'>" + reservation.getAddress2() + "</label>\n" +
												"<br />\n" +
												"<label for='City'>" + reservation.getCity() + "</label>\n" +
												"<br />\n" +
												"<label for='State'>" + reservation.getState() + "</label>\n" +
												"<br />\n" +
												"<label for='ZipCode'>ZipCode:&nbsp;" + String.valueOf(reservation.getZipCode()) + "</label>\n" +
												"<br />\n" +
												"<p>Mobile Number:&nbsp;&nbsp;" + String.valueOf(reservation.getMobileNumber()) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
												"<label for='reservationIDlbl'>Reservation ID:&nbsp;</label><label for='reservationID'>" + reservation.getReservationID() + "</label>\n" +
												"<br />\n" +
												"<label for='reservationDatelbl' >Reservation Date:&nbsp;</label><label for='reservationDate'>" + formatter.format(reservation.getReservationDate()) + "</label>\n" +
												"<br />\n" +
												"<label for='CheckInDate'>Check-In Date:&nbsp;</label><label for='checkInDate'>" + formatter.format(checkInDate) + "</label>\n" +
												"<br />\n" +
												"<label for='CheckOutDate'>Check-Out Date:&nbsp;</label><label for='checkOutDate'>" + formatter.format(checkOutDate) + "</label>\n" +
												"<br />\n" +
												"<label for='orderStatuslbl''>Order Status:&nbsp;</label><label for='orderStatus''>In Progress</label>\n" +
												"<br />\n" +
												"<label for='emailAddresslbl'>Email Address:&nbsp;</label><label for='emailAddress'>" + reservation.getEmailAddress() + "</label>\n" +
											"</td>\n" +
											"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
												"<p><label for='HotelDetails' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Hotel Details</label></p>\n" +
												"<br />\n" +
												"<label for='hotelID'>Hotel ID:&nbsp;" + hotelID + "</label>\n" +
												"<br />\n" +
												"<label for='roomType'>Room Type:&nbsp;" + roomType + "</label>\n" +
												"<br />\n" +
												"<label for='roomType'>Number of Guests:&nbsp;" + String.valueOf(guestCount) + "</label>\n" +
												"<p><label for='OrderTotals' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Order Totals</label></p>\n" +
												"<br />\n" +
												"<label for='Total'>Order Sub-Total:&nbsp;$" + String.valueOf(reservation.getTotalPrice()-5.60) + "</label>\n" +
												"<br />\n" +
												"<label for='Tax'>Tax:&nbsp;$5.60</label>\n" +
												"<br />\n" +
												"<label for='Total'>Total Price:&nbsp;$" + reservation.getTotalPrice() + "</label>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" + 
											"<td colspan='3' style='height:25px;text-align:right;padding:10px;'>\n" +
												"<a href='/EWAProject/Controller/LoginServlet?op=CustomerHome'>Account</a>\n" + 
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
						engine.generateHtml("Footer",request);
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
											"<p>Invalid input Values. Please check the values and try again.</p>\n" +	
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
				HttpSession userSession = request.getSession(true);
				ReserveList rl = (ReserveList)userSession.getAttribute("ReserveList");
				if(rl!=null) {
					
					String[] hotelIDs = rl.getHotelIDList();
					String[] hotelNames = rl.getHotelNameList();
					String[] roomNames = rl.getRoomTypeList();
					Date[] checkInDates = rl.getCheckInDates();
					Date[] checkOutDates = rl.getCheckOutDates();
					double[] roomPrices = rl.getRoomPrices();
					int[] guestCounts = rl.getGuestCounts();
					
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					String username = (String)userSession.getAttribute("customerKey");
					double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
					double tax = 5.60;
					totalPrice = totalPrice + tax;
					String customerName = request.getParameter("customerName");
					String state = request.getParameter("state");
					String address1 = request.getParameter("address1");
					String address2 = request.getParameter("address2");
					String city = request.getParameter("city");
					int zipCode = Integer.parseInt(request.getParameter("zipCode"));
					String mobileNumber = request.getParameter("mobileNumber");
					String emailAddress = request.getParameter("emailAddress");
					
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					
					if(customerName!=null && state !=null && address1!=null && address2 !=null && city!=null && mobileNumber!=null && emailAddress!=null) {
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							Random rand = new Random();
							Date date = new Date();
							String currentDate = formatter.format(date);
							int  reservationID = rand.nextInt(999999999) + 99999999;
							
							Reservations reservation = new Reservations(customerName,username,formatter.parse(currentDate),checkInDates,checkOutDates,reservationID,hotelIDs,roomNames,guestCounts,totalPrice,state,address1,address2,city,zipCode,mobileNumber,emailAddress);
							dbObj.writeReservation(reservation);
							rl.setCheckInDates(null);
							rl.setCheckOutDates(null);
							rl.setGuestCounts(null);
							rl.setHotelIDList(null);
							rl.setHotelList(null);
							rl.setHotelNameList(null);
							rl.setListSize(0);
							rl.setRoomPrices(null);
							rl.setRoomTypeList(null);
							userSession.setAttribute("ReserveList", rl);
							//Display Reservation Details
							engine.generateHtml("Header",request);
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:69%;padding-left: 50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='3' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" +
											"<tr>\n" +	
												"<th colspan='3'><div align='center'>Order Details</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='3' style='height:25px;padding:10px;'>\n" +
													"<p>Reservation Details</p>\n" +	
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +	
												"<td colspan='3' style='height:10px;'><p></p></td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
													"<p><label for='ShippingAddress' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Shipping Address</label>\n" +
													"<br />\n" +
													"<label for='customerName'>Customer Name:&nbsp;" + reservation.getCustomerName() + "</label>\n" +
													"<br />\n" +
													"<label for='Address1'>" + reservation.getAddress1() + "</label>\n" +
													"<br />\n" +
													"<label for='Address2'>" + reservation.getAddress2() + "</label>\n" +
													"<br />\n" +
													"<label for='City'>" + reservation.getCity() + "</label>\n" +
													"<br />\n" +
													"<label for='State'>" + reservation.getState() + "</label>\n" +
													"<br />\n" +
													"<label for='ZipCode'>ZipCode:&nbsp;" + String.valueOf(reservation.getZipCode()) + "</label>\n" +
													"<br />\n" +
													"<p>Mobile Number:&nbsp;&nbsp;" + String.valueOf(reservation.getMobileNumber()) + "</p>\n" +
												"</td>\n" +
												"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
													"<label for='reservationIDlbl'>Reservation ID:&nbsp;</label><label for='reservationID'>" + reservation.getReservationID() + "</label>\n" +
													"<br />\n" +
													"<label for='reservationDatelbl' >Reservation Date:&nbsp;</label><label for='reservationDate'>" + formatter.format(reservation.getReservationDate()) + "</label>\n" +
													"<br />\n" +
													"<label for='orderStatuslbl''>Order Status:&nbsp;</label><label for='orderStatus''>In Progress</label>\n" +
													"<br />\n" +
													"<label for='emailAddresslbl'>Email Address:&nbsp;</label><label for='emailAddress'>" + reservation.getEmailAddress() + "</label>\n" +
												"</td>\n" +
												"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
													"<p><label for='HotelDetails' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Hotel Details</label></p>\n" +
													"<br />");
							for(int i=0;i<hotelIDs.length;i++) {		
								if(hotelIDs[i]!=null) {
									pWriter.println("<p>Hotel ID:&nbsp;&nbsp;" + hotelIDs[i] + "&nbsp;&nbsp;RoomType:" + roomNames[i] + "</p>\n" +
											"<p>Check-InDate:&nbsp;&nbsp;" + formatter.format(checkInDates[i]) + "&nbsp;&nbsp;Check-OutDate:&nbsp;&nbsp;" + formatter.format(checkOutDates[i]) + "</p>");
								}
							}
									pWriter.println("<p><label for='OrderTotals' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Order Totals</label></p>\n" +
													"<br />\n" +
													"<label for='Total'>Order Sub-Total:&nbsp;$" + String.valueOf(reservation.getTotalPrice()-5.60) + "</label>\n" +
													"<br />\n" +
													"<label for='Tax'>Tax:&nbsp;$5.60</label>\n" +
													"<br />\n" +
													"<label for='Total'>Total Price:&nbsp;$" + reservation.getTotalPrice() + "</label>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='3' style='height:25px;text-align:right;padding:10px;'>\n" +
													"<a href='/EWAProject/Controller/LoginServlet?op=CustomerHome'>Account</a>\n" + 
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
							engine.generateHtml("Footer",request);
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
												"<p>Invalid input Values. Please check the values and try again.</p>\n" +	
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
					response.sendRedirect("/EWAProject/Controller/LoginServlet");
				}
			}
		}
		catch(Exception ex) {			
			ex.printStackTrace();
		}
	}
}