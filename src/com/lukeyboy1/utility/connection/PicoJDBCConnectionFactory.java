package com.lukeyboy1.utility.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.lukeyboy1.utility.exceptions.ConnectionProblemException;
import com.lukeyboy1.utility.support.FitbaConstants;


import java.io.InputStream;
import java.net.URL;

public class PicoJDBCConnectionFactory {

	public static Connection fitbaConnection() {
        return anyConnection("jdbc/Fitba");
    }
	
    private static Connection anyConnection(String contextName) {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            String fullContext = "java:comp/env/" + contextName;
            DataSource ds = (DataSource) ctx.lookup(fullContext);
            if (ds != null) {
                conn = ds.getConnection();
                System.out.println("Getting connection from Data Source.");
                if (conn != null) {
                    return conn;
                }
            } else {
                conn = getAlternateConnection(contextName);
            }
        } catch (Exception e) {
            try {
                conn = getAlternateConnection(contextName);
            } catch (Exception e2) {
                System.err.println("anyConnection::1. Couldn't connect to database: " + e2);
            }
            return conn;
        }
        return null;
    }

    private static InputStream getConnectionPropertiesFile() {
        InputStream fis = null;
        try {
            File file = new File(FitbaConstants.WINDOWS_TOMCAT6_PROPERTIES_FILE);
            if (file.exists()) {
                System.err.println("LOAD1: PicoConstants.WINDOWS_TOMCAT6_PROPERTIES_FILE");
            	fis = getConnectionPropertiesFile(FitbaConstants.WINDOWS_TOMCAT6_PROPERTIES_FILE);
            } else {
                file = new File(FitbaConstants.RUNTIME_TOMCAT_PROPERTIES_FILE);
                if (file.exists()) {
                	System.err.println("LOAD2: PicoConstants.RUNTIME_TOMCAT_PROPERTIES_FILE :" + FitbaConstants.RUNTIME_TOMCAT_PROPERTIES_FILE);
                    fis = getConnectionPropertiesFile(FitbaConstants.RUNTIME_TOMCAT_PROPERTIES_FILE);
                } else {
                	System.err.println("LOAD3: PicoConstants.TOMCAT6_PROPERTIES_FILE :" + FitbaConstants.TOMCAT6_PROPERTIES_FILE);
                    
                    fis = getConnectionPropertiesFile(FitbaConstants.TOMCAT6_PROPERTIES_FILE);
                }
            }
            if ((fis != null) && (fis.available() > 0)) {
                return fis;
            } else {
                fis = PicoJDBCConnectionFactory.class.getClassLoader().getResourceAsStream(FitbaConstants.DS_PROPERTIES_FILE);
            }
        } catch (Exception e) {
            System.err.println("getConnectionPropertiesFile():: 2.Properties file not found: " + e);
        }
        return fis;
    }

    private static InputStream getConnectionPropertiesFile(String fileName) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileName);

        } catch (FileNotFoundException e1) {
            System.err.println("* getConnectionPropertiesFile: cannot open: " + fileName);
        }
        return fis;
    }

    private static Connection getAlternateConnection(String connType) throws ConnectionProblemException {
        Connection conn;
        String contextName = connType.replaceAll("/", ".");
        Properties prop = new Properties();
        InputStream fis;
        try {
            fis = getConnectionPropertiesFile();
            prop.load(fis);
            fis.close();
        } catch (FileNotFoundException e1) {
            System.err.println("getAlternateConnection::Properties file not found: " + e1);
        } catch (IOException errorProperties) {
            System.err.println("getAlternateConnection::Couldn't load properties: " + errorProperties);
        }
        String usernameProp = contextName + ".username";
        String passwordProp = contextName + ".password";
        String driverProp = contextName + ".driver";
        String urlProp = contextName + ".url";

        String username = prop.getProperty(usernameProp);
        String password = prop.getProperty(passwordProp);
        String driver = prop.getProperty(driverProp);
        String url = prop.getProperty(urlProp);
        try {
            Class.forName(driver);
            String formattedUrl;
            if (driver.equals(FitbaConstants.H2_DRIVER_NAME)) {
                URL createScript = PicoJDBCConnectionFactory.class.getClassLoader().getResource(FitbaConstants.CREATE_SCRIPT_NAME);
                String createScriptPath = createScript.getPath();
                if (createScriptPath.startsWith("/")) {
                    createScriptPath = createScriptPath.substring(1);
                }
                formattedUrl = String.format(url, createScriptPath);
            } else {
                formattedUrl = url;
            }
            conn = DriverManager.getConnection(formattedUrl, username, password);
            return conn;
        } catch (Exception e2) {
            System.err.println("getAlternateConnection::Couldn't connect to database: " + e2);
        }
        return null;
    }

 

}
