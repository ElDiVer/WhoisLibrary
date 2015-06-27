package org.whoislibrary.templates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.whoislibrary.parser.*;

/**
 * 
 * Template for org domains.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public final class TemplateOrg extends Template {
	private static final DateFormat dateFormat
		= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

	protected static final ParseOperation parse[] = {
		new SkipOperation(2),
		new DateOperation("Creation Date: ", CREATION, dateFormat),
		new DateOperation("Updated Date: ", LASTUPDATED, dateFormat),
		new DateOperation("Registry Expiry Date: ", EXPIRE, dateFormat),
		new StringOperation("Sponsoring Registrar:", REGISTRAR),
		new SkipOperation(1),
		new StringOperation("WHOIS Server: ", SERVER),
		new StringOperation("Referral URL: ", REFERRAL),
		new StringOperation("Domain Status: ", STATUS),
		new StringOperation("Name Server:", NAMESERVER, true)
	};

	protected static final ParseOperation availabilityCheck[] = {
		new StringOperation("NOT FOUND", AVAILABLE)
	};

	protected static final ParseOperation errorCheck[] = {
		new StringOperation("No match for domain ", ERROR),
		new StringOperation("Not a valid domain search pattern", ERROR)
	};

	public TemplateOrg() {
		super("whois.publicinterestregistry.net");
		loadOperations(parse, availabilityCheck, errorCheck);
	}
}
