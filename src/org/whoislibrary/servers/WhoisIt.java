package org.whoislibrary.servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import org.whoislibrary.Whois;
import org.whoislibrary.WhoisAbstract;
import org.whoislibrary.WhoisEntry;

public class WhoisIt extends WhoisAbstract implements Whois {

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
				if(queryLine.contains("Registry Expiry Date:")){
					String expString = queryLine.replace("Expire Date:", "").trim();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
