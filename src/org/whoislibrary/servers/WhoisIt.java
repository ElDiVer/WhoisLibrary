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

public class WhoisIt extends WhoisAbstract implements Whois {
	
	private static final WhoisLogger log = WhoisLoggerFactory.getLogger();

	@Override
	public String getWhoisURL() {
		return  "whois.nic.it";
	}

	@Override
	public WhoisEntry parseResponse(BufferedReader queryResult) {
		String queryLine;
		Date expDate = null;
		System.out.println(queryResult.toString());
		
		try {
			while ((queryLine = queryResult.readLine()) != null) {
				
				if(queryLine.contains("Expire Date:")){
					String expString = queryLine.replace("Expire Date:", "").trim();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
					try {
						log.info("Exp: " + queryLine.replace("Expire Date:", "").trim());						
						//expDate = df.parse(expString.replaceAll("\\p{Cntrl}", ""));
						expDate = df.parse(expString);
					} catch (ParseException e) {						 
						e.printStackTrace();
					}					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new WhoisEntry(domainName, expDate);
	}

}
