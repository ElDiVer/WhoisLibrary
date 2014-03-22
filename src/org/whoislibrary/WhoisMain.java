package org.whoislibrary;

public class WhoisMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(args.length);
		if(args.length>0){
			System.out.println("Args: " + args[0]);
			WhoisCommand query = new WhoisCommand(args[0]);
			WhoisEntry entry = query.executeQuery();
			printEntry(entry);
		}
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
		if(entry!=null){
			System.out.println("Reading WhoisEntry:\n "+entry.toString());
		}
	}

}
