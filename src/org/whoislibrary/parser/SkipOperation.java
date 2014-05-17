package org.whoislibrary.parser;

import org.whoislibrary.WhoisEntry;
import org.whoislibrary.parser.ParseOperation;

/**
 * 
 * Operation to skip n lines of the input.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public class SkipOperation extends ParseOperation {
	int skip = 0;

	public SkipOperation(int skip) {
		super(OperationType.SKIP, Template.NOCODE);
		this.skip = skip;
	}

	public int getLinesToSkip() {
		return skip;
	}

	public int examine(String line, WhoisEntry dest, int index) {
		return 1;
	}
}
