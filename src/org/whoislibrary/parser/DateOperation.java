package org.whoislibrary.parser;

import java.util.Date;
import java.text.*;

import org.whoislibrary.WhoisEntry;

/**
 * 
 * Operation for Date parsing. 
 *  
 * @author Dario Casalinuovo
 * @version 1.0
 *
 */

public class DateOperation extends ParseOperation {
	private String begin = null;
	private String end = null;
	DateFormat dateFormat = null;

	public DateOperation(String begin, int infoCode, DateFormat dateFormat) {
		super(OperationType.DATE, infoCode);
		_Init(begin, null, dateFormat);
	}

	public DateOperation(String begin, int infoCode, String end, DateFormat dateFormat) {
		super(OperationType.DATE, infoCode);
		_Init(begin, end, dateFormat);
	}

	public DateOperation(String begin, int infoCode, DateFormat dateFormat, boolean multiline) {
		super(OperationType.DATE, infoCode, multiline);
		_Init(begin, null, dateFormat);
	}

	public DateOperation(String begin, int infoCode, String end, DateFormat dateFormat, boolean multiline) {
		super(OperationType.DATE, infoCode, multiline);
		_Init(begin, end, dateFormat);
	}

	private final void _Init(String begin, String end, DateFormat dateFormat) {
		this.begin = begin;
		this.end = end;
		this.dateFormat = dateFormat;
	}

	public int examine(String line, WhoisEntry dest, int index) {
		String var = this.findVar(line, begin, end);
		Date date = null;

		if (var == null)
			return 1;

		try {
			date = dateFormat.parse(var);
		} catch (ParseException e) {
			e.printStackTrace();
			// TODO log error
			return 1;
		}

		switch (this.infoCode) {
			case Template.EXPIRE:
			{
			    dest.setExpiration(date);
				break;
			}

			case Template.CREATION:
			{
				dest.setCreation(date);
				break;
			}

			case Template.LASTUPDATED:
			{
				dest.setLastUpdate(date);
				break;
			}
		}

		return 0;
	}
}
