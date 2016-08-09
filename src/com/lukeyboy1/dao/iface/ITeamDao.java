package com.lukeyboy1.dao.iface;

import java.util.ArrayList;

import com.lukeyboy1.beans.TeamBean;


public interface ITeamDao {
	public abstract ArrayList<TeamBean> getTeams();

	public abstract TeamBean getTeam(String teamId);
}
