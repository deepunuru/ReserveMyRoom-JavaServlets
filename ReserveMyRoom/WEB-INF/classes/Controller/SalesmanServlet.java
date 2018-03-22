package Controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Controller.HtmlEngine;
import DataAccess.MySQLDataStoreUtilities;
import JavaBeans.*;

public class SalesmanServlet extends HttpServlet {
	public static MySQLDataStoreUtilities dbObj;
	
	public SalesmanServlet() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	public void displayReservationList(PrintWriter pWriter,String action) {
		try {
			HashMap<String, Reservations> reservationsMap = new HashMap<String, Reservations>();
			Reservations reservation = new Reservations();
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				reservationsMap = dbObj.getReservations();
				pWriter.println("</td>\n" + 
						"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;vertical-align:initial;padding-left: 50px;'>\n" + 
							"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
								"<tr>\n" + 
									"<td colspan='4' style='height:30px;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='4'><div align='center'>Reservation List</div></th>\n" +	
								"</tr>\n" +
								"<tr>\n" + 
									"<td colspan='4' style='height:25px;'><p></p></td> \n" + 
								"</tr>\n" +
								"<tr>\n" +
									"<th style='text-align:center;padding:15px;'><div align='center'>Reservation ID</div></th> \n" +
									"<th style='text-align:center;'><div align='center'>Total Price</div></th> \n" +
									"<th style='text-align:center;'><div align='center'>Reservation Date</div></th> \n" +
									"<th ></th>\n" +
								"</tr>"); 
				if(reservationsMap == null || reservationsMap.isEmpty()) {
					pWriter.println("<tr>\n" +	
							"<td colspan='4' style='text-align:center;'>\n" +
								"<label for='reservationStatusLbl'>No Reservations to Display</label>\n" +
							"</td>\n" +
						"</tr>");
				}
				else {
					for(Map.Entry<String, Reservations> r : reservationsMap.entrySet()) {
						reservation = r.getValue();
						pWriter.println("<tr>\n" +
								"<td style='text-align:center;padding:15px;'>\n" +									
									"<p>" + reservation.getReservationID() + "</p>\n" +
								"</td>\n" +
								"<td style='text-align:center;'>\n" +
									"<p>$" + String.valueOf(reservation.getTotalPrice()) + "</p>\n" +
								"</td>\n" +
								"<td style='text-align:center;'>\n" +
									"<p>" + reservation.getReservationDate() + "</p>\n" +
								"</td>\n" +
								"<td style='text-align:center;' class='rsvBtn'>");
						if(action.equals("modify"))
						{
							pWriter.println("<a style='padding:7px;text-align:center;' href='/EWAProject/Controller/SalesmanServlet?op=modifyReservation&id=" + String.valueOf(reservation.getReservationID()) + "' method='get'>Modify Reservation</a>");
						}
						else {
							pWriter.println("<a style='padding:7px;text-align:center;' href='/EWAProject/Controller/SalesmanServlet?op=deleteReservation&id=" + String.valueOf(reservation.getReservationID()) + "' method='get'>Delete Reservation</a>");
						}
						
									pWriter.println("</td>\n" +
								"</tr>");
					}
					
				}
				pWriter.println("<tr>\n" +
						"<td colspan='4' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
							"<a style='width: 120px;padding:7px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +
						"</td>\n" +
					"</tr>\n" +
		"</table> \n" + 
		"</td>\n" + 
		"<td style='text-align:left;width:13%'>\n" + 
		"</td>\n" + 
	"</tr>\n" + 
	"</table>\n" + 
	"</div>\n" + 
	"</form>\n" + 
	"</div>\n" + 
	"</div>\n" + 
	"</div>	\n" + 
	"<!--start main -->");
				
			}
			else {
				pWriter.println("</td>\n" + 
						"				<td style='text-align:left;width:59%'>\n" + 
						"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='2' style='height:30px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"									<tr>	\n" + 
						"										<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Hotels List</div></th> \n" + 
						"									</tr> \n" + 
						"									<tr> \n" + 
						"										<td colspan='2' style='text-align:center;height:40px;'> \n" + 
																	"<p>" + dbStatus.get("msg") + "</p>\n" +
						"										</td> \n" + 
						"									</tr> 	\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='2' style='height:15px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"								</table> \n" + 
						"				</td>\n" + 
						"				<td style='text-align:left;width:23%'>\n" + 
						"				</td>\n" + 
						"			</tr>\n" + 
						"		</table>\n" + 
						"		</div> \n" + 
						"	</form>	\n" + 
						"	</div>\n" + 
						"</div>\n" + 
						"</div>	\n" + 
						"<!--start main -->");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(true);
		String username = (String)userSession.getAttribute("salesmanKey");
		if(username!=null) {
			String op = request.getParameter("op");
			try {
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				if(op!=null) {
					switch(op) {
						case "createAccount":{
							engine.generateHtml("HeaderSalesman",request,"/EWAProject/Controller/SalesmanServlet?task=createAccount","POST");
							engine.generateHtml("LeftNavLgnReg",request);
							pWriter.println("</td>\n" + 
									"				<td style='text-align:left;width:50%'>\n" + 
									"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"									<tr> 	\n" + 
									"										<td colspan='2' style='height:30px;'><p></p></td> \n" + 
									"									</tr>\n" + 
									"									<tr>	\n" + 
									"										<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Customer Registration From</div></th> \n" + 
									"									</tr> \n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='username'>Username :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
									"											<input type='text' id='username' style='width:200px' name='username' required/> \n" + 
									"										</td> \n" + 
									"									</tr> \n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='FirstName'>First Name :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
									"											<input type='text' id='firstName' style='width:250px' name='firstName' required/> \n" + 
									"										</td> \n" + 
									"									</tr> 	\n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='LastName'>Last Name :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
									"											<input type='text' id='lastName' style='width:250px' name='lastName' required/> \n" + 
									"										</td> \n" + 
									"									</tr> 	\n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='password'>Password :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:left;width:65%;height:40px;'> \n" + 
									"											<input type='password' id='password' style='width:200px' name='password' required/> \n" + 
									"										</td> \n" + 
									"									</tr> \n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='email'>Email ID :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
									"											<input type='email' id='email' style='width:300px' name='email' required/> \n" + 
									"										</td> \n" + 
									"									</tr>\n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='dob'>Date of Birth :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
									"											<input type='text' id='dob' style='width:200px' name='dob' placeholder='DD/MM/YY' required/> \n" + 
									"										</td> \n" + 
									"									</tr>\n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;padding-bottom: 8px;'> \n" + 
									"											<label for='address'>Address :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;padding-bottom: 8px;'> \n" + 
									"											<textarea name='address' id='address' rows='6' cols='52' style='width:350px;'></textarea>\n" + 
									"										</td> \n" + 
									"									</tr>\n" + 
									"									<tr> \n" + 
									"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
									"											<label for='zipCode'>Zip Code :&nbsp;&nbsp;</label> \n" + 
									"										</td> \n" + 
									"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
									"											<input type='number' id='zipCode' style='width:200px' name='zipCode' required/> \n" + 
									"										</td> \n" + 
									"									</tr>\n" + 
									"									<tr> \n" + 
									"										<td colspan='2' align='center'> \n" + 
									"											<button name='sbmtButton' class='loginBtn' type='submit'>Submit</button> \n" + 
									"										</td> \n" + 
									"									</tr> \n" + 
									"									<tr> 	\n" + 
									"										<td colspan='2' style='height:25px;'><p></p></td> \n" + 
									"									</tr> 	\n" + 
																		"<tr>\n" +
																			"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
																				"<a style='width: 120px;padding:7px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +
																			"</td>\n" +
																		"</tr>\n" +
									"								</table> \n" + 
									"				</td>\n" + 
									"				<td style='text-align:left;width:20%'>\n" + 
									"				</td>\n" + 
									"			</tr>\n" + 
									"		</table>\n" + 
									"		</div> \n" + 
									"	</form>	\n" + 
									"	</div>\n" + 
									"</div>\n" + 
									"</div>	\n" + 
									"<!--start main -->");
							engine.generateHtml("Footer",request);
							break;
						}
						case "addReservation":{
							engine.generateHtml("HeaderSalesman",request,"/EWAProject/Controller/SalesmanServlet?task=addReservation","POST");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2'><div align='center'>Add Reservation Form</div></th>\n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='HotelID'>Hotel ID : </label>\n" +
												"</td>\n" +
												"<td style='text-align:left;padding:7px;'>\n" +
													"<select name='hotelID' id='hotelID' style='width:300px;'>\n" + 
														"<option value='selectHotel'>Select Hotel</option>\n" + 
													"</select>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='RoomType'>Room Type : </label>\n" +
												"</td>\n" +
												"<td style='text-align:left;padding:7px;'>\n" +
													"<select name='roomType' id='roomType' style='width:300px;'>\n" + 
														"<option value='selectRoomType'>Select Room Type</option>\n" + 
													"</select>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='GuestCount'>Number of Guests : </label>\n" +
												"</td>\n" +
												"<td style='text-align:left;padding:7px;'>\n" +
													"<select style='width:45px;' id='guestCount' name='guestCount' required>\n" + 
														"<option value='1'>1</option>\n" + 
														"<option value='2'>2</option>\n" + 
														"<option value='3'>3</option>\n" + 
														"<option value='4'>4</option>\n" + 
													"</select>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='checkInDate'>Check-In Date : </label>\n" +
												"</td>\n" +
												"<td style='text-align:left;padding:7px;'>\n" +
													"<input type='text' id='checkInDate' style='width:120px;' name='checkInDate' placeholder='DD/MM/YY' required/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='checkOutDate'>Check-Out Date : </label>\n" +
												"</td>\n" +
												"<td style='text-align:left;padding:7px;'>\n" +
													"<input type='text' id='checkOutDate' style='width:120px;' name='checkOutDate' placeholder='DD/MM/YY' required/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='TotalPrice'>Total Price : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;'>\n" +
													"<input type='text' id='totalPrice' style='width:300px;' name='totalPrice' required/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<label for='username'>Username : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;'>\n" +
													"<input type='text' id='username' style='width:300px' name='username' required/>\n" +
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
												"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
													"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
							break;
						}
						case "modifyReservationSelect":{
							engine.generateHtml("HeaderSalesman",request,"/EWAProject/Controller/SalesmanServlet?op=modifyReservation","GET");
							engine.generateHtml("LeftNav",request);
							displayReservationList(pWriter,"modify");
							engine.generateHtml("Footer",request);
							break;	
						}
						case "deleteReservationSelect":{
							engine.generateHtml("HeaderSalesman",request,"/EWAProject/Controller/SalesmanServlet?op=deleteReservation","GET");
							engine.generateHtml("LeftNav",request);
							displayReservationList(pWriter,"delete");
							engine.generateHtml("Footer",request);
							break;	
						}
						case "modifyReservation":{
							String id = request.getParameter("id");
							HashMap<String, Reservations> reservationsMap = dbObj.getReservations();
							Reservations reservation = reservationsMap.get(id);
							String[] roomNames = reservation.getRoomNames();
							SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
							Date[] checkInDates = reservation.getCheckInDates();
							Date[] checkOutDates = reservation.getCheckOutDates();
							String user = reservation.getUsername();
							ReserveList rl = new ReserveList();
							rl.setHotelIDList(reservation.getHotelIDs());
							userSession.setAttribute("salesmanRL", rl);
							engine.generateHtml("HeaderSalesman",request,"/EWAProject/Controller/SalesmanServlet?task=modifyReservation&id=" + id + "&username=" + user,"POST");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%;padding-left:50px;'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2'><div align='center'>Modify Reservation Form</div></th>\n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>");
							for(int i=0;i<roomNames.length;i++) {
								if(roomNames[i]!=null) {
									pWriter.println("<tr>\n" +
											"<td style='text-align:center;width:50%;display:inline;'>\n" +
												"<p style='display: -moz-box;'>RoomType:&nbsp;&nbsp;<input type='text' id='roomName" + String.valueOf(i) + "' style='width:120px;background-color:#bfbfbf;' name='roomName" + String.valueOf(i) + "' value='" + roomNames[i] + "' readonly/></p>\n" +
												"<script>\n" + 
													"$(function() {\n" + 
														"$( '#checkInDate" + String.valueOf(i) + ",#checkOutDate" + String.valueOf(i) + "' ).datepicker();\n" + 
													"});\n" + 
												"</script>\n" + 
											"</td>\n" +
											"<td style='text-align:left;width:50%;'>\n" +
												"<p style='display: -moz-box;'>Guest Count:&nbsp;&nbsp;<select style='width:45px;' id='guestCount' name='guestCount" + String.valueOf(i) + "' required>\n" + 
													"<option value='1'>1</option>\n" + 
													"<option value='2'>2</option>\n" + 
													"<option value='3'>3</option>\n" + 
													"<option value='4'>4</option>\n" + 
												"</select></p>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
											"<td style='text-align:center;width:50%;display:inline;'>\n" +
												"<p>Check-In Date:&nbsp;&nbsp;<input type='text' id='checkInDate" + String.valueOf(i) + "' style='width:120px;' name='checkInDate" + String.valueOf(i) + "' value='" + formatter.format(checkInDates[i]) + "'/></p>\n" +
											"</td>\n" +
											"<td style='text-align:left;width:50%;'>\n" +
												"<p>Check-Out Date:&nbsp;&nbsp;<input type='text' id='checkOutDate" + String.valueOf(i) + "' style='width:120px;' name='checkOutDate" + String.valueOf(i) + "' value='" + formatter.format(checkOutDates[i]) + "'/></p>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +
										"<td colspan='2' style='height:15px;'>\n" +
											"<p></p>\n" +
										"</td>\n" +
										"</tr>");
								}
							}
							pWriter.println("<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='TotalPrice'>Total Price : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='text' id='totalPrice' style='width:300px;background-color:#bfbfbf;' name='totalPrice' value='" + String.valueOf(reservation.getTotalPrice()) + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='customerName'>Customer Name : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='text' id='customerName' style='width:300px' name='customerName' value='" + reservation.getCustomerName() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='State'>State : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='text' id='state' style='width:300px' name='state' value='" + reservation.getState() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='Address1'>Address 1 : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='text' id='address1' style='width:300px' name='address1' value='" + reservation.getAddress1() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='Address2'>Address 2 : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='text' id='address2' style='width:300px' name='address2' value='" + reservation.getAddress2() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='City'>City : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='text' id='city' style='width:300px' name='city' value='" + reservation.getCity() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='ZipCode'>Zip Code : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='number' id='zipCode' style='width:300px' name='zipCode' value='" + String.valueOf(reservation.getZipCode()) + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='MobileNumber'>Mobile Number : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='number' id='mobileNumber' style='width:300px' name='mobileNumber' value='" + reservation.getMobileNumber() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;width:50%;'>\n" +
													"<label for='EmailAddress'>Email Address : </label>\n" +
												"</td>\n" +
												"<td style='text-align:center;padding:7px;width:50%;'>\n" +
													"<input type='email' id='emailAddress' style='width:300px' name='emailAddress' value='" + reservation.getEmailAddress() + "'/>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;padding:7px;' class='date_btn'>\n" +
													"<input type='submit' value='Submit' style='width: 120px;'>\n" + 
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
													"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
							break;
						}
						case "deleteReservation":{
							String resID = request.getParameter("id");
							HashMap<String, String> dbStatus = new HashMap<String, String>();
							dbStatus = dbObj.checkDBConnection();
							if(dbStatus.get("status").equals("true")) {
								dbObj.deleteReservation(Integer.parseInt(resID));
								engine.generateHtml("HeaderSalesman",request,"","");
								engine.generateHtml("LeftNav",request);
								pWriter.println("</td>\n" + 
										"<td style='text-align:left;width:59%'>\n" + 
											"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:15px;padding:15px;'>\n" +
														"<p>Reservation - #" + resID +" successfully deleted.</p>\n" +
														"<p>Please use the link below to go back to Home Page.</p>\n" +
													"</td> \n" + 
												"</tr>\n" + 
												"<tr>\n" + 
													"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
														"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
							else {
								engine.generateHtml("HeaderSalesman",request,"","");
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
							break;
						}
						default:{
							response.sendRedirect("/EWAProject/Controller/HomeServlet");
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
		else {
			response.sendRedirect("/EWAProject/Controller/LoginServlet");
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		try {
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			switch(task) {
				case "createAccount":{
					String username = request.getParameter("username");
					String userType = "Customer";
					HashMap<String, String> dbStatus = new HashMap<String, String>();
					dbStatus = dbObj.checkDBConnection();
					if(dbStatus.get("status").equals("true")) {
						Users user = dbObj.getUserProfile(username);
						if(user==null) {
							user = new Users(userType,username,request.getParameter("firstName"),request.getParameter("lastName"),request.getParameter("address"),Integer.parseInt(request.getParameter("zipCode")),request.getParameter("email"),request.getParameter("dob"),request.getParameter("password"));
							dbObj.writeUserProfile(user);
							engine.generateHtml("HeaderSalesman",request,"","");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:15px;padding:15px;'>\n" +
													"<p>User - " + username +" Registered Successfully.</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
													"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
						else if((user.getUsername()).equals(username)) {
							engine.generateHtml("HeaderSalesman",request,"","");
							engine.generateHtml("LeftNav",request);
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:15px;padding:15px;'>\n" +
													"<p>User - " + username +" already registered.</p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
													"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
						engine.generateHtml("HeaderSalesman",request,"","");
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
					break;
				}
				case "addReservation":{
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					String hotelID = request.getParameter("hotelID");
					String roomType = request.getParameter("roomType");
					int guestCount = Integer.parseInt(request.getParameter("guestCount"));
					Date checkInDate = formatter.parse(request.getParameter("checkInDate"));
					Date checkOutDate = formatter.parse(request.getParameter("checkOutDate"));
					double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
					String username = request.getParameter("username");
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
							
							engine.generateHtml("HeaderSalesman",request,"","");
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
												"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
													"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
							engine.generateHtml("HeaderSalesman",request,"","");
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
						engine.generateHtml("HeaderSalesman",request,"","");
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
											"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
												"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
					break;
				}
				case "modifyReservation":{
					String resID = request.getParameter("id");
					HashMap<String, Reservations> reservationsMap = dbObj.getReservations();
					Reservations reservation = reservationsMap.get(resID);
					String[] roomNames = reservation.getRoomNames();
					String[] newRoomNames = new String[30];
					int[] guestCounts = new int[30];
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					HttpSession userSession = request.getSession(true);
					ReserveList rl = (ReserveList)userSession.getAttribute("salesmanRL");
					String[] hotelIDList = rl.getHotelIDList();
					Date[] newCheckInDates = new Date[30];
					Date[] newCheckOutDates = new Date[30];
					for(int i=0;i<roomNames.length;i++) {
						if(roomNames[i]!=null) {
							newRoomNames[i] = request.getParameter("roomName" + String.valueOf(i));
							guestCounts[i] = Integer.parseInt(request.getParameter("guestCount" + String.valueOf(i)));
							newCheckInDates[i] = formatter.parse(request.getParameter("checkInDate" + String.valueOf(i)));
							newCheckOutDates[i] = formatter.parse(request.getParameter("checkOutDate" + String.valueOf(i)));
						}
					}
					double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
					String username = request.getParameter("username");
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
					HashMap<String, String> dbStatus = new HashMap<String, String>();
					dbStatus = dbObj.checkDBConnection();
					if(dbStatus.get("status").equals("true")) {
						Reservations newRes = new Reservations(customerName,username,reservation.getReservationDate(),newCheckInDates,newCheckOutDates,reservation.getReservationID(),hotelIDList,newRoomNames,guestCounts,totalPrice,state,address1,address2,city,zipCode,mobileNumber,emailAddress);
						dbObj.updateReservation(newRes);
						rl.setHotelIDList(null);
						userSession.setAttribute("salesmanRL", rl);
						
						engine.generateHtml("HeaderSalesman",request,"","");
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
												"<label for='customerName'>Customer Name:&nbsp;" + newRes.getCustomerName() + "</label>\n" +
												"<br />\n" +
												"<label for='Address1'>" + newRes.getAddress1() + "</label>\n" +
												"<br />\n" +
												"<label for='Address2'>" + newRes.getAddress2() + "</label>\n" +
												"<br />\n" +
												"<label for='City'>" + newRes.getCity() + "</label>\n" +
												"<br />\n" +
												"<label for='State'>" + newRes.getState() + "</label>\n" +
												"<br />\n" +
												"<label for='ZipCode'>ZipCode:&nbsp;" + String.valueOf(newRes.getZipCode()) + "</label>\n" +
												"<br />\n" +
												"<p>Mobile Number:&nbsp;&nbsp;" + String.valueOf(newRes.getMobileNumber()) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
												"<label for='reservationIDlbl'>Reservation ID:&nbsp;</label><label for='reservationID'>" + newRes.getReservationID() + "</label>\n" +
												"<br />\n" +
												"<label for='reservationDatelbl' >Reservation Date:&nbsp;</label><label for='reservationDate'>" + formatter.format(newRes.getReservationDate()) + "</label>\n" +
												"<br />\n" +
												"<label for='orderStatuslbl''>Order Status:&nbsp;</label><label for='orderStatus''>In Progress</label>\n" +
												"<br />\n" +
												"<label for='emailAddresslbl'>Email Address:&nbsp;</label><label for='emailAddress'>" + newRes.getEmailAddress() + "</label>\n" +
											"</td>\n" +
											"<td style='text-align:left;vertical-align: text-top;border: 1px solid #ccc;border-radius: 5px;padding:10px;width:33%;'>\n" +
												"<p><label for='HotelDetails' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Hotel Details</label></p>\n" +
												"<br />");
						for(int i=0;i<hotelIDList.length;i++) {		
							if(hotelIDList[i]!=null) {
								pWriter.println("<p>Hotel ID:&nbsp;&nbsp;" + hotelIDList[i] + "&nbsp;&nbsp;RoomType:" + newRoomNames[i] + "</p>\n" +
										"<p>Check-InDate:&nbsp;&nbsp;" + formatter.format(newCheckInDates[i]) + "&nbsp;&nbsp;Check-OutDate:&nbsp;&nbsp;" + formatter.format(newCheckOutDates[i]) + "</p>");
							}
						}
								pWriter.println("<p><label for='OrderTotals' style='color: #fff;background: #799AC0 none repeat-x scroll left top;border-radius: 5px;'>Order Totals</label></p>\n" +
												"<br />\n" +
												"<label for='Total'>Order Sub-Total:&nbsp;$" + String.valueOf(newRes.getTotalPrice()-5.60) + "</label>\n" +
												"<br />\n" +
												"<label for='Tax'>Tax:&nbsp;$5.60</label>\n" +
												"<br />\n" +
												"<label for='Total'>Total Price:&nbsp;$" + newRes.getTotalPrice() + "</label>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" + 
											"<td colspan='2' style='height:25px;text-align:right;padding:15px;' class='rsvBtn'>\n" +
												"<a style='width: 120px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' method='get'>Account</a>\n" +	
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
						engine.generateHtml("HeaderSalesman",request,"","");
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
					break;
				}
				default:{
					response.sendRedirect("/EWAProject/Controller/HomeServlet");
					break;
				}
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}