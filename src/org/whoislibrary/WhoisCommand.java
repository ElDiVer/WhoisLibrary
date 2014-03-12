package org.whoislibrary;


import java.util.ResourceBundle;

import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;

/**
 * 
 * This class is used to issue the whois command.
 * 
 * @author MyName
 * @since mm-dd-yyyy
 */
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
	
	/** 
	  * This method executes the query and return a WhoisEntry object. 
	  * @return the WhoisEntry object if the domain is found.
	  */
	public WhoisEntry executeQuery(){
		//String url = URLPrefix + searchQuery + URLSuffix;		
		rb = ResourceBundle.getBundle("whois");
		
		if(rb == null) {
			log.error("Error");
		}
		else {
			log.info("Ok");
		}

		String fullclassname = getTLDClass(getSearchQuery());
		Whois myQuery = WhoisFactory.getWhois(fullclassname);
		
		if (myQuery != null) {
			WhoisEntry myEntry = myQuery.executeQuery(getSearchQuery());
			if(myEntry!=null) {
				System.out.println(myEntry.getDomainName()
						+ " expires on " + myEntry.getExpirationDate());
				return myEntry;	
			}
		}		
		return null;
	}

}
