package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Controller.HtmlEngine;
import DataAccess.MySQLDataStoreUtilities;
import JavaBeans.*;

public class ReservationServlet extends HttpServlet {
	
	public ReservationServlet() {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
			String op = request.getParameter("op");
			String resID = request.getParameter("resID");
			if(op.equals("ModifyReservation")) {
				
			}
			else if(op.equals("DeleteReservation")) {
				
				response.setContentType("text/html");
				PrintWriter pWriter = response.getWriter();
				HtmlEngine engine = new HtmlEngine(pWriter);
				
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = dbObj.checkDBConnection();
				if(dbStatus.get("status").equals("true")) {
					dbObj.deleteReservation(Integer.parseInt(resID));
					engine.generateHtml("Header",request);
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
										"<td colspan='2' style='text-align:right;padding:15px;'>\n" +
											"<a href='/EWAProject/Controller/LoginServlet?op=CustomerHome' method='get'>Customer Home</a>\n" +
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
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}