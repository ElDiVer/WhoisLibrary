package org.whoislibrary.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import org.whoislibrary.WhoisEntry;

/**
 * 
 * Template abstract class to parse the whois data.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public abstract class Template {
	private String queryPrefix;
	private String server = null;
	protected ArrayList<ParseOperation> operationList = new ArrayList<ParseOperation>();

	// String
	public static final int	NOCODE = 0;
	public static final int REGISTRAR = 1;
	public static final int SERVER = 2;
	public static final int REFERRAL = 3;
	public static final int NAMESERVER = 4;
	public static final int STATUS = 5;

	// Date
	public static final int LASTUPDATED = 6;
	public static final int CREATION = 7;
	public static final int EXPIRE = 8;

	public Template(String server) {
		this.server = server;
	}

	/** Set a prefix in the query if needed. I.e. for .com
	 * domain is safest to add domain prefix before the query. */
	protected void setQueryPrefix(String queryPrefix) {
		this.queryPrefix = queryPrefix;
	}

	/** Return the query Prefix if set.*/
	public String getQueryPrefix(){
		return queryPrefix;
	}

	/** Get the whois server associated to the Template.*/
	public String getWhoisServer() {
		return server;
	}

	/** Parse the input data using the operations defined by the Template.*/
	public int parseResponse(BufferedReader queryResult, WhoisEntry entry) {
		String queryLine;

		try {   	
	    	for (int i = 0; (queryLine = queryResult.readLine()) != null; i++) {
	    		if (operationList.size() == 0)
	    			break;

	    		ParseOperation operation = operationList.get(0);

	    		if (operation.examine(queryLine, entry, i) == 0) {
	    			operationList.remove(0);
	    		}
	    	}

	    	// NOTE: Theoretically if we have already operations in the queue
	    	// we should return an error since something probably gone bad.

	    	entry.setRawData(queryResult.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 1;
	}

	/** Called by derived classes to load the parser instructions.*/
	protected void loadOperations(ParseOperation operations[]) {
		for (int i = 0; i < operations.length; i++)
			operationList.add(operations[i]);
	}
}
