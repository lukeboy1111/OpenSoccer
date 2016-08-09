package com.lukeyboy1.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Luke Campbell
 */
public class WebUtils
{
    private WebUtils(){} // no instances required

    public static final String sAmpersand = "&amp;";
    public static final String sNbsp      = "&nbsp;";
    public static final String sGreater   = "&gr;";
    public static final String sLess      = "&lt;";
    
    //TODO: need to fix escaping characters problem in getRequestParameter
    
    public static boolean getRequestParameter(HttpServletRequest request, String param)
    {
        return request.getParameter(param) != null && !StringUtils.sFalse.equals(request.getParameter(param));
    }

    public static String getRequestParameter(HttpServletRequest request, String param, String defaultValue)
    {
    	String paramValue = "";
    	//try {
    		paramValue = !StringUtils.isNullOrEmpty(request.getParameter(param)) ? request.getParameter(param) : defaultValue;
    	//}
    	//catch(NullPointerException ne) {
    	//	paramValue = defaultValue;
    	//
    	return paramValue;
    }

    public static int getRequestParameter(HttpServletRequest request, String param, int defaultValue)
    {
        int result = defaultValue;
        try
        {
            result = Integer.parseInt(getRequestParameter(request, param, Integer.toString(defaultValue)));
        }
        catch(NumberFormatException e) {/* silent failure and return default value */ }
        return result;
    }

    public static String getSessionAttribute(HttpSession session, String param, String defaultValue)
    {
    	String theVal = defaultValue;
    	
    	try {
    		String myVal = (String) session.getAttribute(param.toString());
	    	if(!(myVal.equals(""))) {
	    		theVal = myVal;
	    	}
    	}
    	catch(ClassCastException ne) {
    		Integer value = (Integer) session.getAttribute(param);
    		theVal = value.toString();
    	}
    	catch(NullPointerException ne) {
    		theVal = defaultValue;
    	}
    	
    	return theVal;
    }

    public static int getSessionAttribute(HttpSession session, String param, int defaultValue)
    {
        Integer result = defaultValue;
        try
        {
        	String res = getSessionAttribute(session, param, result.toString());
            result = Integer.parseInt(res);
        }
        catch(NumberFormatException e) {/* silent failure and return default value */ }
        
        return result;
    }
    
    public static Integer getSessionAttributeInteger(HttpSession session, String param, Integer defaultValue)
    {
    	//TODO: need to fix return default issue - currently it returns null
        Integer result = defaultValue;
        try
        {
            result = (Integer) session.getAttribute(param);
        }
        catch(NumberFormatException e) {
        	result = defaultValue;
        	/* silent failure and return default value */ 
        }
        return result;
    }
    
    public static Integer getResponseCode(String theAddress) throws Exception {
    	URL url = new URI(theAddress).toURL();
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	Integer responseCode = connection.getResponseCode();
    	return responseCode;
    }
    
    public static String makeURL(String www, String domain, String uri) {
		String url = "http://"+www+"."+domain+uri;
		return(url);
	}
    
    public static String escapeCharacters(String stringToReplace){
    	stringToReplace = stringToReplace.replaceAll("£", "&pound;");
    	return stringToReplace;
    }
    
    
	public static Date getLastModifiedDate() {
		// get the supported ids for GMT-08:00 (Pacific Standard Time)
		String[] ids = TimeZone.getAvailableIDs(0 * 60 * 60 * 1000);

		// create a Pacific Standard Time time zone
		SimpleTimeZone pdt = new SimpleTimeZone(0 * 60 * 60 * 1000, ids[0]);

		 // set up rules for daylight savings time
		 pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		 pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

		 // create a GregorianCalendar with the Pacific Daylight time zone
		 // and the current date and time
		 Calendar calendar = new GregorianCalendar(pdt);
		
		 Date lastModified = (Date) calendar.getTime();
		 
		 return lastModified;
	}

	public static String getPageURI(String pageUrl) {
		String returnValue = "";
		final String REGEX = "http://[^/]+(.*)";
	    

		Pattern p = Pattern.compile(REGEX);
	    Matcher m = p.matcher(pageUrl); // get a matcher object
	    if(m.matches()) {
	    	returnValue = m.group(1);
	    }
		return returnValue;
	}
}
