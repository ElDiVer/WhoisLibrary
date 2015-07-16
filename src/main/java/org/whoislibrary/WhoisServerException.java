package org.whoislibrary;

/**
 * 
 * This exception is thrown when there are network errors
 * with the server.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public class WhoisServerException extends RuntimeException {
	private static final long serialVersionUID = -8246234369879683862L;

	public WhoisServerException(String e) {
		  super("Whois server error: " + e);
	  }
}
