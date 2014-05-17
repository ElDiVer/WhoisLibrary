package org.whoislibrary;

/**
 * 
 * Class to do a query on a string.
 *  
 * @author Ivan Gualandri
 * @version 1.0
 *
 */

public final class WhoisCommand {	
	private String searchQuery = null;

	public WhoisCommand() {
		super();		
	}
	
	public WhoisCommand(String search) {
		super(); 
		this.searchQuery = search;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	/** 
	  * This method executes the query and return a WhoisEntry object. 
	  * @return the WhoisEntry object if the domain is found.
	  */
	public WhoisEntry executeQuery() {
		Whois myQuery = WhoisFactory.getWhois(getSearchQuery());

		if (myQuery != null)
			return myQuery.executeQuery(getSearchQuery());

		return null;
	}

	/**
	 * Alternative version.
	 */
	public WhoisEntry executeQuery(String searchQuery) {
		setSearchQuery(searchQuery);
		return executeQuery();
	}
}
