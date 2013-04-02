package org.whoislibrary.servers;

import java.io.BufferedReader;

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
		System.out.println(queryResult.toString());
		return null;
	}

}
