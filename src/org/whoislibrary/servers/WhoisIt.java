package org.whoislibrary.servers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.whoislibrary.Whois;
import org.whoislibrary.WhoisAbstract;

public class WhoisIt extends WhoisAbstract implements Whois {
	
	public WhoisIt(){
		super("whois.nic.it");
	}

	@Override
	protected void parseLine(String queryLine, int i) {
		Date expDate = null;

		if(queryLine.contains("Expire Date:")){
			String expString = queryLine.replace("Expire Date:", "").trim();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			try {
				log.info("Exp: " + queryLine.replace("Expire Date:", "").trim());						
				expDate = df.parse(expString);
				whoisEntry.setExpirationDate(expDate);
			} catch (ParseException e) {						 
				e.printStackTrace();
			}					
		}

	}

}
