package com.lukeyboy1.service.impl;

import java.util.ArrayList;

import com.lukeyboy1.beans.TeamBean;
import com.lukeyboy1.dao.FitbaDAOFactory;
import com.lukeyboy1.dao.iface.ITeamDao;
import com.lukeyboy1.service.iface.ITeamService;



public class TeamService implements ITeamService {
	ITeamDao dao;
	
	public TeamService() {
		dao = FitbaDAOFactory.getFactory().getTeamDao(false);
	}
	
	public TeamService(Boolean testMode) {
		dao = FitbaDAOFactory.getFactory().getTeamDao(testMode);
	}

	public ArrayList<TeamBean> getTeams() {
		return dao.getTeams();
	}

	public TeamBean getTeam(String teamId) {
		return dao.getTeam(teamId);
	}

}
