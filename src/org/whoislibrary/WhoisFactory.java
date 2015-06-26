package org.whoislibrary;

import java.util.ResourceBundle;
import org.whoislibrary.parser.Template;

/**
 * 
 * Factory class that create the correct istance of the Whois class given the fullclassname.
 * The whois.properties file contains the associations between TLDs and Whois Class. 
 * 
 * @author Ivan Gualandri
 */

public final class WhoisFactory {
	private static ResourceBundle rb = ResourceBundle.getBundle("domain_map");

	/*
	 * Create a Whois object with the right template for the provided query.
	 */
	public static Whois getWhois(String query) {		
		if (rb == null) {
			log.error("Error: No Loaded Parsers");
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
	  * This method find the Template from a domain map (the property file),
	  * and return the classname needed for the WhoisRequest.
	  * @param query The domain query 
	  * @return the classname needed for that Template.
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
