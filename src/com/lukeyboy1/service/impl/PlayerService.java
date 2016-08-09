package com.lukeyboy1.service.impl;

import java.util.ArrayList;

import com.lukeyboy1.bo.PlayerContainer;
import com.lukeyboy1.dao.FitbaDAOFactory;
import com.lukeyboy1.dao.iface.IPlayerDao;
import com.lukeyboy1.gameplay.Player;
import com.lukeyboy1.service.iface.IPlayerService;

public class PlayerService implements IPlayerService {
	private IPlayerDao dao;
	
	public PlayerService(Boolean testMode) {
		dao = FitbaDAOFactory.getFactory().getPlayerDao(testMode);
	}

	public PlayerService() {
		dao = FitbaDAOFactory.getFactory().getPlayerDao(false);
	}

	public ArrayList<Player> getPlayersForTeam(String teamId) {
		return dao.getPlayersForTeam(teamId);
	}

}
