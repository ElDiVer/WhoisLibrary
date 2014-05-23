package org.whoislibrary;

public class WhoisMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equals("--help")) {
				usage();
				System.exit(0);
			} else {
				System.out.println(args.length);
				System.out.println("Args: " + args[0]);
				WhoisCommand query = new WhoisCommand(args[0]);
				WhoisEntry entry = query.executeQuery();
				printEntry(entry);
			}
		}

		WhoisCommand query = new WhoisCommand("google.com");
		WhoisEntry entry = query.executeQuery();
		printEntry(entry);

		entry = query.executeQuery("dreamos.org");
		printEntry(entry);

		entry = query.executeQuery("osdev.it");		
		printEntry(entry);

		entry = query.executeQuery("surelythisdomainwillnotexist.com");		
		printEntry(entry);
	}

	public static void printEntry(WhoisEntry entry) {
		if (entry != null) {
			if (entry.isAvailable())
				System.out.println("Domain "+entry.getDomainName()+" is available.");
			else
				System.out.println("Query result: "+entry.toString());
		} else
			System.out.println("The entry returned is null, please check log.");
	}

	private static void usage() {
		System.out.println(WhoisMain.class.getName() + " ver 0.1");
		System.out.println("Usage: ");
		System.out.println("\tjava -cp . " + WhoisMain.class.getName()+ " urlquery");
		System.out.println("\tjava -cp . " + WhoisMain.class.getName() + " --help for this help");
	}

}
