package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MySQLDataStoreUtilities;
import JavaBeans.*;

public class LoginServlet extends HttpServlet {
	public LoginServlet() {
		
	}
	
	public void displayLoginPage(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			String docType = "<!DOCTYPE HTML>\n";
			pWriter.println(docType + 
					"<html>\n" + 
					"<head>\n" + 
					"<title>Reserve My Room</title>\n" + 
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
					"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>\n" + 
					"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>\n" + 
					"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" + 
					"<script src='/EWAProject/js/jquery.min.js'></script>\n" + 
					"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
					"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
					"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
					"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
					"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
					"</head>\n" + 
					"<body onload='init()'>\n" + 
					"<!-- start header -->\n" + 
					"<div class='header_bg'>\n" + 
					"<div class='wrap'>\n" + 
					"	<div class='header'>\n" + 
					"		<div class='logo'>\n" + 
					"			<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
					"		</div>\n" + 
					"		<div class='h_right'>\n" + 
					"			<!--start menu -->\n" + 
					"			<ul class='menu'>\n" + 
					"				<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
					"				<li><a href='#'>packages</a></li> |\n" + 
					"				<li><a href='#'>top destinations</a></li> |\n" + 
					"				<li><a href='#'>contact us</a></li>\n" + 
					"				<div class='clear'></div>\n" + 
					"			</ul>\n" + 
					"			<ul class='menu1'>\n" + 
					"				<li class='active'><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
					"				<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
					"			</ul>\n" + 
					"		</div>\n" + 
					"		<div class='clear'></div>\n" + 
					"		<div class='top-nav'>\n" + 
					"		<nav class='clearfix'>\n" + 
					"				<ul>\n" + 
					"				<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
					"				<li><a href='#'>packages</a></li> \n" + 
					"				<li><a href='#'>top destinations</a></li> \n" + 
					"				<li><a href='#'>contact us</a></li>\n" + 
					"				</ul>\n" + 
					"				<a href='#' id='pull'>Menu</a>\n" + 
					"			</nav>\n" + 
					"		</div>\n" + 
					"	</div>\n" + 
					"</div>\n" + 
					"</div>\n" + 
					"<!--start main -->\n" + 
					"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
					"		<div class='wrap'>\n" + 
					"	<div class='main'>\n" + 
					"	<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='/EWAProject/Controller/LoginServlet' method='POST'>\n" + 
					"		<div>\n" + 
					"		<table>\n" + 
					"			<tr>\n" + 
					"				<td style='text-align:right;width:33%'>");
			engine.generateHtml("LeftNavLgnReg",request);
			pWriter.println("</td>\n" + 
					"				<td style='text-align:left;width:43%'>\n" + 
					"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
					"									<tr> 	\n" + 
					"										<td colspan='2' style='height:30px;'><p></p></td> \n" + 
					"									</tr>\n" + 
					"									<tr>	\n" + 
					"										<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Login Form</div></th> \n" + 
					"									</tr> \n" + 
					"									<tr> \n" + 
					"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
					"											<label for='username'>Username :&nbsp;&nbsp;</label> \n" + 
					"										</td> \n" + 
					"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
					"											<input type='text' id='username' style='width:200px' name='username' required/> \n" + 
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
					"											<label for='usertypelbl'>User Type :&nbsp;&nbsp;</label> \n" + 
					"										</td> \n" + 
					"										<td style='text-align:left;width:65%;height:40px;'> \n" + 
					"										&nbsp;&nbsp;\n" + 
					"											<select name='userType' style='width:120px;'>  \n" + 
					"												<option value='Customer'>Customer</option>  \n" + 
					"												<option value='Salesman'>Salesman</option>  \n" + 
					"												<option value='Manager'>Manager</option>  \n" + 
					"											</select> \n" + 
					"										</td> \n" + 
					"									</tr> \n" + 
					"									<tr> \n" + 
					"										<td colspan='2' align='center'> \n" + 
					"											<button name='sbmtButton' class='loginBtn' type='submit'>Login</button> \n" + 
					"										</td> \n" + 
					"									</tr> \n" + 
					"									<tr> 	\n" + 
					"										<td colspan='2' style='height:25px;'><p></p></td> \n" + 
					"									</tr> 	\n" + 
					"									<tr> \n" + 
					"										<td colspan='2' style='text-align:center;'> \n" + 
					"											<p>Dont have an Account&nbsp;?&nbsp;<a href='/EWAProject/Controller/RegisterServlet' method='get'>Create One</a></p> \n" + 
					"										</td> \n" + 
					"									</tr> \n" + 
					"									<tr> 	\n" + 
					"										<td colspan='2' style='height:15px;'><p></p></td> \n" + 
					"									</tr>\n" + 
					"								</table> \n" + 
					"				</td>\n" + 
					"				<td style='text-align:left;width:24%'>\n" + 
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
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void customerHomePage(PrintWriter pWriter,String username) {
		try {
			HashMap<String, Reservations> reservationsMap = new HashMap<String, Reservations>();
			MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				reservationsMap = dbObj.getReservations(username);
				Reservations reservation = new Reservations();
				pWriter.println("</td>\n" + 
						"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;vertical-align:initial;padding-left: 50px;'>\n" + 
							"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
								"<tr>\n" + 
									"<td colspan='5' style='height:30px;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='5'><div align='center'>Customer Home Page</div></th>\n" +	
								"</tr>\n" +
								"<tr>\n" + 
									"<td colspan='5' style='height:25px;'><p></p></td> \n" + 
								"</tr>\n" +
								"<tr>\n" +
									"<th style='text-align:center;padding:15px;'><div align='center'>Reservation ID</div></th> \n" +
									"<th style='text-align:center;'><div align='center'>Total Price</div></th> \n" +
									"<th style='text-align:center;'><div align='center'>Reservation Date</div></th> \n" +
									"<th style='text-align:center;'><div align='center'>City</div></th>\n" +
									"<th></th>\n" +
								"</tr>");
					if(reservationsMap == null || reservationsMap.isEmpty()) {
						pWriter.println("<tr>\n" +	
								"<td colspan='5' style='text-align:center;'>\n" +
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
									"<td style='text-align:center;'>\n" +
										"<p>" + reservation.getCity() + "</p>\n" +
									"</td>\n" +
									"<td style='text-align:center;' class='rsvBtn'>\n" +
										"<a href='/EWAProject/Controller/ReservationServlet?op=DeleteReservation&resID=" + reservation.getReservationID() + "'>Delete Reservation</a>\n" +
									"</td>\n" +
									"</tr>");
						}
						
					}
				pWriter.println("<tr>\n" +
									"<td colspan='5' style='text-align:right;padding:15px;height:40px;'>\n" +
										"<a href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
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
						"<td style='text-align:left;width:59%;vertical-align: -webkit-baseline-middle;vertical-align:initial;padding-left: 50px;'>\n" + 
							"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
								"<tr>\n" + 
									"<td style='height:30px;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<td style='height:15px;'>\n" +
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
		catch(Exception ex) {
			ex.printStackTrace();
		}				
	}
	
	public void salesmanHomePage(PrintWriter pWriter) {
		pWriter.println("</td>\n" + 
				"<td style='text-align:left;width:59%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
					"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"<tr>\n" + 
							"<td style='height:30px;'><p></p></td> \n" + 
						"</tr>\n" + 
						"<tr>\n" +
							"<th style='text-align:center;'>\n" +
								"<p>Salesman Home Page</p>\n" +
							"</th>\n" +
						"</tr>\n" +
						"<tr>\n" +	
							"<td style='height:20px;text-align:center;'>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
								"<a href='/EWAProject/Controller/SalesmanServlet?op=createAccount' method='get'>Create Customer Account</a>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
							"<a href='/EWAProject/Controller/SalesmanServlet?op=addReservation' method='get'>Add Customer Reservation</a>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
							"<a href='/EWAProject/Controller/SalesmanServlet?op=modifyReservationSelect' method='get'>Modify Reservation</a>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
								"<a href='/EWAProject/Controller/SalesmanServlet?op=deleteReservationSelect' method='get'>Delete Reservation</a>\n" +
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
	
	public void managerHomePage(PrintWriter pWriter) {
		pWriter.println("</td>\n" + 
				"<td style='text-align:left;width:59%;vertical-align: baseline;padding-left: 50px;'>\n" + 
					"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"<tr>\n" + 
							"<td style='height:30px;'><p></p></td> \n" + 
						"</tr>\n" + 
						"<tr>\n" +
							"<th style='text-align:center;'>\n" +
								"<p><h2>Manager Home Page</h2></p>\n" +
							"</th>\n" +
						"</tr>\n" +
						"<tr>\n" +	
							"<td style='height:20px;text-align:center;'>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
								"<a href='/EWAProject/Controller/ManagerServlet?op=addHotel' method='get'>Add Hotel</a>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
							"<a href='/EWAProject/Controller/ManagerServlet?op=updateHotelSelect' method='get'>Update Hotel</a>\n" +
							"</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
							"<td colspan='2' style='text-align:center;padding:15px;' class='rsvBtn'>\n" +
							"<a href='/EWAProject/Controller/ManagerServlet?op=deleteHotelSelect' method='get'>Delete Hotel</a>\n" +
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String op = request.getParameter("op");
			HttpSession userSession = request.getSession(true);
			
			if(op==null) {
				displayLoginPage(request,response);
			}
			else {
				switch(op) {
					case "CustomerHome":{
						String username = (String)userSession.getAttribute("customerKey");
						if(username != null) {
							response.setContentType("text/html");
							PrintWriter pWriter = response.getWriter();
							HtmlEngine engine = new HtmlEngine(pWriter);
							
							engine.generateHtml("Header",request);
							engine.generateHtml("LeftNav",request);
							customerHomePage(pWriter,username);
							engine.generateHtml("Footer",request);
						}
						else {
							response.sendRedirect("/csj/Controller/LoginServlet");
						}
						break;
					}
					case "SalesmanHome":{
						String username = (String) userSession.getAttribute("salesmanKey");
						if(username!=null) {
							response.setContentType("text/html");
							PrintWriter pWriter = response.getWriter();
							HtmlEngine engine = new HtmlEngine(pWriter);
							engine.generateHtml("HeaderSalesman",request,"","");
							engine.generateHtml("LeftNav",request);
							salesmanHomePage(pWriter);
							engine.generateHtml("Footer",request);
						}
						else {
							response.sendRedirect("/csj/Controller/LoginServlet");
						}
						break;
					}
					case "ManagerHome":{
						String username = (String) userSession.getAttribute("managerKey");
						if(username != null) {
							response.setContentType("text/html");
							PrintWriter pWriter = response.getWriter();
							HtmlEngine engine = new HtmlEngine(pWriter);
							engine.generateHtml("HeaderManager",request,"","");
							engine.generateHtml("LeftNav",request);
							managerHomePage(pWriter);
							engine.generateHtml("Footer",request);
						}
						else {
							response.sendRedirect("/csj/Controller/LoginServlet");
						}
						break;
					}
					default:{
						displayLoginPage(request,response);
						break;
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void displayInvalidCredentials(HttpServletRequest request,HttpServletResponse response,String message) {
		try {
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			String docType = "<!DOCTYPE HTML>\n";
			pWriter.println(docType + 
					"<html>\n" + 
					"<head>\n" + 
					"<title>Reserve My Room</title>\n" + 
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
					"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>\n" + 
					"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>\n" + 
					"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" + 
					"<script src='/EWAProject/js/jquery.min.js'></script>\n" + 
					"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
					"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
					"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
					"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
					"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
					"</head>\n" + 
					"<body onload='init()'>\n" + 
					"<!-- start header -->\n" + 
					"<div class='header_bg'>\n" + 
					"<div class='wrap'>\n" + 
					"	<div class='header'>\n" + 
					"		<div class='logo'>\n" + 
					"			<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
					"		</div>\n" + 
					"		<div class='h_right'>\n" + 
					"			<!--start menu -->\n" + 
					"			<ul class='menu'>\n" + 
					"				<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
					"				<li><a href='#'>packages</a></li> |\n" + 
					"				<li><a href='#'>top destinations</a></li> |\n" + 
					"				<li><a href='#'>contact us</a></li>\n" + 
					"				<div class='clear'></div>\n" + 
					"			</ul>\n" + 
					"			<ul class='menu1'>\n" + 
					"				<li class='active'><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
					"				<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
					"			</ul>\n" + 
					"		</div>\n" + 
					"		<div class='clear'></div>\n" + 
					"		<div class='top-nav'>\n" + 
					"		<nav class='clearfix'>\n" + 
					"				<ul>\n" + 
					"				<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
					"				<li><a href='#'>packages</a></li> \n" + 
					"				<li><a href='#'>top destinations</a></li> \n" + 
					"				<li><a href='#'>contact us</a></li>\n" + 
					"				</ul>\n" + 
					"				<a href='#' id='pull'>Menu</a>\n" + 
					"			</nav>\n" + 
					"		</div>\n" + 
					"	</div>\n" + 
					"</div>\n" + 
					"</div>\n" + 
					"<!--start main -->\n" + 
					"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
					"		<div class='wrap'>\n" + 
					"	<div class='main'>\n" + 
					"	<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='/EWAProject/Controller/LoginServlet' method='GET'>\n" + 
					"		<div>\n" + 
					"		<table>\n" + 
					"			<tr>\n" + 
					"				<td style='text-align:right;width:33%'>");
			engine.generateHtml("LeftNavLgnReg",request);
			pWriter.println("</td>\n" + 
					"				<td style='text-align:left;width:43%'>\n" + 
					"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
					"									<tr> 	\n" + 
					"										<td colspan='2' style='height:30px;'><p></p></td> \n" + 
					"									</tr>\n" + 
					"									<tr>	\n" + 
					"										<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Login Form</div></th> \n" + 
					"									</tr> \n" + 
														"<tr>\n" +
															"<td colspan='2' align='center'>\n" +
																"<label for='msg' style='color: red;'>" + message + "</label>\n" +
															"</td>\n" +
														"</tr>\n" +
					"									<tr> \n" + 
					"										<td style='text-align:right;width:35%;height:40px;'> \n" + 
					"											<label for='username'>Username :&nbsp;&nbsp;</label> \n" + 
					"										</td> \n" + 
					"										<td style='text-align:right;width:65%;height:40px;'> \n" + 
					"											<input type='text' id='username' style='width:200px' name='username' required/> \n" + 
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
					"											<label for='usertypelbl'>User Type :&nbsp;&nbsp;</label> \n" + 
					"										</td> \n" + 
					"										<td style='text-align:left;width:65%;height:40px;'> \n" + 
					"										&nbsp;&nbsp;\n" + 
					"											<select name='userType' style='width:120px;'>  \n" + 
					"												<option value='Customer'>Customer</option>  \n" + 
					"												<option value='Salesman'>Salesman</option>  \n" + 
					"												<option value='Manager'>Manager</option>  \n" + 
					"											</select> \n" + 
					"										</td> \n" + 
					"									</tr> \n" + 
					"									<tr> \n" + 
					"										<td colspan='2' align='center'> \n" + 
					"											<button name='sbmtButton' class='loginBtn' type='submit'>Login</button> \n" + 
					"										</td> \n" + 
					"									</tr> \n" + 
					"									<tr> 	\n" + 
					"										<td colspan='2' style='height:25px;'><p></p></td> \n" + 
					"									</tr> 	\n" + 
					"									<tr> \n" + 
					"										<td colspan='2' style='text-align:center;'> \n" + 
					"											<p>Dont have an Account&nbsp;?&nbsp;<a href='/EWAProject/Controller/RegisterServlet' method='get'>Create One</a></p> \n" + 
					"										</td> \n" + 
					"									</tr> \n" + 
					"									<tr> 	\n" + 
					"										<td colspan='2' style='height:15px;'><p></p></td> \n" + 
					"									</tr>\n" + 
					"								</table> \n" + 
					"				</td>\n" + 
					"				<td style='text-align:left;width:24%'>\n" + 
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
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String userType = request.getParameter("userType");
			if(username!=null && password!=null) {
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = dbObj.checkDBConnection();
				if(dbStatus.get("status").equals("true")) {
					Users userProfile = dbObj.getUserProfile(username);
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					
					if(userProfile!=null) {
						String actualUsername = userProfile.getUsername();
						String actualPassword = userProfile.getPWD();
						String actualUserType = userProfile.getUserType();
						if(actualUsername.equals(username) && actualPassword.equals(password) && actualUserType.equals(userType)) {
							switch(userType) {
								case "Customer":{
									HttpSession userSession = request.getSession(true);
									userSession.setAttribute("customerKey", username);
									ReserveList rs = (ReserveList) userSession.getAttribute("ReserveList");
									if(rs == null) {
										rs = new ReserveList();
										rs.setListSize(0);
										rs.setUsername(username);
										userSession.setAttribute("ReserveList", rs);
									}
									//Display Customer Home Page
									engine.generateHtml("Header",request);
									engine.generateHtml("LeftNav",request);
									customerHomePage(pWriter,username);
									engine.generateHtml("Footer",request);
									break;
								}
								case "Salesman":{
									HttpSession userSession = request.getSession(true);
									userSession.setAttribute("salesmanKey", username);
									//Display Salesman Home Page
									engine.generateHtml("HeaderSalesman",request,"","");
									engine.generateHtml("LeftNav",request);
									salesmanHomePage(pWriter);
									engine.generateHtml("Footer",request);
									break;
								}
								case "Manager":{
									HttpSession userSession = request.getSession(true);
									userSession.setAttribute("managerKey", username);
									//Display Manager Home Page
									engine.generateHtml("HeaderManager",request,"","");
									engine.generateHtml("LeftNav",request);
									managerHomePage(pWriter);
									engine.generateHtml("Footer",request);
									break;
								}
								default:{
									
								}
							}
						}
						else {
							displayInvalidCredentials(request,response,"Invalid Username or Password");
						}
					}
					else {
						displayInvalidCredentials(request,response,"Invalid Username or Password");
					}
				}
				else {
					displayInvalidCredentials(request,response,dbStatus.get("msg"));
				}
			}
			else {
				displayInvalidCredentials(request,response,"Invalid Username or Password");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}