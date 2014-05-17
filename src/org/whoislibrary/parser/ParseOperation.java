package org.whoislibrary.parser;

import org.whoislibrary.WhoisEntry;

/**
 * 
 * Base class to implement the parsing operations.
 *   
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public abstract class ParseOperation {
	private OperationType type = OperationType.UNKNOWN;
	protected int infoCode = Template.NOCODE;

	public enum OperationType {
		UNKNOWN,
		STRING,
		DATE,
		SKIP,
	}

	public ParseOperation(OperationType type, int infoCode) {
		this.infoCode = infoCode;
		this.type = type;
	}

	public OperationType getType() {
		return type;
	}

	public int getInfoCode() {
		return infoCode;
	}

	protected String findVar(String line, String begin, String end) {
		// TODO this should be improved.
		int first = line.indexOf(begin);
		if (first == -1)
			return null;

		first += begin.length();

		int last;
		if (end == null)
			last = line.length();
		else 
			last = line.lastIndexOf(end);

		return line.substring(first, last); 
	}

	public abstract int examine(String line, WhoisEntry dest, int index);
}
