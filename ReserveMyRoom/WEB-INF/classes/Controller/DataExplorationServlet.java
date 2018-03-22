package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.*;
import JavaBeans.*;

public class DataExplorationServlet extends HttpServlet {
	
	public DataExplorationServlet() {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession userSession = request.getSession(true);
			String manager = (String)userSession.getAttribute("managerKey");
			if(manager!=null) {
				String op = request.getParameter("op");
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				if(op==null) {
					engine.generateHtml("HeaderManager",request,"","");
					engine.generateHtml("LeftNav",request);
					pWriter.println("</td>\n" + 
							"<td style='text-align:left;width:59%;padding-left:50px;vertical-align:baseline;'>\n" + 
								"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
									"</tr>\n" + 
									"<tr>\n" + 
										"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Data Exploration Home</div></th> \n" +
									"</tr>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:25px;'>\n" +
											"<p></p>\n" +
										"</td> \n" + 
									"</tr>\n" + 
									"<tr>\n" +
										"<td colspan='2' style='text-align:center;'>\n" +
											"<p><a href='/EWAProject/Controller/DataExplorationServlet?op=TotalReviews' method='get'>Total number of Reviews per State</a></p>\n" +
											"<br />\n" +
											"<p><a href='/EWAProject/Controller/DataExplorationServlet?op=TotalSalesCount' method='get'>Total Hotels Reserved per State</a></p>\n" +
											"<br />\n" +
											"<p><a href='/EWAProject/Controller/DataExplorationServlet?op=TotalReviews5' method='get'>Total number of Reviews with Rating 5</a></p>\n" +
											"<br />\n" +
											"<p><a href='/EWAProject/Controller/DataExplorationServlet?op=AveragePrices' method='get'>Average Hotel Prices reserved per State</a></p>\n" +
											"<br />\n" +
											"<p><a href='/EWAProject/Controller/DataExplorationServlet?op=TotalSalesPrices' method='get'>Total Hotel Prices per State</a></p>\n" +
										"</td>\n" +
									"</tr>\n" +
									"<tr>\n" +
									"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
										"<a style='width: 180px;padding:7px;text-align:center;' href='/EWAProject/Controller/LoginServlet?op=ManagerHome' method='get'>Account</a>\n" +
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
					engine.generateHtml("HeaderManagerExplor",request,"","");
					engine.generateHtml("LeftNav",request);
					switch(op) {
						case "TotalReviews":{
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:79%;padding-left:10px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Total number of Reviews per State</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;'>\n" +
													"<svg width='960' height='600'></svg>\n" +
													"<p id='revCount'>Review Count:</p>\n" +
													"<script type='text/javascript' src='/EWAProject/js/choroPlethTotalReviews.js'></script>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration Home</a>\n" +
											"</td>\n" +
											"</tr>\n" +
										"</table> \n" + 
									"</td>\n" + 
									"<td style='text-align:left;width:3%'>\n" + 
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
						case "TotalSalesCount":{
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:79%;padding-left:10px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Total Hotels Reserved per State</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;'>\n" +
													"<svg width='960' height='600'></svg>\n" +
													"<p id='hotelCount'>Total Hotels:&nbsp;&nbsp;</p>\n" +
													"<script type='text/javascript' src='/EWAProject/js/choroPlethTotalSales.js'></script>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration Home</a>\n" +
											"</td>\n" +
											"</tr>\n" +
										"</table> \n" + 
									"</td>\n" + 
									"<td style='text-align:left;width:3%'>\n" + 
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
						case "TotalReviews5":{
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:79%;padding-left:10px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Total number of Reviews with Rating 5</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;'>\n" +
													"<svg width='960' height='600'></svg>\n" +
													"<p id='revCount'>Review Count:</p>\n" +
													"<script type='text/javascript' src='/EWAProject/js/choroPlethTotRev5.js'></script>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration Home</a>\n" +
											"</td>\n" +
											"</tr>\n" +
										"</table> \n" + 
									"</td>\n" + 
									"<td style='text-align:left;width:3%'>\n" + 
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
						case "AveragePrices":{
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:79%;padding-left:10px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Average Hotel Prices reserved per State</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;'>\n" +
													"<svg width='960' height='600'></svg>\n" +
													"<p id='avgPriceCount'>Average Hotel Price:&nbsp;&nbsp;</p>\n" +
													"<script type='text/javascript' src='/EWAProject/js/choroPlethAvgPrices.js'></script>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration Home</a>\n" +
											"</td>\n" +
											"</tr>\n" +
										"</table> \n" + 
									"</td>\n" + 
									"<td style='text-align:left;width:3%'>\n" + 
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
						case "TotalSalesPrices":{
							pWriter.println("</td>\n" + 
									"<td style='text-align:left;width:79%;padding-left:10px;vertical-align:baseline;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<table style='width:100%;'>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:30px;'><p></p></td> \n" + 
											"</tr>\n" + 
											"<tr>\n" + 
												"<th colspan='2' style='text-align:center;height:40px;'><div align='center'>Total Hotel Prices per State</div></th> \n" +
											"</tr>\n" + 
											"<tr>\n" + 
												"<td colspan='2' style='height:25px;'>\n" +
													"<p></p>\n" +
												"</td> \n" + 
											"</tr>\n" + 
											"<tr>\n" +
												"<td colspan='2' style='text-align:center;'>\n" +
													"<svg width='960' height='600'></svg>\n" +
													"<p id='totProdPrices'>Total Hotel Prices:&nbsp;&nbsp;</p>\n" +
													"<script type='text/javascript' src='/EWAProject/js/choroPlethTotPrices.js'></script>\n" +
												"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
											"<td colspan='2' style='text-align:right;padding:15px;height:40px;' class='rsvBtn'>\n" +
												"<a style='width: 240px;padding:7px;text-align:center;' href='/EWAProject/Controller/DataExplorationServlet' method='get'>Data Exploration Home</a>\n" +
											"</td>\n" +
											"</tr>\n" +
										"</table> \n" + 
									"</td>\n" + 
									"<td style='text-align:left;width:3%'>\n" + 
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
					}
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
