package org.whoislibrary;

import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;


/**
 * 
 * Factory class that create the correct istance of the Whois class given the fullclassname.
 * The whois.properties file contains the associations between TLDs and Whois Class. 
 * 
 * @author Ivan Gualandri
 */
public class WhoisFactory {
	
	private static WhoisLogger log = WhoisLoggerFactory.getLogger(WhoisFactory.class);
	public static Whois getWhois(String fullclassname){		
		try {			
			return (Whois)Class.forName(fullclassname).newInstance();
		} catch (InstantiationException e) {			
			log.error(e.toString());
		} catch (IllegalAccessException e) {
			log.error(e.toString());
		} catch (ClassNotFoundException e) {
			log.error(e.toString());
		}
		return null;
	}

}
