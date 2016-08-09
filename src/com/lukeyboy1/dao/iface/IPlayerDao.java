package com.lukeyboy1.dao.iface;

import java.util.ArrayList;

import com.lukeyboy1.bo.PlayerContainer;
import com.lukeyboy1.gameplay.Player;

public interface IPlayerDao {

	public abstract ArrayList<Player> getPlayersForTeam(String teamId);

}
