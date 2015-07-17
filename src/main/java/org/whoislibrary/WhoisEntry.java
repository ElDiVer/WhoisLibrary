package org.whoislibrary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * This class contains all the informations about the Whois query result.
 * 
 * @author Ivan Gualandri, Dario Casalinuovo
 * @version 2.0
 */
public final class WhoisEntry {
	private final String domainName;

	boolean invalidQuery = false;
	boolean available = false;

	private String rawData = null;

	private Date expiration = null;
	private Date creation = null;
	private Date lastUpdate = null;

	private String registrar = null;
	private String referral = null;
	private String referredServer = null;
	
	private boolean isRedirected = false;
	private String redirectURL = null;	
	private ArrayList<String> nameServerList = new ArrayList<String>();


	public WhoisEntry(String domainName) {
		this.domainName = domainName;
	}

	public boolean isQueryInvalid() {
		return invalidQuery;
	}

	public void setInvalidQuery(boolean invalid) {
		invalidQuery = invalid;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public String getDomainName() {
		return domainName;
	}

	public boolean isRedirected() {
		return isRedirected;
	}

	public void setRedirected(boolean redirected) {
		isRedirected = redirected;
	}
	
	public String getRegistrar() {
		return registrar;
	}

	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	public String getURLRedirect() {
		return redirectURL;
	}

	public void setURLRedirect(String redirect) {
		redirectURL = redirect;
	}

	public String getReferral() {
		return referral;
	}

	public void setReferral(String var) {
		referral = var;
	}

	public String getReferredServer() {
		return referredServer;
	}

	public void setReferredServer(String var) {
		referredServer = var;
	}

	public List<String> getNameServerList() {
		return nameServerList;
	}

	public void addNameServer(String var) {
		nameServerList.add(var);
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date date) {
		expiration = date;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date date) {
		creation = date;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date date) {
		lastUpdate = date;
	}

	public String toString() {
		return domainName + " expires on " + expiration + " was created at "
				+ creation + " with registrar " + registrar + ".";
	}
}
