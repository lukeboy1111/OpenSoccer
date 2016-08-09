package com.lukeyboy1.service;

import com.lukeyboy1.service.iface.IPlayerService;
import com.lukeyboy1.service.iface.ITeamService;
import com.lukeyboy1.service.impl.PlayerService;
import com.lukeyboy1.service.impl.TeamService;


public class ServiceLocator {
	public ServiceLocator() {

	}
	
	public static ITeamService getTeamService() {
		return new TeamService();
	}
	
	public static ITeamService getTeamService(Boolean testMode) {
		return new TeamService(testMode);
	}
	
	public static IPlayerService getPlayerService() {
		return new PlayerService();
	}
	
	public static IPlayerService getPlayerService(Boolean testMode) {
		return new PlayerService(testMode);
	}
}
