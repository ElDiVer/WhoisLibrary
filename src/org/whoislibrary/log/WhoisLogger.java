package org.whoislibrary.log;

public interface WhoisLogger {
	
	public int info(String message);
	public int warn(String message);
	public int debug(String message);
	public int error(String message);
	
}
