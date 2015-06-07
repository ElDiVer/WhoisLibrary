package org.whoislibrary.templates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.whoislibrary.parser.DateOperation;
import org.whoislibrary.parser.ParseOperation;
import org.whoislibrary.parser.SkipOperation;
import org.whoislibrary.parser.Template;

/**
 * 
 * Template class to parse the whois data for .info domains.
 *  
 * @author Ivan Gualandri
 * @version 1.0
 *
 */
public class TemplateInfo extends Template {
	private static final DateFormat dateFormat
	= new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	
	private static final ParseOperation parse[] = {
		new SkipOperation(2),
		new DateOperation("Creation Date: ", CREATION, dateFormat),
		new DateOperation("Updated Date: ", LASTUPDATED, dateFormat),
		new DateOperation("Registry Expiry Date: ", EXPIRE, dateFormat)
	};

	public TemplateInfo() {
		super("whois.afilias.net");
		// TODO Auto-generated constructor stub
	}

}
