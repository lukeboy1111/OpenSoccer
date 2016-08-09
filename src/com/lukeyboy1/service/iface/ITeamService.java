package com.lukeyboy1.service.iface;

import java.util.ArrayList;

import com.lukeyboy1.beans.TeamBean;


public interface ITeamService {
	public abstract ArrayList<TeamBean> getTeams();
	public abstract TeamBean getTeam(String teamId);
}
