package org.whoislibrary.templates;

import org.whoislibrary.parser.*;

/**
 * 
 * Generic Template.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */


public final class TemplateGeneric extends Template {

	private static final ParseOperation parse[] = {
	};

	private static final ParseOperation availabilityCheck[] = {
	};

	protected static final ParseOperation errorCheck[] = {
	};

	public TemplateGeneric() {
		super("");
		setQueryPrefix("domain");
		loadOperations(parse, availabilityCheck, errorCheck);
	}
}
