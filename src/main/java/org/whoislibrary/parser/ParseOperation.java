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
	private boolean multiline = false;
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

	/**
	 * sWhen an operation is multiline, it will be iterated until it found a matching string,
	 * then continue until it found a non matching string. Once the non matching is found,
	 * the parser continue to the next operation.
	 * NOTE: not all variables are multiline see ParseOperation inherited classes.
	 **/
	public ParseOperation(OperationType type, int infoCode, boolean multiline) {
		this.infoCode = infoCode;
		this.type = type;
		this.multiline = multiline;
	}

	public OperationType getType() {
		return type;
	}

	public final boolean isMultiLine() {
		return multiline;
	}

	public final int getInfoCode() {
		return infoCode;
	}

	protected final String findVar(String line, String begin, String end) {
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
