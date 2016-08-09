package com.lukeyboy1.servlet;

//import java.io.PrintWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lukeyboy1.beans.TeamBean;
import com.lukeyboy1.bo.PlayerContainer;
import com.lukeyboy1.core.Constants;
import com.lukeyboy1.core.Match;
import com.lukeyboy1.core.MatchReport;
import com.lukeyboy1.core.MatchRewind;
import com.lukeyboy1.core.Team;
import com.lukeyboy1.gameplay.Player;
import com.lukeyboy1.gameplay.PlayerAttribute;
import com.lukeyboy1.gameplay.PlayerAttributes;
import com.lukeyboy1.interactivity.EndOfMatch;
import com.lukeyboy1.interactivity.Signal;
import com.lukeyboy1.report.ReportObject;
import com.lukeyboy1.service.ServiceLocator;
import com.lukeyboy1.service.iface.IPlayerService;
import com.lukeyboy1.service.iface.ITeamService;
import com.lukeyboy1.utility.Config;
import com.lukeyboy1.utility.Tactics;
import com.lukeyboy1.utility.WebUtils;
import com.lukeyboy1.utility.Tactics.TacticLine;






public class MatchServlet extends HttpServlet {
	
	private static Random rnd = new Random();
    
    private static String DATABASE_ADDRESS;
	/**
	 * Constructor of the object.
	 */
	public MatchServlet() {
		super();
	}
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * This will return all domains
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nextJSP = "view/index.jsp";
		ITeamService teamService = ServiceLocator.getTeamService();
		ArrayList<TeamBean> listTeams = teamService.getTeams();
        
		request.setAttribute("teams", listTeams);
        
		request.setAttribute("isSuccess", 0);
		request.setAttribute("isError", 0);
		request.setAttribute("successMessage", "");
		request.setAttribute("errorMessage", "");
		request.setAttribute("internal", 0);
		request.getRequestDispatcher(nextJSP).forward(request, response);
		
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorMessage = "";
		Boolean isInvalid = false;
		String homeid = WebUtils.getRequestParameter(request, "homeid", "0");
		String awayid = WebUtils.getRequestParameter(request, "awayid", "0");
		String nextJSP = "view/index.jsp";
		request.setAttribute("isError", 0);
		if(!(homeid.equals("0")) && !(awayid.equals("0"))) {
			nextJSP = "view/match.jsp";
			request = execute(homeid, awayid, request);
			request.removeAttribute("isError");
			request.removeAttribute("errorMessage)");
		}
		else {
			isInvalid = true;
			errorMessage = "Invalid Teams Chosen";
		}

		if(isInvalid) {
			nextJSP = "view/index.jsp";
			request.setAttribute("isSuccess", 0);
			request.setAttribute("isError", 1);
			request.setAttribute("errorMessage", errorMessage);
		}
		
