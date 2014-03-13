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
	
	private final String domainName;
	private Date expirationDate;
	private boolean isRedirected;
	private String redirectURL;	

	public WhoisEntry(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainName() {
		return domainName;
	}

	public boolean isRedirected() {
		return isRedirected;
	}

	public void setRedirected(boolean redirected) {
		isRedirected = redirected;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date date) {
		expirationDate = date;
	}
	
	public String getURLRedirect() {
		return redirectURL;
	}

	public void setURLRedirect(String redirect) {
		redirectURL = redirect;
	}
	
	public String toString(){
		return domainName + " expires on " + expirationDate;
	}
			
}
