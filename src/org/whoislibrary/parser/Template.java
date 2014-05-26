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
	protected ParseOperation parseOperations[] = null;
	protected ParseOperation availabilityOperations[] = null;

	// String
	public static final int	ERROR = -2;
	public static final int	AVAILABLE = -1;
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
	public String getQueryPrefix() {
		return queryPrefix;
	}

	/** Get the whois server associated to the Template.*/
	public String getWhoisServer() {
		return server;
	}

	/** Do the checks to establish the domain status.*/
	public int parseResponse(BufferedReader queryResult, WhoisEntry entry) {
		if (availabilityOperations == null || parseOperations == null)
			return 1;

		ArrayList<String> list = new ArrayList<String>();

		String queryLine;
		try {
			while ((queryLine = queryResult.readLine()) != null)
				list.add(queryLine);
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		}

		int ret = executeOperations(list, availabilityOperations, entry);

		if (ret == 0 && entry.isAvailable())
			return ret;

		return executeOperations(list, parseOperations, entry);
	}

	protected void loadOperations(ParseOperation parse[], ParseOperation availability[]) {
		this.parseOperations = parse;
		this.availabilityOperations = availability;
	}

	/** Parse the input data using the operations defined by the Template.*/
	public int executeOperations(ArrayList<String> queryResult,
			ParseOperation operations[], WhoisEntry entry) {

		String queryLine;
		int operationCount = 0;
		boolean multilineCatch = false;

	    for (int i = 0; i < queryResult.size(); i++) {
	    	if (operationCount == operations.length)
	    		break;

	    	queryLine = queryResult.get(i);

	    	//System.out.println(queryLine);
	    	ParseOperation operation = operations[operationCount];
 
	    	switch (operation.getType()) {
	    		case UNKNOWN:
	    			return 1;

	    		case SKIP:
	    		{
	    			SkipOperation skip = (SkipOperation) operation;
	    				
	    			i += skip.getLinesToSkip()-1;
	    			operationCount++;
		   			continue;
	    		}

	    		default:
	    			break;
	    	}

	    	int status = operation.examine(queryLine, entry, i);

	    	if (status == 0 && operation.isMultiLine() == false) {
	    		operationCount++;
	    	} else if (status != 0 && operation.isMultiLine() == true
	    			&& multilineCatch == true) {
	    		operationCount++;
	    		multilineCatch = false;
	    		i--;
	    		continue;
	    	} else if (status == 0 && operation.isMultiLine() == true) {
	    		multilineCatch = true;
	    	}
	    }

    	// NOTE: If we have already unused operations in the queue
    	// we should return an error since something probably gone bad.
	   if (operationCount != operations.length)
	    	return 1;

    	entry.setRawData(queryResult.toString());
    	return 0;
	}
}
