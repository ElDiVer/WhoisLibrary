package org.whoislibrary.templates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.whoislibrary.parser.ParseOperation;
import org.whoislibrary.parser.DateOperation;
import org.whoislibrary.parser.SkipOperation;
import org.whoislibrary.parser.StringOperation;
import org.whoislibrary.parser.Template;

/**
 * 
 * Template for it domains.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public final class TemplateIt extends Template {
	private static final DateFormat dateFormat
		= new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	protected static final ParseOperation parse[] = {
		new SkipOperation(9),
		new StringOperation("Status:             ", STATUS),
		new DateOperation("Created:            ", CREATION, dateFormat),
		new DateOperation("Last Update:        ", LASTUPDATED, dateFormat),
		new DateOperation("Expire Date:        ", EXPIRE, dateFormat)
	};

	protected static final ParseOperation availabilityCheck[] = {
		new SkipOperation(1),
		new StringOperation("Status:             AVAILABLE", AVAILABLE)
	};

	public TemplateIt() {
		super("whois.nic.it");
		loadOperations(parse, availabilityCheck);
	}
}
