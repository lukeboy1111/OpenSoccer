package com.lukeyboy1.dao;

import com.lukeyboy1.dao.iface.IPlayerDao;
import com.lukeyboy1.dao.iface.ITeamDao;
import com.lukeyboy1.dao.impl.PlayerDao;
import com.lukeyboy1.dao.impl.TeamDao;



public class FitbaLSDAOFactory extends FitbaDAOFactory
{
	public FitbaLSDAOFactory()
	{

	}

	public ITeamDao getTeamDao() {
		return getTeamDao(false);
	}
	
	public ITeamDao getTeamDao(Boolean testMode) {
		return new TeamDao(testMode);
	}
	
	public IPlayerDao getPlayerDao() {
		return getPlayerDao(false);
	}
	
	public IPlayerDao getPlayerDao(Boolean testMode) {
		return new PlayerDao(testMode);
	}
	

}
