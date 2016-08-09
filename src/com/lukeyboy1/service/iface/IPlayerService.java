package com.lukeyboy1.service.iface;

import java.util.ArrayList;

import com.lukeyboy1.bo.PlayerContainer;
import com.lukeyboy1.gameplay.Player;

public interface IPlayerService {

	public abstract ArrayList<Player> getPlayersForTeam(String teamId);

}
