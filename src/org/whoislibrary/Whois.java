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

import org.whoislibrary.log.WhoisLogger;
import org.whoislibrary.log.WhoisLoggerFactory;
import org.whoislibrary.parser.Template;


/**
 * 
 * Class to query the Whois server. 
 *  
 * @author Ivan Gualandri
 * @version 1.0
 *
 */

public class Whois {
	private final Template template;
	protected static final WhoisLogger log = WhoisLoggerFactory.getLogger();
	protected WhoisEntry whoisEntry = null;

	public Whois(Template template) {
		this.template = template;
	}

	public WhoisEntry getWhoisEntry() {
		// TODO implement clone
		//return (WhoisEntry)whoisEntry.clone();
		return whoisEntry;
	}

	/** Return the whois port used */ 
	public int getWhoisPort() {
		return 43;
	}

	
	/** Return the query Prefix if set.*/
	public String getQueryPrefix(){
		return template.getQueryPrefix();
	}

	/** A new whoisEntry is created for every query. */
	public WhoisEntry executeQuery(String query) {
		log.debug(query);
		try {
			whoisEntry = new WhoisEntry(query);

			InetAddress server = null;
		    server = InetAddress.getByName(template.getWhoisServer());
		    Socket theSocket = new Socket(server, getWhoisPort());

		    Writer out = new OutputStreamWriter(
		    		theSocket.getOutputStream(), "8859_1");

		    log.debug("write");
		    out.write(prepareQuery(query));
		    log.debug("flush");
		    out.flush();		    

		    InputStream queryResult =
		    		new BufferedInputStream(theSocket.getInputStream());

		    BufferedReader queryData = new BufferedReader(
		    		new InputStreamReader(queryResult));

		    if (template.parseResponse(queryData, whoisEntry) != 1)
		    	whoisEntry = null;

		    theSocket.close();

		    return whoisEntry;
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
	
	private String prepareQuery(String query) {
		if (getQueryPrefix() != null) {
			return String.format( getQueryPrefix() + " %s\r\n",query);
		} else {
			return String.format("%s\r\n",query);
		}
	}

}
