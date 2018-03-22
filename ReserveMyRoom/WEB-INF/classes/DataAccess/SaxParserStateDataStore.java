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

public class SaxParserStateDataStore extends DefaultHandler {

	Cities city;
	public HashMap<String, Cities> cityMap;
	
	public SaxParserStateDataStore() {
		city = new Cities();
		cityMap = new HashMap<String, Cities>();
	}
	
	@Override
	public void startElement(String str1, String str2, String elementName,Attributes attributes) throws SAXException {
		if(elementName.equalsIgnoreCase("state")) {
			city = new Cities();
			city.setStateName(attributes.getValue("name"));
			city.setStateID(attributes.getValue("code"));
			cityMap.put(city.getStateID(), city);
		}
	}	
}