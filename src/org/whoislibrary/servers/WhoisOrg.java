package org.whoislibrary.servers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.whoislibrary.Whois;
import org.whoislibrary.WhoisAbstract;
import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;


public class WhoisOrg extends WhoisAbstract implements Whois {
	
	private static final WhoisLogger log = WhoisLoggerFactory.getLogger();

	public WhoisOrg(){
		super("whois.publicinterestregistry.net");
	}

	@Override
	protected void parseLine(String queryLine, int i) {
		Date expDate = null;

    	if(queryLine.startsWith("[")){
			String remoteTLD = queryLine.substring(1, queryLine.length()-1);
			log.debug(remoteTLD);					
		}

		if(queryLine.contains("Registry Expiry Date:")){					
			String expString = queryLine.replace("Registry Expiry Date:", "").trim();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			try {
				log.info("Exp: " + queryLine.replace("Registry Expiry Date:", "").trim());						
				expDate = df.parse(expString);
				whoisEntry.setExpirationDate(expDate);
			} catch (ParseException e) {						 
				e.printStackTrace();
			}					
		}

		//if(queryLine.contains("expires on")){
		//	String expString = queryLine.replace("Record expires on ", "").replace(".", "");									
		//}
	}
}
