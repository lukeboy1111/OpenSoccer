package com.lukeyboy1.utility;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Luke Campbell
 */
public class StringUtils
{
    // commonly used String constants
    public static final String sTrue  = Boolean.toString(true);
    public static final String sFalse = Boolean.toString(false);
    public static final String sEmpty = "";
    public static final String sNull  = "null";
    public static final String sYes   = "yes";
    public static final String sNo    = "no";

    private StringUtils(){} // no instances required

    public static boolean isNullOrEmpty(String text)
    {
        return text == null || StringUtils.sEmpty.equals(text);
    }
    
    public static Boolean validateExpression(String regex, String checker) {
		Boolean returnValue = false;
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(checker); // get a matcher object
	    if(m.matches()) {
	    	returnValue = true;
	    }
		return returnValue;
	}
}

