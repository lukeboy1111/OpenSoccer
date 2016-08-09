package com.lukeyboy1.utility.support;

import java.io.File;
import java.io.FilenameFilter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class FitbaConstants {
	public static final String BUTTON_CLASS = "button";
	public static final String BUTTON_CLASS_PRESSED = "buttonSelected";
	public static final double pi = 3.14159;
	public static final double e = 2.71828;
	public static final Integer DAY_MINUS = -1;
	public static final Integer DAY_PLUS = 1;
	public static final Integer NOT_DELETED = 0;
	public static final Integer IS_DELETED = 1;
	public static final Integer NUMBER_DOMAINS_DISPLAY = 11;
	public static final String IWMNT = "//iwmnt";
	public static final String LINE_BREAK = "<br />";
	public static final String NEW_PAGE_SUBJECT = "New Web Page Request";
	public static final Integer PAGE_TITLE_MIN_LENGTH = 50;
	public static final Integer PAGE_TITLE_MAX_LENGTH = 63;
	public static final String USER_SESSION_LOGIN = "SESSION_USER_LOGIN";
	public static final String IMG_WIDTH = "_width";
	public static final String IMG_HEIGHT = "_height";
	public static final String GENERIC_SUPPORT_CLASS = "GenericCheckText";
	
	public static final String SNIPPET_TYPE_TEXT = "text";
	public static final String SNIPPET_TYPE_DYNAMIC_TEXT = "dynamicText";
	public static final String SNIPPET_TYPE_IMAGE = "image";
	public static final String SNIPPET_TYPE_VIDEO = "video";
	public static final String SNIPPET_TYPE_CONTENT = "content";
	public static final Integer URGENCY_CHANGE_UP = 10;
	public static final Integer URGENCY_CHANGE_DOWN = -10;
	public static final String TOMCAT6_PROPERTIES_FILE = "/.software/interwoven/TeamSite/local/config/lib/content_center/livesite_customer_src/etc/pico.connection.properties";
	public static final String WINDOWS_TOMCAT6_PROPERTIES_FILE = "connection.properties";
	public static final String RUNTIME_TOMCAT_PROPERTIES_FILE = "connection.properties";
	
	public static final String LOCAL_TOMCAT6_PROPERTIES_FILE = "c:/tomcat6/pico.connection.properties";
    public static final String DS_PROPERTIES_FILE = "pico.connection.properties";
    public static final String H2_DRIVER_NAME = "org.h2.Driver";
    public static final String CREATE_SCRIPT_NAME = "create.sql";
        
    public final String PROXY_HOST = "10.50.0.11";
    public final int PROXY_PORT = 80;
    public final String ASSET_URL_PREFIX = "http://s1.rationalcdn.com/vendors/cms";
        
        
    public static final String DATAFEED_GLOBAL = "datafeed_global";
	public static final String LIVESITE_ROOTPATH = "//.software/interwoven/LiveSiteDisplayServices/runtime/web/";
	public static final String TEAMSITE_ROOTPATH = "//iwmnt/iwadmin/main/pokerstars/STAGING/";
	public static final String WINDOWS_TEAMSITE_ROOTPATH = "Y:\\iwadmin\\main\\pokerstars\\STAGING\\";
	public static final String SITES_ROOTPATH = "//iwmnt/default/main/pokerstars/BRANCH/WORKAREA/shared/sites/SITE/";
	public static final String TEMPLATE_SITE_ROOTPATH = "//iwmnt/default/main/pokerstars/BRANCH/WORKAREA/shared/";
	public static final String TEMPLATE_SITE_RELATIVE_PATH = "/sites/blank_pages";
	public static final String PAGE_SUFFIX = "/index.page";
	public static final String VENDOR_LIST_PATH = "//iwmnt/default/main/pokerstars/realMoney/STAGING/templatedata/realMoney/realMoneyVendorList/data/";
	public static final String VENDOR_FILE_NAME = "testData";
	public static final String TEAMSITE_SHARED_PATH = "//iwmnt/default/main/pokerstars/WORKAREA/shared/";
	public static final String TEAMSITE_SHARED = "//iwmnt/default/main/pokerstars/WORKAREA/shared/";
	public static final String LIVESITE_SHARED = "/usr/share/tomcat6/webapps/ROOT/shared/";
	public static final String DS = "/";
	public static final String CACHE_FILE = "sites/cache.";
	public static final String TEMPLATE_PATH = "template";
	
	public static final String MAPPINGS_FOLDER_NAME = "mappings";
	public static final String DCR_CREATION_MAPPING_FILE_NAME = "DCRCreationMappings.xml";
	public static final String SITEMAP_FILE = "default.sitemap";
	public static final String REDIRECT_301 = "301";
	public static final String FILTERS_FILE = "filters.xml";
	public static final String TS_ROOTPATH = "//iwmnt/default/main/pokerstars/STAGING";
	public static final String DEFAULT_NOT_FOUND_DCR = "templatedata" + FitbaConstants.DS + "/misc/assembled/data/testingData/default-seo-dcr";
	public static final String UTF8_MODE = "UTF-8";
	public static final String DCR_PARAMETER = "CURRENT_DCR";
	public static final Object UNDEFINED_DCR_VALUE = "";

	public static final String WINDOWS_DCR_ROOTPATH = "Y:\\default\\main\\pokerstars\\WORKAREA\\shared\\templatedata\\";
	public static final String DCR_ROOTPATH = TEAMSITE_SHARED + "templatedata/";
	public static final String QUERY_LOG = "/tmp/livesite-query.log";
	public static final Integer MAX_RECURSION_LEVEL = 6;
	public static final String GEO_PARAMETER = "internalContentGeoCode";
	public static final String SEO_TOOL_URL = "https://myseo.web.csr.pstars/";
	public static final String TWITTER_FILE_VPATH = "assets/twitter/";
	public static final String DATA_DIR="C:\\xampp\\tomcat\\webapps\\data\\OpenFootie\\";
	public static final String PROB_MODEL_KEY = DATA_DIR+"match report.data";
	public static final String MATCH_REPORT_KEY = DATA_DIR+"match report.txt";
	public static final String PLAYER_STATS_KEY = DATA_DIR+"player stats.txt";
	public static final String STATS_SUMMARY_KEY = DATA_DIR+"stats summary.txt";
        

	
	
	
	public static enum actionType {
		ADD (1),
		EDIT (2),
		DELETE (3);
		
		private static final Map<Integer,actionType> lookup 
        = new HashMap<Integer,actionType>();

		static {
			for(actionType s : EnumSet.allOf(actionType.class))
				lookup.put(s.getId(), s);
		}
		
		private Integer id;
		
		private actionType(Integer id) {
			this.id = id;
		}
		public Integer getId() {
			return id;
		}
		
		public static String getName(Integer Id) { 
	          return lookup.get(Id).name();
	    }
	}
        
        public static String getReleaseScheduleFile()
	{
		return TEAMSITE_ROOTPATH + "META-INF/release-schedule.xml";
	}
	
	public static String getSiteMappingsFile(Boolean isRuntime) {
		String rootPath = getRootPath(isRuntime);
		String siteMappingsFile = rootPath + "META-INF/sites.xml";
		return siteMappingsFile;
	}

	public static String getPageMetadataFile(Boolean isRuntime, String siteName) {
		String rootPath = getRootPath(isRuntime);
		String pageMetadataFile = rootPath + "META-INF/" + siteName + ".xml";
		return pageMetadataFile;
	}

	private static String getRootPath(Boolean isRuntime) {
		if (isRuntime) {
			return LIVESITE_ROOTPATH;
		}
		else {
			return TEAMSITE_ROOTPATH;
		}
	}
	
	public static String getWindowsPath(String path) {
		if (System.getProperty("os.name").contains("Windows")) {
			if (path.contains("/iwadmin/")) {
				path = "Y:" + path.substring(path.indexOf("/iwadmin/"), path.length());
				path = path.replace("/", "\\");
			}
			else if (path.contains("\\iwadmin\\")) {
				path = "Y:" + path.substring(path.indexOf("\\iwadmin\\"), path.length());
				path = path.replace("/", "\\");
			}
			else if (path.contains("/default/")) {
				path = "Y:" + path.substring(path.indexOf("/default/"), path.length());
				path = path.replace("/", "\\");
			}
			else if (path.contains("\\default\\")) {
				path = "Y:" + path.substring(path.indexOf("\\default\\"), path.length());
				path = path.replace("/", "\\");
			}
		}
		return path;
	}
	
	public static OnlyDirsFilter getDirsFilter() {
		OnlyDirsFilter filter = new OnlyDirsFilter();
		return filter;
	}
	
	static class OnlyDirsFilter implements FilenameFilter{
		
		public boolean accept(File pathname, String name) {
						
			return new File(pathname, name).isDirectory();
		}
	}
}
