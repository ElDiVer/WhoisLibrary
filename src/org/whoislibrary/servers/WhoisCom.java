package org.whoislibrary.servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.whoislibrary.Whois;
import org.whoislibrary.WhoisAbstract;
import org.whoislibrary.WhoisEntry;
import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;

public class WhoisCom extends WhoisAbstract implements Whois{

	private static WhoisLogger log = WhoisLoggerFactory.getLogger();
	
	public WhoisCom(){
		super();
		this.setPrefix("domain");
	}
	
	public String getWhoisURL() {
		return "whois.internic.net";
	}
	
	public WhoisEntry parseResponse(BufferedReader queryResult) {
		String queryLine;
		Date expDate = null;
		int i =0;
	    try {	    	
			while ((queryLine = queryResult.readLine()) != null) {
				if(queryLine.startsWith("[")){
					String remoteTLD = queryLine.substring(1, queryLine.length()-1);
					log.debug(remoteTLD);					
				}
				if(queryLine.contains("Expiration Date:")){
					String expString = queryLine.replace("Expiration Date:", "").trim();
					DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
					try {
						log.info("Exp: " + queryLine.replace("Expiration Date:", "").trim());						
						//expDate = df.parse(expString.replaceAll("\\p{Cntrl}", ""));
						expDate = df.parse(expString);
					} catch (ParseException e) {						 
						e.printStackTrace();
					}					
				}
				i++;
			}
			log.debug("Date: " + expDate );
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return new WhoisEntry(domainName, expDate);
	}
}
