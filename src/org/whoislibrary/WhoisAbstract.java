package org.whoislibrary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.Date;

import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;

/**
 * 
 * Abstract class, with basic methods for Whois servers implementation. 
 *  
 * @author Ivan Gualandri
 * @version 1.0
 *
 */
public abstract class WhoisAbstract {
	
	//
	private static final WhoisLogger log = WhoisLoggerFactory.getLogger();
	private String queryPrefix;
	protected String domainName;
	
	public abstract String getWhoisURL();
	public abstract WhoisEntry parseResponse(BufferedReader queryResult);	
	//public abstract Date getExpirationDate();

	public String getDomainName() {
		return domainName;
	}

	/** Return the whois port used */ 
	public int getWhoisPort() {
		return 43;
	}
	
	/** Set a prefix in the query if needed. I.e. for .com
	 * domain is safest to add domain prefix before the query. */
	public void setPrefix(String queryPrefix){
		this.queryPrefix = queryPrefix;
	}
	
	/** Return the query Prefix if set.*/
	public String getQueryPrefix(){
		return this.getQueryPrefix();
	}
	
	public WhoisEntry executeQuery(String query) {
		log.debug(query);
		try {
			domainName = query;
		    InetAddress server = null;
		    server = InetAddress.getByName(getWhoisURL());
		    Socket theSocket = new Socket(server, getWhoisPort());
		    Writer out = new OutputStreamWriter(theSocket.getOutputStream(), "8859_1");
		    log.debug("write");
		    out.write(prepareQuery(query));
		    log.debug("flush");
		    out.flush();		    

		    InputStream queryResult =
		    		new BufferedInputStream(theSocket.getInputStream());

		    BufferedReader queryData = new BufferedReader(
		    		new InputStreamReader(queryResult));

		    return parseResponse(queryData);
		    
		} catch (MalformedURLException e) {
			log.error(e.getStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getStackTrace().toString());
			e.printStackTrace();
		}
		log.info("WhoisCom executeQuery");
		return null;
	}
	
	public Date getDate(String dateString) {
		return null;
	}
	
	private String prepareQuery(String query) {
		if(queryPrefix!=null){
			return String.format( queryPrefix + " %s\r\n",query);
		} else {
			return String.format("%s\r\n",query);
		}
	}
		
}
