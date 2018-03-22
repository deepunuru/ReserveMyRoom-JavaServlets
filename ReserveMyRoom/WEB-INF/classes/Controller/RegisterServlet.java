package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MySQLDataStoreUtilities;
import JavaBeans.Users;

public class RegisterServlet extends HttpServlet {
	public RegisterServlet() {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		HtmlEngine engine = new HtmlEngine(pWriter);
		String docType = "<!DOCTYPE HTML>\n";
		pWriter.println(docType + 
				"<html>\n" + 
				"<head>\n" + 
				"<title>User Registration</title>\n" + 
				"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
				"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>\n" + 
				"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>\n" + 
				"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" + 
				"<script src='/EWAProject/js/jquery.min.js'></script>\n" + 
				"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
				"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
				"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
				"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
				"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
				"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
				"<script>\n" + 
				"	$(function() {\n" + 
				"		$( '#dob' ).datepicker();\n" + 
				"	});\n" + 
				"</script>\n" + 
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
				"				<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
				"				<li class='active'><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
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
				"<div class='wrap'>\n" + 
				"	<div class='main'>\n" + 
				"	<form class='width:100%;margin: 0 auto;height:60%;border: 1px solid #ccc;border-radius: 15px;' action='/EWAProject/Controller/RegisterServlet' method='POST'>								\n" + 
				"		<div>\n" + 
				"		<table>\n" + 
				"			<tr>\n" + 
				"				<td style='text-align:right;width:30%;'>"); 
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
				"											<label for='usertypelbl'>User Type :&nbsp;&nbsp;</label> \n" + 
				"										</td> \n" + 
				"										<td style='text-align:left;width:65%;height:40px;'> \n" + 
				"											<select name='userType' style='width:120px;'>  \n" + 
				"												<option value='Customer'>Customer</option>  \n" + 
				"												<option value='Salesman'>Salesman</option>  \n" + 
				"												<option value='Manager'>Manager</option>  \n" + 
				"											</select> \n" + 
				"										</td> \n" + 
				"									</tr>\n" + 
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
				"											<input type='text' id='dob' style='width:200px' name='dob' value='DD/MM/YY' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = 'DD/MM/YY';}' required/> \n" + 
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
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
			String username = request.getParameter("username");
			String userType = request.getParameter("userType");
			HashMap<String, String> dbStatus = new HashMap<String, String>();
			dbStatus = dbObj.checkDBConnection();
			if(dbStatus.get("status").equals("true")) {
				Users user = dbObj.getUserProfile(username);
				if(user == null)
				{
					user = new Users(userType,username,request.getParameter("firstName"),request.getParameter("lastName"),request.getParameter("address"),Integer.parseInt(request.getParameter("zipCode")),request.getParameter("email"),request.getParameter("dob"),request.getParameter("password"));
					dbObj.writeUserProfile(user);
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNavLgnReg",request);
					pWriter.println("</td>\n" + 
							"				<td style='text-align:left;width:33%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
							"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
							"									<tr> 	\n" + 
							"										<td colspan='2' style='height:30px;'><p></p></td> \n" + 
							"									</tr>\n" + 
							"									<tr> 	\n" + 
							"										<td colspan='2' style='height:15px;padding:25px;'>"+ 
																		"<p>User - " + username +" Registered Successfully.</p>\n" +
																		"<p>Please proceed to Login page.</p>\n" +	
							"										</td> \n" + 
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
				else if((user.getUsername()).equals(username))
				{
					response.setContentType("text/html");
					PrintWriter pWriter = response.getWriter();
					HtmlEngine engine = new HtmlEngine(pWriter);
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNavLgnReg",request);
					pWriter.println("</td>\n" + 
							"				<td style='text-align:left;width:33%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
							"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
							"									<tr> 	\n" + 
							"										<td colspan='2' style='height:30px;'><p></p></td> \n" + 
							"									</tr>\n" + 
							"									<tr> 	\n" + 
							"										<td colspan='2' style='height:15px;padding:25px;'>"+ 
																		"<p>User - " + username +" already registered.</p>\n" +
																		"<p>Please proceed to Login page.</p>\n" +	
							"										</td> \n" + 
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
			}
			else {
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				engine.generateHtml("Header",request);
				engine.generateHtml("LeftNavLgnReg",request);
				pWriter.println("</td>\n" + 
						"				<td style='text-align:left;width:33%'>\n" + 
						"								<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
						"									<tr> 	\n" + 
						"										<td colspan='2' style='height:15px;padding:25px;'>"+ 
																"<p>" + dbStatus.get("msg") + "</p>\n" +		
						"										</td> \n" + 
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
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}