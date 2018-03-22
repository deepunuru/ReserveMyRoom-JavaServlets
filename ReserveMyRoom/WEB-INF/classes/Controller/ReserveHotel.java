package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Controller.HtmlEngine;
import JavaBeans.*;
import DataAccess.MySQLDataStoreUtilities;

public class ReserveHotel extends HttpServlet {
	MySQLDataStoreUtilities dbObj;
	
	public ReserveHotel() {
		dbObj = new MySQLDataStoreUtilities();
	}
	
	/*public void displayHotels(PrintWriter pWriter,String city,String state,String msg) {
		Hotels hotel = new Hotels();
		
		HashMap<String, String> dbStatus = new HashMap<String, String>();
		dbStatus = dbObj.checkDBConnection();
		if(dbStatus.get("status").equals("true")) {
			if(city!=null && city!="" && state!=null && state!="") {
				pWriter.println("</td>\n" + 
						"				<td style='text-align:center;width:79%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
						"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='6' style='height:10px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"									<tr>	\n" + 
						"										<th colspan='6' style='text-align:center;'><div align='center'>Hotels List</div></th> \n" + 
						"									</tr>\n" +
						"									<tr>\n" + 
						"										<td colspan='6' style='text-align:center;'><p>" + msg + "</p></td>\n" + 
						"									</tr>"); 
				
				
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap(city,state);
				if(hotelsMap==null || hotelsMap.isEmpty()) {
					pWriter.println("<tr>\n" +	
										"<td colspan='6' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>No Matching Hotels Found</label>\n" +
										"</td>\n" +
									"</tr>");
				}
				else {
					pWriter.println("<tr>\n" + 
							"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>Zip Code</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>City</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>State</div></th> \n" +
							"<th></th>\n" +
							"<th></th>\n" +
						"</tr>");
					for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
						hotel = h.getValue();
						pWriter.println("<tr style='height:35px;'> \n" + 
								"<td style='text-align:left;margin:2%;padding-left:13px;'> \n" + 
									"<a style='padding:20px;' href='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' method='get'>" + hotel.getID() + "</a>\n" +
								"</td> \n" + 
								"<td style='text-align:left;'> \n" + 
									"<p>" + String.valueOf(hotel.getZipCode()) + "</p>\n" +
								"</td> \n" +
								"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getCity() + "</p>\n" +
								"</td> \n" +
								"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getState() + "</p>\n" +
								"</td> \n" +
								"<td class='date_btn' style='padding-left:10px;padding-right:10px'> \n" + 										
									"<input type='submit' value='Book Now' formaction='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' formmethod='POST' style='width: 120px;padding:3px;'>\n" + 	
								"</td> \n" +
								"<td class='rsvBtn' style='padding-left:10px;padding-right:10px'> \n" + 
									"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ReserveHotel?id=" + hotel.getID() + "&op=AddToList&city=" + city + "&state=" + state + "' method='get'>Reserve</a>\n" +
								"</td> \n" +
							"</tr>");
					}
				}		
				pWriter.println("									<tr> 	\n" + 
						"										<td colspan='6' style='height:15px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"								</table> \n" + 
						"				</td>\n" + 
						"				<td style='text-align:left;width:3%'>\n" + 
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
			else {
				pWriter.println("</td>\n" + 
						"				<td style='text-align:center;width:79%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
						"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='6' style='height:30px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"									<tr>	\n" + 
						"										<th colspan='6' style='text-align:center;height:40px;'><div align='center'>Hotels List</div></th> \n" + 
						"									</tr>"); 
				
				
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
				if(hotelsMap==null || hotelsMap.isEmpty()) {
					pWriter.println("<tr>\n" +	
										"<td colspan='6' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>No Matching Hotels Found</label>\n" +
										"</td>\n" +
									"</tr>");
				}
				else {
					pWriter.println("<tr>\n" + 
							"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>Zip Code</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>City</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>State</div></th> \n" +
							"<th></th>\n" +
							"<th></th>\n" +
						"</tr>");
					for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
						hotel = h.getValue();
						pWriter.println("<tr style='height:35px;'> \n" + 
								"<td style='text-align:left;margin:2%;padding-left:13px;'> \n" + 
									"<a style='padding:20px;' href='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' method='get'>" + hotel.getID() + "</a>\n" +
								"</td> \n" + 
								"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getZipCode() + "</p>\n" +
								"</td> \n" +
								"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getCity() + "</p>\n" +
								"</td> \n" +
								"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getState() + "</p>\n" +
								"</td> \n" +
								"<td class='date_btn' style='padding-left:10px;padding-right:10px'> \n" + 
									"<input type='submit' value='Book Now' formaction='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' formmethod='POST' style='width: 120px;padding:3px;'>\n" +
								"</td> \n" +
								"<td class='rsvBtn' style='padding-left:10px;padding-right:10px'> \n" + 
									"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ReserveHotel?id=" + hotel.getID() + "&op=AddToList&city=" + city + "&state=" + state + "' method='get'>Reserve</a>\n" +
								"</td> \n" +
							"</tr>");
					}
				}
						
				
						
				pWriter.println("									<tr> 	\n" + 
						"										<td colspan='6' style='height:15px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"								</table> \n" + 
						"				</td>\n" + 
						"				<td style='text-align:left;width:3%'>\n" + 
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
					"											<p>SQL server is not up and running</p>\n" + 
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
	}*/
	
