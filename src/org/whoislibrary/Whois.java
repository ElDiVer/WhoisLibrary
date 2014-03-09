package org.whoislibrary;

/**
 * 
 * Whois.java
 * Interface class that has the following methods.
 * 
 * @author Ivan Gualandri
 */
public interface Whois {
	
	/** Returns the whoisEntry related to the query if found. */
	public WhoisEntry executeQuery(String query);

}
