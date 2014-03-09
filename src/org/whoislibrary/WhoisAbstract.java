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
	public String domainName;
	
	public abstract String getWhoisURL();
	public abstract WhoisEntry parseResponse(BufferedReader queryResult);	
	//public abstract Date getExpirationDate();
	
	public int getWhoisPort() {
		return 43;
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
		    out.write(String.format("%s\r\n",query));
		    log.debug("flush");
		    out.flush();		    
		    InputStream queryResult = new BufferedInputStream(theSocket.getInputStream());
		    BufferedReader queryData = new BufferedReader(new InputStreamReader(queryResult));
		    
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
	
	public Date getDate(String dateString){
		return null;
	}
		
}
