package org.whoislibrary.templates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.whoislibrary.parser.DateOperation;
import org.whoislibrary.parser.ParseOperation;
import org.whoislibrary.parser.StringOperation;
import org.whoislibrary.parser.Template;

/**
 * 
 * Template for org domains.
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public final class TemplateOrg extends Template {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

	private static ParseOperation parserOperations[] = {
		new DateOperation("Creation Date: ", CREATION, dateFormat),
		new DateOperation("Updated Date: ", LASTUPDATED, dateFormat),
		new DateOperation("Registry Expiry Date: ", EXPIRE, dateFormat),
		new StringOperation("Sponsoring Registrar:", REGISTRAR),
		new StringOperation("WHOIS Server: ", SERVER),
		new StringOperation("Referral URL: ", REFERRAL),
		new StringOperation("Domain Status: ", STATUS),
		new StringOperation("Name Server:", NAMESERVER),
		new StringOperation("Name Server:", NAMESERVER)
	};

	public TemplateOrg() {
		super("whois.publicinterestregistry.net");
		loadOperations(parserOperations);
	}
}
