package Controller;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

import javax.servlet.*;
import javax.servlet.http.*;
import DataAccess.MySQLDataStoreUtilities;
import Controller.HtmlEngine;
import JavaBeans.*;

public class ManagerServlet extends HttpServlet{
	MySQLDataStoreUtilities dbObj;
	
	public ManagerServlet() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession userSession = request.getSession(true);
			String manager = (String)userSession.getAttribute("managerKey");
			if(manager!=null) {
				String op = request.getParameter("op");
				if(op!=null) {
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					
					switch(op) {
						case "addHotel":{
							engine.generateHtml("HeaderManager",request,"/EWAProject/Controller/ManagerServlet?task=addHotel","POST");
							engine.generateHtml("LeftNav",request);
							
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Add Hotel Form</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='username'>HotelID :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='hotelID' style='width:300px' name='hotelID' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" + 
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='hotelName'>Hotel Name :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='hotelName' style='width:300px' name='hotelName' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" + 
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='hotelImage'>Hotel Image :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='hotelImage' style='width:300px' name='hotelImage' required/>\n" +
												"</td>\n" + 
											"</tr>\n" + 
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='roomName'>Room Name :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='roomName' style='width:300px' name='roomName' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" + 
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='roomCount'>Number of Rooms :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='number' id='roomCount' min='1' max='40' style='width:300px' name='roomCount' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" + 
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='roomPrice'>Room Price :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='number' id='roomPrice' min='100' style='width:300px' name='roomPrice' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='roomImage'>Room Image :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='roomImage' style='width:300px' name='roomImage' required/>\n" +
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='wifi'>Wifi :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='radio' name='wifi' value='Yes'>Yes<br>\n" +  
													"<input type='radio' name='wifi' value='No'>No<br>\n" +
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='restaurant'>Restaurant :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='radio' name='restaurant' value='Yes'>Yes<br>\n" +  
													"<input type='radio' name='restaurant' value='No'>No<br>\n" +
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='zipcode'>Zipcode :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='number' id='zipcode' max='99999' style='width:300px' name='zipcode' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='address1'>Address1 :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='address1' style='width:300px' name='address1' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='address2'>Address2 :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='address2' style='width:300px' name='address2' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='city'>City :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='city' style='width:300px' name='city' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='state'>State :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='state' style='width:300px' name='state' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='phoneNumber'>PhoneNumber :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='text' id='phoneNumber' style='width:300px' name='phoneNumber' required/>\n" + 
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='onSale'>OnSale :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='radio' name='onSale' value='Yes'>Yes<br>\n" +  
													"<input type='radio' name='onSale' value='No'>No<br>\n" +
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
													"<label for='rebate'>Rebate :&nbsp;&nbsp;</label>\n" + 
												"</td>\n" + 
												"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
													"<input type='radio' name='rebate' value='Yes'>Yes<br>\n" +  
													"<input type='radio' name='rebate' value='No'>No<br>\n" +
												"</td>\n" + 
											"</tr>\n" +
											"<tr> \n" + 
												"<td colspan='2' align='center'> \n" + 
													"<button name='sbmtButton' class='loginBtn' type='submit'>Submit</button> \n" + 
												"</td>\n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'><p></p></td>\n" + 
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
						case "updateHotelSelect":{
							Hotels hotel = new Hotels();
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='4' style='text-align:center;height:40px;'><div align='center'>Update Hotel</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
											"</tr>");
							HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
							if(hotelsMap==null || hotelsMap.isEmpty()) {
								pWriter.println("<tr>\n" +	
													"<td colspan='4' style='text-align:center;'>\n" +
														"<label for='orderStatusLbl'>No Hotels Found</label>\n" +
													"</td>\n" +
												"</tr>");
							}
							else {
								pWriter.println("<tr>\n" + 
										"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th> \n" +
										"<th style='text-align:left;'><div align='left'>Zip Code</div></th> \n" +
										"<th style='text-align:left;'><div align='left'>City</div></th> \n" +
										"<th></th> \n" +
									"</tr>");
								for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
									hotel = h.getValue();
									pWriter.println("<tr style='height:35px;'> \n" + 
											"<td style='text-align:left;margin:2%;padding-left:13px;width:300px;'> \n" + 
												"<a style='padding:20px;' href='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' method='get'>" + hotel.getID() + "</a>\n" +
											"</td> \n" + 
											"<td style='text-align:left;'> \n" + 
												"<p>" + hotel.getZipCode() + "</p>\n" +
											"</td> \n" +
											"<td style='text-align:left;'> \n" + 
												"<p>" + hotel.getCity() + "</p>\n" +
											"</td> \n" +
											"<td class='rsvBtn'> \n" + 
												"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ManagerServlet?op=updateHotelForm&id=" + hotel.getID() + "' method='get'>Modify</a>\n" +
											"</td> \n" +
										"</tr>");
								}
							}
											 
							pWriter.println("<tr>\n" +
											"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
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
						case "updateHotelForm":{
							String hotelID = request.getParameter("id");
							Hotels hotel = dbObj.getHotel(hotelID);
							HashMap<String, Rooms> rooms = hotel.getRooms();
							Rooms room = new Rooms();
							if(hotel!=null) {
								engine.generateHtml("HeaderManager",request,"/EWAProject/Controller/ManagerServlet?task=updateHotel","POST");
								engine.generateHtml("LeftNav",request);
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Update Form</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='hotelID'>HotelID :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;padding:7px;'>\n" +
														"<input type='text' id='hotelID' style='width:300px;background-color:#bfbfbf;' name='hotelID' value='" + hotelID + "' readonly/>\n" +
													"</td>\n" +
												"</tr>\n" + 
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='hotelName'>Hotel Name :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='text' id='hotelName' style='width:300px' name='hotelName' value='" + hotel.getName() + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" + 
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='hotelImage'>Hotel Images :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>");
										int a=0;
										for(Map.Entry<String, String> k : hotel.getHotelImages().entrySet()) {
											pWriter.println("<input type='text' id='hotelImage" + String.valueOf(a) + "' style='width:300px' name='hotelImage" + String.valueOf(a) + "' value='" + k.getValue() + "' readonly/>");
											a++;
										}
										pWriter.println("</td>\n" + 
												"</tr>"); 
												int count=0;
												for(Map.Entry<String, Rooms> j : rooms.entrySet()) {
													room = j.getValue();
													pWriter.println("<tr> \n" + 
															"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
																"<label for='roomName'>Room Name :&nbsp;&nbsp;</label>\n" + 
															"</td>\n" + 
															"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
																"<input type='text' id='roomName" + String.valueOf(count) + "' style='width:300px' name='roomName" + String.valueOf(count) + "' value='" + room.getRoomName() + "' readonly/>\n" + 
															"</td>\n" + 
														"</tr>\n" + 
														"<tr> \n" + 
															"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
																"<label for='roomCount'>Number of Rooms :&nbsp;&nbsp;</label>\n" + 
															"</td>\n" + 
															"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
																"<input type='number' id='roomCount" + String.valueOf(count) + "' min='1' max='40' style='width:300px' name='roomCount" + String.valueOf(count) + "' value='" + room.getRoomCount() + "' readonly/>\n" + 
															"</td>\n" + 
														"</tr>\n" + 
														"<tr> \n" + 
															"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
																"<label for='roomPrice'>Room Price :&nbsp;&nbsp;</label>\n" + 
															"</td>\n" + 
															"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
																"<input type='number' id='roomPrice" + String.valueOf(count) + "' min='100' style='width:300px' name='roomPrice" + String.valueOf(count) + "' value='" + room.getRoomPrice() + "' readonly/>\n" + 
															"</td>\n" + 
														"</tr>\n" +
														"<tr> \n" + 
															"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
																"<label for='roomImage'>Room Images :&nbsp;&nbsp;</label>\n" + 
															"</td>\n" + 
															"<td style='text-align:left;width:65%;padding:15px;'>"); 
													int i=0;
													for(Map.Entry<String, String> l : room.getRoomImages().entrySet()) {
														pWriter.println("<input type='text' id='roomImage" + room.getRoomName() + String.valueOf(i) + "' style='width:300px' name='roomImage" + room.getRoomName() + String.valueOf(i) + "' value='" + l.getValue() + "' readonly/>");
														i++;
													}
													pWriter.println("</td>\n" + 
														"</tr>");
													count++;
												}
								
								pWriter.println("<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='wifi'>Wifi :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>"); 
														if(hotel.getWifi()) {
															pWriter.println("<input type='radio' name='wifi' value='Yes' checked>Yes<br>\n" +  
																			"<input type='radio' name='wifi' value='No'>No<br>");
														}
														else {
															pWriter.println("<input type='radio' name='wifi' value='Yes'>Yes<br>\n" +  
																			"<input type='radio' name='wifi' value='No' checked>No<br>");
														}
														
									pWriter.println("</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='restaurant'>Restaurant :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>");
									if(hotel.getRestaurant()) {
										pWriter.println("<input type='radio' name='restaurant' value='Yes' checked>Yes<br>\n" +  
												"<input type='radio' name='restaurant' value='No'>No<br>");
									}
									else {
										pWriter.println("<input type='radio' name='restaurant' value='Yes'>Yes<br>\n" +  
												"<input type='radio' name='restaurant' value='No' checked>No<br>");
									}
									
									pWriter.println("</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='onSale'>OnSale :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>");
									if(hotel.getOnSale()) {
										pWriter.println("<input type='radio' name='onSale' value='Yes' checked>Yes<br>\n" +  
												"<input type='radio' name='onSale' value='No'>No<br>");
									}
									else {
										pWriter.println("<input type='radio' name='onSale' value='Yes'>Yes<br>\n" +  
												"<input type='radio' name='onSale' value='No' checked>No<br>");
									}
									
									pWriter.println("</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='rebate'>Rebate :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>");
									if(hotel.getRebate()) {
										pWriter.println("<input type='radio' name='rebate' value='Yes' checked>Yes<br>\n" +  
												"<input type='radio' name='rebate' value='No'>No<br>");
									}
									else {
										pWriter.println("<input type='radio' name='rebate' value='Yes'>Yes<br>\n" +  
												"<input type='radio' name='rebate' value='No' checked>No<br>");
									}
									
									pWriter.println("</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='zipcode'>Zipcode :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='number' id='zipcode' max='99999' style='width:300px' name='zipcode' value='" + String.valueOf(hotel.getZipCode()) + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='address1'>Address1 :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='text' id='address1' style='width:300px' name='address1' value='" + hotel.getAddress1() + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='address2'>Address2 :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='text' id='address2' style='width:300px' name='address2' value='" + hotel.getAddress2() + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='city'>City :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='text' id='city' style='width:300px' name='city' value='" + hotel.getCity() + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='state'>State :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='text' id='state' style='width:300px' name='state' value='" + hotel.getState() + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td style='text-align:right;width:35%;padding:15px;'>\n" + 
														"<label for='phoneNumber'>PhoneNumber :&nbsp;&nbsp;</label>\n" + 
													"</td>\n" + 
													"<td style='text-align:left;width:65%;padding:15px;'>\n" + 
														"<input type='text' id='phoneNumber' style='width:300px' name='phoneNumber' value='" + hotel.getPhoneNumber() + "' required/>\n" + 
													"</td>\n" + 
												"</tr>\n" +
												"<tr> \n" + 
													"<td colspan='2' align='center'> \n" + 
														"<button name='sbmtButton' class='loginBtn' type='submit'>Submit</button> \n" + 
													"</td>\n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'><p></p></td>\n" + 
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
							}
							else {
								engine.generateHtml("HeaderManager",request,"","");
								engine.generateHtml("LeftNav",request);
								
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Add Hotel Form</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'>\n" +
														"<p>Invalid Hotel value received</p>\n" +
													"</td> \n" + 
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
							}
							
							break;
						}
						case "deleteHotelSelect":{
							Hotels hotel = new Hotels();
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='4' style='text-align:center;height:40px;'><div align='center'>Delete Hotel</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
											"</tr>");
							HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
							if(hotelsMap==null || hotelsMap.isEmpty()) {
								pWriter.println("<tr>\n" +	
													"<td colspan='4' style='text-align:center;'>\n" +
														"<label for='orderStatusLbl'>No Hotels Found</label>\n" +
													"</td>\n" +
												"</tr>");
							}
							else {
								pWriter.println("<tr>\n" + 
										"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th> \n" +
										"<th style='text-align:left;'><div align='left'>Zip Code</div></th> \n" +
										"<th style='text-align:left;'><div align='left'>City</div></th> \n" +
										"<th></th> \n" +
									"</tr>");
								for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
									hotel = h.getValue();
									pWriter.println("<tr style='height:35px;'> \n" + 
											"<td style='text-align:left;margin:2%;padding-left:13px;width:300px;'> \n" + 
												"<a style='padding:20px;' href='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' method='get'>" + hotel.getID() + "</a>\n" +
											"</td> \n" + 
											"<td style='text-align:left;'> \n" + 
												"<p>" + hotel.getZipCode() + "</p>\n" +
											"</td> \n" +
											"<td style='text-align:left;'> \n" + 
												"<p>" + hotel.getCity() + "</p>\n" +
											"</td> \n" +
											"<td class='rsvBtn'> \n" + 
												"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ManagerServlet?op=deleteHotel&id=" + hotel.getID() + "' method='get'>Delete</a>\n" +
											"</td> \n" +
										"</tr>");
								}
							}
											 
							pWriter.println("<tr>\n" +
											"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
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
						case "deleteHotel":{
							String id = request.getParameter("id");
							dbObj.deleteHotel(id);
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Delete Hotel</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>Hotel - " + id + " deleted successfully</p>\n" +
												"</td> \n" + 
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
						default:{
							break;
						}
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
	
	public void pathTesting(HttpServletRequest request) {
		System.out.println("Path - " + request.getSession().getServletContext().getRealPath("/Hotels"));
		File hotelsFolder = new File(request.getSession().getServletContext().getRealPath("/Hotels"));
		for (File hotel : hotelsFolder.listFiles()) {
			System.out.println(hotel.getName());
	    }
		//System.out.println("Path - " + absoluteDiskPath);
		//File f = new File("C:/Texts");
		//f.mkdir();
		//System.out.println("Absolute Path - " + f.getAbsolutePath);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			String task = request.getParameter("task");
			if(task!=null) {
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				
				switch(task) {
					case "addHotel":{
						String hotelID = request.getParameter("hotelID");
						String retailer = "ReserveMyRoom";
						String hotelName = request.getParameter("hotelName");
						String hotelImage = request.getParameter("hotelImage");
						//System.out.println("HotelImage-" + hotelImage);
						HashMap<String, String> hotelImages = new HashMap<String,String>();
						hotelImages.put("image0", hotelImage);
						
						
						String wifi = request.getParameter("wifi");
						String restaurant = request.getParameter("restaurant");
						String onSale = request.getParameter("onSale");
						String rebate = request.getParameter("rebate");
						String zipcode = request.getParameter("zipcode");
						String address1 = request.getParameter("address1");
						String address2 = request.getParameter("address2");
						String city = request.getParameter("city");
						String state = request.getParameter("state");
						String phoneNumber =request.getParameter("phoneNumber");
						
						String roomName = request.getParameter("roomName");
						String roomCount = request.getParameter("roomCount");
						String roomPrice = request.getParameter("roomPrice");
						String roomImage = request.getParameter("roomImage");
						HashMap<String, String> roomImages = new HashMap<String, String>();
						roomImages.put("image0", roomImage);
						
						Hotels hotel = new Hotels();
						boolean wifiB = true;
						boolean restaurantB = true;
						boolean onSaleB = true;
						boolean rebateB = true;
						if(wifi.equals("Yes")) {
							wifiB = true;
						}
						else {
							wifiB = false;
						}
						if(restaurant.equals("Yes")) {
							restaurantB = true;
						}
						else {
							restaurantB = false;
						}
						if(onSale.equals("Yes")) {
							onSaleB = true;
						}
						else {
							onSaleB = false;
						}
						if(rebate.equals("Yes")) {
							rebateB = true;
						}
						else {
							rebateB = false;
						}
						HashMap<String, Rooms> rooms = new HashMap<String, Rooms>();
						Rooms room = new Rooms();
						room.setRoomName(roomName);
						room.setRoomPrice(Double.parseDouble(roomPrice));
						room.setRoomCount(Integer.parseInt(roomCount));
						room.setRoomImages(roomImages);
						rooms.put("room0", room);
						hotel = new Hotels(hotelID,retailer,hotelName,wifiB,restaurantB,Integer.parseInt(zipcode),address1,address2,city,state,phoneNumber,rooms,hotelImages,onSaleB,rebateB);
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							boolean status = dbObj.addHotel(hotel);
							if(status) {
								engine.generateHtml("HeaderManager",request,"","");
								engine.generateHtml("LeftNav",request);
								
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Add Hotel Form</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'>\n" +
														"<p>Hotel - " + hotel.getID() + " Successfully added.</p>\n" +
														"<p>Please use the link below to go back to Home Page.</p>\n" +
													"</td> \n" + 
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
							}
							else {
								engine.generateHtml("HeaderManager",request,"","");
								engine.generateHtml("LeftNav",request);
								
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Add Hotel Form</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'>\n" +
														"<p>Failed to add Hotel - " + hotel.getID() + " into the database.</p>\n" +
														"<p>Please use the link below to go back to Home Page.</p>\n" +
													"</td> \n" + 
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
							}
							
							
						}
						else {
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Add Hotel Form</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
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
						}
						
						break;
					}
					case "updateHotel":{
						String hotelID = request.getParameter("hotelID");
						String retailer = "ReserveMyRoom";
						String hotelName = request.getParameter("hotelName");
						String wifi = request.getParameter("wifi");
						String restaurant = request.getParameter("restaurant");
						String onSale = request.getParameter("onSale");
						String rebate = request.getParameter("rebate");
						String zipcode = request.getParameter("zipcode");
						String address1 = request.getParameter("address1");
						String address2 = request.getParameter("address2");
						String city = request.getParameter("city");
						String state = request.getParameter("state");
						String phoneNumber =request.getParameter("phoneNumber");
						
						Hotels hotel = dbObj.getHotel(hotelID);
						//HashMap<String, Rooms> rooms = hotel.getRooms();
						//Rooms room = new Rooms();
						
						Hotels newHotel = new Hotels();
						//HashMap<String, Rooms> newRooms = new HashMap<String, Rooms>();
						//Rooms newRoom = new Rooms();
						
						//HashMap<String, String> newHotelImages = new HashMap<String, String>();
						//HashMap<String, String> newRoomImages = new HashMap<String, String>();
						
						
						/*int a=0;
						for(Map.Entry<String, String> k : hotel.getHotelImages().entrySet()) {
							System.out.println("hotelImages - " + request.getParameter("hotelImage" + String.valueOf(a)));
							newHotelImages.put("image" + String.valueOf(a), request.getParameter("hotelImage" + String.valueOf(a)));
							a++;
						}
						int i=0;
						for(Map.Entry<String, Rooms> j : rooms.entrySet()) {
							room = j.getValue();
							newRoom = new Rooms();
							newRoom.setRoomName(request.getParameter("roomName" + String.valueOf(i)));
							System.out.println("roomName-" + request.getParameter("roomName" + String.valueOf(i)));
							newRoom.setRoomPrice(Double.parseDouble(request.getParameter("roomPrice" + String.valueOf(i))));
							System.out.println("roomPrice-" + request.getParameter("roomPrice" + String.valueOf(i)));
							newRoom.setRoomCount(Integer.parseInt(request.getParameter("roomCount" + String.valueOf(i))));
							System.out.println("RoomCount-" + request.getParameter("roomCount" + String.valueOf(i)));
							int b=0;
							for(Map.Entry<String, String> l : room.getRoomImages().entrySet()) {
								newRoomImages.put("image" + String.valueOf(b), request.getParameter("roomImage" + request.getParameter("roomName" + String.valueOf(i)) + String.valueOf(b)));
								System.out.println("roomImages-" + request.getParameter("roomImage" + request.getParameter("roomName" + String.valueOf(i)) + String.valueOf(b)));
								b++;
							}
							newRoom.setRoomImages(newRoomImages);
							newRooms.put("room" + String.valueOf(i), newRoom);
							i++;
						}*/
						boolean wifiB = true;
						boolean restaurantB = true;
						boolean onSaleB = true;
						boolean rebateB = true;
						if(wifi.equals("Yes")) {
							wifiB = true;
						}
						else {
							wifiB = false;
						}
						if(restaurant.equals("Yes")) {
							restaurantB = true;
						}
						else {
							restaurantB = false;
						}
						if(onSale.equals("Yes")) {
							onSaleB = true;
						}
						else {
							onSaleB = false;
						}
						if(rebate.equals("Yes")) {
							rebateB = true;
						}
						else {
							rebateB = false;
						}
						newHotel = new Hotels(hotelID,retailer,hotelName,wifiB,restaurantB,Integer.parseInt(zipcode),address1,address2,city,state,phoneNumber,null,null,onSaleB,rebateB);
						
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							boolean res = dbObj.updateHotel(newHotel);
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							if(res) {
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Update Form</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'>\n" +
														"<p>Hotel - " + newHotel.getID() + " Updated Successfully.</p>\n" +
														"<p>Please use the link below to go back to Home Page.</p>\n" +
													"</td> \n" + 
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
							}
							else {
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Update Form</div></th> \n" +
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;'>\n" +
														"<p>Hotel - " + newHotel.getID() + " was not updated.</p>\n" +
														"<p>Please use the link below to go back to Home Page.</p>\n" +
													"</td> \n" + 
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
							}
							engine.generateHtml("Footer",request);
						}
						else {
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotel Update Form</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p>" + dbStatus.get("msg") + "</p>\n" +
												"</td> \n" + 
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
						}
						
						break;
					}
					default:{
						break;
					}
				}
			}
			else {
				response.sendRedirect("/EWAProject/Controller/HomeServlet");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}