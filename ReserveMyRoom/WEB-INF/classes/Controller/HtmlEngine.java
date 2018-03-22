package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JavaBeans.Hotels;
import JavaBeans.ReserveList;

public class HtmlEngine extends HttpServlet {

	PrintWriter pWriter;
	DealMatches dm;
	
	public HtmlEngine(PrintWriter writer) {
		pWriter = writer;
		dm = new DealMatches();
	}
	
	public void generateHtml(String pageComponent,HttpServletRequest request,String action,String method)
	{
		try {
			switch(pageComponent) {
				case "Header":{
					HttpSession userSession = request.getSession(true);
					String username = (String)userSession.getAttribute("customerKey");
					if(username!=null) {
						ReserveList rs = (ReserveList) userSession.getAttribute("ReserveList");
						int listSize = rs.getListSize();
						String docType = "<!DOCTYPE HTML>\n";
						pWriter.println(docType + 
								"<html>\n" + 
									"<head>\n" + 
										"<title>Reserve My Room</title>\n" + 
										"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
										"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>\n" + 
										"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>\n" + 
										"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" +
										"<link rel='stylesheet' href='/EWAProject/css/bootstrap.css'>\n" + 
										"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>\n" + 
										"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js'></script>\n" + 
										"<!---strat-date-piker---->\n" + 
										"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
										"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
										"<script>\n" + 
											"$(function() {\n" + 
												"$( '#datepicker1,#datepicker2' ).datepicker();\n" + 
											"});\n" + 
										"</script>\n" + 
										"<!---/End-date-piker---->\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" + 
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/MyListServlet' class='accBtn'>My Lists(" + String.valueOf(listSize) + ")</a></li> |\n" +
														"<li><a href='/EWAProject/Controller/LoginServlet?op=CustomerHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" +
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" + 
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else {
						String docType = "<!DOCTYPE HTML>\n";
						pWriter.println(docType + 
								"<html>\n" + 
									"<head>\n" + 
										"<title>Reserve My Room</title>\n" + 
										"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
										"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>\n" + 
										"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>\n" + 
										"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" +
										"<link rel='stylesheet' href='/EWAProject/css/bootstrap.css'>\n" + 
										"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>\n" + 
										"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js'></script>\n" + 
										"<!---strat-date-piker---->\n" + 
										"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
										"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
										"<script>\n" + 
											"$(function() {\n" + 
												"$( '#datepicker1,#datepicker2' ).datepicker();\n" + 
											"});\n" + 
										"</script>\n" + 
										"<!---/End-date-piker---->\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" + 
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					break;
				}
				case "HeaderSalesman":{
					HttpSession userSession = request.getSession(true);
					String salesmanKey = (String)userSession.getAttribute("salesmanKey");
					if(salesmanKey!=null) {
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
										"<!---strat-date-picker---->\n" + 
										"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
										"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
										"<script>\n" + 
											"$(function() {\n" + 
												"$( '#checkInDate,#checkOutDate,#dob' ).datepicker();\n" + 
											"});\n" + 
										"</script>\n" + 
										"<!---/End-date-piker---->\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/HotelList.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/roomTypeSalesman.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else {
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
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" +
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					break;
				}
				case "HeaderManager":{
					HttpSession userSession = request.getSession(true);
					String managerKey = (String)userSession.getAttribute("managerKey");
					if(managerKey!=null) {
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
										"<script src='http://d3js.org/d3.v4.min.js' charset='utf-8'></script>\n" + 
							            "<script src='https://d3js.org/d3-queue.v2.min.js'></script>\n" +
							            "<script src='https://d3js.org/d3-scale-chromatic.v1.min.js'></script>\n" + 
							            "<script src='https://d3js.org/topojson.v2.min.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/HotelList.js'></script>\n" +
										"<script type='text/javascript' src='https://www.google.com/jsapi'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Sales Reports</a></li>\n" +
														"<li style='width:20px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li class='acc'><a href='/EWAProject/Controller/LoginServlet?op=ManagerHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else {
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
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" + 
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					break;
				}
				case "HeaderManagerExplor":{
					HttpSession userSession = request.getSession(true);
					String managerKey = (String)userSession.getAttribute("managerKey");
					if(managerKey!=null) {
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
										"<script src='http://d3js.org/d3.v4.min.js' charset='utf-8'></script>\n" + 
							            "<script src='https://d3js.org/d3-queue.v2.min.js'></script>\n" +
							            "<script src='https://d3js.org/d3-scale-chromatic.v1.min.js'></script>\n" + 
							            "<script src='https://d3js.org/topojson.v2.min.js'></script>\n" +										
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/HotelList.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Sales Reports</a></li>\n" +
														"<li style='width:20px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li class='acc'><a href='/EWAProject/Controller/LoginServlet?op=ManagerHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else {
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
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" + 
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='" + action + "' method='" + method + "'>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					break;
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void printHotel(String[] imgList,String[] cityList,String[] nameList,String[] idList,PrintWriter pWriter,int k) {	
		pWriter.println("<img src='/EWAProject/" + imgList[k] + "' style='width:300px;height:300px' alt='" + imgList[k] +"'>\n" +
				"<p><a href='/EWAProject/Controller/DisplayHotel?id=" + idList[k] + "' method='get'>" + nameList[k] +"</a>\n" +
				"<br />\n" +
				//"<input type='submit' value='Book Now' formaction='/EWAProject/Controller/DisplayHotel?id=" + idList[k] + "' formmethod='POST' style='width: 120px;padding:3px;'>\n" +
				//"<br />\n" +
				"<label>City :&nbsp;&nbsp;" + cityList[k] +"</label>\n" +
				"<br />\n" +
				"<a style='width: 120px;padding:3px;' href='/EWAProject/Controller/ReserveHotel?id=" + idList[k] + "&op=AddToList&city=None&state=None' method='get'>Reserve</a>\n" +
				"<br />\n" +
				"<a href='/EWAProject/Controller/HotelReviews?op=viewReviews&hotelName=" + nameList[k] + "'>View Reviews</a>\n" +
				"<br />\n" +
				"<a href='/EWAProject/Controller/HotelReviews?op=writeReview&hotelName=" + nameList[k] + "'>Write Reviews</a>\n" +
				"<br />");
	}
				
	
	public void generateHtml(String pageComponent,HttpServletRequest request)
	{
		try {
			switch(pageComponent) {
				case "HomePageHeader": {
					HttpSession userSession = request.getSession(true);
					String username = (String)userSession.getAttribute("customerKey");
					if(username!=null) {
						ReserveList rs = (ReserveList) userSession.getAttribute("ReserveList");
						int listSize = rs.getListSize();
						String docType = "<!DOCTYPE HTML>\n";
						pWriter.println(docType + 
						"<html>\n" + 
						"<head>\n" + 
						"<title>Reserve My Room</title>\n" + 
						"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
						"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'/>\n" + 
						"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'/>\n" + 
						"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" + 
						"<script src='/EWAProject/js/css3-mediaqueries.js'></script>\n" + 
						"<!--Start Carousel-->\n" + 
						"<link rel='stylesheet' href='/EWAProject/css/bootstrap.css'>\n" + 
						"<!--<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css'/>-->\n" + 
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>\n" + 
						"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js'></script>\n" + 
						"<!--End Carousel-->\n" + 
						"<!---strat-date-piker---->\n" + 
						"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
						"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
						"<script>\n" + 
							"$(function() {\n" + 
								"$( '#datepicker,#datepicker1' ).datepicker();\n" + 
							"});\n" + 
						"</script>\n" + 
						"<!---/End-date-piker---->\n" + 
						"<link type='text/css' rel='stylesheet' href='/EWAProject/css/DropDown.css' />\n" + 
						//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
						//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
						//"<script type='text/javascript' src='/EWAProject/js/roomTypeList.js'></script>\n" +
						"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
						//"<script type='text/javascript' src='/EWAProject/js/CityAutoComplete.js'></script>\n" +
						//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" + 
						"<!--nav-->\n" + 
						"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
						"</head>\n" + 
						"<body onload='init()'>\n" + 
						"<!-- start header -->\n" + 
						"<div class='header_bg'>\n" + 
						"<div class='wrap'>\n" + 
						"<div class='header'>\n" + 
						"<div class='logo'>\n" + 
							"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
						"</div>\n" + 
						"<div style='float:right;'>\n" + 
						"<!--start menu -->\n" + 
						"<ul class='menu'>\n" + 
						"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
						"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
						"<li><a href='#'>top destinations</a></li> |\n" + 
						"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
						"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
						"<li><a href='/EWAProject/Controller/MyListServlet' class='accBtn'>My Lists(" + String.valueOf(listSize) + ")</a></li> |\n" +
						"<li><a href='/EWAProject/Controller/LoginServlet?op=CustomerHome' class='accBtn'>Account</a></li> |\n" +  
						//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
						"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" +
						"<div class='clear'></div>\n" + 
						"</ul>\n" + 
						"<!-- start profile_details -->\n" +  
						"</div>\n" + 
						"<div class='clear'></div>\n" + 
						"<div class='top-nav'>\n" + 
						"<nav class='clearfix'>\n" + 
						"<ul>\n" + 
						"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
						"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
						"<li><a href='#'>top destinations</a></li> \n" + 
						"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
						"</ul>\n" + 
						"<a href='#' id='pull'>Menu</a>\n" + 
						"</nav>\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>\n" +
						"</div>\n" +
						"<!----Start-Carousel---->\n" + 
						"<div style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;height: 700px;background-repeat: no-repeat;'>\n" + 
						"<div id='myCarousel1' class='carousel slide' data-ride='carousel' style='width:60%;height:620px;left:20%;right:20%;opacity: 0.8;'>\n" + 
						"<ol class='carousel-indicators'>\n" + 
						"<li data-target='#myCarousel1' data-slide-to='0' class='active'></li>\n" + 
						"<li data-target='#myCarousel1' data-slide-to='1'></li>\n" + 
						"<li data-target='#myCarousel1' data-slide-to='2'></li>\n" + 
						"</ol>\n" + 
						"<!-- Wrapper for slides -->\n" + 
						"<div class='carousel-inner'>\n" + 
						"<div class='item active'>\n" + 
						"<img src='/EWAProject/images/140596_275_z.jpg' alt='140596_275_z.jpg' style='width:100%;height:550px;margin-top:5%;'/>	\n" + 
						"</div>\n" + 
						"<div class='item'>\n" + 
						"<img src='/EWAProject/images/besthotelsites-1.jpg' alt='besthotelsites-1.jpg' style='width:100%;height:550px;margin-top:5%;'/>\n" + 
						"</div>\n" + 
						"<div class='item'	>\n" + 
						"<img src='/EWAProject/images/Hotel-Chinzanso-Tokyo_spa1.jpg' alt='Hotel-Chinzanso-Tokyo_spa1.jpg' style='width:100%;height:550px;margin-top:5%;'/>\n" + 
						"</div>\n" + 
						"</div>\n" + 
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
						"</div>\n" + 
						"<!--End Carousel-->");
					}
					else {
						String docType = "<!DOCTYPE HTML>\n";
						pWriter.println(docType + 
						"<html>\n" + 
						"<head>\n" + 
						"<title>Reserve My Room</title>\n" + 
						"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
						"<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'/>\n" + 
						"<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'/>\n" + 
						"<link href='/EWAProject/css/style.css' rel='stylesheet' type='text/css' media='all' />\n" + 
						"<script src='/EWAProject/js/css3-mediaqueries.js'></script>\n" + 
						"<!--Start Carousel-->\n" + 
						"<link rel='stylesheet' href='/EWAProject/css/bootstrap.css'>\n" + 
						"<!--<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css'/>-->\n" + 
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>\n" + 
						"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js'></script>\n" + 
						"<!--End Carousel-->\n" + 
						"<!---strat-date-piker---->\n" + 
						"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
						"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
						"<script>\n" + 
							"$(function() {\n" + 
								"$( '#datepicker,#datepicker1' ).datepicker();\n" + 
							"});\n" + 
						"</script>\n" + 
						"<!---/End-date-piker---->\n" + 
						"<link type='text/css' rel='stylesheet' href='/EWAProject/css/DropDown.css' />\n" + 
						//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
						//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
						//"<script type='text/javascript' src='/EWAProject/js/roomTypeList.js'></script>\n" +
						"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
						//"<script type='text/javascript' src='/EWAProject/js/CityAutoComplete.js'></script>\n" +
						//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" + 
						"<!--nav-->\n" + 
						"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
						"</head>\n" + 
						"<body onload='init()'>\n" + 
						"<!-- start header -->\n" + 
						"<div class='header_bg'>\n" + 
						"<div class='wrap'>\n" + 
						"<div class='header'>\n" + 
						"<div class='logo'>\n" + 
							"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
						"</div>\n" + 
						"<div style='float:right;'>\n" + 
						"<!--start menu -->\n" + 
						"<ul class='menu'>\n" + 
						"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
						"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
						"<li><a href='#'>top destinations</a></li> |\n" + 
						"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
						"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
						"<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
						"<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
						"<div class='clear'></div>\n" + 
						"</ul>\n" + 
						"<!-- start profile_details -->\n" + 
						"</div>\n" + 
						"<div class='clear'></div>\n" + 
						"<div class='top-nav'>\n" + 
						"<nav class='clearfix'>\n" + 
						"<ul>\n" + 
						"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
						"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
						"<li><a href='#'>top destinations</a></li> \n" + 
						"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
						"</ul>\n" + 
						"<a href='#' id='pull'>Menu</a>\n" + 
						"</nav>\n" + 
						"</div>\n" + 
						"</div>\n" + 
						"</div>\n" +
						"</div>\n" +
						"<!----Start-Carousel---->\n" + 
						"<div style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;height: 700px;background-repeat: no-repeat;'>\n" + 
						"<div id='myCarousel1' class='carousel slide' data-ride='carousel' style='width:60%;height:620px;left:20%;right:20%;opacity: 0.8;'>\n" + 
						"<ol class='carousel-indicators'>\n" + 
						"<li data-target='#myCarousel1' data-slide-to='0' class='active'></li>\n" + 
						"<li data-target='#myCarousel1' data-slide-to='1'></li>\n" + 
						"<li data-target='#myCarousel1' data-slide-to='2'></li>\n" + 
						"</ol>\n" + 
						"<!-- Wrapper for slides -->\n" + 
						"<div class='carousel-inner'>\n" + 
						"<div class='item active'>\n" + 
						"<img src='/EWAProject/images/140596_275_z.jpg' alt='140596_275_z.jpg' style='width:100%;height:550px;margin-top:5%;'/>	\n" + 
						"</div>\n" + 
						"<div class='item'>\n" + 
						"<img src='/EWAProject/images/besthotelsites-1.jpg' alt='besthotelsites-1.jpg' style='width:100%;height:550px;margin-top:5%;'/>\n" + 
						"</div>\n" + 
						"<div class='item'	>\n" + 
						"<img src='/EWAProject/images/Hotel-Chinzanso-Tokyo_spa1.jpg' alt='Hotel-Chinzanso-Tokyo_spa1.jpg' style='width:100%;height:550px;margin-top:5%;'/>\n" + 
						"</div>\n" + 
						"</div>\n" + 
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
						"</div>\n" + 
						"<!--End Carousel-->");
					}
					
					break;
				}
				case "HomePageContent":{
					
					pWriter.println("</td>\n" + 
					"<td style='width:85%;'>\n" + 
					"<div class='online_reservation'>\n" + 
					"<div class='b_room'>\n" + 
					"<div class='booking_room'>\n" + 
					"<h4>book a room online</h4>\n" + 
					"<p>No ReserveMyRoom cancellation fee to change or cancel almost any hotel reservation</p>\n" + 
					"</div>\n" + 
					"<div class='reservation'>\n" + 
						"<form action='/EWAProject/Controller/HomeServlet' method='POST'>\n" + 
							"<table style='width: 100%;'>\n" + 
								"<tr>\n" + 
									"<td style='padding:10px;'>\n" + 
										"<table>\n" +
											"<tr>\n" + 
												"<td style='width:25%;text-align:center;padding: 10px;'>\n" + 
													"<h5>Destination</h5>\n" + 
													"<div name='autofill' class='book_date' style='padding-top:1px;width:350px;'>\n" +
														"<input type='text' name='city' id='city' onkeyup='doCompletionCity()' placeholder='City...'>\n" +
														"<input type='hidden' id='cityHidden' name='cityHidden'>\n" +
														"<input type='hidden' id='stateHidden' name='stateHidden'>\n" +
														"<div id='cityTblRow'>\n" +
															"<table id='cityTable' class='gridtable' style='position:absolute;text-align:left;width:315px;'></table>\n" +
														"</div>\n" +
													"</div>\n" +
												"</td>\n" + 
												"<td style='width:25%;text-align:center;padding: 10px;'>\n" + 
													"<h5>check-in-date:</h5>\n" + 
													"<div class='book_date'>\n" + 
														"<div style='padding-top:1px;'>\n" + 
															"<input class='date' id='datepicker' type='text' name='checkInDate' placeholder='DD/MM/YY' required>\n" + 
														"</div>\n" + 					
													"</div>\n" + 
												"</td>\n" + 
												"<td style='width:25%;text-align:center;padding: 10px;'>\n" + 
													"<h5>check-out-date:</h5>\n" + 
													"<div class='book_date'>\n" + 
														"<div style='padding-top:1px;'>\n" + 
															"<input class='date' id='datepicker1' type='text' name='checkOutDate' placeholder='DD/MM/YY' required>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</td>\n" + 
												"<td style='width:25%;text-align:center;padding: 10px;'>\n" + 
													"<h5>Adults:</h5>\n" + 
													"<!----------start section_room----------->\n" + 
													"<div class='section_room' style='padding-top:1px;'>\n" + 
														"<select id='guestCount' name='guestCount' required>\n" + 
															"<option value='1'>1</option>\n" + 
															"<option value='2'>2</option>\n" + 
															"<option value='3'>3</option>\n" + 
															"<option value='4'>4</option>\n" + 
														"</select>\n" + 
													"</div>\n" + 
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" + 
												"<td style='text-align:right;padding:10px;' colspan='5'>\n" +
													"<div class='date_btn'>\n" + 
														"<input type='submit' style='width:250px;' value='book now' />\n" + 
													"</div>\n" + 
												"</td>\n" +
											"</tr>\n" +
										"</table>\n" +
									"</td>\n" + 
								"</tr>\n" + 
							"</table>\n" + 
						"</form>\n" + 
					"</div>\n" + 
					"<div class='clear'></div>\n" + 
					"</div>\n" + 
					"</div>\n" + 
					"<div style='padding:20px;'></div>\n" +
					"<div style='width:100%;'>");
					String absoluteDiskPath="";
					try {
						absoluteDiskPath = request.getServletContext().getRealPath("/DealMatches.txt");
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
					HashMap<String, Hotels> deals = dm.readDealMatches(absoluteDiskPath);
					Hotels hotel = new Hotels();
					if(deals!=null) {
						
						String[] imgList = new String[30];
						String[] cityList = new String[30];
						String[] nameList = new String[30];
						String[] idList = new String[30];
						if(deals.size()==1 || absoluteDiskPath.equals("") || absoluteDiskPath==null) {
							File f = new File(absoluteDiskPath);
							for(Map.Entry<String, Hotels> p : deals.entrySet()) {
								if(p.getKey().equals("Invalid File Path") || p.getKey().equals("Hotel List is null") || p.getKey().equals("MySQL server is not up and running") || p.getKey().equals("No Offers Found")) {
									pWriter.println("<table style='width:100%;padding:20px;'>\n" +
											"<tr>\n" +
												"<th><div align='center'>We beat our competitors in all aspects\nPrice-Match Guaranteed</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<p>" + p.getKey() + "</p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<th><div align='center'>Deal Matches</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<p>No Deals Found</p>\n" +
												"</td>\n" +
											"</tr>\n" +
										"</table>");
								}
								else {
									hotel = p.getValue();
									HashMap<String,String> hotelImages = hotel.getHotelImages();
									imgList[0] = hotelImages.entrySet().iterator().next().getValue();
									cityList[0] = hotel.getCity();
									nameList[0] = hotel.getName();
									idList[0] = hotel.getID();
									pWriter.println("<table style='width:100%;padding:20px;'>\n" +
											"<tr>\n" +
												"<th><div align='center'>We beat our competitors in all aspects\nPrice-Match Guaranteed</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>\n" +
													"<p>" + p.getKey() + "</p>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<th><div align='center'>Deal Matches</div></th>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td style='text-align:center;'>");
									printHotel(imgList,cityList,nameList,idList,pWriter,0);
								pWriter.println("</td>\n" +
											"</tr>\n" +
										"</table>");
								}
							}
						}
						else {
							String[] dealList = new String[2];
							int i=0;
							pWriter.println("<table style='width:100%;padding:20px;'>\n" +
									"<tr>\n" +
										"<th colspan='2'><div align='center'>We beat our competitors in all aspects\nPrice-Match Guaranteed</div></th>\n" +
									"</tr>\n" +
									"<tr>\n" +
										"<td colspan='2' style='text-align:center;'>");
							if(deals.size()==0) {
								pWriter.println("<p>No Offers Found</p>");
							}
							else {
								for(Map.Entry<String, Hotels> p : deals.entrySet()) {
									pWriter.println("<p>" + p.getKey() + "</p>");
									dealList[i] = p.getKey();
									i++;
								}
							}				
						pWriter.println("</td>\n" +
									"</tr>\n" +
									"<tr>\n" +
										"<th colspan='2'><div align='center'>Deal Matches</div></th>\n" +
									"</tr>\n" +
									"<tr>");
						hotel = deals.get(dealList[0]);
						if(hotel==null) {
							pWriter.println("<td colspan='2' style='text-align:center;padding:10px;'>\n" +
									"<p>No Deals Found</p>");
						}
						else {
							HashMap<String,String> hotelImages = hotel.getHotelImages();
							imgList[0] = hotelImages.entrySet().iterator().next().getValue();
							cityList[0] = hotel.getCity();
							nameList[0] = hotel.getName();
							idList[0] = hotel.getID();
							pWriter.println("<td style='text-align:center;padding:10px;'>");
							printHotel(imgList,cityList,nameList,idList,pWriter,0);
							pWriter.println("</td>\n" +
									"<td style='text-align:center;padding:10px;'>");
							hotel = deals.get(dealList[1]);
							hotelImages = hotel.getHotelImages();
							imgList[1] = hotelImages.entrySet().iterator().next().getValue();
							cityList[1] = hotel.getCity();
							nameList[1] = hotel.getName();
							idList[1] = hotel.getID();
							printHotel(imgList,cityList,nameList,idList,pWriter,1);
						}
						pWriter.println("</td>\n" +
									"</tr>\n" +
								"</table>");
						}
					}
					pWriter.println("</div>\n" +
					"<!--start grids_of_3 -->\n" + 
					"<div class='grids_of_3'>\n" + 
					"<div class='grid1_of_3'>\n" + 
					"<div class='grid1_of_3_img'>\n" + 
					"<a href='#'>\n" + 
					"<img src='/EWAProject/images/pic2.jpg' alt='' />\n" + 
					"<span class='next'> </span>\n" + 
					"</a>\n" + 
					"</div>\n" + 
					"<h4><a href='#'>single room<span>120$</span></a></h4>\n" + 
					"<p>Single Room for Sale</p>\n" + 
					"</div>\n" + 
					"<div class='grid1_of_3'>\n" + 
					"<div class='grid1_of_3_img'>\n" + 
					"<a href='#'>\n" + 
					"<img src='/EWAProject/images/pic1.jpg' alt='' />\n" + 
					"<span class='next'> </span>\n" + 
					"</a>\n" + 
					"</div>\n" + 
					"<h4><a href='#'>double room<span>180$</span></a></h4>\n" + 
					"<p>Double Room for Sale</p>\n" + 
					"</div>\n" + 
					"<div class='grid1_of_3'>\n" + 
					"<div class='grid1_of_3_img'>\n" + 
					"<a href='#'>\n" + 
					"<img src='/EWAProject/images/pic3.jpg' alt='' />\n" + 
					"<span class='next'> </span>\n" + 
					"</a>\n" + 
					"</div>\n" + 
					"<h4><a href='#'>suite room<span>210$</span></a></h4>\n" + 
					"<p>Premium Suite Room for Sale</p>\n" + 
					"</div>\n" + 
					"<div class='clear'></div>\n" + 
					"</div>\n" + 
					"</td>\n" + 
					"</tr>\n" + 
					"</table>\n" + 
					"</div>\n" + 
					"</div>		\n" + 
					"<!--start main -->");
					break;
				}
				case "HomePageLeftNav":{
					
					pWriter.println("<!--start main -->\n" + 
					"<div class='main_bg'>\n" + 
					"<div class='wrap'>\n" + 
					"<table>\n" + 
					"<tr>\n" + 
					"<td style='width:15%;vertical-align: baseline;'>\n" + 
					"<aside class='sidebar1'>\n" + 
					"<ul>\n" + 
					"<li>\n" + 
					"<h4>Categories</h4>\n" + 
					"<ul>\n" + 
					"<li><a href='/EWAProject/Controller/HomeServlet'>Hotels</a></li>\n" + 
					"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
					"<li><a href='#'>Top Destinations</a></li>\n" + 
					"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
					"</ul>\n" + 
					"</li>\n" + 
					"<li>\n" + 
					"<h4>About us</h4>\n" + 
					"<ul>\n" + 
					"<li class='text'>\n" + 
					"<p style='margin: 0;'>Reserve My Room is a smart web application that allows customers to book Hotel rooms from almost every destination in the world. 					<a href='#' class='readmore'>Read More &raquo;</a></p>\n" + 
					"</li>\n" + 
					"</ul>\n" + 
					"</li>\n" + 
					"<li>\n" + 
											"<h4>Search site</h4>\n" + 
											"<ul>\n" + 
												"<li class='text'>\n" + 
													"<div name='autofillform'>\n" +
														"<input type='text' name='searchId' value='' id='searchId' onkeyup='doCompletion()' placeholder='search here...' style='padding:5px;float: none;font-size:16px;width:95%;'/>\n" +
														"<div id='auto-row'>\n" +
															"<table id='complete-table' class='gridtable' style='position:absolute;width:315px;'></table>\n" +
														"</div>\n" +
													"</div>\n" + 
												"</li>\n" + 
											"</ul>\n" + 
					"</li>\n" + 
					"<li>\n" + 
					"<h4>Helpful Links</h4>\n" + 
					"<ul>\n" + 
					"<li><a href='http://www.expedia.com' title='premium templates'>Expedia</a></li>\n" + 
					"<li><a href='http://www.tripadvisor.com' title='web hosting'>Trip Advisor</a></li>\n" + 
					"<li><a href='http://www.priceline.com' title='Tux Themes'>Priceline</a></li>\n" + 
					"</ul>\n" + 
					"</li>\n" + 
					"</ul>\n" + 
					"</aside>");
					break;
				}
				case "HomePageFooter":{
					pWriter.println("<div class='footer_bg'>\n" + 
							"<div class='wrap'>\n" + 
							"<div class='footer'>\n" + 
							"<div class='copy'>\n" + 
							"</div>\n" + 
							"<div class='f_nav'>\n" + 
							"<ul>\n" + 
								"<li><a href='/EWAProject/Controller/HomeServlet'>Home</a></li>\n" + 
								"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
								"<li><a href='#'>reservation</a></li>\n" + 
								"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>Contact Us</a></li>\n" +  
							"</ul>\n" + 
							"</div>\n" + 
							"<div class='soc_icons'>\n" + 
							"<ul>\n" + 
							"<li><a class='icon1' href='#'></a></li>\n" + 
							"<li><a class='icon2' href='#'></a></li>\n" + 
							"<li><a class='icon3' href='#'></a></li>\n" + 
							"<li><a class='icon4' href='#'></a></li>\n" + 
							"<li><a class='icon5' href='#'></a></li>\n" + 
							"<div class='clear'></div>\n" + 
							"</ul>\n" + 
							"</div>\n" + 
							"<div class='clear'></div>\n" + 
							"</div>\n" + 
							"</div>\n" + 
							"</div>\n" + 
							"</body>\n" + 
							"</html>");
					break;
				}
				case "Header":{
					HttpSession userSession = request.getSession(true);
					String username = (String)userSession.getAttribute("customerKey");
					String salesmanKey = (String)userSession.getAttribute("salesmanKey");
					String managerKey = (String)userSession.getAttribute("managerKey");
					if(username!=null) {
						ReserveList rs = (ReserveList) userSession.getAttribute("ReserveList");
						int listSize = rs.getListSize();
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
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/MyListServlet' class='accBtn'>My Lists(" + String.valueOf(listSize) + ")</a></li> |\n" +
														"<li><a href='/EWAProject/Controller/LoginServlet?op=CustomerHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" +  
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='' method=''>\n" + 
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else if(managerKey!=null) {
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
										"<script src='http://d3js.org/d3.v4.min.js' charset='utf-8'></script>\n" + 
							            "<script src='https://d3js.org/d3-queue.v2.min.js'></script>\n" +
							            "<script src='https://d3js.org/d3-scale-chromatic.v1.min.js'></script>\n" + 
							            "<script src='https://d3js.org/topojson.v2.min.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/HotelList.js'></script>\n" +
										"<script type='text/javascript' src='https://www.google.com/jsapi'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/DataAnalyticsServlet?op=home' method='get'>Data Analytics</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/InventorySalesServlet?var=inventoryHome' method='get'>Inventory</a></li> |\n" +
							            				"<li><a href='/EWAProject/Controller/InventorySalesServlet?var=salesReportHome' method='get'>Sales Reports</a></li>\n" +
														"<li style='width:20px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li class='acc'><a href='/EWAProject/Controller/LoginServlet?op=ManagerHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='' method=''>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else if(salesmanKey!=null) {
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
										"<!---strat-date-picker---->\n" + 
										"<link rel='stylesheet' href='/EWAProject/css/jquery-ui.css' />\n" + 
										"<script src='/EWAProject/js/jquery-ui.js'></script>\n" + 
										"<script>\n" + 
											"$(function() {\n" + 
												"$( '#checkInDate,#checkOutDate,#dob' ).datepicker();\n" + 
											"});\n" + 
										"</script>\n" + 
										"<!---/End-date-piker---->\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/HotelList.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/roomTypeSalesman.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet?op=SalesmanHome' class='accBtn'>Account</a></li> |\n" +  
														//"<li><form><input type='submit' class='accBtn' value='Account' formaction='/EWAProject/Controller/LoginServlet?op=CustomerHome' formmethod='GET'/></form></li> |\n" +
														"<li><a href='/EWAProject/Controller/LogoutServlet'>Sign Out</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='' method=''>\n" +
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					else {
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
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFGrid.css' />\n" + 
										//"<link type='text/css' rel='stylesheet' href='/EWAProject/css/JFFormStyle-1.css' />\n" + 
										//"<script type='text/javascript' src='/EWAProject/js/JFForms.js'></script>\n" +
										"<script type='text/javascript' src='/EWAProject/js/AutoComplete.js'></script>\n" +
										"<link rel='icon' type='image/png' href='/EWAProject/images/hotel_green_round.png'/>\n" + 
									"</head>\n" + 
									"<body onload='init()'>\n" + 
										"<!-- start header -->\n" + 
										"<div class='header_bg'>\n" + 
											"<div class='wrap'>\n" + 
												"<div class='header'>\n" + 
													"<div class='logo'>\n" + 
														"<a href='/EWAProject/Controller/HomeServlet'><img src='/EWAProject/images/logo.png' alt=''></a>\n" + 
													"</div>\n" + 
													"<div style='float:right;'>\n" + 
														"<!--start menu -->\n" + 
														"<ul class='menu'>\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet'>hotels</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
														"<li><a href='#'>top destinations</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
														"<li style='width:100px;'><input type='hidden' id='hiddenListItem'></li>\n" + 
														"<li><a href='/EWAProject/Controller/LoginServlet'>Sign In</a></li> |\n" + 
														"<li><a href='/EWAProject/Controller/RegisterServlet'>Create an Account</a></li>\n" + 
														"<div class='clear'></div>\n" + 
														"</ul>\n" + 
													"</div>\n" + 
													"<div class='clear'></div>\n" + 
														"<div class='top-nav'>\n" + 
															"<nav class='clearfix'>\n" + 
																"<ul>\n" + 
																	"<li class='active'><a href='/EWAProject/Controller/HomeServlet'>hotel</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
																	"<li><a href='#'>top destinations</a></li> \n" + 
																	"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
																"</ul>\n" + 
																"<a href='#' id='pull'>Menu</a>\n" + 
															"</nav>\n" + 
														"</div>\n" + 
													"</div>\n" + 
												"</div>\n" + 
											"</div>\n" + 
										"<!--start main -->\n" + 
										"<div class='main_bg' style='background: url(/EWAProject/images/slider-bg.jpg);background-size: cover;background-repeat: no-repeat;height:80%;'>\n" + 
										"<div class='wrap'>\n" + 
										"<div class='main'>\n" + 
										"<form class='width:100%;margin: 0 auto;height: 60%;border: 1px solid #ccc;border-radius: 15px;' action='' method=''>\n" + 
										"<div>\n" + 
										"<table>\n" + 
										"<tr>\n" + 
										"<td style='text-align:right;width:18%;vertical-align: baseline;'>");
					}
					break;
				}
				case "LeftNav":{
					pWriter.println("<aside class='sidebar2' style='width:90%;padding-left:5%;padding-right:5%;text-align:left;align:right;opacity: 0.7;'>\n" + 
										"<ul>\n" + 
											"<li>\n" + 
												"<h4>Categories</h4>\n" + 
												"<ul>\n" + 
													"<li><a href='/EWAProject/Controller/HomeServlet'>Hotels</a></li>\n" + 
													"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
													"<li><a href='#'>Top destinations</a></li>\n" + 
													"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
												"</ul>\n" + 
											"</li>\n" + 
											"<li>\n" + 
												"<h4>About us</h4>\n" + 
												"<ul>\n" + 
													"<li class='text'>\n" + 
														"<p style='margin: 0;'>Reserve My Room is a smart web application that allows customers to book Hotel rooms from almost every destination in the world. 					<a href='#' class='readmore'>Read More &raquo;</a></p>\n" + 
													"</li>\n" + 
												"</ul>\n" + 
											"</li>\n" + 
											"<li>\n" + 
												"<h4>Search site</h4>\n" + 
												"<ul>\n" + 
													"<li class='text'>\n" + 
														"<div name='autofillform'>\n" +
															"<input type='text' name='searchId' value='' id='searchId' onkeyup='doCompletion()' placeholder='search here...' style='padding:5px;float: none;font-size:16px;width:95%;'/>\n" +
															"<div id='auto-row'>\n" +
																"<table id='complete-table' class='gridtable' style='position:absolute;width:315px;'></table>\n" +
															"</div>\n" +
														"</div>\n" +
													"</li>\n" + 
												"</ul>\n" + 
											"</li>\n" + 
											"<li>\n" + 
												"<h4>Helpful Links</h4>\n" + 
												"<ul>\n" + 
													"<li><a href='http://www.expedia.com' title='premium templates'>Expedia</a></li>\n" + 
													"<li><a href='http://www.tripadvisor.com' title='web hosting'>Trip Advisor</a></li>\n" + 
													"<li><a href='http://www.priceline.com' title='Tux Themes'>Priceline</a></li>\n" + 
												"</ul>\n" + 
											"</li>\n" + 										
										"</ul>\n" + 
									"</aside>");
					break;
				}
				case "LeftNavLgnReg":{
					pWriter.println("<aside class='sidebar2' style='width:50%;padding-left:5%;padding-right:5%;text-align:left;align:right;opacity: 0.7;'>\n" + 
										"<ul>\n" + 
											"<li>\n" + 
												"<h4>Categories</h4>\n" + 
												"<ul>\n" + 
													"<li><a href='/EWAProject/Controller/HomeServlet'>Hotels</a></li>\n" + 
													"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
													"<li><a href='#'>Top destinations</a></li>\n" + 
													"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>contact us</a></li>\n" +  
												"</ul>\n" + 
											"</li>\n" + 
											"<li>\n" + 
												"<h4>About us</h4>\n" + 
												"<ul>\n" + 
													"<li class='text'>\n" + 
														"<p style='margin: 0;'>Reserve My Room is a smart web application that allows customers to book Hotel rooms from almost every destination in the world. 					<a href='#' class='readmore'>Read More &raquo;</a></p>\n" + 
													"</li>\n" + 
												"</ul>\n" + 
											"</li>\n" + 
											"<li>\n" + 
												"<h4>Search site</h4>\n" + 
												"<ul>\n" + 
													"<li class='text'>\n" + 
														"<div name='autofillform'>\n" +
															"<input type='text' name='searchId' value='' id='searchId' onkeyup='doCompletion()' placeholder='search here...' style='padding:5px;float: none;font-size:16px;width:95%;'/>\n" +
															"<div id='auto-row'>\n" +
																"<table id='complete-table' class='gridtable' style='position:absolute;width:315px;'></table>\n" +
															"</div>\n" +
														"</div>\n" +
													"</li>\n" + 
												"</ul>\n" + 
											"</li>\n" + 
											"<li>\n" + 
												"<h4>Helpful Links</h4>\n" + 
												"<ul>\n" + 
													"<li><a href='http://www.expedia.com' title='premium templates'>Expedia</a></li>\n" + 
													"<li><a href='http://www.tripadvisor.com' title='web hosting'>Trip Advisor</a></li>\n" + 
													"<li><a href='http://www.priceline.com' title='Tux Themes'>Priceline</a></li>\n" + 
												"</ul>\n" + 
											"</li>\n" + 										
										"</ul>\n" + 
									"</aside>");
					break;
				}
				case "Content":{
					pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:59%'>\n" + 
										"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:15px;'><p></p></td> \n" + 
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
					break;
				}
				case "Footer":{
					pWriter.println("<div class='footer_bg'>\n" + 
							"<div class='wrap'>\n" + 
							"<div class='footer'>\n" + 
								"<div class='copy'>\n" + 
									"<p class='link'><span><a></a></span></p>\n" + 
								"</div>\n" + 
								"<div class='f_nav'>\n" + 
									"<ul>\n" + 
										"<li><a href='/EWAProject/Controller/HomeServlet'>Home</a></li>\n" + 
										"<li><a href='/EWAProject/Controller/HotelReviews?op=trendingHotels'>Trending Hotels</a></li>\n" + 
										"<li><a href='#'>reservation</a></li>\n" + 
										"<li><a href='/EWAProject/Controller/HomeServlet?var=contactUs'>Contact Us</a></li>\n" + 
									"</ul>\n" + 
								"</div>\n" + 
								"<div class='soc_icons'>\n" + 
									"<ul>\n" + 
										"<li><a class='icon1' href='#'></a></li>\n" + 
										"<li><a class='icon2' href='#'></a></li>\n" + 
										"<li><a class='icon3' href='#'></a></li>\n" + 
										"<li><a class='icon4' href='#'></a></li>\n" + 
										"<li><a class='icon5' href='#'></a></li>\n" + 
										"<div class='clear'></div>\n" + 
									"</ul>\n" + 
								"</div>\n" + 
								"<div class='clear'></div>\n" + 
							"</div>\n" + 
							"</div>\n" + 
							"</div>\n" + 
							"</body>\n" + 
							"</html>");
					break;
				}
				default:{
					break;
				}
			}	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
}