package com.lukeyboy1.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.lukeyboy1.beans.TeamBean;
import com.lukeyboy1.dao.iface.ITeamDao;
import com.lukeyboy1.utility.Config;



public class TeamDao extends TemplateDao implements ITeamDao  {
	private static Random rnd = new Random();
    private static String DATABASE_ADDRESS;
    
    public TeamDao(Boolean testing) {
    	super(testing);
    	this.setTestMode(testing);
    }
    
	public ArrayList<TeamBean> getTeams() {
		ArrayList<TeamBean> listTeams = new ArrayList<TeamBean>();
		
        String HOME_TEAM_QUERY = "select Id, Name from Team";
        
        Connection conn = getConnection();
        Statement homeTeamStmt = null;
        ResultSet homeTeamRS = null;
        
        try {
            
            // boiler-plate JDBC housekeeping
            // InitialContext ctx = new InitialContext();
            // Context envCtx = (Context) ctx.lookup("java:comp/env");
            // DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQLDB");
            
            
            
            homeTeamStmt = conn.createStatement();
            
            
            if (homeTeamStmt.execute(HOME_TEAM_QUERY)) {
                homeTeamRS = homeTeamStmt.getResultSet();
            }
            
            
            // Extract match data from database
            
            while(homeTeamRS.next()) {
            	String homeTeamName = homeTeamRS.getString("Name");
            	Integer homeTeamId = homeTeamRS.getInt("Id");
            	TeamBean tb = new TeamBean(homeTeamId, homeTeamName);
            	listTeams.add(tb);
            }
            
        }
        catch(Exception e) {
        	
        } finally {
        	if (homeTeamStmt != null) {
                try {
                	homeTeamStmt.close();
                } catch (SQLException sqlex) {
                    
                }
                
                homeTeamStmt = null;
            }
        }
		return listTeams;
	}

	public TeamBean getTeam(String teamId) {
		TeamBean tb = new TeamBean(0,"");
		
        String HOME_TEAM_QUERY = "select Id, Name from Team where Id="+teamId;
        
        Connection conn = getConnection();
        Statement homeTeamStmt = null;
        
        try {
            
            // boiler-plate JDBC housekeeping
            // InitialContext ctx = new InitialContext();
            // Context envCtx = (Context) ctx.lookup("java:comp/env");
            // DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQLDB");
            
            ResultSet homeTeamRS = null;
            
            homeTeamStmt = conn.createStatement();
            
            
            if (homeTeamStmt.execute(HOME_TEAM_QUERY)) {
                homeTeamRS = homeTeamStmt.getResultSet();
            }
            
            
            // Extract match data from database
            while(homeTeamRS.next()) {
            	String homeTeamName = homeTeamRS.getString("Name");
            	Integer homeTeamId = homeTeamRS.getInt("Id");
            	tb = new TeamBean(homeTeamId, homeTeamName);
            	
            }
            
        }
        catch(Exception e) {
        	
        } finally {
        	if (homeTeamStmt != null) {
                try {
                	homeTeamStmt.close();
                } catch (SQLException sqlex) {
                    
                }
                
                homeTeamStmt = null;
            }
        }
		return tb;
	}

}
