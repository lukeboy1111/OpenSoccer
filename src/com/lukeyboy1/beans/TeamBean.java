package com.lukeyboy1.beans;

public class TeamBean {
	private Integer teamId;
	private String teamName;
	
	public TeamBean(Integer homeTeamId, String homeTeamName) {
		teamId = homeTeamId;
		teamName = homeTeamName;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
}
