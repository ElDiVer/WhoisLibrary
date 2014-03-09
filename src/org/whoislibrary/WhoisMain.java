package org.whoislibrary;

public class WhoisMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		WhoisCommand myQuery = new WhoisCommand("google.com");
		WhoisEntry firstEntry = myQuery.executeQuery();
		System.out.println("Reading WhoisEntry:\n\tDomain: " + firstEntry.getDomainName() + "\n\t Expiration: " + firstEntry.expirationDate);
		WhoisCommand myQuery2 = new WhoisCommand("dreamos.org");
		firstEntry = myQuery2.executeQuery();
		System.out.println("Reading WhoisEntry:\n\tDomain: " + firstEntry.getDomainName() + "\n\tExpiration: " + firstEntry.expirationDate);
		WhoisCommand myQuery3 = new WhoisCommand("osdev.it");
		myQuery3.executeQuery();		
		//System.out.println("Reading WhoisEntry:\n\tDomain: " + firstEntry.getDomainName() + "\n\tExpiration: " + firstEntry.expirationDate);
		try {
		    //Class.forName("java.lang.Object");
		    Class.forName("android.util.Log");		    
		    System.out.println("Exists");
		} catch(Exception e) {
		    System.out.println("Doesn't exist"); 
		}
	}

}
