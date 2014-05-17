package org.whoislibrary.templates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.whoislibrary.parser.ParseOperation;
import org.whoislibrary.parser.DateOperation;
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
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	private static final ParseOperation parserOperations[] = {
		new StringOperation("Status:             ", STATUS),
		new DateOperation("Created:            ", CREATION, dateFormat),
		new DateOperation("Last Update:        ", LASTUPDATED, dateFormat),
		new DateOperation("Expire Date:        ", EXPIRE, dateFormat)
	};

	public TemplateIt() {
		super("whois.nic.it");
		loadOperations(parserOperations);
	}
}
