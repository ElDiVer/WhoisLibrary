package org.whoislibrary.servers;


import org.whoislibrary.Whois;

public class WhoisOrg extends WhoisCom implements Whois{
	
	@Override
	public String getWhoisURL() {		
		return "whois.publicinterestregistry.net";
	}

}
