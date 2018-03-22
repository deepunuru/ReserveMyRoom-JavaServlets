package DataAccess;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.Serializable;

import JavaBeans.*;

public class SaxParserDataStore extends DefaultHandler {
	
	Hotels hotel;
	Rooms room;
	int i=1;
	int roomCounter=1;
	int hotelImgCounter=1;
	
	public HashMap<String, Hotels> hotelMap;
	public HashMap<String, Rooms> rooms;
	public HashMap<String, String> roomImages;
	public HashMap<String, String> hotelImages;
	
	
	String currentElement="";
	String elementValueRead;
	
	public SaxParserDataStore() {
		hotelMap = new HashMap<String, Hotels>();
		hotel = new Hotels();
		room = new Rooms();
		rooms = new HashMap<String, Rooms>();
		roomImages = new HashMap<String, String>();
		hotelImages = new HashMap<String, String>();
	}
	
	@Override
	public void startElement(String str1, String str2, String elementName,Attributes attributes) throws SAXException {
		if(elementName.equalsIgnoreCase("hotel")) {
			hotel = new Hotels();
			hotel.setID(attributes.getValue("id"));
			hotel.setRetailer(attributes.getValue("retailer"));
		}
		if(elementName.equalsIgnoreCase("room")) {
			i=1;
			room = new Rooms();
			roomImages = new HashMap<String, String>();
			room.setRoomName(attributes.getValue("name"));
			room.setRoomCount(Integer.parseInt(attributes.getValue("roomcount")));
			room.setRoomPrice(Double.parseDouble(attributes.getValue("roomprice")));
		}
		if(elementName.equalsIgnoreCase("hotelimages")) {
			hotelImgCounter=1;
			hotelImages = new HashMap<String, String>();
		}
	}
	
	@Override
	public void endElement(String str1,String str2,String element) throws SAXException {
		if(element.equals("hotel")) {
			hotelMap.put(hotel.getID(), hotel);
			return;
		}
		if(element.equalsIgnoreCase("roomimage")) {
			roomImages.put("roomImage-" + i, elementValueRead);
			i++;
			return;
		}
		if(element.equalsIgnoreCase("room")) {
			room.setRoomImages(roomImages);
			rooms.put("room-" + roomCounter, room);
			roomCounter++;
			return;
		}
		if(element.equalsIgnoreCase("rooms")) {
			hotel.setRooms(rooms);
			rooms = new HashMap<String, Rooms>();
			roomCounter=1;
			return;
		}
		
		if(element.equalsIgnoreCase("hotelimage")) {
			hotelImages.put("hotelImage-" + hotelImgCounter, elementValueRead);
			hotelImgCounter++;
			return;
		}
		if(element.equalsIgnoreCase("hotelimages")) {
			hotel.setHotelImages(hotelImages);
			return;
		}
		
		if(element.equalsIgnoreCase("name")) {
			hotel.setName(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("wifi")) {
			if(elementValueRead.equalsIgnoreCase("Yes")) {
				hotel.setWifi(true);
			}
			else {
				hotel.setWifi(false);
			}
			return;
		}
		if(element.equalsIgnoreCase("restaurant")) {
			if(elementValueRead.equalsIgnoreCase("Yes")) {
				hotel.setRestaurant(true);
			}
			else {
				hotel.setRestaurant(false);
			}
			return;
		}
		if(element.equalsIgnoreCase("onSale")) {
			if(elementValueRead.equalsIgnoreCase("Yes")) {
				hotel.setOnSale(true);
			}
			else {
				hotel.setOnSale(false);
			}
		}
		if(element.equalsIgnoreCase("rebate")) {
			if(elementValueRead.equalsIgnoreCase("Yes")) {
				hotel.setRebate(true);
			}
			else {
				hotel.setRebate(false);
			}
		}
		if(element.equalsIgnoreCase("pincode")) {
			hotel.setZipCode(Integer.parseInt(elementValueRead));
			return;
		}
		if(element.equalsIgnoreCase("address1")) {
			hotel.setAddress1(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("address2")) {
			hotel.setAddress2(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("city")) {
			hotel.setCity(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("state")) {
			hotel.setState(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("phonenumber")) {
			hotel.setPhoneNumber(elementValueRead);
			return;
		}
	}
	
	@Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }
}