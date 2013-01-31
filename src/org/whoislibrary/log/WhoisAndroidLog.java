package org.whoislibrary.log;
//import android.util.Log;

public class WhoisAndroidLog implements WhoisLogger{
		
	public int info(String message) {
		//Log.i("WHOIS", message);
		return 0;
	}

	public int warn(String message) {
		//Log.w("WHOIS", message);
		return 0;
	}

	public int debug(String message) {
		//Log.d("WHOIS", message);
		return 0;
	}

	public int error(String message) {
		//Log.d("WHOIS", message);
		return 0;
	}

}

