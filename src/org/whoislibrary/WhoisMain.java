package org.whoislibrary;

public class WhoisMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		WhoisCommand query = new WhoisCommand("google.com");
		WhoisEntry entry = query.executeQuery();
		printEntry(entry);

		query.setSearchQuery("dreamos.org");
		entry = query.executeQuery();
		printEntry(entry);

		query.setSearchQuery("osdev.it");
		entry = query.executeQuery();		
		printEntry(entry);
	}

	public static void printEntry(WhoisEntry entry) {
		System.out.println("Reading WhoisEntry:\n "+entry.toString());
	}

}
