package org.whoislibrary;

import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;



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