		request.getRequestDispatcher(nextJSP).forward(request, response);
		
		
	}
	
	
	
	/**
     * The 'desktop' version of a match: it demonstrates the match preparation and outputs various information relevant to the match
     * @param args The database ids of the teams
     */
    public HttpServletRequest execute(String homeTeamId, String awayTeamId, HttpServletRequest request) {
        
        
    	IPlayerService playerService = ServiceLocator.getPlayerService();
    	ITeamService teamService = ServiceLocator.getTeamService();
    	
        
        
        
        try {
            
            TeamBean htb = teamService.getTeam(homeTeamId);
            String homeTeamName = htb.getTeamName();
            TeamBean atb = teamService.getTeam(awayTeamId);
            String awayTeamName = atb.getTeamName();
            
            request.setAttribute("homeTeamName", homeTeamName);
            request.setAttribute("awayTeamName", awayTeamName);
            
            Team homeTeam = new Team(homeTeamName);
            Team awayTeam = new Team(awayTeamName);
            
            ArrayList<Player> listHomePlayers = playerService.getPlayersForTeam(homeTeamId);
            ArrayList<Player> listAwayPlayers = playerService.getPlayersForTeam(awayTeamId);
            
            for (Player player: listHomePlayers) {
            	
            	int position = player.getPosition();
            	homeTeam.addPlayer(player, db2enum(position));
            }
            
            for (Player player: listAwayPlayers) {
            	
            	int position = player.getPosition();
            	awayTeam.addPlayer(player, db2enum(position));
            }
            
            homeTeam.defineTactics();
            awayTeam.defineTactics();
            
            homeTeam.alignPlayersDesktop();
            awayTeam.alignPlayersDesktop();
            
            
            String homeTeamString = homeTeam.lineupString();
            String awayTeamString = awayTeam.lineupString();
            request.setAttribute("homeTeamLineup", homeTeamString);
            request.setAttribute("awayTeamLineup", awayTeamString);
            
            Match match = new Match(homeTeam, awayTeam);
            
            Signal currentSignal = match.play(0); // Kick off
            int count = 0;
            
            while (!(currentSignal instanceof EndOfMatch) && currentSignal != null) { 
                currentSignal = match.play(currentSignal.getTime() + 1);
            }
            
            if (currentSignal instanceof EndOfMatch) 
                match.play(2 * MatchReport.halfDuration); // Final whistle
            
            // match.showProbModel();
            
            MatchReport report = match.getMatchReport();
            ArrayList<ReportObject> reportOutput = report.getReport();
            String result = report.getScoreLine(homeTeam, awayTeam);
            
            String reportAsString = match.returnMatchEvents();
            String statsAsString = match.returnMatchStats();
            request.setAttribute("matchReportAsString", reportAsString);
            request.setAttribute("matchStatsAsString", statsAsString);
            request.setAttribute("matchReport", reportOutput);
            request.setAttribute("matchScore", result);
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return request;
    }
    
    /**
     * Testing only: displays the averages of the pass attribute strength per team and position in random basis
     * @param homeTeam The home team object
     * @param awayTeam The away team object
     */
    private static void avgTest(Team homeTeam, Team awayTeam) {
        
        ArrayList<Byte> positions = new ArrayList<Byte>();
        ArrayList<Boolean> isHomeTeam = new ArrayList<Boolean>();
        
        int testSize = rnd.nextInt(8) + 1;
        
        for (int i = 0; i < testSize; i++) {
            positions.add((byte) (rnd.nextInt(4) + 1));
            isHomeTeam.add(rnd.nextBoolean());
        }
        
        for (int i = 0; i < testSize; i++) {
            System.out.println("Position: " + positions.get(i));
            System.out.println("Team: " + (isHomeTeam.get(i)?homeTeam.getName():awayTeam.getName()));
            System.out.println("Average passing: " + (isHomeTeam.get(i)?homeTeam.getAverageFromAction(positions.get(i), Constants.Pass):awayTeam.getAverageFromAction(positions.get(i), Constants.Pass)));
            System.out.println();
        }
        
    }
    
    /**
     * Testing only: displayes the averages of random attributes strength per team and position in random basis
     * @param homeTeam The home team object
     * @param awayTeam The away team object
     */
    private static void avgAttributeTest(Team homeTeam, Team awayTeam) {
        
        ArrayList<String> allAttributes = new ArrayList<String>();
        
        int testSize = rnd.nextInt(10) + 1;
        
        for (PlayerAttribute attr:PlayerAttributes.getAll()) {
            allAttributes.add(attr.getName());
        }
        
        for (int i = 0; i < testSize; i++) {
            int currentPosition = rnd.nextInt(4) + 1;
            Team currentTeam = rnd.nextBoolean()?homeTeam:awayTeam;
            String attribute = allAttributes.get(rnd.nextInt(allAttributes.size()));
            
            System.out.println("Position: " + currentPosition);
            System.out.println("Team: " + currentTeam.getName());
            System.out.println("Attribute: " + attribute);
            System.out.println("Average value: " + currentTeam.getAverageFromAttribute(currentPosition, attribute));
            
            System.out.println();
            
        }
        
    }
    
    /**
     * Testing only: Display random attributes strengths of random players
     * @param homeTeam The home team object
     * @param awayTeam The away team object
     */
    private static void initTest(Team homeTeam, Team awayTeam) {
        
        ArrayList<String> allAttributes = new ArrayList<String>();
        
        for (PlayerAttribute attr:PlayerAttributes.getAll()) {
            allAttributes.add(attr.getName());
        }
        
        int numPlayers = rnd.nextInt(11) + 1;
        
        for (int i = 0; i < numPlayers; i++) {
            
            int testedAttribute = rnd.nextInt(allAttributes.size());
            int testedPlayer = rnd.nextInt(11);
            
            String attribute = allAttributes.get(testedAttribute);
            
            Player homePlayer = homeTeam.getAnyPlayer(testedPlayer);
            Player awayPlayer = awayTeam.getAnyPlayer(testedPlayer);
            
            System.out.println(homePlayer.getFamilyName() + " has attribute " + attribute + " of value " + homePlayer.getSkill(attribute));
            System.out.println(awayPlayer.getFamilyName() + " has attribute " + attribute + " of value " + awayPlayer.getSkill(attribute));
            
            System.out.println();
        }
        
    }
    
    /**
     * Utility: Transform player position as read from database to the corresponding enum value
     * @param pos
     * @return
     */
    private static Tactics.TacticLine db2enum(int pos) {
        
        switch (pos) {
        case 1:
            return Tactics.TacticLine.GK;
        case 2:
            return Tactics.TacticLine.DEFENDER;
        case 3:
            return Tactics.TacticLine.MIDFIELDER;
        case 4:
            return Tactics.TacticLine.FORWARD;
        }
        
        return null;
        
    }
	
}
