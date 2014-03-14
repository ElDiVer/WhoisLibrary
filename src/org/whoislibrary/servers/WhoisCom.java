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


public class WhoisCom extends WhoisAbstract implements Whois{

	private static final WhoisLogger log = WhoisLoggerFactory.getLogger();
	
	public WhoisCom(){
		super("whois.internic.net");
		this.setPrefix("domain");
	}

	@Override
	protected void parseLine(String queryLine, int i) {
		Date expDate = null;
	   	if(queryLine.startsWith("[")){
			String remoteTLD = queryLine.substring(1, queryLine.length()-1);
			log.debug(remoteTLD);					
		}

		if(queryLine.contains("Expiration Date:")){
			String expString = queryLine.replace("Expiration Date:", "").trim();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
			try {
				log.info("Exp: " + queryLine.replace("Expiration Date:", "").trim());						
				expDate = df.parse(expString);
			    whoisEntry.setExpirationDate(expDate);
			} catch (ParseException e) {						 
				e.printStackTrace();
			}					
		}
	}
}
