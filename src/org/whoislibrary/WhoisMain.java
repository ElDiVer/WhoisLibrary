package org.whoislibrary;

public class WhoisMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		WhoisCommand query = new WhoisCommand("google.com");
		WhoisEntry firstEntry = query.executeQuery();
		printEntry(firstEntry);

		query.setSearchQuery("dreamos.org");
		firstEntry = query.executeQuery();
		printEntry(firstEntry);

		query.setSearchQuery("osdev.it");
		firstEntry = query.executeQuery();		
		printEntry(firstEntry);
	}

	public static void printEntry(WhoisEntry entry) {
		System.out.println("Reading WhoisEntry:\n\tDomain: "
				+ entry.getDomainName() + "\n\tExpiration: "
				+ entry.getExpirationDate());
	}

}
