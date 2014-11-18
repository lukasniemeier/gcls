package de.lukasniemeier.gamecenterlivesender.data;

import java.util.Collection;
import java.util.List;

import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.model.teams.Team;

public interface DataManager {

    List<Game> getGames();

    Collection<Team> getTeams();

    Team getTeam(String teamCode);
}
