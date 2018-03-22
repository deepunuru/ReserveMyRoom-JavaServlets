package Controller;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;

import DataAccess.MongoDBDataStoreUtilities;
import JavaBeans.*;

public class HotelReviews extends HttpServlet {
	
	MongoDBDataStoreUtilities mDBObj;
	
	public HotelReviews() {
		mDBObj = new MongoDBDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String hotelName = request.getParameter("hotelName");
		
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		HtmlEngine engine = new HtmlEngine(pWriter);
		if(op!=null) {
			if(op.equals("writeReview")) {
				engine.generateHtml("Header",request,"/EWAProject/Controller/HotelReviews","POST");
				engine.generateHtml("LeftNav",request);
				
				pWriter.println("</td>\n" + 
						"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
							"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='2' style='text-align:center;'><p>Hotel Review Form</p></th> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='hotelName'>Hotel Name : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;padding: 10px;padding-left:25px;width:65%;'>\n" +
										"<input type='text' id='hotelName' style='width:300px;background-color:#bfbfbf;' name='hotelName' value='" + hotelName + "' readonly/>\n" +
									"</td>\n" +
								"</tr>\n" +	
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='RoomPrice'>Room Price : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='roomPrice' style='width:300px' name='roomPrice' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='Retailer'>Retailer Name : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='retailer' style='width:300px' name='retailer' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='ZipCode'>Retailer ZipCode : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='zipCode' style='width:300px' name='zipCode' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='City'>Retailer City : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='city' style='width:300px' name='city' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='State'>Retailer State : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='state' style='width:300px' name='state' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='OnSale'>On Sale : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='radio' name='onSale' value='Yes'/> Yes<br>\n" + 
										"<input type='radio' name='onSale' value='No'/> No<br>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='Rebate'>Hotel Rebate : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='radio' name='rebate' value='Yes'/> Yes<br>\n" + 
										"<input type='radio' name='rebate' value='No'/> No<br>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='Username'>UserID : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='username' style='width:300px;' name='username' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='Age'>Age : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='age' style='width:300px' name='age' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='Gender'>Gender : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='gender' style='width:300px' name='gender' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='Occupation'>Occupation : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input type='text' id='occupation' style='width:300px' name='occupation' required/>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='ReviewRating'>Review Rating : </label>\n" +
									"</td>\n" +
									"<td style='text-align:left;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<select name='reviewRating' id='reviewRating' style='width: 45px;'>\n" +
											"<option value='1'>1</option>\n" +
											"<option value='2'>2</option>\n" +
											"<option value='3'>3</option>\n" +
											"<option value='4'>4</option>\n" +
											"<option value='5'>5</option>\n" +
										"</select>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='ReviewDate'>Review Date : </label>\n" +
									"</td>\n" +
									"<td style='text-align:right;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<input class='date' id='datepicker1' type='text' name='reviewDate' placeholder='DD/MM/YY' required>\n" + 
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td style='text-align:right;width:35%;'>\n" +
										"<label for='ReviewText'>Review Text : </label>\n" +
									"</td>\n" +
									"<td style='text-align:center;width:65%;padding: 10px;padding-left:25px;'>\n" +
										"<textarea cols='40' rows='6' style='width:300px;' name='reviewText' id='reviewText' required></textarea>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td colspan='2' style='text-align:center;padding:7px;' class='date_btn'>\n" +									
										"<input type='submit' value='Submit' style='width: 120px;'>\n" + 
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +	
									"<td colspan='2' style='padding:20px;text-align:right;' class='rsvBtn'>\n" +
										"<a style='width: 120px;padding:7px;text-align:center;' href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
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
			else if(op.equals("viewReviews")) {
				engine.generateHtml("Header",request);
				engine.generateHtml("LeftNav",request);
				
				pWriter.println("</td>\n" + 
						"<td style='text-align:center;width:81%;vertical-align: -webkit-baseline-middle;background-color:#fff;border-radius: 8px;opacity: 0.7;padding:20px;'>\n" + 
							"<table style='width:100%;'>\n" + 
								"<tr>\n" + 
									"<td colspan='4' style='height:30px;text-align:center;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='4' style='text-align:center;'><p>Review List</p></th> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<td colspan='4' style='height:30px;text-align:center;'><p></p></td> \n" + 
								"</tr>");
				
				HashMap<String, String> dbStatus = new HashMap<String, String>();
				dbStatus = mDBObj.checkMongoDB();
				if(dbStatus.get("status").equals("true")) {
					HashMap<String, Review> reviewsMap = mDBObj.getReviews(hotelName);
					if(reviewsMap==null || reviewsMap.isEmpty()) {
						pWriter.println("<tr>\n" +	
								"<td colspan='4' style='text-align:center;'>\n" +
									"<label for='statusLbl'>No Reviews to Display</label>\n" +
								"</td>\n" +
							"</tr>");
					}
					else {
						Review rev = new Review();
						for(Map.Entry<String, Review> r : reviewsMap.entrySet()) {
							rev = r.getValue();
							pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
									"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
										"<p>Hotel Name:&nbsp;&nbsp;" + rev.getHotelName() + "</p>\n" +
									"</td>\n" +
									"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
										"<p>Room Price:&nbsp;&nbsp;$" + String.valueOf(rev.getRoomPrice()) + "</p>\n" +
									"</td>\n" +
									"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
										"<p>Username:&nbsp;&nbsp;" + rev.getUsername() + "</p>\n" +
									"</td>\n" +
									"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
										"<p>ZipCode:&nbsp;&nbsp;" + rev.getZipCode() + "</p>\n" +
									"</td>\n" +
									"</tr>\n" +
									"<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
									"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +									
										"<p>Review Rating:&nbsp;&nbsp;" + String.valueOf(rev.getReviewRating()) + "</p>\n" +
									"</td>\n" +
									"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
										"<p>Review Date:&nbsp;&nbsp;" + rev.getReviewDate() + "</p>\n" +
									"</td>\n" +
									"<td colspan='2' style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
										"<p>Review Text:&nbsp;&nbsp;" + rev.getReviewText() + "</p>\n" +
									"</td>\n" +
									"</tr>\n" +
									"<tr>\n" +
										"<td colspan='4' style='padding:10px;'>\n" +
											"<p></p>\n" +
										"</td>\n" +
									"</tr>");	
							
						}
					}
				}
				else {
					pWriter.println("<tr>\n" +	
							"<td colspan='4' style='text-align:center;'>\n" +
								"<label for='statusLbl'>" + dbStatus.get("msg") + "</label>\n" +
							"</td>\n" +
						"</tr>");
				}
				
				pWriter.println("<tr>\n" +	
									"<td colspan='4' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
										"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
									"</td>\n" +
								"</tr>\n" +
							"</table> \n" + 
						"</td>\n" + 
						"<td style='text-align:left;width:1%'>\n" + 
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
			else if(op.equals("trendingHotels")) {
				engine.generateHtml("Header",request);
				engine.generateHtml("LeftNav",request);
				pWriter.println("</td>\n" + 
						"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding: 50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
							"<table style='width:100%;'>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<th colspan='2' style='text-align:center;'><p>Trending Hotels</p></th> \n" + 
								"</tr>\n" + 
								"<tr>\n" + 
									"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
								"</tr>\n" + 
								"<tr>\n" +
									"<td colspan='2' style='text-align:center;padding:15px;' class='date_btn'>\n" +
										"<input type='submit' value='Top 5 Most Liked Hotels' formaction='/EWAProject/Controller/HotelReviews?task=mostLikedHotels' formmethod='POST' style='width:250px;padding:7px;'>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td colspan='2' style='text-align:center;padding:15px;' class='date_btn'>\n" +
										"<input type='submit' value='Top 5 Zip-Codes with Maximum number of Hotels Booked' formaction='/EWAProject/Controller/HotelReviews?task=mostSoldZipCodes' formmethod='POST' style='width:500px;padding:7px;'>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +
									"<td colspan='2' style='text-align:center;padding:15px;' class='date_btn'>\n" +
										"<input type='submit' value='Top 5 Most Booked Hotels Irrespective of Rating' formaction='/EWAProject/Controller/HotelReviews?task=mostSoldHotels' formmethod='POST' style='width:500px;padding:7px;'>\n" +
									"</td>\n" +
								"</tr>\n" +
								"<tr>\n" +	
									"<td colspan='2' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
										"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
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
			response.sendRedirect("/EWAProject/Controller/HomeServlet");
		}
	}
	
	public boolean checkDouble(String var) {
		try {
			final String Digits     = "(\\p{Digit}+)";
			final String HexDigits  = "(\\p{XDigit}+)";
			// an exponent is 'e' or 'E' followed by an optionally 
			// signed decimal integer.
			final String Exp        = "[eE][+-]?"+Digits;
			final String fpRegex    =
			    ("[\\x00-\\x20]*"+ // Optional leading "whitespace"
			    "[+-]?(" +         // Optional sign character
			    "NaN|" +           // "NaN" string
			    "Infinity|" +      // "Infinity" string

			    // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
			    "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

			    // . Digits ExponentPart_opt FloatTypeSuffix_opt
			    "(\\.("+Digits+")("+Exp+")?)|"+

			    // Hexadecimal strings
			    "((" +
			    // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
			    "(0[xX]" + HexDigits + "(\\.)?)|" +

			    // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
			    "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

			    ")[pP][+-]?" + Digits + "))" +
			    "[fFdD]?))" +
			    "[\\x00-\\x20]*");// Optional trailing "whitespace"

			if (Pattern.matches(fpRegex, var)) {
			    //Double.valueOf(myString); Will not throw NumberFormatException
			    return true;
			} 
			else {
			    return false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter pWriter = response.getWriter();
			HtmlEngine engine = new HtmlEngine(pWriter);
			
			String task = request.getParameter("task");
			if(task==null) {
				if(checkDouble(request.getParameter("roomPrice")) && isInteger(request.getParameter("zipCode")) && isInteger(request.getParameter("age")) && isInteger(request.getParameter("reviewRating"))) {
					double price = Double.parseDouble(request.getParameter("roomPrice"));
					//Store review details
					boolean bSale = true;
					boolean bRebate = true;
					String onSale = request.getParameter("onSale");
					String rebate = request.getParameter("rebate");
					if(onSale.equals("Yes")) {
						bSale = true;
					}
					else if(onSale.equals("No")) {
						bSale = false;
					}
					if(rebate.equals("Yes")) {
						bRebate = true;
					}
					else if(rebate.equals("No")) {
						bRebate = false;
					}
					Review review = new Review(request.getParameter("hotelName"),price,request.getParameter("retailer"),Integer.parseInt(request.getParameter("zipCode")),request.getParameter("city"),request.getParameter("state"),bSale,bRebate,request.getParameter("username"),Integer.parseInt(request.getParameter("age")),request.getParameter("gender"),request.getParameter("occupation"),Integer.parseInt(request.getParameter("reviewRating")),request.getParameter("reviewDate"),request.getParameter("reviewText"));
					
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNav",request);
					
					HashMap<String, String> dbStatus = new HashMap<String, String>();
					dbStatus = mDBObj.checkMongoDB();
					if(dbStatus.get("status").equals("true")) {
						mDBObj.writeReview(review);
						pWriter.println("</td>\n" + 
								"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding: 50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<table style='width:100%;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='2' style='text-align:center;'><p>Hotels Reviews</p></th> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +
											"<td colspan='2' style='text-align:center;'>\n" +
												"<p>Review for Hotel - " + request.getParameter("hotelName") +" submitted successfully.</p>\n" +
												"<p>Please use the link below to go back to Home Page.</p>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='2' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
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
					else {
						pWriter.println("</td>\n" + 
								"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
									"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='2' style='text-align:center;'><p>Hotels Reviews</p></th> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" +
											"<td colspan='2' style='text-align:center;'>\n" +
												"<p>" + dbStatus.get("msg") + "</p>\n" +
											"</td>\n" +
										"</tr>\n" +
										"<tr>\n" +	
											"<td colspan='2' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
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
					engine.generateHtml("Footer",request);
				}
				else {
					engine.generateHtml("Header",request);
					engine.generateHtml("LeftNav",request);
					pWriter.println("</td>\n" + 
							"<td style='text-align:center;width:69%;vertical-align: -webkit-baseline-middle;padding-left: 50px;'>\n" + 
								"<table style='width:100%;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
									"</tr>\n" + 
									"<tr>\n" + 
										"<th colspan='2' style='text-align:center;'><p>Hotels Reviews</p></th> \n" + 
									"</tr>\n" + 
									"<tr>\n" + 
										"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
									"</tr>\n" + 
									"<tr>\n" +
										"<td colspan='2' style='text-align:center;'>\n" +
											"<p>Invalid numeric input received. Please enter valid values and try again.</p>\n" +
											"<p>Please use the link below to go back to Home Page.</p>\n" +
										"</td>\n" +
									"</tr>\n" +
									"<tr>\n" +	
										"<td colspan='2' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
											"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HomeServlet' method='get'>Home Page</a>\n" +
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
				engine.generateHtml("Header",request);
				engine.generateHtml("LeftNav",request);
				switch(task) {
					case "mostLikedHotels":{
						pWriter.println("</td>\n" + 
								"<td style='text-align:center;width:81%;vertical-align: -webkit-baseline-middle;background-color:#fff;border-radius: 8px;opacity: 0.7;padding:20px;'>\n" + 
									"<table style='width:100%;'>\n" + 
										"<tr>\n" + 
											"<td colspan='4' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='4' style='text-align:center;'><p>Top 5 Most Liked Hotels</p></th> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='4' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>");
						
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = mDBObj.checkMongoDB();
						if(dbStatus.get("status").equals("true")) {
							List<Review> reviewsList = mDBObj.getMostLikedHotels();
							if(reviewsList==null || reviewsList.isEmpty()) {
								pWriter.println("<tr>\n" +	
										"<td colspan='4' style='text-align:center;'>\n" +
											"<label for='statusLbl'>No Reviews to Display</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {
								for(Review r: reviewsList) {
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
												"<p>Hotel Name:&nbsp;&nbsp;" + r.getHotelName() + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
												"<p>Room Price:&nbsp;&nbsp;$" + String.valueOf(r.getRoomPrice()) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
												"<p>Username:&nbsp;&nbsp;" + r.getUsername() + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
												"<p>ZipCode:&nbsp;&nbsp;" + r.getZipCode() + "</p>\n" +
											"</td>\n" +
											"</tr>\n" +
											"<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +									
												"<p>Review Rating:&nbsp;&nbsp;" + String.valueOf(r.getReviewRating()) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
												"<p>Review Date:&nbsp;&nbsp;" + r.getReviewDate() + "</p>\n" +
											"</td>\n" +
											"<td colspan='2' style='text-align:left;border: 1px solid #ccc;border-radius: 2px;padding:10px;'>\n" +
												"<p>Review Text:&nbsp;&nbsp;" + r.getReviewText() + "</p>\n" +
											"</td>\n" +
											"</tr>\n" +
											"<tr>\n" +
												"<td colspan='4' style='padding:10px;'>\n" +
													"<p></p>\n" +
												"</td>\n" +
											"</tr>");	
									
								}
							}
						}
						else {
							pWriter.println("<tr>\n" +	
									"<td colspan='4' style='text-align:center;'>\n" +
										"<label for='statusLbl'>" + dbStatus.get("msg") + "</label>\n" +
									"</td>\n" +
								"</tr>");
						}
						
						pWriter.println("<tr>\n" +	
											"<td colspan='4' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
												"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HotelReviews?op=trendingHotels' method='get'>Trending Hotels</a>\n" +
											"</td>\n" +
										"</tr>\n" +
									"</table> \n" + 
								"</td>\n" + 
								"<td style='text-align:left;width:1%'>\n" + 
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
					case "mostSoldZipCodes":{
						pWriter.println("</td>\n" + 
								"<td style='text-align:center;width:59%;vertical-align:baseline;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<table style='width:100%;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='2' style='text-align:center;'><p>Top 5 Zip-Codes with Maximum number of Hotels Booked</p></th> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" +
										"<tr>\n" + 
											"<th style='text-align:center;'><p>Zip Code</p></th> \n" +
											"<th style='text-align:center;'><p>Hotel Rooms Sold</p></th> \n" +
										"</tr>");
						
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = mDBObj.checkMongoDB();
						if(dbStatus.get("status").equals("true")) {
							int[][] zipCodeList = mDBObj.getMaxZipCodes();
							if(zipCodeList==null) {
								pWriter.println("<tr>\n" +	
										"<td colspan='2' style='text-align:center;'>\n" +
											"<label for='StatusLbl'>No Zip Codes to Display</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {		
								for(int i=0;i<5;i++) {
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + String.valueOf(zipCodeList[i][0]) + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + String.valueOf(zipCodeList[i][1]) + "</p>\n" +
											"</td>\n" +
											"</tr>");
								}
							}
						}
						else {
							pWriter.println("<tr>\n" +	
									"<td colspan='2' style='text-align:center;'>\n" +
										"<label for='StatusLbl'>" + dbStatus.get("msg") + "</label>\n" +
									"</td>\n" +
								"</tr>");
						}
						
						pWriter.println("<tr>\n" +	
								"<td colspan='4' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
									"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HotelReviews?op=trendingHotels' method='get'>Trending Hotels</a>\n" +
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
						break;
					}
					case "mostSoldHotels":{
						pWriter.println("</td>\n" + 
								"<td style='text-align:center;width:59%;vertical-align:baseline;padding:50px;background-color:#fff;border-radius: 8px;opacity: 0.7;'>\n" + 
									"<table style='width:100%;'>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<th colspan='2' style='text-align:center;'><p>Top 5 Most Booked Hotels Irrespective of Rating</p></th> \n" + 
										"</tr>\n" + 
										"<tr>\n" + 
											"<td colspan='2' style='height:30px;text-align:center;'><p></p></td> \n" + 
										"</tr>\n" +
										"<tr>\n" + 
											"<th style='text-align:center;'><p>Hotel Name</p></th> \n" +
											"<th style='text-align:center;'><p>Hotel Rooms Sold</p></th> \n" +
										"</tr>");
						
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = mDBObj.checkMongoDB();
						if(dbStatus.get("status").equals("true")) {
							String[][] mostSoldList = mDBObj.getMostSoldHotels();
							if(mostSoldList==null) {
								pWriter.println("<tr>\n" +	
										"<td colspan='2' style='text-align:center;'>\n" +
											"<label for='StatusLbl'>No Hotels to Display</label>\n" +
										"</td>\n" +
									"</tr>");
							}
							else {	
								for(int i=0;i<5;i++) {
									pWriter.println("<tr style='border: 1px solid #ccc;border-radius: 2px;'>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + mostSoldList[i][0] + "</p>\n" +
											"</td>\n" +
											"<td style='text-align:center;border: 1px solid #ccc;border-radius: 2px;'>\n" +
												"<p>" + mostSoldList[i][1] + "</p>\n" +
											"</td>\n" +
											"</tr>");
								}
							}
						}
						else {
							pWriter.println("<tr>\n" +	
									"<td colspan='2' style='text-align:center;'>\n" +
										"<label for='StatusLbl'>" + dbStatus.get("msg") + "</label>\n" +
									"</td>\n" +
								"</tr>");
						}
						
						pWriter.println("<tr>\n" +	
								"<td colspan='4' align='right' style='padding:20px;text-align: center;' class='rsvBtn'>\n" +
									"<a style='width: 180px;padding:3px;' href='/EWAProject/Controller/HotelReviews?op=trendingHotels' method='get'>Trending Hotels</a>\n" +
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
						break;
					}
					default:{
						response.sendRedirect("/EWAProject/Controller/HomeServlet");
						break;
					}
				}
				engine.generateHtml("Footer",request);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}