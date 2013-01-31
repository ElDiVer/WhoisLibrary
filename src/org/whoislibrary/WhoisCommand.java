package org.whoislibrary;


import java.util.ResourceBundle;

import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;

public class WhoisCommand {	
	private static WhoisLogger log = WhoisLoggerFactory.getLogger();
	private String searchQuery = null;
	private ResourceBundle rb;

	public WhoisCommand() {
		super();		
	}
	
	public WhoisCommand(String search) {
		super(); 
		this.searchQuery = search;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	 /** 
	  * This method find the tld from a domain query, and return the classname needed for the whoisRequest.
	  * @param query The domain query 
	  * @return the classname needed for that TLD.
	  */
	private String getTLDClass(String query){		
		String tld = query.substring(query.lastIndexOf(".")+1);
		if(rb.containsKey(tld)) {
			String value = rb.getString(tld);
			log.debug("Key: " + tld + " Value: " + value);
			return value;
		}
		return null;
	}
	
	public WhoisEntry executeQuery(){
		//String url = URLPrefix + searchQuery + URLSuffix;		
		rb = ResourceBundle.getBundle("whois");
		if(rb == null) 
			log.error("Error");
		else
			log.info("Ok");
//		Enumeration <String> keys = rb.getKeys();		
//		while(keys.hasMoreElements()){
//			String key = keys.nextElement();
//			String value = rb.getString(key);
//			log.debug(key + " : " + value);			
//		}
//		try {
//			System.out.println("Class: " + Class.forName("org.whoislibrary.WhoisEntry").toString());
//		} catch (ClassNotFoundException e) {
//			System.out.println("Class NOT FOUND");
//			e.printStackTrace();
//		}
		String fullclassname = getTLDClass(getSearchQuery());
		Whois myQuery = WhoisFactory.getWhois(fullclassname);
		if (myQuery != null) {
			WhoisEntry myEntry = myQuery.executeQuery(getSearchQuery());
			if(myEntry!=null) {
				System.out.println(myEntry.domainName + " expires on " + myEntry.expirationDate);
				return myEntry;	
			}
		}		
		return null;
	}

}
