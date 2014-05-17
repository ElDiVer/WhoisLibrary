package org.whoislibrary;

import java.util.ResourceBundle;
import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;
import org.whoislibrary.parser.Template;

/**
 * 
 * Factory class that create the correct istance of the Whois class given the fullclassname.
 * The whois.properties file contains the associations between TLDs and Whois Class. 
 * 
 * @author Ivan Gualandri
 */

public final class WhoisFactory {
	private static final WhoisLogger log = WhoisLoggerFactory.getLogger(WhoisFactory.class);
	private static ResourceBundle rb = ResourceBundle.getBundle("domain_map");

	public static Whois getWhois(String query) {		
		if (rb == null) {
			log.error("WhoisFactory.Whois Error: No Loaded Parsers");
			return null;
		}

		String classname = getParserClass(query);

		try {
			if (classname != null) {
				Template template = (Template)Class.forName(classname).newInstance();
				return new Whois(template);
			}
		} catch (InstantiationException e) {
			log.error(e.toString());
		} catch (IllegalAccessException e) {
			log.error(e.toString());
		} catch (ClassNotFoundException e) {
			log.error(e.toString());
		}
		return null;
	}

	/** 
	  * This method find the tld from a domain query, and return the classname 
	  * needed for the whoisRequest.
	  * @param query The domain query 
	  * @return the classname needed for that TLD.
	  */
	private static String getParserClass(String query) {
		String domain = query.substring(query.lastIndexOf(".")+1);
		if(rb.containsKey(domain)) {
			String value = rb.getString(domain);
			log.debug("Key: " + domain + " Value: " + value);
			return value;
		}
		return null;
	}
}
