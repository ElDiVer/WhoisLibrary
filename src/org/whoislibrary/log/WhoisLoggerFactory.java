package org.whoislibrary.log;

public class WhoisLoggerFactory {
	
	private static final String ANDROID
		= "org.whoislibrary.log.android.WhoisAndroidLog";

	private static final String APACHE
		= "org.whoislibrary.log.WhoisLog";

	private static WhoisLogger logger = null;

	public static WhoisLogger getLogger() {
		return getLogger(WhoisLoggerFactory.class);
	}

	public static WhoisLogger getLogger(@SuppressWarnings("rawtypes") Class className){
		if (logger == null) {
			try {		    
			    Class.forName("android.util.Log");		    
			    //System.out.println("Exists");
			    logger = (WhoisLogger) Class.forName(ANDROID).newInstance();
			    logger.setPackage(className.getPackage());
			    //return null;			
			} catch(Exception e) {
			    //System.out.println("Doesn't exist");
			    try {
			    	logger = (WhoisLogger) Class.forName(APACHE).newInstance();
			    } catch(Exception e2) {
			    	e2.printStackTrace();
			    }
			}
		}
		return logger;
	}

}