	public void displayHotels(PrintWriter pWriter,String city,String state,String msg) {
		Hotels hotel = new Hotels();
		
		HashMap<String, String> dbStatus = new HashMap<String, String>();
		dbStatus = dbObj.checkDBConnection();
		if(dbStatus.get("status").equals("true")) {
			if(city!=null && city!="" && state!=null && state!="") {
				pWriter.println("</td>\n" + 
						"				<td style='text-align:center;width:81%;vertical-align: -webkit-baseline-middle;'>\n" + 
						"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='7' style='height:10px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"									<tr>	\n" + 
						"										<th colspan='7' style='text-align:center;'><div align='center'>Hotels List</div></th> \n" + 
						"									</tr>\n" +
						"									<tr>\n" + 
						"										<td colspan='7' style='text-align:center;'><p>" + msg + "</p></td>\n" + 
						"									</tr>"); 
				
				
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap(city,state);
				if(hotelsMap==null || hotelsMap.isEmpty()) {
					pWriter.println("<tr>\n" +	
										"<td colspan='7' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>No Matching Hotels Found</label>\n" +
										"</td>\n" +
									"</tr>");
				}
				else {
					pWriter.println("<tr>\n" + 
							"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>Zip Code</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>City</div></th> \n" +
							"<th></th> \n" +
							"<th></th>\n" +
							"<th></th>\n" +
							"<th></th>\n" +
						"</tr>");
					for(Map.Entry<String, Hotels> h : hotelsMap.entrySet()) {
						hotel = h.getValue();
						pWriter.println("<tr style='height:35px;'> \n" + 
								"<td style='text-align:left;margin:2%;padding-left:13px;width:300px;'> \n" + 
									"<a style='padding:20px;' href='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' method='get'>" + hotel.getID() + "</a>\n" +
								"</td> \n" + 
								"<td style='text-align:left;'> \n" + 
									"<p>" + String.valueOf(hotel.getZipCode()) + "</p>\n" +
								"</td> \n" +
								"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getCity() + "</p>\n" +
								"</td> \n" +
								"<td class='date_btn' style='padding-bottom:10px;padding-top: 10px;'> \n" + 										
									"<input type='submit' value='Book Now' formaction='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' formmethod='POST' style='width: 120px;padding:3px;'>\n" + 	
								"</td> \n" +
								"<td class='rsvBtn' style='padding-bottom:10px;padding-top: 10px;'> \n" + 
									"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ReserveHotel?id=" + hotel.getID() + "&op=AddToList&city=" + city + "&state=" + state + "' method='get'>Reserve</a>\n" +
								"</td> \n" +
								"<td class='rsvBtn' style='padding-bottom:10px;padding-top: 10px;'>\n" +
									"<a href='/EWAProject/Controller/HotelReviews?op=viewReviews&hotelName=" + hotel.getName() + "'>View Reviews</a>\n" +
								"</td>\n" +
								"<td class='rsvBtn' style='padding-bottom:10px;padding-top: 10px;'>\n" +
									"<a href='/EWAProject/Controller/HotelReviews?op=writeReview&hotelName=" + hotel.getName() + "'>Write Reviews</a>\n" +
								"</td>\n" +
							"</tr>");
					}
				}		
				pWriter.println("									<tr> 	\n" + 
						"										<td colspan='7' style='height:15px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"								</table> \n" + 
						"				</td>\n" + 
						"				<td style='text-align:left;width:1%'>\n" + 
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
			else {
				pWriter.println("</td>\n" + 
						"				<td style='text-align:center;width:81%;vertical-align: -webkit-baseline-middle;'>\n" + 
						"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='7' style='height:30px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"									<tr>	\n" + 
						"										<th colspan='7' style='text-align:center;height:40px;'><div align='center'>Hotels List</div></th> \n" + 
						"									</tr>"); 
				
				
				HashMap<String, Hotels> hotelsMap = dbObj.getHotelsMap();
				if(hotelsMap==null || hotelsMap.isEmpty()) {
					pWriter.println("<tr>\n" +	
										"<td colspan='7' style='text-align:center;'>\n" +
											"<label for='orderStatusLbl'>No Matching Hotels Found</label>\n" +
										"</td>\n" +
									"</tr>");
				}
				else {
					pWriter.println("<tr>\n" + 
							"<th style='text-align:left;padding:33px;'><div align='left'>Hotel ID</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>Zip Code</div></th> \n" +
							"<th style='text-align:left;'><div align='left'>City</div></th> \n" +
							"<th></th> \n" +
							"<th></th>\n" +
							"<th></th>\n" +
							"<th></th>\n" +
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
								/*"<td style='text-align:left;'> \n" + 
									"<p>" + hotel.getState() + "</p>\n" +
								"</td> \n" +*/
								"<td class='date_btn'> \n" + 
									"<input type='submit' value='Book Now' formaction='/EWAProject/Controller/DisplayHotel?id=" + hotel.getID() + "' formmethod='POST' style='width: 120px;padding:3px;'>\n" +
								"</td> \n" +
								"<td class='rsvBtn'> \n" + 
									"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ReserveHotel?id=" + hotel.getID() + "&op=AddToList&city=" + city + "&state=" + state + "' method='get'>Reserve</a>\n" +
								"</td> \n" +
								"<td class='rsvBtn' style='padding-bottom:10px;padding-top: 10px;'>\n" +
									"<a href='/EWAProject/Controller/HotelReviews?op=viewReviews&hotelName=" + hotel.getName() + "'>View Reviews</a>\n" +
								"</td>\n" +
								"<td class='rsvBtn' style='padding-bottom:10px;padding-top: 10px;'>\n" +
									"<a href='/EWAProject/Controller/HotelReviews?op=writeReview&hotelName=" + hotel.getName() + "'>Write Reviews</a>\n" +
								"</td>\n" +
							"</tr>");
					}
				}
						
				
						
				pWriter.println("									<tr> 	\n" + 
						"										<td colspan='7' style='height:15px;'><p></p></td> \n" + 
						"									</tr>\n" + 
						"								</table> \n" + 
						"				</td>\n" + 
						"				<td style='text-align:left;width:1%'>\n" + 
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession userSession = request.getSession(true);
			String username = (String)userSession.getAttribute("customerKey");
			if(username!=null) {
				boolean flag = false;
				String hotelID = request.getParameter("id");
				String op = request.getParameter("op");
				String city = request.getParameter("city");
				String state =request.getParameter("state");
				if(op.equals("AddToList")) {
					ReserveList rs = (ReserveList) userSession.getAttribute("ReserveList");
					ArrayList<String> hotelList = rs.getHotelList();
					int listSize = rs.getListSize();
					Iterator itr = hotelList.iterator();  
					while(itr.hasNext()){
						String s = itr.next().toString();
						if(s.equals(hotelID)) {
							flag = true;
						}
					}  
					if(flag == false) {
						listSize = listSize + 1;
						if(hotelList!=null) {
							hotelList.add(hotelID);
						}
						else {
							hotelList = new ArrayList<String>();
							hotelList.add(hotelID);
						}
					}
					rs.setHotelList(hotelList);
					rs.setListSize(listSize);
					rs.setUsername(username);
					userSession.setAttribute("ReserveList", rs);
					
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNav",request);
					if(flag==false) {
						if(city.equals("None") && state.equals("None")) {
							displayHotels(pWriter,null,null,"");
						}
						else {
							displayHotels(pWriter,city,state,"");
						}
					}
					else {
						if(city.equals("None") && state.equals("None")) {
							displayHotels(pWriter,null,null,"Hotel "+ hotelID +" already added to the list");
						}
						else {
							displayHotels(pWriter,city,state,"Hotel "+ hotelID +" already added to the list");
						}
					}
					engine.generateHtml("Footer",request);
				}
				else if(op.equals("AddToListNoCity")) {
					ReserveList rs = (ReserveList) userSession.getAttribute("ReserveList");
					ArrayList<String> hotelList = rs.getHotelList();
					int listSize = rs.getListSize();
					Iterator itr = hotelList.iterator();  
					while(itr.hasNext()){
						String s = itr.next().toString();
						if(s.equals(hotelID)) {
							flag = true;
						}
					}  
					if(flag == false) {
						listSize = listSize + 1;
						if(hotelList!=null) {
							hotelList.add(hotelID);
						}
						else {
							hotelList = new ArrayList<String>();
							hotelList.add(hotelID);
						}
					}
					rs.setHotelList(hotelList);
					rs.setListSize(listSize);
					rs.setUsername(username);
					userSession.setAttribute("ReserveList", rs);
					response.sendRedirect("/EWAProject/Controller/DisplayHotel?id=" + hotelID);
				}
				else if(op.equals("RemoveFromList")) {
					
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