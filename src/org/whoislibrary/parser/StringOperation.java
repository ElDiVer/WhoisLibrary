package org.whoislibrary.parser;

import org.whoislibrary.WhoisEntry;
import org.whoislibrary.parser.ParseOperation;

/**
 * 
 * Operation to parse strings.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public class StringOperation extends ParseOperation {
	private String begin = null;
	private String end = null;
	
	public StringOperation(String begin, int infoCode, String end) {
		super(OperationType.STRING, infoCode);
		this.begin = begin;
		this.end = end;
	}

	/** 
	 * In this case we assume that the end is only \n
	 */
	public StringOperation(String begin, int infoCode) {
		super(OperationType.STRING, infoCode);
		this.begin = begin;
	}

	public int examine(String line, WhoisEntry dest, int index) {
		String var = this.findVar(line, begin, end);
		if (var == null)
			return 1;

		switch (this.infoCode) {

			case Template.REGISTRAR:
				dest.setRegistrar(var);
				break;

			case Template.REFERRAL:
				dest.setReferral(var);
				break;

			case Template.SERVER:
				dest.setReferredServer(var);

			case Template.NAMESERVER:
				dest.addNameServer(var);
				break;

			case Template.STATUS:
				//dest.setStatus(var);
				break;
		}

		return 0;
	}
}
