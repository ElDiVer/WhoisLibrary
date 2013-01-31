package org.whoislibrary;

import java.net.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WhoisQuery {
	
	/*private String URLPrefix = "http://reports.internic.net/cgi/whois?whois_nic=";	
	private String URLSuffix = "&type=domain";*/
	private String URLPrefix = "http://show-ip.net/whois?query=";	
	private String URLSuffix = "&output=normal";
	private String searchQuery = null;

	public WhoisQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WhoisQuery(String search) {
		super();
		// TODO Auto-generated constructor stub
		this.searchQuery = search;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public WhoisQuery executeQuery(){
		String url = URLPrefix + searchQuery + URLSuffix;
		try {
			System.out.println("SearchQuery is: " + url); 
			URL searchURL = new URL(url);			
			InputStream searchStream = searchURL.openStream();			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setIgnoringElementContentWhitespace(true);
			dbf.setNamespaceAware(false);
			Document doc = dbf.newDocumentBuilder().parse(searchStream);
			Element root = doc.getDocumentElement();
			System.out.println("++++");
			NodeList nodes = root.getElementsByTagName("pre");
			System.out.println("Size of List: " + nodes.getLength());
			//BufferedReader input = new BufferedReader(new InputStreamReader(searchURL.openStream()));
			String str;
			//System.out.println(input.toString());
		} catch (MalformedURLException e) { 
			e.printStackTrace();			
			System.out.println("Malformed URL EXCEPTION");
		} catch (IOException e) { 			
			e.printStackTrace();
			System.out.println("IOException");
		} catch (ParserConfigurationException e){
			e.printStackTrace();
			System.out.println("ParserException");
		} catch (SAXException e){
			e.printStackTrace();
			System.out.println("SAXException");
		}
		
		return null;
	}

}
