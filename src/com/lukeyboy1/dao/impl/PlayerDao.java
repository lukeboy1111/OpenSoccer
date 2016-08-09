package com.lukeyboy1.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.lukeyboy1.beans.TeamBean;
import com.lukeyboy1.bo.PlayerContainer;
import com.lukeyboy1.dao.iface.IPlayerDao;
import com.lukeyboy1.gameplay.Player;
import com.lukeyboy1.gameplay.PlayerAttribute;
import com.lukeyboy1.gameplay.PlayerAttributes;

public class PlayerDao extends TemplateDao implements IPlayerDao {
	
	public PlayerDao(Boolean testMode) {
		super(testMode);
	}

	public ArrayList<Player> getPlayersForTeam(String teamId) {
		ArrayList<Player> list = new ArrayList<Player>();
		String HOME_SQUAD_QUERY = "select * from player, playerskills where player.id = playerskills.player and Team=" + 
        	teamId + " order by Position, player.id"; 
		Connection conn = getConnection();
        Statement homeTeamStmt = null;
        
        
        try {
            
            
            ResultSet homeSquadRS = null;
            
            homeTeamStmt = conn.createStatement();
            
            
            if (homeTeamStmt.execute(HOME_SQUAD_QUERY)) {
                homeSquadRS = homeTeamStmt.getResultSet();
            }
            
            
            // Extract match data from database
            
            while (homeSquadRS.next()) {
                int shirtNo = homeSquadRS.getInt("ShirtNo");
                String firstName = homeSquadRS.getString("FirstName");
                String lastName = homeSquadRS.getString("LastName");
                int position = homeSquadRS.getInt("Position");
                Player player = new Player(shirtNo, firstName, lastName, position);
                for (PlayerAttribute attr:PlayerAttributes.getAll()) {
                    player.addSkill(attr.getName(), homeSquadRS.getDouble(attr.getName()));
                }
                list.add(player);
            }
            
        }
        catch(Exception e) {
        	
        }
		return list;
	}

}
