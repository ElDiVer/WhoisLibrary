package org.whoislibrary;

import java.util.Date;

/**
 * 
 * This class contains all the informations about the Whois query result.
 * 
 * @author Ivan Gualandri
 * @version 1.0
 */
public class WhoisEntry {
	
	public String domainName;
	public Date expirationDate;
	private boolean isRedirected;
	private String redirectURL;	

	public WhoisEntry(String domainName, Date expDate) {
		// TODO Auto-generated constructor stub
		this.domainName = domainName;
		this.expirationDate = expDate;
	}

	public String getDomainName() {
		return domainName;
	}

	public boolean isRedirected() {
		return isRedirected;
	}
	
	//public set
			
}