package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import DataAccess.*;
import JavaBeans.Cities;
import JavaBeans.Hotels;
import JavaBeans.ReserveList;

public class HomeServlet extends HttpServlet implements Runnable {
	
	public static SaxParserDataStore datastoreObj;
	public static SaxParserStateDataStore stateStoreObj;
	public static MySQLDataStoreUtilities dbObj;
	public static MongoDBDataStoreUtilities mdbObj;
	Thread cityLoader;
	
	public void init() throws ServletException{
		try {
			mdbObj = new MongoDBDataStoreUtilities();
			initializeParser();
			dbObj = new MySQLDataStoreUtilities();
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbObj.createDB();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				dbObj.populateDatabase(datastoreObj);
				dbObj.insertSampleUser();	
				
			}
			else {
				System.out.println("Msg-" + dbStatus.get("msg"));
			}
			cityLoader = new Thread(this);
			cityLoader.setPriority(Thread.MIN_PRIORITY);
			cityLoader.start();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void run() {
		try {
			boolean stat = false;
			stat = dbObj.checkCityTable();
			if(stat==false) {
				populateCities();
			}
			dbObj.insertSampleReservations();
			
			Random rand = new Random();
			int min = 6;
			int max = 20;
			int count = rand.nextInt((max - min) + 1) + min;
			for(int i=0;i<count;i++) {
				mdbObj.loadSampleReviews();
			}
			
			//mdbObj.getTrendingReviews();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void destroy() {
		cityLoader.interrupt();
	}
	
	public void populateCities() {
		try {
			String relativeWebPath = "/WEB-INF/classes/StateList.xml";
			String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			stateStoreObj = new SaxParserStateDataStore();
			parser.parse(absoluteDiskPath, stateStoreObj);
			Cities city = new Cities();
			ParseCityJson cityObj = new ParseCityJson();
			
			for(Map.Entry<String, Cities> c : stateStoreObj.cityMap.entrySet()) {
				city = c.getValue();
				relativeWebPath = "/Cities/" + city.getStateID() + ".json";
				absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
				cityObj.getJsonData(absoluteDiskPath,city.getStateName());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void initializeParser() {
		try {
			String relativeWebPath = "/WEB-INF/classes/HotelCatalog.xml";
			String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			datastoreObj = new SaxParserDataStore();
			parser.parse(absoluteDiskPath, datastoreObj);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public HomeServlet() {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String var = request.getParameter("var");
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			if(var==null) {
				engine.generateHtml("HomePageHeader",request);
				engine.generateHtml("HomePageLeftNav",request);
				engine.generateHtml("HomePageContent",request);
				engine.generateHtml("HomePageFooter",request);
			}
			else if(var.equals("contactUs")) {
				engine.generateHtml("Header",request,"/EWAProject/Controller/HomeServlet?var=contactUs","POST");
				engine.generateHtml("LeftNav",request);
				pWriter.println("</td>\n" + 
						"<td style='text-align:left;width:59%;padding-left:50px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"<p>Thank you for using ReserveMyRoom website for your reservation. Please complete the form below, so we can provide quick and efficient service. If this is an urgent matter please contact Customer support:</p>\n" +
							"<table style='width:100%;'>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Contact Us Form</div></th> \n" +
								"</tr>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:25px;'>\n" +
										"<p></p>\n" +
									"</td> \n" + 
								"</tr>\n" + 
								"<tr>\n" +
									"<td align='right'>\n" +
										"<label for='name'>Name:</label>\n" +
									"</td>\n" +
									"<td align='left' style='padding:15px;'>\n" +
										"<input name='name' style='width:300px;' id='name' type='text' />\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td align='right'>\n" +
										"<label for='email'>Email:</label>\n" +
									"</td>\n" +
									"<td align='left' style='padding:15px;'>\n" +
										"<input name='email' style='width:300px;' id='email' type='email' />\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td align='right'>\n" +
										"<p><label for='message'>Message:</label>\n" +
									"</td>\n" +
									"<td align='left' style='padding:15px;'>\n" +
										"<textarea cols='37' rows='11' style='width:300px;' name='message' id='message'></textarea></p>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td colspan='2' style='text-align:center;padding:7px;' class='date_btn'>\n" +
										"<input type='submit' value='Submit' style='width: 120px;'>\n" + 
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
								"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
									"<a style='width: 120px;padding:7px;text-align:center;' href='/EWAProject/Controller/HomeServlet' method='get'>Home</a>\n" +
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
				engine.generateHtml("HomePageHeader",request);
				engine.generateHtml("HomePageLeftNav",request);
				engine.generateHtml("HomePageContent",request);
				engine.generateHtml("HomePageFooter",request);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void displayHotels(PrintWriter pWriter,String city,String state) {
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
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		try {
			String var = request.getParameter("var");
			
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			
			if(var==null) {
				String city = request.getParameter("cityHidden");
				String state = request.getParameter("stateHidden");
				
				String checkInDate = request.getParameter("checkInDate");
				//System.out.println(checkInDate);
				String checkOutDate = request.getParameter("checkOutDate");
				//System.out.println(checkOutDate);
				String guestCount = request.getParameter("guestCount");
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				
				HttpSession userSession = request.getSession(true);
				String username = (String)userSession.getAttribute("customerKey");
				ReserveList rl = (ReserveList)userSession.getAttribute("ReserveList");
				if(rl==null) {
					rl = new ReserveList();
				}
				Date[] checkInDates = new Date[10];
				Date[] checkOutDates = new Date[10];
				checkInDates[0] = formatter.parse(checkInDate);
				checkOutDates[0] = formatter.parse(checkOutDate);
				rl.setCheckInDates(checkInDates);
				rl.setCheckOutDates(checkOutDates);
				rl.setUsername(username);
				userSession.setAttribute("ReserveList", rl);
				
				engine.generateHtml("Header",request);
				engine.generateHtml("LeftNav",request);
				displayHotels(pWriter,city,state);
				engine.generateHtml("Footer",request);
			}
			else {
				engine.generateHtml("Header",request,"/EWAProject/Controller/HomeServlet?var=contactUs","POST");
				engine.generateHtml("LeftNav",request);
				pWriter.println("</td>\n" + 
						"<td style='text-align:left;width:59%;padding-left:50px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"<p>Thank you for using ReserveMyRoom website for your reservation. Please complete the form below, so we can provide quick and efficient service. If this is an urgent matter please contact Customer support:</p>\n" +
							"<table style='width:100%;'>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Contact Us Form</div></th> \n" +
								"</tr>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:25px;'>\n" +
										"<p></p>\n" +
									"</td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='text-align:center;'>\n" +
										"<p>Your details have been submitted successfully. Thank you for your feedback.</p>\n" +
									"</td> \n" + 
								"</tr>\n" + 
								"<tr>\n" +
								"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
									"<a style='width: 120px;padding:7px;text-align:center;' href='/EWAProject/Controller/HomeServlet' method='get'>Home</a>\n" +
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
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}