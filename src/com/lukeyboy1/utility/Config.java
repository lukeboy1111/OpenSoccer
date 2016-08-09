/*
 * Copyright 2010 Andreas Tasoulas
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 */

package com.lukeyboy1.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import com.lukeyboy1.core.Constants;
import com.lukeyboy1.test.context.InitialContextFactoryForTest;
import com.lukeyboy1.utility.exceptions.ConnectionProblemException;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Config {
	private static DataSource dataSource;
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	public void setupMocks() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
	}
    
    public static Connection dbConnection(Boolean testMode) {
		return anyConnection("jdbc/Connection", testMode);
	}
    
    private static Connection anyConnection(String contextName, Boolean testMode) {
	    
		Connection conn = null;
		Context initContext = null;
		try {
			DataSource ds = null;
			if(testMode) {
				setUp();
				ds = InitialContextFactoryForTest.getDataSource();
			}
			else {
				initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:comp/env");
				ds = (DataSource) envContext.lookup(contextName);
			}
			
            conn = ds.getConnection();  
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try{
			System.err.println("enter getContext name="+contextName);
		 	Context ctx = new InitialContext();
		  	String fullContext =  "java:comp/env/"+contextName;
		  	System.err.println("looking="+fullContext);
		  	DataSource ds = (DataSource) ctx.lookup(fullContext);
		  	System.err.println("after lookup");
		  	if (ds != null) {
		  		System.err.println("call getConnection");
		  		conn = ds.getConnection();
		  		System.err.println("after getConnection");
		  		if(conn != null)  {
		  			System.err.println("Connection Successful");
		  			return conn;
		  		}
		  	}
		  	else {
		  		System.err.println("* alternate connection");
		 		conn = getAlternateConnection(contextName); 
		   	}
		} catch(Exception e) {
			System.err.println("* Exception is:"+e.getMessage());
			try {
		  		conn = getAlternateConnection(contextName); 
		  	}
		  	catch(Exception e2) {
		  		System.err.println("1.Couldn't connect to database: " + e2);
		  	}
		  	return conn;
		}
		*/
		return conn;
	}
    
    private static Connection getAlternateConnection(String connType) throws ConnectionProblemException {
		Connection conn;
		String contextName = connType.replace("/", ".");
		System.err.println("Looking for "+contextName);
		Properties prop = new Properties();
    	FileInputStream fis;
    	
		try {
			fis = getConnectionPropertiesFile();
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e1) {
			System.err.println("Properties file not found: " + e1);
		} catch (IOException errorProperties) {
			System.err.println("Couldn't load properties: " + errorProperties);
		}
		
    	String usernameProp = contextName+".username";
    	String passwordProp = contextName+".password";
    	String driverProp = contextName+".driver";
    	String urlProp = contextName+".url";
    	
    	
    	String username = prop.getProperty(usernameProp);
	    String password = prop.getProperty(passwordProp);
	    String driver = prop.getProperty(driverProp);
	    String url = prop.getProperty(urlProp);
	    
	    
	    try{
	    	Class.forName(driver);
	    	conn = DriverManager.getConnection(url, username, password); 
	    	return conn;
	    }
	    catch (Exception e2) {
	    	System.err.println("Couldn't connect to database: " + e2);
		}
		return null;
	}
    
    private static FileInputStream getConnectionPropertiesFile() {
		FileInputStream fis = null;
		try {
			System.err.println("Open: "+Constants.TOMCAT6_PROPERTIES_FILE);
			fis = new FileInputStream(Constants.TOMCAT6_PROPERTIES_FILE);
			
		} catch (FileNotFoundException e1) {
			System.err.println("Properties file not found: " + e1);
		} finally {
			try {
				System.err.println("Open Local: "+Constants.LOCAL_TOMCAT6_PROPERTIES_FILE);
				fis = new FileInputStream(Constants.LOCAL_TOMCAT6_PROPERTIES_FILE);
			}
			catch(FileNotFoundException e2) {
				System.err.println("2.Properties file not found: " + e2);
			}
		}
		return fis;
	}
    
    protected static void setUp() throws Exception {
        // sets up the InitialContextFactoryForTest as default factory.
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                InitialContextFactoryForTest.class.getName());
        // binds the object
        dataSource = InitialContextFactoryForTest.getDataSource();
        InitialContextFactoryForTest.bind("jdbc/Connection", dataSource);
    }
	
	
    
    private static String getConfigValue(String line, String varName) {
        
        if (line.indexOf(varName) != -1) {
            
            int rValStart = line.indexOf("=");
            
            if (rValStart > line.indexOf(varName)) {
                return line.substring(rValStart + 1).trim();
            }
        }
        
        return null;
        
    }
    
    public static String readConfig(String key) {
        
        String value = null;

        try {
          
            BufferedReader in = new BufferedReader(new FileReader("./META-INF/config.txt"));  
            
            String strLine;
            while ((strLine = in.readLine()) != null) {
                value = getConfigValue(strLine, key);
                if (value != null) break;
            }

            // dispose all the resources after using them.
            in.close();
            
            return value;

        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        
        return null;
        
    }
    
    
	
	
}
