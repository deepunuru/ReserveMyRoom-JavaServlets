package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.*;

import DataAccess.*;
import JavaBeans.*;

public class DataExplorationUtility extends HttpServlet {
	
	MySQLDataStoreUtilities dbObj;
	MongoDBDataStoreUtilities mDBObj;
	
	public DataExplorationUtility() {
		dbObj = new MySQLDataStoreUtilities();
		mDBObj = new MongoDBDataStoreUtilities();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String op = request.getParameter("op");
			if(op == null) {
				response.sendRedirect("/EWAProject/Controller/HomeServlet");
			}
			else {
				switch(op) {
					case "TotalReviews": {
						Bson bList = null;
						Bson sortObj = null;
						bList = Aggregates.group("$state", Accumulators.sum("TotalReviews", 1),Accumulators.min("ReviewMin", "$reviewRating"),Accumulators.max("ReviewMax", "$reviewRating"),Accumulators.avg("ReviewAvg", "$reviewRating"));
						sortObj = Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.descending("TotalReviews")));
						List<Document> choroPlethList = new ArrayList<Document>();
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = mDBObj.checkMongoDB();
						if(dbStatus.get("status").equals("true")) {
							MongoCollection<Document> myReviews = mDBObj.getCollection();
							FindIterable<Document> docs=null;
							AggregateIterable<Document> iterable=null;
							try {
								iterable = myReviews.aggregate(Arrays.asList(bList,sortObj));
								MongoCursor<Document> cursor = iterable.iterator();
								while (cursor.hasNext()) {
									Document doc = cursor.next();
									switch(doc.getString("_id")) {
										case "West Virginia":{
											int[] cityArray = new int[] {54001,54003,54005,54007,54009,54011,54013,54015,54017,54019,54021,54023,54025,54027,54029,54031,54033,54035,54037,54039,54041,54043,54045,54047,54049,54051,54053,54055,54057,54059,54061,54063,54065,54067,54069,54071,54073,54075,54077,54079,54081,54083,54085,54087,54089,54091,54093,54095,54097,54099,54101,54103,54105,54107,54109};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Pennsylvania ":{
											int[] cityArray = new int[] {42001,42003,42005,42007,42009,42011,42013,42015,42017,42019,42021,42023,42025,42027,42029,42031,42033,42035,42037,42039,42041,42043,42045,42047,42049,42051,42053,42055,42057,42059,42061,42063,42065,42067,42069,42071,42073,42075,42077,42079,42081,42083,42085,42087,42089,42091,42093,42095,42097,42099,42101,42103,42105,42107,42109,42111,42113,42115,42117,42119,42121,42123,42125,42127,42129,42131,42133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Alaska":{
											int[] cityArray = new int[] {2013,2016,2020,2050,2060,2068,2070,2090,2100,2105,2110,2122,2130,2150,2164,2170,2180,2185,2188,2195,2198,2220,2230,2240,2261,2270,2275,2282,2290};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Hawaii":{
											int[] cityArray = new int[] {15001,15003,15005,15007,15009};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Indiana":{
											int[] cityArray = new int[] {18001,18003,18005,18007,18009,18011,18013,18015,18017,18019,18021,18023,18025,18027,18029,18031,18033,18035,18037,18039,18041,18043,18045,18047,18049,18051,18053,18055,18057,18059,18061,18063,18065,18067,18069,18071,18073,18075,18077,18079,18081,18083,18085,18087,18089,18091,18093,18095,18097,18099,18101,18103,18105,18107,18109,18111,18113,18115,18117,18119,18121,18123,18125,18127,18129,18131,18133,18135,18137,18139,18141,18143,18145,18147,18149,18151,18153,18155,18157,18159,18161,18163,18165,18167,18169,18171,18173,18175,18177,18179,18181,18183};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Tennessee":{
											int[] cityArray = new int[] {47001,47003,47005,47007,47009,47011,47013,47015,47017,47019,47021,47023,47025,47027,47029,47031,47033,47035,47037,47039,47041,47043,47045,47047,47049,47051,47053,47055,47057,47059,47061,47063,47065,47067,47069,47071,47073,47075,47077,47079,47081,47083,47085,47087,47089,47091,47093,47095,47097,47099,47101,47103,47105,47107,47109,47111,47113,47115,47117,47119,47121,47123,47125,47127,47129,47131,47133,47135,47137,47139,47141,47143,47145,47147,47149,47151,47153,47155,47157,47159,47161,47163,47165,47167,47169,47171,47173,47175,47177,47179,47181,47183,47185,47187,47189};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Minnesota":{
											int[] cityArray = new int[] {27001,27003,27005,27007,27009,27011,27013,27015,27017,27019,27021,27023,27025,27027,27029,27031,27033,27035,27037,27039,27041,27043,27045,27047,27049,27051,27053,27055,27057,27059,27061,27063,27065,27067,27069,27071,27073,27075,27077,27079,27081,27083,27085,27087,27089,27091,27093,27095,27097,27099,27101,27103,27105,27107,27109,27111,27113,27115,27117,27119,27121,27123,27125,27127,27129,27131,27133,27135,27137,27139,27141,27143,27145,27147,27149,27151,27153,27155,27157,27159,27161,27163,27165,27167,27169,27171,27173};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Alabama":{
											int[] cityArray = new int[] {1001,1003,1005,1007,1009,1011,1013,1015,1017,1019,1021,1023,1025,1027,1029,1031,1033,1035,1037,1039,1041,1043,1045,1047,1049,1051,1053,1055,1057,1059,1061,1063,1065,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,1095,1097,1099,1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,1129,1131,1133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Virginia":{
											int[] cityArray = new int[] {51001,51003,51005,51007,51009,51011,51013,51015,51017,51019,51021,51023,51025,51027,51029,51031,51033,51035,51036,51037,51041,51043,51045,51047,51049,51051,51053,51057,51059,51061,51063,51065,51067,51069,51071,51073,51075,51077,51079,51081,51083,51085,51087,51089,51091,51093,51095,51097,51099,51101,51103,51105,51107,51109,51111,51113,51115,51117,51119,51121,51125,51127,51131,51133,51135,51137,51139,51141,51143,51145,51147,51149,51153,51155,51157,51159,51161,51163,51165,51167,51169,51171,51173,51175,51177,51179,51181,51183,51185,51187,51191,51193,51195,51197,51199,51510,51515,51520,51530,51540,51550,51570,51580,51590,51595,51600,51610,51620,51630,51640,51650,51660,51670,51678,51680,51683,51685,51690,51700,51710,51720,51730,51735,51740,51750,51760,51770,51775,51790,51800,51810,51820,51830,51840};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Georgia":{
											int[] cityArray = new int[] {13001,13003,13005,13007,13009,13011,13013,13015,13017,13019,13021,13023,13025,13027,13029,13031,13033,13035,13037,13039,13043,13045,13047,13049,13051,13053,13055,13057,13059,13061,13063,13065,13067,13069,13071,13073,13075,13077,13079,13081,13083,13085,13087,13089,13091,13093,13095,13097,13099,13101,13103,13105,13107,13109,13111,13113,13115,13117,13119,13121,13123,13125,13127,13129,13131,13133,13135,13137,13139,13141,13143,13145,13147,13149,13151,13153,13155,13157,13159,13161,13163,13165,13167,13169,13171,13173,13175,13177,13179,13181,13183,13185,13187,13189,13191,13193,13195,13197,13199,13201,13205,13207,13209,13211,13213,13215,13217,13219,13221,13223,13225,13227,13229,13231,13233,13235,13237,13239,13241,13243,13245,13247,13249,13251,13253,13255,13257,13259,13261,13263,13265,13267,13269,13271,13273,13275,13277,13279,13281,13283,13285,13287,13289,13291,13293,13295,13297,13299,13301,13303,13305,13307,13309,13311,13313,13315,13317,13319,13321};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Missouri":{
											int[] cityArray = new int[] {29001,29003,29005,29007,29009,29011,29013,29015,29017,29019,29021,29023,29025,29027,29029,29031,29033,29035,29037,29039,29041,29043,29045,29047,29049,29051,29053,29055,29057,29059,29061,29063,29065,29067,29069,29071,29073,29075,29077,29079,29081,29083,29085,29087,29089,29091,29093,29095,29097,29099,29101,29103,29105,29107,29109,29111,29113,29115,29117,29119,29121,29123,29125,29127,29129,29131,29133,29135,29137,29139,29141,29143,29145,29147,29149,29151,29153,29155,29157,29159,29161,29163,29165,29167,29169,29171,29173,29175,29177,29179,29181,29183,29185,29186,29187,29189,29195,29197,29199,29201,29203,29205,29207,29209,29211,29213,29215,29217,29219,29221,29223,29225,29227,29229,29510};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Michigan":{
											int[] cityArray = new int[] {26001,26003,26005,26007,26009,26011,26013,26015,26017,26019,26021,26023,26025,26027,26029,26031,26033,26035,26037,26039,26041,26043,26045,26047,26049,26051,26053,26055,26057,26059,26061,26063,26065,26067,26069,26071,26073,26075,26077,26079,26081,26083,26085,26087,26089,26091,26093,26095,26097,26099,26101,26103,26105,26107,26109,26111,26113,26115,26117,26119,26121,26123,26125,26127,26129,26131,26133,26135,26137,26139,26141,26143,26145,26147,26149,26151,26153,26155,26157,26159,26161,26163,26165};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Arkansas":{
											int[] cityArray = new int[] {5001,5003,5005,5007,5009,5011,5013,5015,5017,5019,5021,5023,5025,5027,5029,5031,5033,5035,5037,5039,5041,5043,5045,5047,5049,5051,5053,5055,5057,5059,5061,5063,5065,5067,5069,5071,5073,5075,5077,5079,5081,5083,5085,5087,5089,5091,5093,5095,5097,5099,5101,5103,5105,5107,5109,5111,5113,5115,5117,5119,5121,5123,5125,5127,5129,5131,5133,5135,5137,5139,5141,5143,5145,5147,5149};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Massachusetts":{
											int[] cityArray = new int[] {25001,25003,25005,25007,25009,25011,25013,25015,25017,25019,25021,25023,25025,25027};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Wisconsin":{
											int[] cityArray = new int[] {55001,55003,55005,55007,55009,55011,55013,55015,55017,55019,55021,55023,55025,55027,55029,55031,55033,55035,55037,55039,55041,55043,55045,55047,55049,55051,55053,55055,55057,55059,55061,55063,55065,55067,55069,55071,55073,55075,55077,55078,55079,55081,55083,55085,55087,55089,55091,55093,55095,55097,55099,55101,55103,55105,55107,55109,55111,55113,55115,55117,55119,55121,55123,55125,55127,55129,55131,55133,55135,55137,55139,55141};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "South Carolina":{
											int[] cityArray = new int[] {45001,45003,45005,45007,45009,45011,45013,45015,45017,45019,45021,45023,45025,45027,45029,45031,45033,45035,45037,45039,45041,45043,45045,45047,45049,45051,45053,45055,45057,45059,45061,45063,45065,45067,45069,45071,45073,45075,45077,45079,45081,45083,45085,45087,45089,45091};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Utah":{
											int[] cityArray = new int[] {49001,49003,49005,49007,49009,49011,49013,49015,49017,49019,49021,49023,49025,49027,49029,49031,49033,49035,49037,49039,49041,49043,49045,49047,49049,49051,49053,49055,49057};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "California":{
											int[] cityArray = new int[] {6001,6003,6005,6007,6009,6011,6013,6015,6017,6019,6021,6023,6025,6027,6029,6031,6033,6035,6037,6039,6041,6043,6045,6047,6049,6051,6053,6055,6057,6059,6061,6063,6065,6067,6069,6071,6073,6075,6077,6079,6081,6083,6085,6087,6089,6091,6093,6095,6097,6099,6101,6103,6105,6107,6109,6111,6113,6115};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Ohio":{
											int[] cityArray = new int[] {39001,39003,39005,39007,39009,39011,39013,39015,39017,39019,39021,39023,39025,39027,39029,39031,39033,39035,39037,39039,39041,39043,39045,39047,39049,39051,39053,39055,39057,39059,39061,39063,39065,39067,39069,39071,39073,39075,39077,39079,39081,39083,39085,39087,39089,39091,39093,39095,39097,39099,39101,39103,39105,39107,39109,39111,39113,39115,39117,39119,39121,39123,39125,39127,39129,39131,39133,39135,39137,39139,39141,39143,39145,39147,39149,39151,39153,39155,39157,39159,39161,39163,39165,39167,39169,39171,39173,39175};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Illinois":{
											int[] cityArray = new int[] {17001,17003,17005,17007,17009,17011,17013,17015,17017,17019,17021,17023,17025,17027,17029,17031,17033,17035,17037,17039,17041,17043,17045,17047,17049,17051,17053,17055,17057,17059,17061,17063,17065,17067,17069,17071,17073,17075,17077,17079,17081,17083,17085,17087,17089,17091,17093,17095,17097,17099,17101,17103,17105,17107,17109,17111,17113,17115,17117,17119,17121,17123,17125,17127,17129,17131,17133,17135,17137,17139,17141,17143,17145,17147,17149,17151,17153,17155,17157,17159,17161,17163,17165,17167,17169,17171,17173,17175,17177,17179,17181,17183,17185,17187,17189,17191,17193,17195,17197,17199,17201,17203};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Texas":{
											int[] cityArray = new int[] {48001,48003,48005,48007,48009,48011,48013,48015,48017,48019,48021,48023,48025,48027,48029,48031,48033,48035,48037,48039,48041,48043,48045,48047,48049,48051,48053,48055,48057,48059,48061,48063,48065,48067,48069,48071,48073,48075,48077,48079,48081,48083,48085,48087,48089,48091,48093,48095,48097,48099,48101,48103,48105,48107,48109,48111,48113,48115,48117,48119,48121,48123,48125,48127,48129,48131,48133,48135,48137,48139,48141,48143,48145,48147,48149,48151,48153,48155,48157,48159,48161,48163,48165,48167,48169,48171,48173,48175,48177,48179,48181,48183,48185,48187,48189,48191,48193,48195,48197,48199,48201,48203,48205,48207,48209,48211,48213,48215,48217,48219,48221,48223,48225,48227,48229,48231,48233,48235,48237,48239,48241,48243,48245,48247,48249,48251,48253,48255,48257,48259,48261,48263,48265,48267,48269,48271,48273,48275,48277,48279,48281,48283,48285,48287,48289,48291,48293,48295,48297,48299,48301,48303,48305,48307,48309,48311,48313,48315,48317,48319,48321,48323,48325,48327,48329,48331,48333,48335,48337,48339,48341,48343,48345,48347,48349,48351,48353,48355,48357,48359,48361,48363,48365,48367,48369,48371,48373,48375,48377,48379,48381,48383,48385,48387,48389,48391,48393,48395,48397,48399,48401,48403,48405,48407,48409,48411,48413,48415,48417,48419,48421,48423,48425,48427,48429,48431,48433,48435,48437,48439,48441,48443,48445,48447,48449,48451,48453,48455,48457,48459,48461,48463,48465,48467,48469,48471,48473,48475,48477,48479,48481,48483,48485,48487,48489,48491,48493,48495,48497,48499,48501,48503,48505,48507};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Arizona":{
											int[] cityArray = new int[] {4001,4003,4005,4007,4009,4011,4012,4013,4015,4017,4019,4021,4023,4025,4027};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "New Jersey":{
											int[] cityArray = new int[] {34001,34003,34005,34007,34009,34011,34013,34015,34017,34019,34021,34023,34025,34027,34029,34031,34033,34035,34037,34039,34041};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Florida":{
											int[] cityArray = new int[] {12001,12003,12005,12007,12009,12011,12013,12015,12017,12019,12021,12023,12027,12029,12031,12033,12035,12037,12039,12041,12043,12045,12047,12049,12051,12053,12055,12057,12059,12061,12063,12065,12067,12069,12071,12073,12075,12077,12079,12081,12083,12085,12086,12087,12089,12091,12093,12095,12097,12099,12101,12103,12105,12107,12109,12111,12113,12115,12117,12119,12121,12123,12125,12127,12129,12131,12133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
									}
								}
							}
							catch(Exception ex) {	
								ex.printStackTrace();
							}
							mDBObj.disconnectMongoDB();
						}
						else {
							Document doc = new Document();
							doc.put("DBError", dbStatus.get("msg"));
							choroPlethList.add(doc);
						}
						Gson gson = new Gson();
						String jsonString = gson.toJson(choroPlethList);
						response.setContentType("application/json");
						PrintWriter pw = response.getWriter();
						pw.write(jsonString);
						pw.flush();
						break;
					}
					case "TotalSalesCount":{
						Bson bList = null;
						Bson sortObj = null;
						bList = Aggregates.group("$state",Accumulators.min("ReviewMin", "$reviewRating"),Accumulators.max("ReviewMax", "$reviewRating"),Accumulators.avg("ReviewAvg", "$reviewRating"));
						
						sortObj = Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.descending("ReviewAvg")));
						List<Document> choroPlethList = new ArrayList<Document>();
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = mDBObj.checkMongoDB();
						if(dbStatus.get("status").equals("true")) {
							MongoCollection<Document> myReviews = mDBObj.getCollection();
							String[][] TotalSalesMap = dbObj.getTotalHotelSales();
							
							FindIterable<Document> docs=null;
							AggregateIterable<Document> iterable=null;
							try {
								iterable = myReviews.aggregate(Arrays.asList(bList,sortObj));
								MongoCursor<Document> cursor = iterable.iterator();
								while (cursor.hasNext()) {
									Document doc = cursor.next();
									switch(doc.getString("_id")) {
										case "West Virginia":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("West Virginia")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {54001,54003,54005,54007,54009,54011,54013,54015,54017,54019,54021,54023,54025,54027,54029,54031,54033,54035,54037,54039,54041,54043,54045,54047,54049,54051,54053,54055,54057,54059,54061,54063,54065,54067,54069,54071,54073,54075,54077,54079,54081,54083,54085,54087,54089,54091,54093,54095,54097,54099,54101,54103,54105,54107,54109};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Pennsylvania":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Pennsylvania")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {42001,42003,42005,42007,42009,42011,42013,42015,42017,42019,42021,42023,42025,42027,42029,42031,42033,42035,42037,42039,42041,42043,42045,42047,42049,42051,42053,42055,42057,42059,42061,42063,42065,42067,42069,42071,42073,42075,42077,42079,42081,42083,42085,42087,42089,42091,42093,42095,42097,42099,42101,42103,42105,42107,42109,42111,42113,42115,42117,42119,42121,42123,42125,42127,42129,42131,42133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Alaska":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Alaska")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {2013,2016,2020,2050,2060,2068,2070,2090,2100,2105,2110,2122,2130,2150,2164,2170,2180,2185,2188,2195,2198,2220,2230,2240,2261,2270,2275,2282,2290};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Hawaii":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Hawaii")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {15001,15003,15005,15007,15009};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Indiana":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Indiana")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {18001,18003,18005,18007,18009,18011,18013,18015,18017,18019,18021,18023,18025,18027,18029,18031,18033,18035,18037,18039,18041,18043,18045,18047,18049,18051,18053,18055,18057,18059,18061,18063,18065,18067,18069,18071,18073,18075,18077,18079,18081,18083,18085,18087,18089,18091,18093,18095,18097,18099,18101,18103,18105,18107,18109,18111,18113,18115,18117,18119,18121,18123,18125,18127,18129,18131,18133,18135,18137,18139,18141,18143,18145,18147,18149,18151,18153,18155,18157,18159,18161,18163,18165,18167,18169,18171,18173,18175,18177,18179,18181,18183};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Tennessee":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Tennessee")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {47001,47003,47005,47007,47009,47011,47013,47015,47017,47019,47021,47023,47025,47027,47029,47031,47033,47035,47037,47039,47041,47043,47045,47047,47049,47051,47053,47055,47057,47059,47061,47063,47065,47067,47069,47071,47073,47075,47077,47079,47081,47083,47085,47087,47089,47091,47093,47095,47097,47099,47101,47103,47105,47107,47109,47111,47113,47115,47117,47119,47121,47123,47125,47127,47129,47131,47133,47135,47137,47139,47141,47143,47145,47147,47149,47151,47153,47155,47157,47159,47161,47163,47165,47167,47169,47171,47173,47175,47177,47179,47181,47183,47185,47187,47189};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Minnesota":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Minnesota")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {27001,27003,27005,27007,27009,27011,27013,27015,27017,27019,27021,27023,27025,27027,27029,27031,27033,27035,27037,27039,27041,27043,27045,27047,27049,27051,27053,27055,27057,27059,27061,27063,27065,27067,27069,27071,27073,27075,27077,27079,27081,27083,27085,27087,27089,27091,27093,27095,27097,27099,27101,27103,27105,27107,27109,27111,27113,27115,27117,27119,27121,27123,27125,27127,27129,27131,27133,27135,27137,27139,27141,27143,27145,27147,27149,27151,27153,27155,27157,27159,27161,27163,27165,27167,27169,27171,27173};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Alabama":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Alabama")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {1001,1003,1005,1007,1009,1011,1013,1015,1017,1019,1021,1023,1025,1027,1029,1031,1033,1035,1037,1039,1041,1043,1045,1047,1049,1051,1053,1055,1057,1059,1061,1063,1065,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,1095,1097,1099,1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,1129,1131,1133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Virginia":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Virginia")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {51001,51003,51005,51007,51009,51011,51013,51015,51017,51019,51021,51023,51025,51027,51029,51031,51033,51035,51036,51037,51041,51043,51045,51047,51049,51051,51053,51057,51059,51061,51063,51065,51067,51069,51071,51073,51075,51077,51079,51081,51083,51085,51087,51089,51091,51093,51095,51097,51099,51101,51103,51105,51107,51109,51111,51113,51115,51117,51119,51121,51125,51127,51131,51133,51135,51137,51139,51141,51143,51145,51147,51149,51153,51155,51157,51159,51161,51163,51165,51167,51169,51171,51173,51175,51177,51179,51181,51183,51185,51187,51191,51193,51195,51197,51199,51510,51515,51520,51530,51540,51550,51570,51580,51590,51595,51600,51610,51620,51630,51640,51650,51660,51670,51678,51680,51683,51685,51690,51700,51710,51720,51730,51735,51740,51750,51760,51770,51775,51790,51800,51810,51820,51830,51840};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Georgia":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Georgia")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {13001,13003,13005,13007,13009,13011,13013,13015,13017,13019,13021,13023,13025,13027,13029,13031,13033,13035,13037,13039,13043,13045,13047,13049,13051,13053,13055,13057,13059,13061,13063,13065,13067,13069,13071,13073,13075,13077,13079,13081,13083,13085,13087,13089,13091,13093,13095,13097,13099,13101,13103,13105,13107,13109,13111,13113,13115,13117,13119,13121,13123,13125,13127,13129,13131,13133,13135,13137,13139,13141,13143,13145,13147,13149,13151,13153,13155,13157,13159,13161,13163,13165,13167,13169,13171,13173,13175,13177,13179,13181,13183,13185,13187,13189,13191,13193,13195,13197,13199,13201,13205,13207,13209,13211,13213,13215,13217,13219,13221,13223,13225,13227,13229,13231,13233,13235,13237,13239,13241,13243,13245,13247,13249,13251,13253,13255,13257,13259,13261,13263,13265,13267,13269,13271,13273,13275,13277,13279,13281,13283,13285,13287,13289,13291,13293,13295,13297,13299,13301,13303,13305,13307,13309,13311,13313,13315,13317,13319,13321};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Missouri":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Missouri")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {29001,29003,29005,29007,29009,29011,29013,29015,29017,29019,29021,29023,29025,29027,29029,29031,29033,29035,29037,29039,29041,29043,29045,29047,29049,29051,29053,29055,29057,29059,29061,29063,29065,29067,29069,29071,29073,29075,29077,29079,29081,29083,29085,29087,29089,29091,29093,29095,29097,29099,29101,29103,29105,29107,29109,29111,29113,29115,29117,29119,29121,29123,29125,29127,29129,29131,29133,29135,29137,29139,29141,29143,29145,29147,29149,29151,29153,29155,29157,29159,29161,29163,29165,29167,29169,29171,29173,29175,29177,29179,29181,29183,29185,29186,29187,29189,29195,29197,29199,29201,29203,29205,29207,29209,29211,29213,29215,29217,29219,29221,29223,29225,29227,29229,29510};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Michigan":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Michigan")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {26001,26003,26005,26007,26009,26011,26013,26015,26017,26019,26021,26023,26025,26027,26029,26031,26033,26035,26037,26039,26041,26043,26045,26047,26049,26051,26053,26055,26057,26059,26061,26063,26065,26067,26069,26071,26073,26075,26077,26079,26081,26083,26085,26087,26089,26091,26093,26095,26097,26099,26101,26103,26105,26107,26109,26111,26113,26115,26117,26119,26121,26123,26125,26127,26129,26131,26133,26135,26137,26139,26141,26143,26145,26147,26149,26151,26153,26155,26157,26159,26161,26163,26165};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Arkansas":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Arkansas")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {5001,5003,5005,5007,5009,5011,5013,5015,5017,5019,5021,5023,5025,5027,5029,5031,5033,5035,5037,5039,5041,5043,5045,5047,5049,5051,5053,5055,5057,5059,5061,5063,5065,5067,5069,5071,5073,5075,5077,5079,5081,5083,5085,5087,5089,5091,5093,5095,5097,5099,5101,5103,5105,5107,5109,5111,5113,5115,5117,5119,5121,5123,5125,5127,5129,5131,5133,5135,5137,5139,5141,5143,5145,5147,5149};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Massachusetts":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Massachusetts")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {25001,25003,25005,25007,25009,25011,25013,25015,25017,25019,25021,25023,25025,25027};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Wisconsin":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Wisconsin")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {55001,55003,55005,55007,55009,55011,55013,55015,55017,55019,55021,55023,55025,55027,55029,55031,55033,55035,55037,55039,55041,55043,55045,55047,55049,55051,55053,55055,55057,55059,55061,55063,55065,55067,55069,55071,55073,55075,55077,55078,55079,55081,55083,55085,55087,55089,55091,55093,55095,55097,55099,55101,55103,55105,55107,55109,55111,55113,55115,55117,55119,55121,55123,55125,55127,55129,55131,55133,55135,55137,55139,55141};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "South Carolina":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("South Carolina")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {45001,45003,45005,45007,45009,45011,45013,45015,45017,45019,45021,45023,45025,45027,45029,45031,45033,45035,45037,45039,45041,45043,45045,45047,45049,45051,45053,45055,45057,45059,45061,45063,45065,45067,45069,45071,45073,45075,45077,45079,45081,45083,45085,45087,45089,45091};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Utah":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Utah")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {49001,49003,49005,49007,49009,49011,49013,49015,49017,49019,49021,49023,49025,49027,49029,49031,49033,49035,49037,49039,49041,49043,49045,49047,49049,49051,49053,49055,49057};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "California":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("California")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {6001,6003,6005,6007,6009,6011,6013,6015,6017,6019,6021,6023,6025,6027,6029,6031,6033,6035,6037,6039,6041,6043,6045,6047,6049,6051,6053,6055,6057,6059,6061,6063,6065,6067,6069,6071,6073,6075,6077,6079,6081,6083,6085,6087,6089,6091,6093,6095,6097,6099,6101,6103,6105,6107,6109,6111,6113,6115};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Ohio":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Ohio")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {39001,39003,39005,39007,39009,39011,39013,39015,39017,39019,39021,39023,39025,39027,39029,39031,39033,39035,39037,39039,39041,39043,39045,39047,39049,39051,39053,39055,39057,39059,39061,39063,39065,39067,39069,39071,39073,39075,39077,39079,39081,39083,39085,39087,39089,39091,39093,39095,39097,39099,39101,39103,39105,39107,39109,39111,39113,39115,39117,39119,39121,39123,39125,39127,39129,39131,39133,39135,39137,39139,39141,39143,39145,39147,39149,39151,39153,39155,39157,39159,39161,39163,39165,39167,39169,39171,39173,39175};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Illinois":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Illinois")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {17001,17003,17005,17007,17009,17011,17013,17015,17017,17019,17021,17023,17025,17027,17029,17031,17033,17035,17037,17039,17041,17043,17045,17047,17049,17051,17053,17055,17057,17059,17061,17063,17065,17067,17069,17071,17073,17075,17077,17079,17081,17083,17085,17087,17089,17091,17093,17095,17097,17099,17101,17103,17105,17107,17109,17111,17113,17115,17117,17119,17121,17123,17125,17127,17129,17131,17133,17135,17137,17139,17141,17143,17145,17147,17149,17151,17153,17155,17157,17159,17161,17163,17165,17167,17169,17171,17173,17175,17177,17179,17181,17183,17185,17187,17189,17191,17193,17195,17197,17199,17201,17203};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Texas":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Texas")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {48001,48003,48005,48007,48009,48011,48013,48015,48017,48019,48021,48023,48025,48027,48029,48031,48033,48035,48037,48039,48041,48043,48045,48047,48049,48051,48053,48055,48057,48059,48061,48063,48065,48067,48069,48071,48073,48075,48077,48079,48081,48083,48085,48087,48089,48091,48093,48095,48097,48099,48101,48103,48105,48107,48109,48111,48113,48115,48117,48119,48121,48123,48125,48127,48129,48131,48133,48135,48137,48139,48141,48143,48145,48147,48149,48151,48153,48155,48157,48159,48161,48163,48165,48167,48169,48171,48173,48175,48177,48179,48181,48183,48185,48187,48189,48191,48193,48195,48197,48199,48201,48203,48205,48207,48209,48211,48213,48215,48217,48219,48221,48223,48225,48227,48229,48231,48233,48235,48237,48239,48241,48243,48245,48247,48249,48251,48253,48255,48257,48259,48261,48263,48265,48267,48269,48271,48273,48275,48277,48279,48281,48283,48285,48287,48289,48291,48293,48295,48297,48299,48301,48303,48305,48307,48309,48311,48313,48315,48317,48319,48321,48323,48325,48327,48329,48331,48333,48335,48337,48339,48341,48343,48345,48347,48349,48351,48353,48355,48357,48359,48361,48363,48365,48367,48369,48371,48373,48375,48377,48379,48381,48383,48385,48387,48389,48391,48393,48395,48397,48399,48401,48403,48405,48407,48409,48411,48413,48415,48417,48419,48421,48423,48425,48427,48429,48431,48433,48435,48437,48439,48441,48443,48445,48447,48449,48451,48453,48455,48457,48459,48461,48463,48465,48467,48469,48471,48473,48475,48477,48479,48481,48483,48485,48487,48489,48491,48493,48495,48497,48499,48501,48503,48505,48507};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Arizona":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Arizona")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {4001,4003,4005,4007,4009,4011,4012,4013,4015,4017,4019,4021,4023,4025,4027};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "New Jersey":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("New Jersey")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {34001,34003,34005,34007,34009,34011,34013,34015,34017,34019,34021,34023,34025,34027,34029,34031,34033,34035,34037,34039,34041};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Florida":{
											int totalSales=0;
											for(int j=0;j<TotalSalesMap.length;j++) {
												if(TotalSalesMap[j][0]!=null) {
													if(TotalSalesMap[j][0].equals("Florida")) {
														totalSales = Integer.parseInt(TotalSalesMap[j][1]);
													}
												}
											}
											
											int[] cityArray = new int[] {12001,12003,12005,12007,12009,12011,12013,12015,12017,12019,12021,12023,12027,12029,12031,12033,12035,12037,12039,12041,12043,12045,12047,12049,12051,12053,12055,12057,12059,12061,12063,12065,12067,12069,12071,12073,12075,12077,12079,12081,12083,12085,12086,12087,12089,12091,12093,12095,12097,12099,12101,12103,12105,12107,12109,12111,12113,12115,12117,12119,12121,12123,12125,12127,12129,12131,12133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
														.append("TotalHotelSales", totalSales)
										                .append("ReviewMin", doc.getInteger("ReviewMin"))
										                .append("ReviewMax", doc.getInteger("ReviewMax"))
										                .append("ReviewAvg", doc.getDouble("ReviewAvg"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
									}
								}
							}
							catch(Exception ex) {	
								ex.printStackTrace();
							}
							mDBObj.disconnectMongoDB();
						}
						else {
							Document doc = new Document();
							doc.put("DBError", dbStatus.get("msg"));
							choroPlethList.add(doc);
						}
						Gson gson = new Gson();
						String jsonString = gson.toJson(choroPlethList);
						response.setContentType("application/json");
						PrintWriter pw = response.getWriter();
						pw.write(jsonString);
						pw.flush();
						break;
					}
					case "TotalReviews5":{
						Bson bList = null;
						Bson sortObj = null;
						Bson matchObj = null;
						Bson projectObj = null;
						bList = Aggregates.group("$state", Accumulators.sum("TotalReviews", 1));
						projectObj = Aggregates.project(com.mongodb.client.model.Projections.fields(com.mongodb.client.model.Projections.include("reviewRating")));
						sortObj = Aggregates.sort(com.mongodb.client.model.Sorts.orderBy(com.mongodb.client.model.Sorts.descending("TotalReviews")));
						
						matchObj = Aggregates.match(com.mongodb.client.model.Filters.eq("reviewRating",5));
						//limitObj = Aggregates.limit(100);
						List<Document> choroPlethList = new ArrayList<Document>();
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = mDBObj.checkMongoDB();
						if(dbStatus.get("status").equals("true")) {
							MongoCollection<Document> myReviews = mDBObj.getCollection();
							FindIterable<Document> docs=null;
							AggregateIterable<Document> iterable=null;
							
							try {
								iterable = myReviews.aggregate(Arrays.asList(matchObj,bList,sortObj));
								MongoCursor<Document> cursor = iterable.iterator();
								while (cursor.hasNext()) {
									Document doc = cursor.next();
									switch(doc.getString("_id")) {
										case "West Virginia":{
											int[] cityArray = new int[] {54001,54003,54005,54007,54009,54011,54013,54015,54017,54019,54021,54023,54025,54027,54029,54031,54033,54035,54037,54039,54041,54043,54045,54047,54049,54051,54053,54055,54057,54059,54061,54063,54065,54067,54069,54071,54073,54075,54077,54079,54081,54083,54085,54087,54089,54091,54093,54095,54097,54099,54101,54103,54105,54107,54109};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Pennsylvania":{
											int[] cityArray = new int[] {42001,42003,42005,42007,42009,42011,42013,42015,42017,42019,42021,42023,42025,42027,42029,42031,42033,42035,42037,42039,42041,42043,42045,42047,42049,42051,42053,42055,42057,42059,42061,42063,42065,42067,42069,42071,42073,42075,42077,42079,42081,42083,42085,42087,42089,42091,42093,42095,42097,42099,42101,42103,42105,42107,42109,42111,42113,42115,42117,42119,42121,42123,42125,42127,42129,42131,42133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Alaska":{
											int[] cityArray = new int[] {2013,2016,2020,2050,2060,2068,2070,2090,2100,2105,2110,2122,2130,2150,2164,2170,2180,2185,2188,2195,2198,2220,2230,2240,2261,2270,2275,2282,2290};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Hawaii":{
											int[] cityArray = new int[] {15001,15003,15005,15007,15009};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Indiana":{
											int[] cityArray = new int[] {18001,18003,18005,18007,18009,18011,18013,18015,18017,18019,18021,18023,18025,18027,18029,18031,18033,18035,18037,18039,18041,18043,18045,18047,18049,18051,18053,18055,18057,18059,18061,18063,18065,18067,18069,18071,18073,18075,18077,18079,18081,18083,18085,18087,18089,18091,18093,18095,18097,18099,18101,18103,18105,18107,18109,18111,18113,18115,18117,18119,18121,18123,18125,18127,18129,18131,18133,18135,18137,18139,18141,18143,18145,18147,18149,18151,18153,18155,18157,18159,18161,18163,18165,18167,18169,18171,18173,18175,18177,18179,18181,18183};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Tennessee":{
											int[] cityArray = new int[] {47001,47003,47005,47007,47009,47011,47013,47015,47017,47019,47021,47023,47025,47027,47029,47031,47033,47035,47037,47039,47041,47043,47045,47047,47049,47051,47053,47055,47057,47059,47061,47063,47065,47067,47069,47071,47073,47075,47077,47079,47081,47083,47085,47087,47089,47091,47093,47095,47097,47099,47101,47103,47105,47107,47109,47111,47113,47115,47117,47119,47121,47123,47125,47127,47129,47131,47133,47135,47137,47139,47141,47143,47145,47147,47149,47151,47153,47155,47157,47159,47161,47163,47165,47167,47169,47171,47173,47175,47177,47179,47181,47183,47185,47187,47189};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Minnesota":{
											int[] cityArray = new int[] {27001,27003,27005,27007,27009,27011,27013,27015,27017,27019,27021,27023,27025,27027,27029,27031,27033,27035,27037,27039,27041,27043,27045,27047,27049,27051,27053,27055,27057,27059,27061,27063,27065,27067,27069,27071,27073,27075,27077,27079,27081,27083,27085,27087,27089,27091,27093,27095,27097,27099,27101,27103,27105,27107,27109,27111,27113,27115,27117,27119,27121,27123,27125,27127,27129,27131,27133,27135,27137,27139,27141,27143,27145,27147,27149,27151,27153,27155,27157,27159,27161,27163,27165,27167,27169,27171,27173};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Alabama":{
											int[] cityArray = new int[] {1001,1003,1005,1007,1009,1011,1013,1015,1017,1019,1021,1023,1025,1027,1029,1031,1033,1035,1037,1039,1041,1043,1045,1047,1049,1051,1053,1055,1057,1059,1061,1063,1065,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,1095,1097,1099,1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,1129,1131,1133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Virginia":{
											int[] cityArray = new int[] {51001,51003,51005,51007,51009,51011,51013,51015,51017,51019,51021,51023,51025,51027,51029,51031,51033,51035,51036,51037,51041,51043,51045,51047,51049,51051,51053,51057,51059,51061,51063,51065,51067,51069,51071,51073,51075,51077,51079,51081,51083,51085,51087,51089,51091,51093,51095,51097,51099,51101,51103,51105,51107,51109,51111,51113,51115,51117,51119,51121,51125,51127,51131,51133,51135,51137,51139,51141,51143,51145,51147,51149,51153,51155,51157,51159,51161,51163,51165,51167,51169,51171,51173,51175,51177,51179,51181,51183,51185,51187,51191,51193,51195,51197,51199,51510,51515,51520,51530,51540,51550,51570,51580,51590,51595,51600,51610,51620,51630,51640,51650,51660,51670,51678,51680,51683,51685,51690,51700,51710,51720,51730,51735,51740,51750,51760,51770,51775,51790,51800,51810,51820,51830,51840};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Georgia":{
											int[] cityArray = new int[] {13001,13003,13005,13007,13009,13011,13013,13015,13017,13019,13021,13023,13025,13027,13029,13031,13033,13035,13037,13039,13043,13045,13047,13049,13051,13053,13055,13057,13059,13061,13063,13065,13067,13069,13071,13073,13075,13077,13079,13081,13083,13085,13087,13089,13091,13093,13095,13097,13099,13101,13103,13105,13107,13109,13111,13113,13115,13117,13119,13121,13123,13125,13127,13129,13131,13133,13135,13137,13139,13141,13143,13145,13147,13149,13151,13153,13155,13157,13159,13161,13163,13165,13167,13169,13171,13173,13175,13177,13179,13181,13183,13185,13187,13189,13191,13193,13195,13197,13199,13201,13205,13207,13209,13211,13213,13215,13217,13219,13221,13223,13225,13227,13229,13231,13233,13235,13237,13239,13241,13243,13245,13247,13249,13251,13253,13255,13257,13259,13261,13263,13265,13267,13269,13271,13273,13275,13277,13279,13281,13283,13285,13287,13289,13291,13293,13295,13297,13299,13301,13303,13305,13307,13309,13311,13313,13315,13317,13319,13321};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Missouri":{
											int[] cityArray = new int[] {29001,29003,29005,29007,29009,29011,29013,29015,29017,29019,29021,29023,29025,29027,29029,29031,29033,29035,29037,29039,29041,29043,29045,29047,29049,29051,29053,29055,29057,29059,29061,29063,29065,29067,29069,29071,29073,29075,29077,29079,29081,29083,29085,29087,29089,29091,29093,29095,29097,29099,29101,29103,29105,29107,29109,29111,29113,29115,29117,29119,29121,29123,29125,29127,29129,29131,29133,29135,29137,29139,29141,29143,29145,29147,29149,29151,29153,29155,29157,29159,29161,29163,29165,29167,29169,29171,29173,29175,29177,29179,29181,29183,29185,29186,29187,29189,29195,29197,29199,29201,29203,29205,29207,29209,29211,29213,29215,29217,29219,29221,29223,29225,29227,29229,29510};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Michigan":{
											int[] cityArray = new int[] {26001,26003,26005,26007,26009,26011,26013,26015,26017,26019,26021,26023,26025,26027,26029,26031,26033,26035,26037,26039,26041,26043,26045,26047,26049,26051,26053,26055,26057,26059,26061,26063,26065,26067,26069,26071,26073,26075,26077,26079,26081,26083,26085,26087,26089,26091,26093,26095,26097,26099,26101,26103,26105,26107,26109,26111,26113,26115,26117,26119,26121,26123,26125,26127,26129,26131,26133,26135,26137,26139,26141,26143,26145,26147,26149,26151,26153,26155,26157,26159,26161,26163,26165};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Arkansas":{
											int[] cityArray = new int[] {5001,5003,5005,5007,5009,5011,5013,5015,5017,5019,5021,5023,5025,5027,5029,5031,5033,5035,5037,5039,5041,5043,5045,5047,5049,5051,5053,5055,5057,5059,5061,5063,5065,5067,5069,5071,5073,5075,5077,5079,5081,5083,5085,5087,5089,5091,5093,5095,5097,5099,5101,5103,5105,5107,5109,5111,5113,5115,5117,5119,5121,5123,5125,5127,5129,5131,5133,5135,5137,5139,5141,5143,5145,5147,5149};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Massachusetts":{
											int[] cityArray = new int[] {25001,25003,25005,25007,25009,25011,25013,25015,25017,25019,25021,25023,25025,25027};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Wisconsin":{
											int[] cityArray = new int[] {55001,55003,55005,55007,55009,55011,55013,55015,55017,55019,55021,55023,55025,55027,55029,55031,55033,55035,55037,55039,55041,55043,55045,55047,55049,55051,55053,55055,55057,55059,55061,55063,55065,55067,55069,55071,55073,55075,55077,55078,55079,55081,55083,55085,55087,55089,55091,55093,55095,55097,55099,55101,55103,55105,55107,55109,55111,55113,55115,55117,55119,55121,55123,55125,55127,55129,55131,55133,55135,55137,55139,55141};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "South Carolina":{
											int[] cityArray = new int[] {45001,45003,45005,45007,45009,45011,45013,45015,45017,45019,45021,45023,45025,45027,45029,45031,45033,45035,45037,45039,45041,45043,45045,45047,45049,45051,45053,45055,45057,45059,45061,45063,45065,45067,45069,45071,45073,45075,45077,45079,45081,45083,45085,45087,45089,45091};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Utah":{
											int[] cityArray = new int[] {49001,49003,49005,49007,49009,49011,49013,49015,49017,49019,49021,49023,49025,49027,49029,49031,49033,49035,49037,49039,49041,49043,49045,49047,49049,49051,49053,49055,49057};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "California":{
											int[] cityArray = new int[] {6001,6003,6005,6007,6009,6011,6013,6015,6017,6019,6021,6023,6025,6027,6029,6031,6033,6035,6037,6039,6041,6043,6045,6047,6049,6051,6053,6055,6057,6059,6061,6063,6065,6067,6069,6071,6073,6075,6077,6079,6081,6083,6085,6087,6089,6091,6093,6095,6097,6099,6101,6103,6105,6107,6109,6111,6113,6115};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Ohio":{
											int[] cityArray = new int[] {39001,39003,39005,39007,39009,39011,39013,39015,39017,39019,39021,39023,39025,39027,39029,39031,39033,39035,39037,39039,39041,39043,39045,39047,39049,39051,39053,39055,39057,39059,39061,39063,39065,39067,39069,39071,39073,39075,39077,39079,39081,39083,39085,39087,39089,39091,39093,39095,39097,39099,39101,39103,39105,39107,39109,39111,39113,39115,39117,39119,39121,39123,39125,39127,39129,39131,39133,39135,39137,39139,39141,39143,39145,39147,39149,39151,39153,39155,39157,39159,39161,39163,39165,39167,39169,39171,39173,39175};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Illinois":{
											int[] cityArray = new int[] {17001,17003,17005,17007,17009,17011,17013,17015,17017,17019,17021,17023,17025,17027,17029,17031,17033,17035,17037,17039,17041,17043,17045,17047,17049,17051,17053,17055,17057,17059,17061,17063,17065,17067,17069,17071,17073,17075,17077,17079,17081,17083,17085,17087,17089,17091,17093,17095,17097,17099,17101,17103,17105,17107,17109,17111,17113,17115,17117,17119,17121,17123,17125,17127,17129,17131,17133,17135,17137,17139,17141,17143,17145,17147,17149,17151,17153,17155,17157,17159,17161,17163,17165,17167,17169,17171,17173,17175,17177,17179,17181,17183,17185,17187,17189,17191,17193,17195,17197,17199,17201,17203};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Texas":{
											int[] cityArray = new int[] {48001,48003,48005,48007,48009,48011,48013,48015,48017,48019,48021,48023,48025,48027,48029,48031,48033,48035,48037,48039,48041,48043,48045,48047,48049,48051,48053,48055,48057,48059,48061,48063,48065,48067,48069,48071,48073,48075,48077,48079,48081,48083,48085,48087,48089,48091,48093,48095,48097,48099,48101,48103,48105,48107,48109,48111,48113,48115,48117,48119,48121,48123,48125,48127,48129,48131,48133,48135,48137,48139,48141,48143,48145,48147,48149,48151,48153,48155,48157,48159,48161,48163,48165,48167,48169,48171,48173,48175,48177,48179,48181,48183,48185,48187,48189,48191,48193,48195,48197,48199,48201,48203,48205,48207,48209,48211,48213,48215,48217,48219,48221,48223,48225,48227,48229,48231,48233,48235,48237,48239,48241,48243,48245,48247,48249,48251,48253,48255,48257,48259,48261,48263,48265,48267,48269,48271,48273,48275,48277,48279,48281,48283,48285,48287,48289,48291,48293,48295,48297,48299,48301,48303,48305,48307,48309,48311,48313,48315,48317,48319,48321,48323,48325,48327,48329,48331,48333,48335,48337,48339,48341,48343,48345,48347,48349,48351,48353,48355,48357,48359,48361,48363,48365,48367,48369,48371,48373,48375,48377,48379,48381,48383,48385,48387,48389,48391,48393,48395,48397,48399,48401,48403,48405,48407,48409,48411,48413,48415,48417,48419,48421,48423,48425,48427,48429,48431,48433,48435,48437,48439,48441,48443,48445,48447,48449,48451,48453,48455,48457,48459,48461,48463,48465,48467,48469,48471,48473,48475,48477,48479,48481,48483,48485,48487,48489,48491,48493,48495,48497,48499,48501,48503,48505,48507};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Arizona":{
											int[] cityArray = new int[] {4001,4003,4005,4007,4009,4011,4012,4013,4015,4017,4019,4021,4023,4025,4027};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "New Jersey":{
											int[] cityArray = new int[] {34001,34003,34005,34007,34009,34011,34013,34015,34017,34019,34021,34023,34025,34027,34029,34031,34033,34035,34037,34039,34041};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
										case "Florida":{
											int[] cityArray = new int[] {12001,12003,12005,12007,12009,12011,12013,12015,12017,12019,12021,12023,12027,12029,12031,12033,12035,12037,12039,12041,12043,12045,12047,12049,12051,12053,12055,12057,12059,12061,12063,12065,12067,12069,12071,12073,12075,12077,12079,12081,12083,12085,12086,12087,12089,12091,12093,12095,12097,12099,12101,12103,12105,12107,12109,12111,12113,12115,12117,12119,12121,12123,12125,12127,12129,12131,12133};
											for(int i=0;i<cityArray.length;i++) {
												Document cityDoc = new Document("_id", doc.getString("_id"))
										                .append("TotalReviews", doc.getInteger("TotalReviews"))
										                .append("ReviewRating", doc.getInteger("reviewRating"))
										                .append("id", cityArray[i]);
												choroPlethList.add(cityDoc);
											}
											break;
										}
									}
								}
							}
							catch(Exception ex) {	
								ex.printStackTrace();
							}
							mDBObj.disconnectMongoDB();
						}
						else {
							Document doc = new Document();
							doc.put("DBError", dbStatus.get("msg"));
							choroPlethList.add(doc);
						}
						Gson gson = new Gson();
						String jsonString = gson.toJson(choroPlethList);
						response.setContentType("application/json");
						PrintWriter pw = response.getWriter();
						pw.write(jsonString);
						pw.flush();
						break;
					}
					case "AveragePrices":{
						List<Document> choroPlethList = new ArrayList<Document>();
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							String[][] TotalSalesMap = dbObj.getAverageHotelsReservedPrices();
							try {
								for(int k=0;k<TotalSalesMap.length;k++) {
									if(TotalSalesMap[k][0]!=null) {
										averagePricesSwitchBlock(TotalSalesMap,choroPlethList,k);
										System.out.println("State-" + TotalSalesMap[k][0] + "AverageHotelPrices-" + TotalSalesMap[k][1]);
									}
								}
							}
							catch(Exception ex) {	
								ex.printStackTrace();
							}
						}
						else {
							Document doc = new Document();
							doc.put("DBError", dbStatus.get("msg"));
							choroPlethList.add(doc);
						}
						Gson gson = new Gson();
						String jsonString = gson.toJson(choroPlethList);
						response.setContentType("application/json");
						PrintWriter pw = response.getWriter();
						pw.write(jsonString);
						pw.flush();
						break;
					}
					case "TotalSalesPrices":{
						List<Document> choroPlethList = new ArrayList<Document>();
						HashMap<String, String> dbStatus = new HashMap<String, String>();
						dbStatus = dbObj.checkDBConnection();
						if(dbStatus.get("status").equals("true")) {
							String[][] TotalSalesMap = dbObj.getTotalHotelReservedPrices();
							try {
								for(int k=0;k<TotalSalesMap.length;k++) {
									if(TotalSalesMap[k][0]!=null) {
										totalPricesSwitchBlock(TotalSalesMap,choroPlethList,k);
									}
								}
							}
							catch(Exception ex) {	
								ex.printStackTrace();
							}
						}
						else {
							Document doc = new Document();
							doc.put("DBError", dbStatus.get("msg"));
							choroPlethList.add(doc);
						}
						Gson gson = new Gson();
						String jsonString = gson.toJson(choroPlethList);
						response.setContentType("application/json");
						PrintWriter pw = response.getWriter();
						pw.write(jsonString);
						pw.flush();
						break;
					}
					default:{
						break;
					}
				}
			}
		}
		catch(Exception ex) {	
			ex.printStackTrace();
		}
	}
	
	public void averagePricesSwitchBlock(String[][] TotalSalesMap,List<Document> choroPlethList,int k) {
		switch(TotalSalesMap[k][0]) {
			case "West Virginia":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("West Virginia")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {54001,54003,54005,54007,54009,54011,54013,54015,54017,54019,54021,54023,54025,54027,54029,54031,54033,54035,54037,54039,54041,54043,54045,54047,54049,54051,54053,54055,54057,54059,54061,54063,54065,54067,54069,54071,54073,54075,54077,54079,54081,54083,54085,54087,54089,54091,54093,54095,54097,54099,54101,54103,54105,54107,54109};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Pennsylvania":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Pennsylvania")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {42001,42003,42005,42007,42009,42011,42013,42015,42017,42019,42021,42023,42025,42027,42029,42031,42033,42035,42037,42039,42041,42043,42045,42047,42049,42051,42053,42055,42057,42059,42061,42063,42065,42067,42069,42071,42073,42075,42077,42079,42081,42083,42085,42087,42089,42091,42093,42095,42097,42099,42101,42103,42105,42107,42109,42111,42113,42115,42117,42119,42121,42123,42125,42127,42129,42131,42133};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Alaska":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Alaska")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {2013,2016,2020,2050,2060,2068,2070,2090,2100,2105,2110,2122,2130,2150,2164,2170,2180,2185,2188,2195,2198,2220,2230,2240,2261,2270,2275,2282,2290};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Hawaii":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Hawaii")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {15001,15003,15005,15007,15009};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Indiana":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Indiana")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {18001,18003,18005,18007,18009,18011,18013,18015,18017,18019,18021,18023,18025,18027,18029,18031,18033,18035,18037,18039,18041,18043,18045,18047,18049,18051,18053,18055,18057,18059,18061,18063,18065,18067,18069,18071,18073,18075,18077,18079,18081,18083,18085,18087,18089,18091,18093,18095,18097,18099,18101,18103,18105,18107,18109,18111,18113,18115,18117,18119,18121,18123,18125,18127,18129,18131,18133,18135,18137,18139,18141,18143,18145,18147,18149,18151,18153,18155,18157,18159,18161,18163,18165,18167,18169,18171,18173,18175,18177,18179,18181,18183};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Tennessee":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Tennessee")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {47001,47003,47005,47007,47009,47011,47013,47015,47017,47019,47021,47023,47025,47027,47029,47031,47033,47035,47037,47039,47041,47043,47045,47047,47049,47051,47053,47055,47057,47059,47061,47063,47065,47067,47069,47071,47073,47075,47077,47079,47081,47083,47085,47087,47089,47091,47093,47095,47097,47099,47101,47103,47105,47107,47109,47111,47113,47115,47117,47119,47121,47123,47125,47127,47129,47131,47133,47135,47137,47139,47141,47143,47145,47147,47149,47151,47153,47155,47157,47159,47161,47163,47165,47167,47169,47171,47173,47175,47177,47179,47181,47183,47185,47187,47189};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Minnesota":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Minnesota")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {27001,27003,27005,27007,27009,27011,27013,27015,27017,27019,27021,27023,27025,27027,27029,27031,27033,27035,27037,27039,27041,27043,27045,27047,27049,27051,27053,27055,27057,27059,27061,27063,27065,27067,27069,27071,27073,27075,27077,27079,27081,27083,27085,27087,27089,27091,27093,27095,27097,27099,27101,27103,27105,27107,27109,27111,27113,27115,27117,27119,27121,27123,27125,27127,27129,27131,27133,27135,27137,27139,27141,27143,27145,27147,27149,27151,27153,27155,27157,27159,27161,27163,27165,27167,27169,27171,27173};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Alabama":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Alabama")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {1001,1003,1005,1007,1009,1011,1013,1015,1017,1019,1021,1023,1025,1027,1029,1031,1033,1035,1037,1039,1041,1043,1045,1047,1049,1051,1053,1055,1057,1059,1061,1063,1065,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,1095,1097,1099,1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,1129,1131,1133};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Virginia":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Virginia")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {51001,51003,51005,51007,51009,51011,51013,51015,51017,51019,51021,51023,51025,51027,51029,51031,51033,51035,51036,51037,51041,51043,51045,51047,51049,51051,51053,51057,51059,51061,51063,51065,51067,51069,51071,51073,51075,51077,51079,51081,51083,51085,51087,51089,51091,51093,51095,51097,51099,51101,51103,51105,51107,51109,51111,51113,51115,51117,51119,51121,51125,51127,51131,51133,51135,51137,51139,51141,51143,51145,51147,51149,51153,51155,51157,51159,51161,51163,51165,51167,51169,51171,51173,51175,51177,51179,51181,51183,51185,51187,51191,51193,51195,51197,51199,51510,51515,51520,51530,51540,51550,51570,51580,51590,51595,51600,51610,51620,51630,51640,51650,51660,51670,51678,51680,51683,51685,51690,51700,51710,51720,51730,51735,51740,51750,51760,51770,51775,51790,51800,51810,51820,51830,51840};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Georgia":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Georgia")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {13001,13003,13005,13007,13009,13011,13013,13015,13017,13019,13021,13023,13025,13027,13029,13031,13033,13035,13037,13039,13043,13045,13047,13049,13051,13053,13055,13057,13059,13061,13063,13065,13067,13069,13071,13073,13075,13077,13079,13081,13083,13085,13087,13089,13091,13093,13095,13097,13099,13101,13103,13105,13107,13109,13111,13113,13115,13117,13119,13121,13123,13125,13127,13129,13131,13133,13135,13137,13139,13141,13143,13145,13147,13149,13151,13153,13155,13157,13159,13161,13163,13165,13167,13169,13171,13173,13175,13177,13179,13181,13183,13185,13187,13189,13191,13193,13195,13197,13199,13201,13205,13207,13209,13211,13213,13215,13217,13219,13221,13223,13225,13227,13229,13231,13233,13235,13237,13239,13241,13243,13245,13247,13249,13251,13253,13255,13257,13259,13261,13263,13265,13267,13269,13271,13273,13275,13277,13279,13281,13283,13285,13287,13289,13291,13293,13295,13297,13299,13301,13303,13305,13307,13309,13311,13313,13315,13317,13319,13321};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Missouri":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Missouri")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {29001,29003,29005,29007,29009,29011,29013,29015,29017,29019,29021,29023,29025,29027,29029,29031,29033,29035,29037,29039,29041,29043,29045,29047,29049,29051,29053,29055,29057,29059,29061,29063,29065,29067,29069,29071,29073,29075,29077,29079,29081,29083,29085,29087,29089,29091,29093,29095,29097,29099,29101,29103,29105,29107,29109,29111,29113,29115,29117,29119,29121,29123,29125,29127,29129,29131,29133,29135,29137,29139,29141,29143,29145,29147,29149,29151,29153,29155,29157,29159,29161,29163,29165,29167,29169,29171,29173,29175,29177,29179,29181,29183,29185,29186,29187,29189,29195,29197,29199,29201,29203,29205,29207,29209,29211,29213,29215,29217,29219,29221,29223,29225,29227,29229,29510};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Michigan":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Michigan")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {26001,26003,26005,26007,26009,26011,26013,26015,26017,26019,26021,26023,26025,26027,26029,26031,26033,26035,26037,26039,26041,26043,26045,26047,26049,26051,26053,26055,26057,26059,26061,26063,26065,26067,26069,26071,26073,26075,26077,26079,26081,26083,26085,26087,26089,26091,26093,26095,26097,26099,26101,26103,26105,26107,26109,26111,26113,26115,26117,26119,26121,26123,26125,26127,26129,26131,26133,26135,26137,26139,26141,26143,26145,26147,26149,26151,26153,26155,26157,26159,26161,26163,26165};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Arkansas":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Arkansas")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {5001,5003,5005,5007,5009,5011,5013,5015,5017,5019,5021,5023,5025,5027,5029,5031,5033,5035,5037,5039,5041,5043,5045,5047,5049,5051,5053,5055,5057,5059,5061,5063,5065,5067,5069,5071,5073,5075,5077,5079,5081,5083,5085,5087,5089,5091,5093,5095,5097,5099,5101,5103,5105,5107,5109,5111,5113,5115,5117,5119,5121,5123,5125,5127,5129,5131,5133,5135,5137,5139,5141,5143,5145,5147,5149};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Massachusetts":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Massachusetts")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {25001,25003,25005,25007,25009,25011,25013,25015,25017,25019,25021,25023,25025,25027};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Wisconsin":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Wisconsin")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {55001,55003,55005,55007,55009,55011,55013,55015,55017,55019,55021,55023,55025,55027,55029,55031,55033,55035,55037,55039,55041,55043,55045,55047,55049,55051,55053,55055,55057,55059,55061,55063,55065,55067,55069,55071,55073,55075,55077,55078,55079,55081,55083,55085,55087,55089,55091,55093,55095,55097,55099,55101,55103,55105,55107,55109,55111,55113,55115,55117,55119,55121,55123,55125,55127,55129,55131,55133,55135,55137,55139,55141};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "South Carolina":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("South Carolina")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {45001,45003,45005,45007,45009,45011,45013,45015,45017,45019,45021,45023,45025,45027,45029,45031,45033,45035,45037,45039,45041,45043,45045,45047,45049,45051,45053,45055,45057,45059,45061,45063,45065,45067,45069,45071,45073,45075,45077,45079,45081,45083,45085,45087,45089,45091};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Utah":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Utah")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {49001,49003,49005,49007,49009,49011,49013,49015,49017,49019,49021,49023,49025,49027,49029,49031,49033,49035,49037,49039,49041,49043,49045,49047,49049,49051,49053,49055,49057};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "California":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("California")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {6001,6003,6005,6007,6009,6011,6013,6015,6017,6019,6021,6023,6025,6027,6029,6031,6033,6035,6037,6039,6041,6043,6045,6047,6049,6051,6053,6055,6057,6059,6061,6063,6065,6067,6069,6071,6073,6075,6077,6079,6081,6083,6085,6087,6089,6091,6093,6095,6097,6099,6101,6103,6105,6107,6109,6111,6113,6115};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Ohio":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Ohio")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {39001,39003,39005,39007,39009,39011,39013,39015,39017,39019,39021,39023,39025,39027,39029,39031,39033,39035,39037,39039,39041,39043,39045,39047,39049,39051,39053,39055,39057,39059,39061,39063,39065,39067,39069,39071,39073,39075,39077,39079,39081,39083,39085,39087,39089,39091,39093,39095,39097,39099,39101,39103,39105,39107,39109,39111,39113,39115,39117,39119,39121,39123,39125,39127,39129,39131,39133,39135,39137,39139,39141,39143,39145,39147,39149,39151,39153,39155,39157,39159,39161,39163,39165,39167,39169,39171,39173,39175};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Illinois":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Illinois")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}											
				int[] cityArray = new int[] {17001,17003,17005,17007,17009,17011,17013,17015,17017,17019,17021,17023,17025,17027,17029,17031,17033,17035,17037,17039,17041,17043,17045,17047,17049,17051,17053,17055,17057,17059,17061,17063,17065,17067,17069,17071,17073,17075,17077,17079,17081,17083,17085,17087,17089,17091,17093,17095,17097,17099,17101,17103,17105,17107,17109,17111,17113,17115,17117,17119,17121,17123,17125,17127,17129,17131,17133,17135,17137,17139,17141,17143,17145,17147,17149,17151,17153,17155,17157,17159,17161,17163,17165,17167,17169,17171,17173,17175,17177,17179,17181,17183,17185,17187,17189,17191,17193,17195,17197,17199,17201,17203};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Texas":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Texas")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {48001,48003,48005,48007,48009,48011,48013,48015,48017,48019,48021,48023,48025,48027,48029,48031,48033,48035,48037,48039,48041,48043,48045,48047,48049,48051,48053,48055,48057,48059,48061,48063,48065,48067,48069,48071,48073,48075,48077,48079,48081,48083,48085,48087,48089,48091,48093,48095,48097,48099,48101,48103,48105,48107,48109,48111,48113,48115,48117,48119,48121,48123,48125,48127,48129,48131,48133,48135,48137,48139,48141,48143,48145,48147,48149,48151,48153,48155,48157,48159,48161,48163,48165,48167,48169,48171,48173,48175,48177,48179,48181,48183,48185,48187,48189,48191,48193,48195,48197,48199,48201,48203,48205,48207,48209,48211,48213,48215,48217,48219,48221,48223,48225,48227,48229,48231,48233,48235,48237,48239,48241,48243,48245,48247,48249,48251,48253,48255,48257,48259,48261,48263,48265,48267,48269,48271,48273,48275,48277,48279,48281,48283,48285,48287,48289,48291,48293,48295,48297,48299,48301,48303,48305,48307,48309,48311,48313,48315,48317,48319,48321,48323,48325,48327,48329,48331,48333,48335,48337,48339,48341,48343,48345,48347,48349,48351,48353,48355,48357,48359,48361,48363,48365,48367,48369,48371,48373,48375,48377,48379,48381,48383,48385,48387,48389,48391,48393,48395,48397,48399,48401,48403,48405,48407,48409,48411,48413,48415,48417,48419,48421,48423,48425,48427,48429,48431,48433,48435,48437,48439,48441,48443,48445,48447,48449,48451,48453,48455,48457,48459,48461,48463,48465,48467,48469,48471,48473,48475,48477,48479,48481,48483,48485,48487,48489,48491,48493,48495,48497,48499,48501,48503,48505,48507};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Arizona":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Arizona")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {4001,4003,4005,4007,4009,4011,4012,4013,4015,4017,4019,4021,4023,4025,4027};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "New Jersey":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("New Jersey")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {34001,34003,34005,34007,34009,34011,34013,34015,34017,34019,34021,34023,34025,34027,34029,34031,34033,34035,34037,34039,34041};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Florida":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Florida")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {12001,12003,12005,12007,12009,12011,12013,12015,12017,12019,12021,12023,12027,12029,12031,12033,12035,12037,12039,12041,12043,12045,12047,12049,12051,12053,12055,12057,12059,12061,12063,12065,12067,12069,12071,12073,12075,12077,12079,12081,12083,12085,12086,12087,12089,12091,12093,12095,12097,12099,12101,12103,12105,12107,12109,12111,12113,12115,12117,12119,12121,12123,12125,12127,12129,12131,12133};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("AverageHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
		}
	}
	
	public void totalPricesSwitchBlock(String[][] TotalSalesMap,List<Document> choroPlethList,int k) {
		switch(TotalSalesMap[k][0]) {
			case "West Virginia":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("West Virginia")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {54001,54003,54005,54007,54009,54011,54013,54015,54017,54019,54021,54023,54025,54027,54029,54031,54033,54035,54037,54039,54041,54043,54045,54047,54049,54051,54053,54055,54057,54059,54061,54063,54065,54067,54069,54071,54073,54075,54077,54079,54081,54083,54085,54087,54089,54091,54093,54095,54097,54099,54101,54103,54105,54107,54109};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Pennsylvania":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Pennsylvania")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {42001,42003,42005,42007,42009,42011,42013,42015,42017,42019,42021,42023,42025,42027,42029,42031,42033,42035,42037,42039,42041,42043,42045,42047,42049,42051,42053,42055,42057,42059,42061,42063,42065,42067,42069,42071,42073,42075,42077,42079,42081,42083,42085,42087,42089,42091,42093,42095,42097,42099,42101,42103,42105,42107,42109,42111,42113,42115,42117,42119,42121,42123,42125,42127,42129,42131,42133};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Alaska":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Alaska")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {2013,2016,2020,2050,2060,2068,2070,2090,2100,2105,2110,2122,2130,2150,2164,2170,2180,2185,2188,2195,2198,2220,2230,2240,2261,2270,2275,2282,2290};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Hawaii":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Hawaii")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {15001,15003,15005,15007,15009};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Indiana":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Indiana")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {18001,18003,18005,18007,18009,18011,18013,18015,18017,18019,18021,18023,18025,18027,18029,18031,18033,18035,18037,18039,18041,18043,18045,18047,18049,18051,18053,18055,18057,18059,18061,18063,18065,18067,18069,18071,18073,18075,18077,18079,18081,18083,18085,18087,18089,18091,18093,18095,18097,18099,18101,18103,18105,18107,18109,18111,18113,18115,18117,18119,18121,18123,18125,18127,18129,18131,18133,18135,18137,18139,18141,18143,18145,18147,18149,18151,18153,18155,18157,18159,18161,18163,18165,18167,18169,18171,18173,18175,18177,18179,18181,18183};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Tennessee":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Tennessee")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {47001,47003,47005,47007,47009,47011,47013,47015,47017,47019,47021,47023,47025,47027,47029,47031,47033,47035,47037,47039,47041,47043,47045,47047,47049,47051,47053,47055,47057,47059,47061,47063,47065,47067,47069,47071,47073,47075,47077,47079,47081,47083,47085,47087,47089,47091,47093,47095,47097,47099,47101,47103,47105,47107,47109,47111,47113,47115,47117,47119,47121,47123,47125,47127,47129,47131,47133,47135,47137,47139,47141,47143,47145,47147,47149,47151,47153,47155,47157,47159,47161,47163,47165,47167,47169,47171,47173,47175,47177,47179,47181,47183,47185,47187,47189};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Minnesota":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Minnesota")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {27001,27003,27005,27007,27009,27011,27013,27015,27017,27019,27021,27023,27025,27027,27029,27031,27033,27035,27037,27039,27041,27043,27045,27047,27049,27051,27053,27055,27057,27059,27061,27063,27065,27067,27069,27071,27073,27075,27077,27079,27081,27083,27085,27087,27089,27091,27093,27095,27097,27099,27101,27103,27105,27107,27109,27111,27113,27115,27117,27119,27121,27123,27125,27127,27129,27131,27133,27135,27137,27139,27141,27143,27145,27147,27149,27151,27153,27155,27157,27159,27161,27163,27165,27167,27169,27171,27173};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Alabama":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Alabama")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {1001,1003,1005,1007,1009,1011,1013,1015,1017,1019,1021,1023,1025,1027,1029,1031,1033,1035,1037,1039,1041,1043,1045,1047,1049,1051,1053,1055,1057,1059,1061,1063,1065,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,1095,1097,1099,1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,1129,1131,1133};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Virginia":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Virginia")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {51001,51003,51005,51007,51009,51011,51013,51015,51017,51019,51021,51023,51025,51027,51029,51031,51033,51035,51036,51037,51041,51043,51045,51047,51049,51051,51053,51057,51059,51061,51063,51065,51067,51069,51071,51073,51075,51077,51079,51081,51083,51085,51087,51089,51091,51093,51095,51097,51099,51101,51103,51105,51107,51109,51111,51113,51115,51117,51119,51121,51125,51127,51131,51133,51135,51137,51139,51141,51143,51145,51147,51149,51153,51155,51157,51159,51161,51163,51165,51167,51169,51171,51173,51175,51177,51179,51181,51183,51185,51187,51191,51193,51195,51197,51199,51510,51515,51520,51530,51540,51550,51570,51580,51590,51595,51600,51610,51620,51630,51640,51650,51660,51670,51678,51680,51683,51685,51690,51700,51710,51720,51730,51735,51740,51750,51760,51770,51775,51790,51800,51810,51820,51830,51840};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Georgia":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Georgia")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {13001,13003,13005,13007,13009,13011,13013,13015,13017,13019,13021,13023,13025,13027,13029,13031,13033,13035,13037,13039,13043,13045,13047,13049,13051,13053,13055,13057,13059,13061,13063,13065,13067,13069,13071,13073,13075,13077,13079,13081,13083,13085,13087,13089,13091,13093,13095,13097,13099,13101,13103,13105,13107,13109,13111,13113,13115,13117,13119,13121,13123,13125,13127,13129,13131,13133,13135,13137,13139,13141,13143,13145,13147,13149,13151,13153,13155,13157,13159,13161,13163,13165,13167,13169,13171,13173,13175,13177,13179,13181,13183,13185,13187,13189,13191,13193,13195,13197,13199,13201,13205,13207,13209,13211,13213,13215,13217,13219,13221,13223,13225,13227,13229,13231,13233,13235,13237,13239,13241,13243,13245,13247,13249,13251,13253,13255,13257,13259,13261,13263,13265,13267,13269,13271,13273,13275,13277,13279,13281,13283,13285,13287,13289,13291,13293,13295,13297,13299,13301,13303,13305,13307,13309,13311,13313,13315,13317,13319,13321};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Missouri":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Missouri")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {29001,29003,29005,29007,29009,29011,29013,29015,29017,29019,29021,29023,29025,29027,29029,29031,29033,29035,29037,29039,29041,29043,29045,29047,29049,29051,29053,29055,29057,29059,29061,29063,29065,29067,29069,29071,29073,29075,29077,29079,29081,29083,29085,29087,29089,29091,29093,29095,29097,29099,29101,29103,29105,29107,29109,29111,29113,29115,29117,29119,29121,29123,29125,29127,29129,29131,29133,29135,29137,29139,29141,29143,29145,29147,29149,29151,29153,29155,29157,29159,29161,29163,29165,29167,29169,29171,29173,29175,29177,29179,29181,29183,29185,29186,29187,29189,29195,29197,29199,29201,29203,29205,29207,29209,29211,29213,29215,29217,29219,29221,29223,29225,29227,29229,29510};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Michigan":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Michigan")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {26001,26003,26005,26007,26009,26011,26013,26015,26017,26019,26021,26023,26025,26027,26029,26031,26033,26035,26037,26039,26041,26043,26045,26047,26049,26051,26053,26055,26057,26059,26061,26063,26065,26067,26069,26071,26073,26075,26077,26079,26081,26083,26085,26087,26089,26091,26093,26095,26097,26099,26101,26103,26105,26107,26109,26111,26113,26115,26117,26119,26121,26123,26125,26127,26129,26131,26133,26135,26137,26139,26141,26143,26145,26147,26149,26151,26153,26155,26157,26159,26161,26163,26165};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Arkansas":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Arkansas")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {5001,5003,5005,5007,5009,5011,5013,5015,5017,5019,5021,5023,5025,5027,5029,5031,5033,5035,5037,5039,5041,5043,5045,5047,5049,5051,5053,5055,5057,5059,5061,5063,5065,5067,5069,5071,5073,5075,5077,5079,5081,5083,5085,5087,5089,5091,5093,5095,5097,5099,5101,5103,5105,5107,5109,5111,5113,5115,5117,5119,5121,5123,5125,5127,5129,5131,5133,5135,5137,5139,5141,5143,5145,5147,5149};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Massachusetts":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Massachusetts")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {25001,25003,25005,25007,25009,25011,25013,25015,25017,25019,25021,25023,25025,25027};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Wisconsin":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Wisconsin")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {55001,55003,55005,55007,55009,55011,55013,55015,55017,55019,55021,55023,55025,55027,55029,55031,55033,55035,55037,55039,55041,55043,55045,55047,55049,55051,55053,55055,55057,55059,55061,55063,55065,55067,55069,55071,55073,55075,55077,55078,55079,55081,55083,55085,55087,55089,55091,55093,55095,55097,55099,55101,55103,55105,55107,55109,55111,55113,55115,55117,55119,55121,55123,55125,55127,55129,55131,55133,55135,55137,55139,55141};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "South Carolina":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("South Carolina")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {45001,45003,45005,45007,45009,45011,45013,45015,45017,45019,45021,45023,45025,45027,45029,45031,45033,45035,45037,45039,45041,45043,45045,45047,45049,45051,45053,45055,45057,45059,45061,45063,45065,45067,45069,45071,45073,45075,45077,45079,45081,45083,45085,45087,45089,45091};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Utah":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Utah")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {49001,49003,49005,49007,49009,49011,49013,49015,49017,49019,49021,49023,49025,49027,49029,49031,49033,49035,49037,49039,49041,49043,49045,49047,49049,49051,49053,49055,49057};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "California":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("California")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {6001,6003,6005,6007,6009,6011,6013,6015,6017,6019,6021,6023,6025,6027,6029,6031,6033,6035,6037,6039,6041,6043,6045,6047,6049,6051,6053,6055,6057,6059,6061,6063,6065,6067,6069,6071,6073,6075,6077,6079,6081,6083,6085,6087,6089,6091,6093,6095,6097,6099,6101,6103,6105,6107,6109,6111,6113,6115};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Ohio":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Ohio")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {39001,39003,39005,39007,39009,39011,39013,39015,39017,39019,39021,39023,39025,39027,39029,39031,39033,39035,39037,39039,39041,39043,39045,39047,39049,39051,39053,39055,39057,39059,39061,39063,39065,39067,39069,39071,39073,39075,39077,39079,39081,39083,39085,39087,39089,39091,39093,39095,39097,39099,39101,39103,39105,39107,39109,39111,39113,39115,39117,39119,39121,39123,39125,39127,39129,39131,39133,39135,39137,39139,39141,39143,39145,39147,39149,39151,39153,39155,39157,39159,39161,39163,39165,39167,39169,39171,39173,39175};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Illinois":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Illinois")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}											
				int[] cityArray = new int[] {17001,17003,17005,17007,17009,17011,17013,17015,17017,17019,17021,17023,17025,17027,17029,17031,17033,17035,17037,17039,17041,17043,17045,17047,17049,17051,17053,17055,17057,17059,17061,17063,17065,17067,17069,17071,17073,17075,17077,17079,17081,17083,17085,17087,17089,17091,17093,17095,17097,17099,17101,17103,17105,17107,17109,17111,17113,17115,17117,17119,17121,17123,17125,17127,17129,17131,17133,17135,17137,17139,17141,17143,17145,17147,17149,17151,17153,17155,17157,17159,17161,17163,17165,17167,17169,17171,17173,17175,17177,17179,17181,17183,17185,17187,17189,17191,17193,17195,17197,17199,17201,17203};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Texas":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Texas")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {48001,48003,48005,48007,48009,48011,48013,48015,48017,48019,48021,48023,48025,48027,48029,48031,48033,48035,48037,48039,48041,48043,48045,48047,48049,48051,48053,48055,48057,48059,48061,48063,48065,48067,48069,48071,48073,48075,48077,48079,48081,48083,48085,48087,48089,48091,48093,48095,48097,48099,48101,48103,48105,48107,48109,48111,48113,48115,48117,48119,48121,48123,48125,48127,48129,48131,48133,48135,48137,48139,48141,48143,48145,48147,48149,48151,48153,48155,48157,48159,48161,48163,48165,48167,48169,48171,48173,48175,48177,48179,48181,48183,48185,48187,48189,48191,48193,48195,48197,48199,48201,48203,48205,48207,48209,48211,48213,48215,48217,48219,48221,48223,48225,48227,48229,48231,48233,48235,48237,48239,48241,48243,48245,48247,48249,48251,48253,48255,48257,48259,48261,48263,48265,48267,48269,48271,48273,48275,48277,48279,48281,48283,48285,48287,48289,48291,48293,48295,48297,48299,48301,48303,48305,48307,48309,48311,48313,48315,48317,48319,48321,48323,48325,48327,48329,48331,48333,48335,48337,48339,48341,48343,48345,48347,48349,48351,48353,48355,48357,48359,48361,48363,48365,48367,48369,48371,48373,48375,48377,48379,48381,48383,48385,48387,48389,48391,48393,48395,48397,48399,48401,48403,48405,48407,48409,48411,48413,48415,48417,48419,48421,48423,48425,48427,48429,48431,48433,48435,48437,48439,48441,48443,48445,48447,48449,48451,48453,48455,48457,48459,48461,48463,48465,48467,48469,48471,48473,48475,48477,48479,48481,48483,48485,48487,48489,48491,48493,48495,48497,48499,48501,48503,48505,48507};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Arizona":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Arizona")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {4001,4003,4005,4007,4009,4011,4012,4013,4015,4017,4019,4021,4023,4025,4027};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "New Jersey":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("New Jersey")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {34001,34003,34005,34007,34009,34011,34013,34015,34017,34019,34021,34023,34025,34027,34029,34031,34033,34035,34037,34039,34041};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
			case "Florida":{
				double totalSales=0;
				for(int j=0;j<TotalSalesMap.length;j++) {
					if(TotalSalesMap[j][0]!=null) {
						if(TotalSalesMap[j][0].equals("Florida")) {
							totalSales = Double.parseDouble(TotalSalesMap[j][1]);
						}
					}
				}
				int[] cityArray = new int[] {12001,12003,12005,12007,12009,12011,12013,12015,12017,12019,12021,12023,12027,12029,12031,12033,12035,12037,12039,12041,12043,12045,12047,12049,12051,12053,12055,12057,12059,12061,12063,12065,12067,12069,12071,12073,12075,12077,12079,12081,12083,12085,12086,12087,12089,12091,12093,12095,12097,12099,12101,12103,12105,12107,12109,12111,12113,12115,12117,12119,12121,12123,12125,12127,12129,12131,12133};
				for(int i=0;i<cityArray.length;i++) {
					Document cityDoc = new Document("_id", TotalSalesMap[k][0])
			                .append("TotalHotelPrice", totalSales)
			                .append("id", cityArray[i]);
					choroPlethList.add(cityDoc);
				}
				break;
			}
		}
	}
	
}
