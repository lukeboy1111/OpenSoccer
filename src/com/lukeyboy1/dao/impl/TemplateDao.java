package com.lukeyboy1.dao.impl;

import java.sql.Connection;

import com.lukeyboy1.utility.Config;

public class TemplateDao {
	protected Boolean testMode = false;
	private Connection conn = null;
    
    public TemplateDao(Boolean testing) {
    	testMode = testing;
    }
    
    protected Connection getConnection() {
    	if(null == conn) {
    		conn = Config.dbConnection(testMode);
    	}
        return conn;
    }

	public Boolean getTestMode() {
		return testMode;
	}

	public void setTestMode(Boolean testMode) {
		this.testMode = testMode;
	}
    
}
