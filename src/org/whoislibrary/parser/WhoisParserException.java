package org.whoislibrary.parser;

/**
 * 
 * This exception is thrown when there are parsing errors.
 * with the server.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public class WhoisParserException extends RuntimeException {
	private static final long serialVersionUID = -2324101280715579645L;

	public WhoisParserException(String e) {
		  super("Whois parser error: " + e);
	  }
}
